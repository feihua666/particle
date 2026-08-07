package com.particle.global.crawler.action.flow;

import com.particle.global.crawler.action.ActionResult;

import com.particle.global.crawler.common.constants.ActionType;
import com.particle.global.crawler.runtime.RuntimeContext;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 退出循环 Action
 * <p>
 * 在循环中使用时，退出当前循环。
 * </p>
 *
 * @author yangwei
 * @since 2026/05/12 13:00
 */
@Slf4j
@Data
public class BreakAction extends FlowBaseAction {

    public BreakAction() {
        setType(ActionType.FLOW_BREAK);
    }

    @Override
    public ActionResult doExecute(RuntimeContext context) {
        log.info("执行 Break Action");
        return ActionResult.breakLoop();
    }

    public static BreakAction create() {
        return new BreakAction();
    }
}
