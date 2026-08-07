package com.particle.global.crawler.runtime;

import com.particle.global.crawler.action.ActionResult;
import com.particle.global.crawler.action.BaseAction;
import com.particle.global.crawler.action.CrawlAction;
import com.particle.global.crawler.exception.ActionException;
import com.particle.global.crawler.pipeline.CrawlPipeline;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * Pipeline 执行器
 *
 * @author yangwei
 * @since 2026/05/12 13:00
 */
@Slf4j
public class RuntimeExecutor {

    /**
     * 执行 Pipeline
     *
     * @param pipeline        Pipeline
     * @param runtimeContext   运行时上下文
     */
    public void execute(CrawlPipeline pipeline, RuntimeContext runtimeContext) {
        // 每次执行创建新的结果列表（线程安全）
        List<ActionResult> results = new ArrayList<>();
        long startTime = System.currentTimeMillis();


        // 验证 Pipeline
        validatePipeline(pipeline);

        // 执行所有 Action
        executeActions(pipeline.getActions(), runtimeContext, results);

        log.info("Pipeline 执行成功: {}, 耗时: {}ms", pipeline.getName(), System.currentTimeMillis() - startTime);

    }

    /**
     * 验证 Pipeline
     */
    private void validatePipeline(CrawlPipeline pipeline) {
        if (pipeline == null) {
            throw new ActionException("Pipeline 不能为空");
        }
        if (pipeline.getActions() == null || pipeline.getActions().isEmpty()) {
            throw new ActionException("Pipeline 至少需要一个 Action");
        }
    }

    /**
     * 执行 Action 列表
     */
    private Object executeActions(List<CrawlAction> actions, RuntimeContext runtimeContext, List<ActionResult> results) {
        Object lastResult = null;

        for (CrawlAction action : actions) {
            // 检查是否已取消
            if (runtimeContext.getIsCancel() != null && runtimeContext.getIsCancel()) {
                throw new ActionException("Pipeline 执行被取消");
            }

            ActionResult result = executeAction(action, runtimeContext, results);

            // 检查流控信号
            if (result.isReturn()) {
                log.debug("收到 return 信号，退出 Pipeline");
                break;
            }

            lastResult = result.getData();
        }

        return lastResult;
    }

    /**
     * 执行单个 Action
     */
    private ActionResult executeAction(CrawlAction action, RuntimeContext runtimeContext, List<ActionResult> results) {
        // 执行 Action
        try {

            // enabled 检查在调度层处理
            if (action instanceof BaseAction) {
                BaseAction baseAction = (BaseAction) action;
                if (!Boolean.TRUE.equals(baseAction.getEnabled())) {
                    log.debug("Action 已禁用，跳过: {}",
                            baseAction.getName() != null ? baseAction.getName() : action.getClass().getSimpleName());
                    return ActionResult.skipped();
                }
            }

            log.debug("执行 Action: {}", action.getClass().getSimpleName());
            ActionResult result = action.execute(runtimeContext);
            results.add(result);

            if (!result.getIsSuccess()) {
                log.warn("Action 执行失败: {}, 错误: {}",
                        action.getClass().getSimpleName(), result.getMessage());
            }

            return result;
        } catch (Exception e) {
            ActionResult failedResult = ActionResult.fail(e.getMessage());
            results.add(failedResult);
            throw new ActionException("Action 执行异常: " + action.getClass().getSimpleName(), e);
        }
    }
}
