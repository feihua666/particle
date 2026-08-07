package com.particle.global.crawler.action.browser;

import com.particle.global.crawler.action.ActionResult;
import com.particle.global.crawler.common.constants.ActionType;
import com.particle.global.crawler.runtime.RuntimeContext;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 关闭浏览器动作
 *
 * @author yangwei
 * @since 2026/05/12 13:00
 */
@Slf4j
@Data
public class BrowserCloseAction extends BrowserBaseAction {

    public BrowserCloseAction() {
        setType(ActionType.BROWSER_CLOSE);
    }

    @Override
    public ActionResult doExecute(RuntimeContext context) {
        log.info("关闭浏览器");
        context.getDriver().close();
        return ActionResult.success("浏览器已关闭");
    }


    public static BrowserCloseAction create() {
        return new BrowserCloseAction();
    }

}
