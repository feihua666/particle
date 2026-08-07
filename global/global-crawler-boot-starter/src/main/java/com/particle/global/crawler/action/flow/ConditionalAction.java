package com.particle.global.crawler.action.flow;

import com.particle.global.crawler.common.constants.ActionType;
import com.particle.global.crawler.runtime.RuntimeContext;

import com.particle.global.crawler.action.ActionResult;
import com.particle.global.crawler.action.CrawlAction;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 条件 Action
 * <p>
 * 根据条件判断执行不同的 Action 分支。
 * </p>
 *
 * @author yangwei
 * @since 2026/05/12 13:00
 */
@Slf4j
@Data
public class ConditionalAction extends FlowBaseAction {

    public ConditionalAction() {
        setType(ActionType.FLOW_CONDITIONAL);
    }

    /**
     * 条件
     */
    private String expression;

    /**
     * 条件为真时执行的 Action 列表
     */
    private List<CrawlAction> thenActions;

    /**
     * 条件为假时执行的 Action 列表
     */
    private List<CrawlAction> elseActions;

    @Override
    public ActionResult doExecute(RuntimeContext context) {
        boolean condition = context.resolveCondition(expression);

        log.info("Conditional Action - 条件评估结果: {}", condition);

        List<CrawlAction> branch = condition ? thenActions : elseActions;

        if (branch == null || branch.isEmpty()) {
            return ActionResult.success();
        }

        for (CrawlAction action : branch) {
            ActionResult result = action.execute(context);
            // 流控信号直接透传，不拦截
            if (result.hasFlowControl() || !result.getIsSuccess()) {
                return result;
            }
        }

        return ActionResult.success();
    }

    public static ConditionalAction create(String expression, List<CrawlAction> thenActions, List<CrawlAction> elseActions) {
        ConditionalAction conditionalAction = new ConditionalAction();
        conditionalAction.setExpression(expression);
        conditionalAction.setThenActions(thenActions);
        conditionalAction.setElseActions(elseActions);
        return conditionalAction;
    }
}
