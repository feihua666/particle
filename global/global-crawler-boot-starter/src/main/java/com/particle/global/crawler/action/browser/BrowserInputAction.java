package com.particle.global.crawler.action.browser;

import com.particle.global.crawler.common.constants.ActionType;
import com.particle.global.crawler.runtime.RuntimeContext;

import com.particle.global.crawler.action.ActionResult;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 输入文本动作
 *
 * @author yangwei
 * @since 2026/05/12 13:00
 */
@Slf4j
@Data
public class BrowserInputAction extends BrowserBaseAction {

    public BrowserInputAction() {
        setType(ActionType.BROWSER_INPUT);
    }

    /**
     * CSS 选择器
     */
    private String selector;

    /**
     * 要输入的文本
     */
    private String text;

    /**
     * 超时时间（毫秒）
     */
    private Integer timeout;

    @Override
    public ActionResult doExecute(RuntimeContext context) {
        String realText = context.resolveExpression(text);
        log.info("输入文本: selector={}, text={},timeout={}", selector, realText, timeout);
        context.getDriver().input(selector, realText, timeout);
        return ActionResult.success("成功输入文本到: " + selector);
    }

    public static BrowserInputAction create(String selector, String text, Integer timeout) {
        BrowserInputAction action = new BrowserInputAction();
        action.setSelector(selector);
        action.setText(text);
        action.setTimeout(timeout);
        return action;
    }

    public static BrowserInputAction create(String selector, String text) {
        return create(selector, text, 5000);
    }

}
