package com.particle.global.crawler.action.browser;

import com.particle.global.crawler.common.constants.ActionType;
import com.particle.global.crawler.runtime.RuntimeContext;

import com.particle.global.crawler.action.ActionResult;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 打开页面动作
 * @author yangwei
 * @since 2026/05/12 13:00
 */
@Slf4j
@Data
public class BrowserOpenAction extends BrowserBaseAction {

    public BrowserOpenAction() {
        setType(ActionType.BROWSER_OPEN);
    }

    /**
     * 目标 URL
     */
    private String url;

    /**
     * 等待加载完成时间（毫秒）
     */
    private Integer loadTimeout;

    @Override
    public ActionResult doExecute(RuntimeContext context) {
        String newUrl = context.resolveExpression(url);
        log.info("打开页面: {}", newUrl);
        context.getDriver().open(newUrl);
        return ActionResult.success("成功打开页面: " + newUrl);
    }

    public static BrowserOpenAction create(String url, Integer loadTimeout) {
        BrowserOpenAction action = new BrowserOpenAction();
        action.setUrl(url);
        action.setLoadTimeout(loadTimeout);
        return action;
    }

    public static BrowserOpenAction create(String url) {
        return create(url, 30000);
    }

}
