package com.particle.global.crawler.action.extract;

import com.particle.global.crawler.action.ActionResult;
import com.particle.global.crawler.common.constants.ActionType;
import com.particle.global.crawler.runtime.RuntimeContext;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 提取属性动作
 *
 * @author yangwei
 * @since 2026/05/12 13:00
 */
@Slf4j
@Data
public class ExtractAttrAction extends ExtractBaseAction {

    public ExtractAttrAction() {
        setType(ActionType.EXTRACT_ATTR);
    }

    /**
     * 属性名（如 href, src, data-id）
     */
    private String attrName;

    /**
     * 结果存储的变量名
     */

    @Override
    public ActionResult doExecute(RuntimeContext context) {
        log.info("提取属性: selector={}, attr={}", selector, attrName);
        List<String> attrs = context.getDriver().extractAttr(selector, attrName);

        log.info("提取属性成功: {} 条记录", attrs.size());
        return ActionResult.success("提取属性成功", attrs);
    }

    public static ExtractAttrAction create(String selector, String attrName, String resultKey) {
        ExtractAttrAction action = new ExtractAttrAction();
        action.setSelector(selector);
        action.setAttrName(attrName);
        action.setResultKey(resultKey);
        return action;
    }

    public static ExtractAttrAction create(String selector, String attrName) {
        return create(selector, attrName, null);
    }

}
