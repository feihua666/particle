package com.particle.global.crawler.dag;

/**
 * 爬虫DAG常量定义
 */
public class CrawlerDagConstants {

    /**
     * 爬虫节点类型枚举
     */
    public static class NodeType {
        /**
         * HTTP请求节点
         */
        public static final String HTTP_REQUEST = "http_request";

        /**
         * 页面抓取节点
         */
        public static final String PAGE_CRAWL = "page_crawl";

        /**
         * 数据提取节点
         */
        public static final String DATA_EXTRACT = "data_extract";

        /**
         * 数据处理节点
         */
        public static final String DATA_PROCESS = "data_process";

        /**
         * 存储节点
         */
        public static final String DATA_STORE = "data_store";

        /**
         * 延迟节点
         */
        public static final String DELAY = "delay";

        /**
         * 条件判断节点
         */
        public static final String CONDITION = "condition";
    }
}