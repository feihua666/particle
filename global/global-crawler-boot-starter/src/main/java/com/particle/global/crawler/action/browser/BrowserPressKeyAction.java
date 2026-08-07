package com.particle.global.crawler.action.browser;

import com.particle.global.crawler.common.constants.ActionType;
import com.particle.global.crawler.runtime.RuntimeContext;
import com.particle.global.crawler.action.ActionResult;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 浏览器按键动作
 * <p>
 * 模拟按下指定的键盘按键，如 Enter、Tab、Escape 等。
 * </p>
 * @author yangwei
 * @since 2026/05/12 13:00
 */
@Slf4j
@Data
public class BrowserPressKeyAction extends BrowserBaseAction {

    public BrowserPressKeyAction() {
        setType(ActionType.BROWSER_PRESS);
    }

    /**
     * 按键名称（如 "Enter", "Tab", "Escape"）
     */
    private String key;

    @Override
    public ActionResult doExecute(RuntimeContext context) {
        log.info("按下按键: {}", key);
        context.getDriver().pressKey(key);
        return ActionResult.success("成功按下按键: " + key);
    }

    public static BrowserPressKeyAction create(String key) {
        BrowserPressKeyAction action = new BrowserPressKeyAction();
        action.setKey(key);
        return action;
    }

    public static BrowserPressKeyAction create() {
        return create("Enter");
    }

}
