package com.particle.global.crawler.action.extract;

import com.particle.global.crawler.common.constants.ActionType;
import com.particle.global.crawler.runtime.RuntimeContext;

import com.particle.global.crawler.action.ActionResult;
import com.particle.global.crawler.driver.CrawlDriver;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 提取 HTML Action
 * @author yangwei
 * @since 2026/05/12 13:00
 */
@Slf4j
@Data
public class ExtractHtmlAction extends ExtractBaseAction {

    public ExtractHtmlAction() {
        setType(ActionType.EXTRACT_HTML);
    }

    @Override
    public ActionResult doExecute(RuntimeContext context) {
        log.info("提取 HTML: selector={}", selector);
        CrawlDriver driver = context.getDriver();
        List<String> htmls = driver.extractHtml(selector);
        log.info("提取 HTML 成功: {} 条记录", htmls.size());

        return ActionResult.success("提取 Html 成功", htmls);
    }

    public static ExtractHtmlAction create(String selector) {
        ExtractHtmlAction action = new ExtractHtmlAction();
        action.setSelector(selector);
        return action;
    }
}
