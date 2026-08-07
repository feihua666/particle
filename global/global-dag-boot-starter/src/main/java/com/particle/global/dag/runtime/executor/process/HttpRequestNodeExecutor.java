package com.particle.global.dag.runtime.executor.process;

import cn.hutool.core.convert.Convert;
import cn.hutool.json.JSONUtil;
import com.particle.global.dag.constants.NodeTypeConstants;
import com.particle.global.dag.model.*;
import com.particle.global.dag.runtime.ExecutionContext;
import com.particle.global.dag.runtime.NodeExecutionResult;
import com.particle.global.dag.runtime.executor.BaseNodeExecutor;
import com.particle.global.tool.json.JsonTool;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.time.Duration;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * HTTP 请求节点执行器
 * </p>
 *
 * @author Claude
 * @since 2026-01-12 13:45:00
 */
public class HttpRequestNodeExecutor extends BaseNodeExecutor {

    @Override
    public boolean supports(DagNode node) {
        return NodeTypeConstants.HTTP.equalsIgnoreCase(node.getType());
    }

    @Override
    public NodeExecutionResult doExecute(DagNode node, ExecutionContext context, Map<String, NodePort> inputMap) throws Exception {
        try {
            Map<String, Object> httpConfig = (Map<String, Object>) getDataFromInput(inputMap, "httpConfig");
            if (httpConfig == null) {
                httpConfig = (Map<String, Object>) getDataFromValuePorts(node, NodePort.CONTENT_PORT_NAME);
            }
            String url = (String)httpConfig.get("url");
            if (url == null || url.trim().isEmpty()) {
                return NodeExecutionResult.failure(new IllegalArgumentException("HTTP node URL is required"));
            }
            String method = (String)httpConfig.get("method");
            method = method != null ? method : "GET";

            String headerStr = (String)httpConfig.get("headerStr");
            Map<String, String> headers = null;
            if (headerStr != null && !headerStr.trim().isEmpty()) {
                headers = JSONUtil.toBean(headerStr, Map.class);
            }

            Object connectTimeoutObj = httpConfig.get("connectTimeout");
            // 默认 30秒
            Long connectTimeout = Convert.toLong(connectTimeoutObj, 30000L);

            String body = (String) getDataFromInput(inputMap, NodePort.INPUT_PORT_NAME);



            // 执行 HTTP 请求
            java.net.http.HttpResponse<String> response = executeHttpRequest(method,
                    url,
                    headers,
                    connectTimeout,
                    body);

            // 构建端口化输出
            NodeOutput output = new NodeOutput();
            Map<String, Object> responseData = new java.util.HashMap<>();
            responseData.put("body", response.body());
            responseData.put("statusCode", response.statusCode());
            responseData.put("headers", response.headers().map());
            List<DataType> outputDataTypes = getOutputPortDataType(node,NodePort.OUTPUT_PORT_NAME);

            PortType outputPortPortType = getOutputPortPortType(node,NodePort.OUTPUT_PORT_NAME);
            output.addPort(NodePort.OUTPUT_PORT_NAME, responseData,outputPortPortType, outputDataTypes);

            return NodeExecutionResult.success(output);
        } catch (Exception e) {
            return NodeExecutionResult.failure(e);
        }
    }


    private java.net.http.HttpResponse<String> executeHttpRequest(String method,
                                                                  String url,
                                                                  Map<String, String> headers,
                                                                  long connectTimeout,
                                                                  String body) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofMillis(connectTimeout))
                .build();

        java.net.http.HttpRequest.Builder requestBuilder = java.net.http.HttpRequest.newBuilder()
                .uri(URI.create(url));

        if (headers != null) {
            for (Map.Entry<String, String> header : headers.entrySet()) {
                requestBuilder.header(header.getKey(), header.getValue());
            }
        }

        if ("POST".equalsIgnoreCase(method) || "PUT".equalsIgnoreCase(method) || "PATCH".equalsIgnoreCase(method)) {
            String bodyStr = body != null ? body : "";
            requestBuilder.method(method, java.net.http.HttpRequest.BodyPublishers.ofString(bodyStr));
        } else {
            requestBuilder.GET();
        }

        java.net.http.HttpRequest request = requestBuilder.build();
        return client.send(request, java.net.http.HttpResponse.BodyHandlers.ofString());
    }
}
