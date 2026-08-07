package com.particle.global.crawler.action.extract;

import com.particle.global.crawler.common.constants.ActionType;
import com.particle.global.crawler.runtime.RuntimeContext;

import com.particle.global.crawler.action.ActionResult;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
/**
 * 提取文本动作
 * @author yangwei
 * @since 2026/05/12 13:00
 */
@Slf4j
@Data
public class ExtractTextAction extends ExtractBaseAction {

    public ExtractTextAction() {
        setType(ActionType.EXTRACT_TEXT);
    }

    @Override
    public ActionResult doExecute(RuntimeContext context) {
        log.info("提取文本: selector={}", selector);
        List<String> texts = context.getDriver().extractText(selector);
        log.info("提取文本成功: {} 条记录", texts.size());
        return ActionResult.success("提取文本成功", texts);
    }

    public static ExtractTextAction create(String selector, String resultKey) {
        ExtractTextAction action = new ExtractTextAction();
        action.setSelector(selector);
        action.setResultKey(resultKey);
        return action;
    }

    public static ExtractTextAction create(String selector) {
        return create(selector, null);
    }

}
