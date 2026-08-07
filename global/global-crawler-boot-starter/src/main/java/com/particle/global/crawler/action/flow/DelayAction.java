package com.particle.global.crawler.action.flow;

import com.particle.global.crawler.common.constants.ActionType;
import com.particle.global.crawler.exception.ActionException;
import com.particle.global.crawler.runtime.RuntimeContext;

import com.particle.global.crawler.action.ActionResult;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 延迟执行动作
 * @author yangwei
 * @since 2026/05/12 13:00
 */
@Slf4j
@Data
public class DelayAction extends FlowBaseAction {

    public DelayAction() {
        setType(ActionType.FLOW_DELAY);
    }

    /**
     * 延迟时间（毫秒）
     */
    private Long millis;

    @Override
    public ActionResult doExecute(RuntimeContext context) {
        log.info("延迟执行: {}ms", millis);
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            // 恢复中断状态
            Thread.currentThread().interrupt();
            throw new ActionException("延迟操作被中断", e);
        }
        return ActionResult.success("延迟 " + millis + "ms 完成");
    }

    public static DelayAction create(Long millis) {
        DelayAction action = new DelayAction();
        action.setMillis(millis);
        return action;
    }

    public static DelayAction create() {
        return create(1000L);
    }
}
