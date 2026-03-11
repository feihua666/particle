package com.particle.global.dag.runtime.executor;

import com.particle.global.dag.constants.NodeTypeConstants;
import com.particle.global.dag.model.DagNode;
import com.particle.global.dag.runtime.ExecutionContext;
import com.particle.global.dag.runtime.NodeExecutionResult;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.time.Duration;
import java.util.Map;

/**
 * <p>
 * HTTP请求节点执行器
 * </p>
 * <p>
 * 用于执行HTTP请求，支持GET、POST等方法，可配置URL、请求头、请求体等参数
 * </p>
 *
 * @author Claude
 * @since 2026-01-12 13:45:00
 */
public class HttpRequestNodeExecutor implements NodeExecutor {

    @Override
    public boolean supports(DagNode node) {
        String nodeType = node.getType();
        return NodeTypeConstants.HTTP.equalsIgnoreCase(nodeType);
    }

    @Override
    public NodeExecutionResult execute(DagNode node, ExecutionContext context) throws Exception {
        try {
            // 从节点配置中获取HTTP请求参数
            Map<String, Object> config = node.getConfig();
            if (config == null) {
                return NodeExecutionResult.failure(new IllegalArgumentException("HTTP node config is null"));
            }

            String method = (String) config.getOrDefault("method", "GET");
            String url = (String) config.get("url");
            Map<String, String> headers = (Map<String, String>) config.get("headers");
            Object body = config.get("body");

            if (url == null || url.trim().isEmpty()) {
                return NodeExecutionResult.failure(new IllegalArgumentException("HTTP node URL is required"));
            }

            // 执行HTTP请求
            Object result = executeHttpRequest(method, url, headers, body);
            return NodeExecutionResult.success(result);
        } catch (Exception e) {
            return NodeExecutionResult.failure(e);
        }
    }

    private Object executeHttpRequest(String method, String url, Map<String, String> headers, Object body) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(30))
                .build();

        java.net.http.HttpRequest.Builder requestBuilder = java.net.http.HttpRequest.newBuilder()
                .uri(URI.create(url));

        // 添加请求头
        if (headers != null) {
            for (Map.Entry<String, String> header : headers.entrySet()) {
                requestBuilder.header(header.getKey(), header.getValue());
            }
        }

        // 设置请求体
        if ("POST".equalsIgnoreCase(method) || "PUT".equalsIgnoreCase(method) || "PATCH".equalsIgnoreCase(method)) {
            String bodyStr = body != null ? body.toString() : "";
            requestBuilder.method(method, java.net.http.HttpRequest.BodyPublishers.ofString(bodyStr));
        } else {
            requestBuilder.GET(); // Default to GET for other methods
        }

        java.net.http.HttpRequest request = requestBuilder.build();
        java.net.http.HttpResponse<String> response = client.send(request, java.net.http.HttpResponse.BodyHandlers.ofString());

        // 返回响应结果
        Map<String, Object> result = Map.of(
            "statusCode", response.statusCode(),
            "headers", response.headers().map(),
            "body", response.body(),
            "url", url,
            "method", method
        );

        return result;
    }
}
