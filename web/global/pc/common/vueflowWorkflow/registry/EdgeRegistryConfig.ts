import type { Component } from 'vue'

/**
 * 边注册配置
 *
 * 单一配置源，包含两层元数据：
 * - ui: 显示元数据（工具栏、样式）
 * - data: 边类型元数据（DEFAULT / CONDITIONAL）
 *
 * 与后端 EdgeRegistryConfigDTO 对应
 */
export interface EdgeRegistryConfig {
    // ==================== 技术标识 ====================
    /** VueFlow 边类型标识，如 "default"、"conditional" */
    type: string

    /** Vue 组件 */
    component: Component

    // ==================== UI 配置（前端用，后端不关心） ====================
    ui: {
        /** 显示名称（如"默认连线"、"条件分支"） */
        typeName: string

        /** 图标 */
        icon?: string

        /** 描述 */
        description?: string

        /** 默认样式 */
        defaultStyle?: Record<string, any>

        /** 是否默认动画 */
        animated?: boolean
    }

    // ==================== Data 配置（前后端共享，后端需要） ====================
    data: {
        /** 边类型 */
        edgeType: 'DEFAULT' | 'CONDITIONAL'

        /** 是否需要配置条件 */
        hasCondition?: boolean
    }
}
