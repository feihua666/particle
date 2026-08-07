package com.particle.global.crawler.pipeline.action;

import com.particle.global.crawler.action.browser.*;
import com.particle.global.crawler.action.extract.*;
import com.particle.global.crawler.action.flow.*;
import com.particle.global.crawler.action.store.*;
import com.particle.global.crawler.common.constants.ActionType;

/**
 * <p>
 * 默认注册的 Action
 * </p>
 *
 * @author yangwei
 * @since 2026/5/13 10:44
 */
public class ActionRegistryInitializer {

    static {
        // ==================== 浏览器操作 ====================
        ActionTypeIdResolver.register(ActionType.BROWSER_OPEN, BrowserOpenAction.class);
        ActionTypeIdResolver.register(ActionType.BROWSER_NEW_TAB, BrowserNewTabAction.class);
        ActionTypeIdResolver.register(ActionType.BROWSER_CLOSE_PAGE, BrowserCloseTabAction.class);
        ActionTypeIdResolver.register(ActionType.BROWSER_CLICK, BrowserClickAction.class);
        ActionTypeIdResolver.register(ActionType.BROWSER_INPUT, BrowserInputAction.class);
        ActionTypeIdResolver.register(ActionType.BROWSER_HOVER, BrowserHoverAction.class);
        ActionTypeIdResolver.register(ActionType.BROWSER_SCROLL, BrowserScrollAction.class);
        ActionTypeIdResolver.register(ActionType.BROWSER_ENTER, BrowserEnterAction.class);
        ActionTypeIdResolver.register(ActionType.BROWSER_PRESS, BrowserPressKeyAction.class);
        ActionTypeIdResolver.register(ActionType.BROWSER_PRESS, BrowserPressKeyAction.class);
        ActionTypeIdResolver.register(ActionType.BROWSER_CLOSE, BrowserCloseAction.class);

        // ==================== HTTP 操作 ====================
        // TODO: 待实现 HTTP Action
        // ActionTypeIdResolver.register(ActionType.HTTP_GET, HttpGetAction.class);
        // ActionTypeIdResolver.register(ActionType.HTTP_POST, HttpPostAction.class);
        // ActionTypeIdResolver.register(ActionType.HTTP_DOWNLOAD, HttpDownloadAction.class);

        // ==================== 数据提取 ====================
        ActionTypeIdResolver.register(ActionType.EXTRACT_TEXT, ExtractTextAction.class);
        ActionTypeIdResolver.register(ActionType.EXTRACT_HTML, ExtractHtmlAction.class);
        ActionTypeIdResolver.register(ActionType.EXTRACT_ATTR, ExtractAttrAction.class);
        ActionTypeIdResolver.register(ActionType.EXTRACT_TITLE, ExtractTitleAction.class);

        // ==================== 流程控制 ====================
        ActionTypeIdResolver.register(ActionType.FLOW_DELAY, DelayAction.class);
        ActionTypeIdResolver.register(ActionType.FLOW_RETRY, RetryAction.class);
        ActionTypeIdResolver.register(ActionType.FLOW_LOOP, LoopAction.class);
        ActionTypeIdResolver.register(ActionType.FLOW_CONDITIONAL, ConditionalAction.class);
        ActionTypeIdResolver.register(ActionType.FLOW_BREAK, BreakAction.class);
        ActionTypeIdResolver.register(ActionType.FLOW_CONTINUE, ContinueAction.class);

        // ==================== 存储 ====================
        ActionTypeIdResolver.register(ActionType.STORE_RAW, RawStoreAction.class);
        ActionTypeIdResolver.register(ActionType.STORE_DATA, DataStoreAction.class);
    }

}
