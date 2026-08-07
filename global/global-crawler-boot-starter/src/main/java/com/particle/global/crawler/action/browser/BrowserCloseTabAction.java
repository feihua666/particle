package com.particle.global.crawler.action.browser;


import com.particle.global.crawler.common.constants.ActionType;
import com.particle.global.crawler.runtime.RuntimeContext;
import com.particle.global.crawler.action.ActionResult;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 关闭当前标签页动作
 *
 * @author yangwei
 * @since 2026/05/12 13:00
 */
@Slf4j
@Data
public class BrowserCloseTabAction extends BrowserBaseAction {

    public BrowserCloseTabAction() {
        setType(ActionType.BROWSER_CLOSE_PAGE);
    }

    @Override
    public ActionResult doExecute(RuntimeContext context) {
        context.getDriver().closeTab();
        log.info("关闭当前标签页");
        return ActionResult.success("成功关闭当前标签页");
    }

    public static BrowserCloseTabAction create() {
        return new BrowserCloseTabAction();
    }

}
