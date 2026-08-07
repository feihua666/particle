package com.particle.global.crawler.action.browser;


import com.particle.global.crawler.common.constants.ActionType;
import com.particle.global.crawler.runtime.RuntimeContext;
import com.particle.global.crawler.action.ActionResult;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 新建标签页动作
 * @author yangwei
 * @since 2026/05/12 13:00
 */
@Slf4j
@Data
public class BrowserNewTabAction extends BrowserBaseAction {

    public BrowserNewTabAction() {
        setType(ActionType.BROWSER_NEW_TAB);
    }

    /**
     * 新页面 URL（可选）
     */
    private String url;

    @Override
    public ActionResult doExecute(RuntimeContext context) {

        String newUrl = context.resolveExpression(url);
        // 创建新标签页（Driver 内部会自动切换为当前 Page）
        context.getDriver().newTab(newUrl);
        log.info("创建新标签页并激活: {}", newUrl != null ? newUrl : "空白页");
        return ActionResult.success("成功创建新标签页");
    }

    public static BrowserNewTabAction create(String url) {
        BrowserNewTabAction action = new BrowserNewTabAction();
        action.setUrl(url);
        return action;
    }

    public static BrowserNewTabAction create() {
        return new BrowserNewTabAction();
    }

}
