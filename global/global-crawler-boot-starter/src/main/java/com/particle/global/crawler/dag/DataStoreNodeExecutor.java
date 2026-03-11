package com.particle.global.crawler.dag;

import com.particle.global.dag.runtime.executor.NodeExecutor;
import com.particle.global.dag.model.DagNode;
import com.particle.global.dag.runtime.ExecutionContext;
import com.particle.global.dag.runtime.NodeExecutionResult;
import com.particle.global.tool.json.JsonTool;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * 数据存储节点执行器
 */
public class DataStoreNodeExecutor implements NodeExecutor {

    @Override
    public boolean supports(DagNode node) {
        return CrawlerDagConstants.NodeType.DATA_STORE.equals(node.getType());
    }

    @Override
    public NodeExecutionResult execute(DagNode node, ExecutionContext context) throws Exception {
        Map<String, Object> config = node.getConfig();

        String sourceNodeId = (String) config.get("sourceNodeId");
        Object sourceData = null;

        if (sourceNodeId != null) {
            // 从执行上下文中获取源节点的结果
            sourceData = context.getVariable(sourceNodeId + "_processed_data");
            if (sourceData == null) {
                sourceData = context.getVariable(sourceNodeId + "_extracted_data");
            }
            if (sourceData == null) {
                sourceData = context.getVariable(sourceNodeId + "_result");
            }
        } else {
            // 如果没有指定源节点，尝试从配置中获取数据
            sourceData = config.get("inputData");
        }

        if (sourceData == null) {
            return NodeExecutionResult.failure(new IllegalArgumentException("未找到要存储的数据"));
        }

        String storageType = (String) config.get("storageType");
        String filePath = (String) config.get("filePath");

        if (storageType == null) {
            storageType = "json"; // 默认存储类型为JSON
        }

        if (filePath == null || filePath.isEmpty()) {
            return NodeExecutionResult.failure(new IllegalArgumentException("存储路径不能为空"));
        }

        try {
            // 确保目录存在
            Path path = Paths.get(filePath);
            Files.createDirectories(path.getParent());

            switch (storageType.toLowerCase()) {
                case "json":
                    saveAsJson(sourceData, filePath);
                    break;
                case "csv":
                    saveAsCsv(sourceData, filePath);
                    break;
                case "xml":
                    saveAsXml(sourceData, filePath);
                    break;
                case "txt":
                    saveAsTxt(sourceData, filePath);
                    break;
                default:
                    return NodeExecutionResult.failure(new IllegalArgumentException("不支持的存储类型: " + storageType));
            }

            // 存储完成信息到执行上下文
            Map<String, Object> storageInfo = new HashMap<>();
            storageInfo.put("filePath", filePath);
            storageInfo.put("storageType", storageType);
            storageInfo.put("timestamp", System.currentTimeMillis());
            storageInfo.put("size", getDataSize(sourceData));

            context.setVariable(node.getId() + "_storage_result", storageInfo);

            return NodeExecutionResult.success(storageInfo);

        } catch (IOException e) {
            return NodeExecutionResult.failure(e);
        }
    }

    /**
     * 保存为JSON格式
     */
    private void saveAsJson(Object data, String filePath) throws IOException {
        String jsonContent = JsonTool.toJsonStr(data);
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(jsonContent);
        }
    }

    /**
     * 保存为CSV格式
     */
    @SuppressWarnings("unchecked")
    private void saveAsCsv(Object data, String filePath) throws IOException {
        StringBuilder csvContent = new StringBuilder();

        if (data instanceof Map) {
            Map<String, Object> mapData = (Map<String, Object>) data;
            // 添加头部
            List<String> headers = new ArrayList<>(mapData.keySet());
            csvContent.append(String.join(",", headers)).append("\n");

            // 添加数据行 - 如果值是列表，则每行一条记录
            boolean isSingleRow = true;
            for (Object value : mapData.values()) {
                if (value instanceof List) {
                    isSingleRow = false;
                    break;
                }
            }

            if (isSingleRow) {
                // 单行数据
                List<String> row = new ArrayList<>();
                for (Object value : mapData.values()) {
                    row.add(value != null ? escapeCsvField(value.toString()) : "");
                }
                csvContent.append(String.join(",", row)).append("\n");
            } else {
                // 多行数据 - 按照列表长度处理
                int maxRows = 0;
                for (Object value : mapData.values()) {
                    if (value instanceof List) {
                        maxRows = Math.max(maxRows, ((List<?>) value).size());
                    }
                }

                for (int i = 0; i < maxRows; i++) {
                    List<String> row = new ArrayList<>();
                    for (String header : headers) {
                        Object value = mapData.get(header);
                        String cellValue = "";
                        if (value instanceof List) {
                            List<?> listValue = (List<?>) value;
                            if (i < listValue.size()) {
                                cellValue = escapeCsvField(listValue.get(i).toString());
                            }
                        } else {
                            cellValue = escapeCsvField(value != null ? value.toString() : "");
                        }
                        row.add(cellValue);
                    }
                    csvContent.append(String.join(",", row)).append("\n");
                }
            }
        } else if (data instanceof List) {
            // 处理列表数据
            List<?> listData = (List<?>) data;
            if (!listData.isEmpty() && listData.get(0) instanceof Map) {
                // 列表中的每个元素都是Map
                Map<String, Object> firstRow = (Map<String, Object>) listData.get(0);
                List<String> headers = new ArrayList<>(firstRow.keySet());
                csvContent.append(String.join(",", headers)).append("\n");

                for (Object rowData : listData) {
                    if (rowData instanceof Map) {
                        Map<String, Object> rowMap = (Map<String, Object>) rowData;
                        List<String> row = new ArrayList<>();
                        for (String header : headers) {
                            Object cellValue = rowMap.get(header);
                            row.add(cellValue != null ? escapeCsvField(cellValue.toString()) : "");
                        }
                        csvContent.append(String.join(",", row)).append("\n");
                    }
                }
            } else {
                // 简单列表 - 每行一个元素
                for (Object item : listData) {
                    csvContent.append(escapeCsvField(item != null ? item.toString() : "")).append("\n");
                }
            }
        } else {
            // 单个值
            csvContent.append(escapeCsvField(data != null ? data.toString() : "")).append("\n");
        }

        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(csvContent.toString());
        }
    }

    /**
     * 保存为XML格式
     */
    @SuppressWarnings("unchecked")
    private void saveAsXml(Object data, String filePath) throws IOException {
        StringBuilder xmlContent = new StringBuilder();
        xmlContent.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");

        if (data instanceof Map) {
            Map<String, Object> mapData = (Map<String, Object>) data;
            xmlContent.append("<root>\n");
            for (Map.Entry<String, Object> entry : mapData.entrySet()) {
                appendXmlElement(xmlContent, entry.getKey(), entry.getValue(), 1);
            }
            xmlContent.append("</root>\n");
        } else if (data instanceof List) {
            List<?> listData = (List<?>) data;
            xmlContent.append("<root>\n");
            for (int i = 0; i < listData.size(); i++) {
                appendXmlElement(xmlContent, "item_" + i, listData.get(i), 1);
            }
            xmlContent.append("</root>\n");
        } else {
            xmlContent.append("<root>").append(escapeXml(data != null ? data.toString() : "")).append("</root>\n");
        }

        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(xmlContent.toString());
        }
    }

    /**
     * 保存为TXT格式
     */
    private void saveAsTxt(Object data, String filePath) throws IOException {
        String txtContent = formatForText(data);
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(txtContent);
        }
    }

    /**
     * 递归追加XML元素
     */
    @SuppressWarnings("unchecked")
    private void appendXmlElement(StringBuilder sb, String tagName, Object value, int depth) {
        String indent = "  ".repeat(depth);
        sb.append(indent).append("<").append(tagName).append(">");

        if (value instanceof Map) {
            sb.append("\n");
            Map<String, Object> mapValue = (Map<String, Object>) value;
            for (Map.Entry<String, Object> entry : mapValue.entrySet()) {
                appendXmlElement(sb, entry.getKey(), entry.getValue(), depth + 1);
            }
            sb.append(indent).append("</").append(tagName).append(">\n");
        } else if (value instanceof List) {
            sb.append("\n");
            List<?> listValue = (List<?>) value;
            for (int i = 0; i < listValue.size(); i++) {
                appendXmlElement(sb, "item", listValue.get(i), depth + 1);
            }
            sb.append(indent).append("</").append(tagName).append(">\n");
        } else {
            sb.append(escapeXml(value != null ? value.toString() : "")).append("</").append(tagName).append(">\n");
        }
    }

    /**
     * 转义CSV字段
     */
    private String escapeCsvField(String field) {
        if (field.contains(",") || field.contains("\"") || field.contains("\n")) {
            return "\"" + field.replace("\"", "\"\"") + "\"";
        }
        return field;
    }

    /**
     * 转义XML内容
     */
    private String escapeXml(String xml) {
        return xml.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&apos;");
    }

    /**
     * 格式化文本输出
     */
    @SuppressWarnings("unchecked")
    private String formatForText(Object data) {
        StringBuilder sb = new StringBuilder();

        if (data instanceof Map) {
            Map<String, Object> mapData = (Map<String, Object>) data;
            for (Map.Entry<String, Object> entry : mapData.entrySet()) {
                sb.append(entry.getKey()).append(": ");
                if (entry.getValue() instanceof Map || entry.getValue() instanceof List) {
                    sb.append("\n  ").append(formatForText(entry.getValue()).replaceAll("\n", "\n  "));
                } else {
                    sb.append(entry.getValue()).append("\n");
                }
            }
        } else if (data instanceof List) {
            List<?> listData = (List<?>) data;
            for (int i = 0; i < listData.size(); i++) {
                sb.append("Item ").append(i).append(": ");
                if (listData.get(i) instanceof Map || listData.get(i) instanceof List) {
                    sb.append("\n  ").append(formatForText(listData.get(i)).replaceAll("\n", "\n  "));
                } else {
                    sb.append(listData.get(i)).append("\n");
                }
            }
        } else {
            sb.append(data != null ? data.toString() : "null");
        }

        return sb.toString();
    }

    /**
     * 获取数据大小估算
     */
    private int getDataSize(Object data) {
        if (data == null) {
            return 0;
        }
        return data.toString().getBytes().length;
    }
}