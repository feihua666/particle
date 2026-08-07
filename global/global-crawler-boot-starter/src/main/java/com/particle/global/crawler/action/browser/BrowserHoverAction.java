package com.particle.global.crawler.action.browser;

import com.particle.global.crawler.action.ActionResult;
import com.particle.global.crawler.common.constants.ActionType;
import com.particle.global.crawler.runtime.RuntimeContext;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;



/**
 * 鼠标悬停 Action
 * @author yangwei
 * @since 2026/05/12 13:00
 */
@Slf4j
@Data
public class BrowserHoverAction extends BrowserBaseAction {

    /**
     * CSS 选择器
     */
    private String selector;
    /**
     * 超时时间（毫秒）
     */
    private Integer timeout;

    public BrowserHoverAction() {
        setType(ActionType.BROWSER_HOVER);
    }

    public BrowserHoverAction(String selector, Integer timeout) {
        this.selector = selector;
        this.timeout = timeout;
    }

    @Override
    public ActionResult doExecute(RuntimeContext context) {
        log.info("鼠标悬停: selector={},timeout={}", selector, timeout);
        context.getDriver().hover(selector);
        return ActionResult.success("悬停成功，selector=" + selector);
    }

    public static BrowserHoverAction create(String selector, Integer timeout) {
        BrowserHoverAction action = new BrowserHoverAction();
        action.setSelector(selector);
        action.setTimeout(timeout);
        return action;
    }

    public static BrowserHoverAction create(String selector) {
        return create(selector, 5000);
    }
}
