package com.particle.global.crawler.dag;

import com.particle.global.dag.engine.DagEngine;
import com.particle.global.dag.model.DagDefinition;
import com.particle.global.dag.model.DagNode;
import com.particle.global.dag.model.DagEdge;
import com.particle.global.dag.runtime.ExecutionContext;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;

/**
 * 爬虫DAG服务类
 * 提供便捷的爬虫工作流创建和执行功能
 */
public class CrawlerDagService {

    private final DagEngine dagEngine;

    public CrawlerDagService(DagEngine dagEngine) {
        this.dagEngine = dagEngine;
    }

    /**
     * 执行爬虫工作流
     * @param dagDefinition 工作流定义
     * @param context 执行上下文
     * @return 执行结果
     */
    public Object executeCrawlerWorkflow(DagDefinition dagDefinition, ExecutionContext context) {
        return dagEngine.execute(dagDefinition, context);
    }

    /**
     * 创建简单的爬虫工作流 - 爬取页面并提取数据
     * @param crawlUrl 爬取URL
     * @param selectors 选择器配置
     * @param outputFilePath 输出文件路径
     * @return DAG定义
     */
    public DagDefinition createSimpleCrawlerWorkflow(String crawlUrl, List<Map<String, Object>> selectors, String outputFilePath) {
        List<DagNode> nodes = new ArrayList<>();
        List<DagEdge> edges = new ArrayList<>();

        // 1. 创建页面爬取节点
        DagNode crawlNode = DagNode.builder()
                .id("crawl_node")
                .name("页面爬取节点")
                .type(CrawlerDagConstants.NodeType.PAGE_CRAWL)
                .config(Map.of(
                        "url", crawlUrl,
                        "useAntiDetection", true
                ))
                .build();

        // 2. 创建数据提取节点
        DagNode extractNode = DagNode.builder()
                .id("extract_node")
                .name("数据提取节点")
                .type(CrawlerDagConstants.NodeType.DATA_EXTRACT)
                .config(Map.of(
                        "sourceNodeId", "crawl_node",
                        "extractRules", selectors
                ))
                .build();

        // 3. 创建数据存储节点
        DagNode storeNode = DagNode.builder()
                .id("store_node")
                .name("数据存储节点")
                .type(CrawlerDagConstants.NodeType.DATA_STORE)
                .config(Map.of(
                        "sourceNodeId", "extract_node",
                        "storageType", "json",
                        "filePath", outputFilePath
                ))
                .build();

        nodes.add(crawlNode);
        nodes.add(extractNode);
        nodes.add(storeNode);

        // 创建边：crawl -> extract -> store
        edges.add(DagEdge.builder()
                .id("crawl_to_extract")
                .fromNodeId("crawl_node")
                .toNodeId("extract_node")
                .build());
        edges.add(DagEdge.builder()
                .id("extract_to_store")
                .fromNodeId("extract_node")
                .toNodeId("store_node")
                .build());

        return DagDefinition.builder()
                .id("simple_crawler_workflow")
                .name("简单爬虫工作流")
                .nodes(nodes)
                .edges(edges)
                .build();
    }

    /**
     * 创建复杂的爬虫工作流 - 包含多个页面爬取、数据处理和存储
     * @param pages 待爬取的页面列表
     * @param extractionRules 提取规则
     * @param processingRules 处理规则
     * @param outputFilePath 输出文件路径
     * @return DAG定义
     */
    public DagDefinition createComplexCrawlerWorkflow(
            List<Map<String, Object>> pages,
            List<Map<String, Object>> extractionRules,
            List<Map<String, Object>> processingRules,
            String outputFilePath) {

        List<DagNode> nodes = new ArrayList<>();
        List<DagEdge> edges = new ArrayList<>();

        // 创建起始节点 - 多个页面爬取
        for (int i = 0; i < pages.size(); i++) {
            Map<String, Object> page = pages.get(i);
            String nodeId = "crawl_node_" + i;

            DagNode crawlNode = DagNode.builder()
                    .id(nodeId)
                    .name("页面爬取节点" + (i + 1))
                    .type(CrawlerDagConstants.NodeType.PAGE_CRAWL)
                    .config(Map.of(
                            "url", page.get("url"),
                            "selector", page.get("selector"),
                            "useAntiDetection", true
                    ))
                    .build();

            nodes.add(crawlNode);
        }

        // 创建数据提取节点（可以并行处理）
        List<String> extractNodeIds = new ArrayList<>();
        for (int i = 0; i < pages.size(); i++) {
            String crawlNodeId = "crawl_node_" + i;
            String extractNodeId = "extract_node_" + i;

            DagNode extractNode = DagNode.builder()
                    .id(extractNodeId)
                    .name("数据提取节点" + (i + 1))
                    .type(CrawlerDagConstants.NodeType.DATA_EXTRACT)
                    .config(Map.of(
                            "sourceNodeId", crawlNodeId,
                            "extractRules", extractionRules
                    ))
                    .build();

            nodes.add(extractNode);
            extractNodeIds.add(extractNodeId);

            // 添加爬取到提取的边
            edges.add(DagEdge.builder()
                    .id(crawlNodeId + "_to_" + extractNodeId)
                    .fromNodeId(crawlNodeId)
                    .toNodeId(extractNodeId)
                    .build());
        }

        // 创建数据处理节点（聚合多个提取结果）
        String processNodeId = "process_node";
        DagNode processNode = DagNode.builder()
                .id(processNodeId)
                .name("数据处理节点")
                .type(CrawlerDagConstants.NodeType.DATA_PROCESS)
                .config(Map.of(
                        "sourceNodeId", extractNodeIds.get(0), // 可以根据需要调整聚合方式
                        "processRules", processingRules
                ))
                .build();

        nodes.add(processNode);

        // 将所有提取节点连接到处理节点
        for (String extractNodeId : extractNodeIds) {
            edges.add(DagEdge.builder()
                    .id(extractNodeId + "_to_process")
                    .fromNodeId(extractNodeId)
                    .toNodeId(processNodeId)
                    .build());
        }

        // 创建数据存储节点
        String storeNodeId = "store_node";
        DagNode storeNode = DagNode.builder()
                .id(storeNodeId)
                .name("数据存储节点")
                .type(CrawlerDagConstants.NodeType.DATA_STORE)
                .config(Map.of(
                        "sourceNodeId", processNodeId,
                        "storageType", "json",
                        "filePath", outputFilePath
                ))
                .build();

        nodes.add(storeNode);

        // 连接处理节点到存储节点
        edges.add(DagEdge.builder()
                .id("process_to_store")
                .fromNodeId(processNodeId)
                .toNodeId(storeNodeId)
                .build());

        return DagDefinition.builder()
                .id("complex_crawler_workflow")
                .name("复杂爬虫工作流")
                .nodes(nodes)
                .edges(edges)
                .build();
    }
}