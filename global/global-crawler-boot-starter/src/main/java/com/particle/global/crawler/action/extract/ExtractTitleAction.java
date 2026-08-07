package com.particle.global.crawler.action.extract;


import com.particle.global.crawler.common.constants.ActionType;
import com.particle.global.crawler.runtime.RuntimeContext;
import com.particle.global.crawler.action.ActionResult;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 提取标题动作
 * <p>
 * 从页面中提取标题文本，使用 driver.getTitle() 方法获取页面标题。
 * </p>
 * @author yangwei
 * @since 2026/05/12 13:00
 */
@Slf4j
@Data
public class ExtractTitleAction extends ExtractBaseAction {

    public ExtractTitleAction() {
        setType(ActionType.EXTRACT_TITLE);
    }

    @Override
    public ActionResult doExecute(RuntimeContext context) {

        log.info("提取标题");
        String title = context.getDriver().getTitle();
        return ActionResult.success("提取标题成功", title);
    }


    public static ExtractTitleAction create() {
        return new ExtractTitleAction();
    }
}
