package com.particle.global.crawler.action.flow;

import com.particle.global.crawler.action.ActionResult;
import com.particle.global.crawler.action.CrawlAction;

import com.particle.global.crawler.common.constants.ActionType;
import com.particle.global.crawler.runtime.RuntimeContext;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 重试 Action
 * <p>
 * 当内部 Action 执行失败时，自动重试指定次数。
 * </p>
 *
 * @author yangwei
 * @since 2026/05/12 13:00
 */
@Slf4j
@Data
public class RetryAction extends FlowBaseAction {

    public RetryAction() {
        setType(ActionType.FLOW_RETRY);
    }

    /**
     * 最大重试次数
     */
    private Integer maxAttempts = 3;

    /**
     * 重试间隔（毫秒）
     */
    private Long delayMs = 1000L;

    /**
     * 要执行的动作列表
     */
    private List<CrawlAction> actions;

    @Override
    public ActionResult doExecute(RuntimeContext context) {
        if (actions == null || actions.isEmpty()) {
            return ActionResult.fail("Retry Action - 动作列表为空");
        }

        ActionResult lastResult = null;

        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            log.debug("Retry Action - 第 {}/{} 次尝试", attempt, maxAttempts);
            lastResult = null;

            boolean failed = false;
            for (CrawlAction action : actions) {
                ActionResult result = action.execute(context);

                // 流控信号直接透传，不重试
                if (result.hasFlowControl()) {
                    return result;
                }

                if (!result.getIsSuccess()) {
                    lastResult = result;
                    failed = true;
                    break; // 本次执行失败，准备重试
                }

                lastResult = result;
            }

            if (!failed) {
                // 全部成功
                if (attempt > 1) {
                    log.info("Retry Action - 第 {} 次尝试成功", attempt);
                }
                return lastResult;
            }

            // 执行失败，判断是否继续重试
            log.warn("Retry Action - 第 {}/{} 次失败: {}",
                    attempt, maxAttempts,
                    lastResult != null ? lastResult.getMessage() : "未知");

            if (attempt < maxAttempts) {
                sleep();
            }
        }

        log.error("Retry Action - 重试耗尽 maxAttempts={}", maxAttempts);
        return ActionResult.fail("重试耗尽(" + maxAttempts + "次): "
                + (lastResult != null ? lastResult.getMessage() : "未知错误"));
    }

    /**
     * 休眠
     */
    private void sleep() {
        try {
            Thread.sleep(delayMs);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("重试等待被中断", e);
        }
    }


    /**
     * 创建重试 Action
     * @param maxAttempts
     * @param delayMs
     * @return
     */
    public static RetryAction create(Integer maxAttempts,Long delayMs) {
        RetryAction retryAction = new RetryAction();
        retryAction.setMaxAttempts(maxAttempts);
        retryAction.setDelayMs(delayMs);
        return retryAction;
    }
}
