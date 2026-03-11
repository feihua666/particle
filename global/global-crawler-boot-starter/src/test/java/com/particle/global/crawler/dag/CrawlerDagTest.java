package com.particle.global.crawler.dag;

import com.particle.global.crawler.dag.CrawlerDagService;
import com.particle.global.crawler.dag.CrawlerDagConstants;
import com.particle.global.dag.engine.DefaultDagEngine;
import com.particle.global.dag.runtime.executor.NodeExecutorRegistry;
import com.particle.global.dag.model.DagDefinition;
import com.particle.global.dag.options.ExecutionOptions;
import com.particle.global.dag.runtime.ExecutionContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

/**
 * 爬虫DAG功能测试
 */
@SpringBootTest
public class CrawlerDagTest {

    @Autowired
    private NodeExecutorRegistry nodeExecutorRegistry;

    @Test
    public void testSimpleCrawlerWorkflow() {
        // 创建DAG引擎
        DefaultDagEngine dagEngine = new DefaultDagEngine();
        // 注册爬虫相关的节点执行器
        dagEngine.getNodeExecutorRegistry().register(new com.particle.global.crawler.dag.HttpRequestNodeExecutor());
        dagEngine.getNodeExecutorRegistry().register(new com.particle.global.crawler.dag.PageCrawlNodeExecutor());
        dagEngine.getNodeExecutorRegistry().register(new com.particle.global.crawler.dag.DataExtractNodeExecutor());
        dagEngine.getNodeExecutorRegistry().register(new com.particle.global.crawler.dag.DataProcessNodeExecutor());
        dagEngine.getNodeExecutorRegistry().register(new com.particle.global.crawler.dag.DataStoreNodeExecutor());
        dagEngine.getNodeExecutorRegistry().register(new com.particle.global.crawler.dag.DelayNodeExecutor());
        dagEngine.getNodeExecutorRegistry().register(new com.particle.global.crawler.dag.ConditionNodeExecutor());

        CrawlerDagService crawlerDagService = new CrawlerDagService(dagEngine);

        // 定义提取规则
        List<Map<String, Object>> selectors = Arrays.asList(
            createExtractRule("title", "title", "text", false),
            createExtractRule("links", "a[href]", "href", true)
        );

        // 创建简单爬虫工作流
        DagDefinition workflow = crawlerDagService.createSimpleCrawlerWorkflow(
            "https://example.com",
            selectors,
            "./output/crawler_result.json"
        );

        // 创建执行上下文
        ExecutionContext context = new ExecutionContext();

        // 执行工作流 - 使用同步执行方法
        Object result = dagEngine.executeAndWait(workflow, ExecutionOptions.full(), context);

        System.out.println("爬虫工作流执行结果: " + result);
    }

    @Test
    public void testCustomCrawlerDag() {
        // 创建DAG引擎
        DefaultDagEngine dagEngine = new DefaultDagEngine();
        // 注册爬虫相关的节点执行器
        dagEngine.getNodeExecutorRegistry().register(new com.particle.global.crawler.dag.HttpRequestNodeExecutor());
        dagEngine.getNodeExecutorRegistry().register(new com.particle.global.crawler.dag.PageCrawlNodeExecutor());
        dagEngine.getNodeExecutorRegistry().register(new com.particle.global.crawler.dag.DataExtractNodeExecutor());
        dagEngine.getNodeExecutorRegistry().register(new com.particle.global.crawler.dag.DataProcessNodeExecutor());
        dagEngine.getNodeExecutorRegistry().register(new com.particle.global.crawler.dag.DataStoreNodeExecutor());
        dagEngine.getNodeExecutorRegistry().register(new com.particle.global.crawler.dag.DelayNodeExecutor());
        dagEngine.getNodeExecutorRegistry().register(new com.particle.global.crawler.dag.ConditionNodeExecutor());

        // 创建自定义DAG定义
        List<Map<String, Object>> nodes = Arrays.asList(
            // HTTP请求节点
            createNode("http_request_node", "HTTP请求节点", CrawlerDagConstants.NodeType.HTTP_REQUEST, Map.of(
                "url", "https://httpbin.org/get",
                "method", "GET"
            )),
            // 延迟节点
            createNode("delay_node", "延迟节点", CrawlerDagConstants.NodeType.DELAY, Map.of(
                "delayMs", 1000
            )),
            // 数据存储节点
            createNode("store_node", "数据存储节点", CrawlerDagConstants.NodeType.DATA_STORE, Map.of(
                "sourceNodeId", "http_request_node",
                "storageType", "json",
                "filePath", "./output/http_response.json"
            ))
        );

        List<Map<String, Object>> edges = Arrays.asList(
            createEdge("http_to_delay", "http_request_node", "delay_node"),
            createEdge("delay_to_store", "delay_node", "store_node")
        );

        DagDefinition dagDefinition = DagDefinition.builder()
            .id("custom_crawler_dag")
            .name("自定义爬虫DAG")
            .nodes(createDagNodes(nodes))
            .edges(createDagEdges(edges))
            .build();

        // 创建执行上下文
        ExecutionContext context = new ExecutionContext();

        // 执行DAG - 使用同步执行方法
        Object result = dagEngine.executeAndWait(dagDefinition, ExecutionOptions.full(), context);

        System.out.println("自定义爬虫DAG执行结果: " + result);
    }

    private Map<String, Object> createExtractRule(String fieldName, String selector, String attribute, boolean isList) {
        Map<String, Object> rule = new HashMap<>();
        rule.put("fieldName", fieldName);
        rule.put("selector", selector);
        rule.put("attribute", attribute);
        rule.put("isList", isList);
        return rule;
    }

    private Map<String, Object> createNode(String id, String name, String type, Map<String, Object> config) {
        Map<String, Object> node = new HashMap<>();
        node.put("id", id);
        node.put("name", name);
        node.put("type", type);
        node.put("config", config);
        return node;
    }

    private Map<String, Object> createEdge(String id, String from, String to) {
        Map<String, Object> edge = new HashMap<>();
        edge.put("id", id);
        edge.put("from", from);
        edge.put("to", to);
        return edge;
    }

    private List<com.particle.global.dag.model.DagNode> createDagNodes(List<Map<String, Object>> nodeMaps) {
        List<com.particle.global.dag.model.DagNode> nodes = new ArrayList<>();
        for (Map<String, Object> nodeMap : nodeMaps) {
            com.particle.global.dag.model.DagNode node = com.particle.global.dag.model.DagNode.builder()
                .id((String) nodeMap.get("id"))
                .name((String) nodeMap.get("name"))
                .type((String) nodeMap.get("type"))
                .config((Map<String, Object>) nodeMap.get("config"))
                .build();
            nodes.add(node);
        }
        return nodes;
    }

    private List<com.particle.global.dag.model.DagEdge> createDagEdges(List<Map<String, Object>> edgeMaps) {
        List<com.particle.global.dag.model.DagEdge> edges = new ArrayList<>();
        for (Map<String, Object> edgeMap : edgeMaps) {
            com.particle.global.dag.model.DagEdge edge = com.particle.global.dag.model.DagEdge.builder()
                .id((String) edgeMap.get("id"))
                .fromNodeId((String) edgeMap.get("from"))
                .toNodeId((String) edgeMap.get("to"))
                .build();
            edges.add(edge);
        }
        return edges;
    }
}
