package com.particle.global.crawler.common.constants;

/**
 * <p>
 * Action 类型 Code 常量
 * </p>
 *
 * @author yangwei
 * @since 2026/5/13 10:32
 */
public class ActionType {

    public static final String BROWSER = "browser";

    // 浏览器操作
    public static final String BROWSER_OPEN       = "browser.open";
    public static final String BROWSER_NEW_TAB    = "browser.newTab";
    public static final String BROWSER_CLOSE_PAGE = "browser.closePage";
    public static final String BROWSER_CLICK      = "browser.click";
    public static final String BROWSER_INPUT      = "browser.input";
    public static final String BROWSER_HOVER      = "browser.hover";
    public static final String BROWSER_SCROLL     = "browser.scroll";
    public static final String BROWSER_ENTER      = "browser.enter";
    public static final String BROWSER_PRESS      = "browser.press";
    public static final String BROWSER_CLOSE      = "browser.close";

    // HTTP
    public static final String HTTP_GET      = "http.get";
    public static final String HTTP_POST     = "http.post";
    public static final String HTTP_DOWNLOAD = "http.download";

    // 数据提取
    public static final String EXTRACT_TEXT  = "extract.text";
    public static final String EXTRACT_HTML  = "extract.html";
    public static final String EXTRACT_ATTR  = "extract.attr";
    public static final String EXTRACT_TITLE = "extract.title";
    public static final String EXTRACT_LIST  = "extract.list";
    public static final String EXTRACT_TABLE = "extract.table";
    public static final String EXTRACT_JSON  = "extract.json";

    // 流程控制
    public static final String FLOW_DELAY       = "flow.delay";
    public static final String FLOW_RETRY       = "flow.retry";
    public static final String FLOW_LOOP        = "flow.loop";
    public static final String FLOW_CONDITIONAL = "flow.conditional";
    public static final String FLOW_BREAK       = "flow.break";
    public static final String FLOW_CONTINUE    = "flow.continue";

    // 存储
    public static final String STORE_RAW  = "store.raw";
    public static final String STORE_DATA = "store.data";
}
