package com.particle.global.crawler.action.browser;
import com.particle.global.crawler.common.constants.ActionType;
import com.particle.global.crawler.runtime.RuntimeContext;

import com.particle.global.crawler.action.ActionResult;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 点击元素动作
 *
 * @author yangwei
 * @since 2026/05/12 13:00
 */
@Slf4j
@Data
public class BrowserClickAction extends BrowserBaseAction {

    /**
     * CSS 选择器
     */
    private String selector;

    /**
     * 超时时间（毫秒）
     */
    private Integer timeout;

    public BrowserClickAction () {
        setType(ActionType.BROWSER_CLICK);
    }

    @Override
    public ActionResult doExecute(RuntimeContext context) {
        log.info("点击元素: selector={}, timeout={}", selector, timeout);
        context.getDriver().click(selector, timeout);
        return ActionResult.success("成功点击元素: " + selector);
    }

    public static BrowserClickAction create(String selector, Integer timeout) {
        BrowserClickAction action = new BrowserClickAction();
        action.setSelector(selector);
        action.setTimeout(timeout);
        return action;
    }

    public static BrowserClickAction create(String selector) {
        return create(selector, 5000);
    }

}
