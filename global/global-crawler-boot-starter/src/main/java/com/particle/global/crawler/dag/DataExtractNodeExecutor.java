package com.particle.global.crawler.dag;

import com.particle.global.dag.runtime.executor.NodeExecutor;
import com.particle.global.dag.model.DagNode;
import com.particle.global.dag.runtime.ExecutionContext;
import com.particle.global.dag.runtime.NodeExecutionResult;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;
import java.util.regex.Pattern;

/**
 * 数据提取节点执行器
 */
public class DataExtractNodeExecutor implements NodeExecutor {

    @Override
    public boolean supports(DagNode node) {
        return CrawlerDagConstants.NodeType.DATA_EXTRACT.equals(node.getType());
    }

    @Override
    public NodeExecutionResult execute(DagNode node, ExecutionContext context) throws Exception {
        try {
            Map<String, Object> config = node.getConfig();

            String sourceNodeId = (String) config.get("sourceNodeId"); // 源节点ID，用于获取页面内容
            String htmlContent = null;

            if (sourceNodeId != null) {
                // 从执行上下文中获取源节点的结果
                Object sourceResult = context.getVariable(sourceNodeId + "_result");
                if (sourceResult != null) {
                    htmlContent = sourceResult.toString();
                }
            } else {
                // 如果没有指定源节点，则尝试从输入数据中获取HTML内容
                htmlContent = (String) config.get("htmlContent");
            }

            if (htmlContent == null || htmlContent.isEmpty()) {
                return NodeExecutionResult.failure(new IllegalArgumentException("未找到要提取的HTML内容"));
            }

            Document document = Jsoup.parse(htmlContent);

            // 解析提取规则
            List<Map<String, Object>> extractRules = (List<Map<String, Object>>) config.get("extractRules");

            if (extractRules == null || extractRules.isEmpty()) {
                return NodeExecutionResult.failure(new IllegalArgumentException("提取规则不能为空"));
            }

            Map<String, Object> extractedData = new HashMap<>();

            for (Map<String, Object> rule : extractRules) {
                String fieldName = (String) rule.get("fieldName");
                String selector = (String) rule.get("selector");
                String attribute = (String) rule.get("attribute"); // 如 "href", "src", "text" 等
                Boolean isList = (Boolean) rule.getOrDefault("isList", false);
                String regexPattern = (String) rule.get("regexPattern"); // 可选的正则表达式

                if (isList) {
                    Elements elements = document.select(selector);
                    List<String> values = new ArrayList<>();

                    for (Element element : elements) {
                        String value = extractValue(element, attribute);

                        if (regexPattern != null && !regexPattern.isEmpty()) {
                            value = applyRegex(value, regexPattern);
                        }

                        if (value != null) {
                            values.add(value);
                        }
                    }

                    extractedData.put(fieldName, values);
                } else {
                    Element element = document.selectFirst(selector);
                    String value = null;

                    if (element != null) {
                        value = extractValue(element, attribute);

                        if (regexPattern != null && !regexPattern.isEmpty()) {
                            value = applyRegex(value, regexPattern);
                        }
                    }

                    extractedData.put(fieldName, value);
                }
            }

            // 将提取的数据存储到执行上下文
            context.setVariable(node.getId() + "_extracted_data", extractedData);

            return NodeExecutionResult.success(extractedData);
        } catch (Exception e) {
            return NodeExecutionResult.failure(e);
        }
    }

    /**
     * 从元素中提取值
     */
    private String extractValue(Element element, String attribute) {
        if (attribute == null || "text".equalsIgnoreCase(attribute)) {
            return element.text();
        } else if ("html".equalsIgnoreCase(attribute)) {
            return element.html();
        } else if ("ownText".equalsIgnoreCase(attribute)) {
            return element.ownText();
        } else {
            return element.attr(attribute); // 获取属性值，如 href, src 等
        }
    }

    /**
     * 应用正则表达式提取
     */
    private String applyRegex(String value, String regexPattern) {
        if (value == null || regexPattern == null) {
            return value;
        }

        try {
            Pattern pattern = Pattern.compile(regexPattern);
            java.util.regex.Matcher matcher = pattern.matcher(value);

            if (matcher.find()) {
                // 如果有捕获组，返回第一个捕获组的内容，否则返回整个匹配内容
                if (matcher.groupCount() > 0) {
                    return matcher.group(1);
                } else {
                    return matcher.group();
                }
            }
        } catch (Exception e) {
            // 正则表达式错误时返回原值
            return value;
        }

        return value;
    }
}