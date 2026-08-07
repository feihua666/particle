package com.particle.global.workflow.enums;

/**
 * 工作流触发类型枚举
 * 
 * @author particle
 * @since 2026-04-29
 */
public enum WorkflowTriggerType {

    /**
     * 手动触发
     * 用户在前端界面点击执行按钮触发
     */
    MANUAL("MANUAL", "手动触发"),

    /**
     * 定时触发
     * 通过定时任务调度器自动触发
     */
    SCHEDULED("SCHEDULED", "定时触发"),

    /**
     * API 触发
     * 通过外部 API 调用触发
     */
    API("API", "API触发"),

    /**
     * 事件触发
     * 由系统事件（如数据变更、消息到达）触发
     */
    EVENT("EVENT", "事件触发"),

    /**
     * Webhook 触发
     * 由外部系统的 Webhook 回调触发
     */
    WEBHOOK("WEBHOOK", "Webhook触发");

    private final String code;
    private final String description;

    WorkflowTriggerType(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    /**
     * 根据 code 获取枚举
     * 
     * @param code 触发类型代码
     * @return 对应的枚举值，未找到返回 null
     */
    public static WorkflowTriggerType fromCode(String code) {
        if (code == null) {
            return null;
        }
        for (WorkflowTriggerType type : values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        return null;
    }
}
