package com.particle.global.crawler.action;

import lombok.Data;

/**
 * Action 执行结果
 * <p>
 * 封装单个 Action 的执行结果，支持流控信号。
 * </p>
 *
 * @author yangwei
 * @since 2026/05/12 13:00
 */
@Data
public class ActionResult {

    /**
     * 是否成功
     */
    private Boolean isSuccess;

    /**
     * 执行结果数据
     */
    private Object data;

    /**
     * 错误信息
     */
    private String message;

    /**
     * 流控信号
     */
    private FlowControl flowControl;

    /**
     * 流控类型枚举
     */
    public enum FlowControl {
        /**
         * 正常执行
         */
        NONE,

        /**
         * 退出循环
         */
        BREAK,

        /**
         * 跳过本次迭代
         */
        CONTINUE,

        /**
         * 直接返回（退出整个 Pipeline）
         */
        RETURN,
        /**
         * 跳过（不执行）
         */
        SKIPPED
    }

    /**
     * 创建成功结果
     */
    public static ActionResult success() {
        return create(true, null, null, FlowControl.NONE);
    }

    /**
     * 创建成功结果（带消息）
     */
    public static ActionResult success(String message) {
        return create(true, null, message, FlowControl.NONE);
    }

    /**
     * 创建成功结果（带数据和消息）
     */
    public static ActionResult success(String message, Object data) {
        return create(true, data, message, FlowControl.NONE);
    }

    /**
     * 创建失败结果
     */
    public static ActionResult fail(String message) {
        return create(false, null, message, FlowControl.NONE);
    }

    /**
     * 创建退出循环信号
     */
    public static ActionResult breakLoop() {
        return create(true, null, "break", FlowControl.BREAK);
    }

    /**
     * 创建跳过本次迭代信号
     */
    public static ActionResult continueLoop() {
        return create(true, null, "continue", FlowControl.CONTINUE);
    }

    /**
     * 创建直接返回信号
     */
    public static ActionResult returnPipeline() {
        return create(true, null, "return", FlowControl.RETURN);
    }

    /**
     * 创建跳过（不执行）信号
     */
    public static ActionResult skipped() {
        return create(true, null, "skipped", FlowControl.SKIPPED);
    }
    /**
     * 判断是否为退出循环信号
     */
    public boolean isBreak() {
        return flowControl == FlowControl.BREAK;
    }

    /**
     * 判断是否为跳过本次迭代信号
     */
    public boolean isContinue() {
        return flowControl == FlowControl.CONTINUE;
    }

    /**
     * 判断是否为直接返回信号
     */
    public boolean isReturn() {
        return flowControl == FlowControl.RETURN;
    }

    /**
     * 判断是否有流控信号
     */
    public boolean hasFlowControl() {
        return flowControl != null && flowControl != FlowControl.NONE;
    }

    /**
     * 私有构造方法
     */
    private static ActionResult create(Boolean success,
                                       Object data,
                                       String message,
                                       FlowControl flowControl) {
        ActionResult result = new ActionResult();
        result.setIsSuccess(success);
        result.setData(data);
        result.setMessage(message);
        result.setFlowControl(flowControl);
        return result;
    }
}
