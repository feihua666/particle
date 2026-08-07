package com.particle.global.crawler.action.browser;


import com.particle.global.crawler.common.constants.ActionType;
import com.particle.global.crawler.runtime.RuntimeContext;
import com.particle.global.crawler.action.ActionResult;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 浏览器回车动作
 * <p>
 * 在指定元素上模拟按下回车键（Enter），常用于表单提交或触发搜索等操作。
 * </p>
 * @author yangwei
 * @since 2026/05/12 13:00
 */
@Slf4j
@Data
public class BrowserEnterAction extends BrowserBaseAction {

    public BrowserEnterAction() {
        setType(ActionType.BROWSER_ENTER);
    }

    /**
     * CSS 选择器（可选）
     * <p>
     * 如果未指定，则在当前聚焦元素上按回车
     * </p>
     */
    private String selector;

    /**
     * 超时时间（毫秒）
     */
    private Integer timeout;

    @Override
    public ActionResult doExecute(RuntimeContext context) {
        if (selector != null && !selector.isEmpty()) {
            log.info("在元素上按回车: selector={}", selector);
            // 聚焦到指定元素
            context.getDriver().click(selector, 5000);
        } else {
            log.info("在当前焦点元素上按回车");
        }
        // 模拟按下 Enter 键
        context.getDriver().pressKey("Enter");
        return ActionResult.success("成功执行回车操作");
    }
    public static BrowserEnterAction create(String selector,Integer  timeout) {
        BrowserEnterAction action = create();
        action.setSelector(selector);
        action.setTimeout(timeout);
        return action;
    }
    public static BrowserEnterAction create(String selector) {
        BrowserEnterAction action = create();
        action.setSelector(selector);
        return action;
    }

    public static BrowserEnterAction create() {
        return new BrowserEnterAction();
    }
}
