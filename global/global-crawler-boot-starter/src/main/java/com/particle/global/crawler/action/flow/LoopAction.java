package com.particle.global.crawler.action.flow;

import com.particle.global.crawler.action.ActionResult;
import com.particle.global.crawler.action.CrawlAction;
import com.particle.global.crawler.common.constants.ActionType;
import com.particle.global.crawler.common.constants.ReservedKeys;
import com.particle.global.crawler.runtime.RuntimeContext;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 循环 Action
 * <p>
 * 对列表中的每个元素执行一组 Action。
 * </p>
 *
 * @author yangwei
 * @since 2026/05/12 13:00
 */
@Slf4j
@Data
public class LoopAction extends FlowBaseAction {

    public LoopAction() {
        setType(ActionType.FLOW_LOOP);
    }

    /**
     * 循环项列表变量名
     */
    private String itemsKey;

    /**
     * 循环项列表（可以是变量名或直接是列表）
     */
    private Object items;

    /**
     * 最大迭代次数（防止无限循环）
     */
    private Integer maxIterations = 1000;

    /**
     * 循环体中的 Action 列表
     */
    private List<CrawlAction> actions;

    @Override
    public ActionResult doExecute(RuntimeContext context) {
        List<?> items = getItems(context);
        log.info("循环执行，size={}", items.size());
        int count = 0;

        for (Object item : items) {
            if (++count > maxIterations) {
                log.warn("Loop Action - 达到最大迭代次数: {}", maxIterations);
                break;
            }

            // 设置当前迭代项到上下文
            context.setVariable(ReservedKeys.LOOP_ITEM, item);
            context.setVariable(ReservedKeys.LOOP_INDEX, count - 1);

            boolean shouldBreak = false;
            boolean shouldContinue = false;

            for (CrawlAction action : actions) {
                ActionResult result = action.execute(context);

                if (result.isBreak()) {
                    shouldBreak = true;
                    break;  // 跳出内层循环
                }
                if (result.isContinue()) {
                    shouldContinue = true;
                    break;  // 跳出内层循环
                }
                if (!result.getIsSuccess()) {
                    return result;
                }
            }

            if (shouldBreak) {
                log.info("Loop Action - 收到 break 信号，退出循环");
                break;  // 跳出外层循环
            }
            if (shouldContinue) {
                log.info("Loop Action - 收到 continue 信号，跳过本次迭代");
                continue;  // 跳过本次迭代，进入下一次外层循环
            }
        }

        return ActionResult.success();
    }

    /**
     * 获取循环项列表
     */
    private List<?> getItems(RuntimeContext context) {
        if (items instanceof List) {
            return (List<?>) items;
        }
        if (itemsKey != null && !itemsKey.isEmpty()) {
            // 尝试从上下文获取
            Object value = context.getVariable(itemsKey);
            if (value instanceof List) {
                return (List<?>) value;
            }
        }
        return List.of();
    }


    /**
     * 创建 Loop Action
     * @param items 循环项列表,如果没有指定则使用 itemsKey 取数据，该数据优先
     * @param itemsKey 循环项列表变量名
     * @param actions
     * @return
     */
    public static LoopAction create(List<Object>  items,
                                    String itemsKey,
                                    List<CrawlAction> actions) {
        LoopAction loopAction = new LoopAction();
        loopAction.setItems(items);
        loopAction.setItemsKey(itemsKey);
        loopAction.setActions(actions);
        return loopAction;
    }
}
