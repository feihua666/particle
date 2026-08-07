package com.particle.global.crawler.action.extract;

import com.particle.global.crawler.action.ActionResult;
import com.particle.global.crawler.action.BaseAction;
import com.particle.global.crawler.runtime.RuntimeContext;
import lombok.Data;

/**
 * 数据提取 Action 基类
 * <p>
 * 所有数据提取相关 Action 的基类。
 * </p>
 * @author yangwei
 * @since 2026/05/12 13:00
 */
@Data
public abstract class ExtractBaseAction extends BaseAction {

    /**
     * CSS 选择器
     */
    protected String selector;

    /**
     * 结果存储的变量名
     */
    protected String resultKey;

    @Override
    protected void afterExecute(RuntimeContext context, ActionResult result) {
        if (isValidResultKey()) {
            context.setVariable(resultKey, result.getData());
        }
        super.afterExecute(context, result);
    }

    /**
     * 检查选择器是否有效
     *
     * @return true 如果选择器有效
     */
    protected boolean isValidSelector() {
        return selector != null && !selector.isEmpty();
    }

    /**
     * 检查结果键是否有效
     *
     * @return true 如果结果键有效
     */
    protected boolean isValidResultKey() {
        return resultKey != null && !resultKey.isEmpty();
    }
}
