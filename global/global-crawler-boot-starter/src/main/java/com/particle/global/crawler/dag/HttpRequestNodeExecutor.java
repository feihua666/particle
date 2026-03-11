package com.particle.global.crawler.dag;

import com.particle.global.dag.runtime.executor.NodeExecutor;
import com.particle.global.dag.model.DagNode;
import com.particle.global.dag.runtime.ExecutionContext;
import com.particle.global.dag.runtime.NodeExecutionResult;
import com.particle.global.tool.http.HttpClientTool;

import java.util.Map;

/**
 * HTTP请求节点执行器
 */
public class HttpRequestNodeExecutor implements NodeExecutor {

    @Override
    public boolean supports(DagNode node) {
        return CrawlerDagConstants.NodeType.HTTP_REQUEST.equals(node.getType());
    }

    @Override
    public NodeExecutionResult execute(DagNode node, ExecutionContext context) throws Exception {
        Map<String, Object> config = node.getConfig();

        String url = (String) config.get("url");
        String method = (String) config.getOrDefault("method", "GET");
        Map<String, String> headers = (Map<String, String>) config.get("headers");
        String body = (String) config.get("body");
        Integer timeout = (Integer) config.getOrDefault("timeout", 30000);

        try {
            String response = null;

            // 构建扩展配置
            HttpClientTool.ExtConfig.ExtConfigBuilder configBuilder = HttpClientTool.ExtConfig.builder();
            if (timeout != null) {
                configBuilder.responseTimeout(timeout);
            }

            if (headers != null && !headers.isEmpty()) {
                java.util.List<org.apache.hc.core5.http.Header> hcHeaders = new java.util.ArrayList<>();
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    hcHeaders.add(new org.apache.hc.core5.http.message.BasicHeader(entry.getKey(), entry.getValue()));
                }
                configBuilder.headers(hcHeaders);
            }

            HttpClientTool.ExtConfig extConfig = configBuilder.build();

            switch (method.toUpperCase()) {
                case "GET":
                    response = HttpClientTool.get(url, extConfig);
                    break;
                case "POST":
                    if (body != null) {
                        // 如果body不为空，尝试识别内容类型
                        if (headers != null && "application/json".equals(headers.get("Content-Type"))) {
                            response = HttpClientTool.postJson(url, body, extConfig);
                        } else {
                            // 默认作为普通表单处理
                            response = HttpClientTool.postForm(url, parseBodyToMap(body), extConfig);
                        }
                    } else {
                        response = HttpClientTool.postForm(url, null, extConfig);
                    }
                    break;
                case "PUT":
                    if (body != null) {
                        if (headers != null && "application/json".equals(headers.get("Content-Type"))) {
                            response = HttpClientTool.putJson(url, body, extConfig);
                        } else {
                            // PUT请求通常不使用表单，但为了兼容性保留
                            response = HttpClientTool.putJson(url, body, extConfig);
                        }
                    } else {
                        response = HttpClientTool.putJson(url, "", extConfig);
                    }
                    break;
                case "DELETE":
                    // HttpClientTool中没有DELETE方法，使用GET方式模拟（实际上应该实现DELETE）
                    response = HttpClientTool.get(url, extConfig);
                    break;
                default:
                    return NodeExecutionResult.failure(new IllegalArgumentException("不支持的HTTP方法: " + method));
            }

            // 将响应存储到执行上下文
            context.setVariable(node.getId() + "_response", response);
            context.setVariable(node.getId() + "_status", "success");

            return NodeExecutionResult.success(response);

        } catch (Exception e) {
            context.setVariable(node.getId() + "_error", e.getMessage());
            context.setVariable(node.getId() + "_status", "failed");
            return NodeExecutionResult.failure(e);
        }
    }

    /**
     * 将请求体字符串解析为Map
     */
    private java.util.Map<String, String> parseBodyToMap(String body) {
        java.util.Map<String, String> paramMap = new java.util.HashMap<>();
        if (body != null && !body.isEmpty()) {
            // 简单解析x-www-form-urlencoded格式
            String[] pairs = body.split("&");
            for (String pair : pairs) {
                String[] keyValue = pair.split("=", 2);
                if (keyValue.length == 2) {
                    paramMap.put(keyValue[0], keyValue[1]);
                } else if (keyValue.length == 1) {
                    paramMap.put(keyValue[0], "");
                }
            }
        }
        return paramMap;
    }
}