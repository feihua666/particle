package com.particle.global.crawler.action;


import com.particle.global.crawler.runtime.RuntimeContext;
import lombok.Data;

/**
 * Action 基础抽象类
 * <p>
 * 所有 Action 的基类，提供通用的功能和属性。
 * </p>
 * @author yangwei
 * @since 2026/05/12 13:00
 */

@Data
public abstract class BaseAction implements CrawlAction {

    private String type;

    /**
     * Action 名称（可选，用于日志和调试）
     */
    private String name;

    /**
     * Action 描述（可选）
     */
    private String description;

    /**
     * 是否启用
     */
    private Boolean enabled = true;

    @Override
    public ActionResult execute(RuntimeContext context){

        beforeExecute(context);
        ActionResult result = doExecute(context);
        afterExecute(context, result);
        return result;
    }

    /**
     * 执行 Action
     * <p>
     * 子类必须实现此方法，执行 Action 的具体逻辑。
     * </p>
     *
     * @param context 运行时上下文
     * @return 执行结果
     */
    public abstract ActionResult doExecute(RuntimeContext context);


    /**
     * 执行前钩子方法
     * <p>
     * 子类可以重写此方法，在 execute 之前执行。
     * </p>
     *
     * @param context 运行时上下文
     */
    protected void beforeExecute(RuntimeContext context) {
        // 默认空实现
    }

    /**
     * 执行后钩子方法
     * <p>
     * 子类可以重写此方法，在 execute 之后执行。
     * </p>
     *
     * @param context 运行时上下文
     * @param result  执行结果
     */
    protected void afterExecute(RuntimeContext context, ActionResult result) {
        // 默认空实现
    }

    /**
     * 检查 Action 是否启用
     *
     * @return true 如果启用
     */
    protected boolean isEnabled() {
        return enabled != null && enabled;
    }

    /**
     * 创建成功结果
     *
     * @return 成功的 ActionResult
     */
    protected ActionResult success() {
        return ActionResult.success();
    }

    /**
     * 创建成功结果（带数据）
     *
     * @param data 结果数据
     * @return 成功的 ActionResult
     */
    protected ActionResult success(Object data) {
        return ActionResult.success(null, data);
    }

    /**
     * 创建失败结果
     *
     * @param message 错误消息
     * @return 失败的 ActionResult
     */
    protected ActionResult fail(String message) {
        return ActionResult.fail(message);
    }

}
