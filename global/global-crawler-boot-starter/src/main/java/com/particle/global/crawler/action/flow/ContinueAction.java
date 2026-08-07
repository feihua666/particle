package com.particle.global.crawler.action.flow;

import com.particle.global.crawler.action.ActionResult;

import com.particle.global.crawler.common.constants.ActionType;
import com.particle.global.crawler.runtime.RuntimeContext;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 跳过本次迭代 Action
 * <p>
 * 在循环中使用时，跳过当前迭代，进入下一次迭代。
 * </p>
 *
 * @author yangwei
 * @since 2026/05/12 13:00
 */
@Slf4j
@Data
public class ContinueAction extends FlowBaseAction {

    public ContinueAction() {
        setType(ActionType.FLOW_CONTINUE);
    }

    @Override
    public ActionResult doExecute(RuntimeContext context) {
        log.info("执行 Continue Action");
        return ActionResult.continueLoop();
    }


    public static ContinueAction create() {
        return new ContinueAction();
    }

}
