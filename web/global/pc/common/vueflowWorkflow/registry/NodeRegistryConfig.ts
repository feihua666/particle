import type { Component } from 'vue'
import type { NodeGroupEnum } from './NodeGroupRegistryConfig'
import {DataType, type NodeRole, type NodeType} from '../workflow'
import type { PortDefinition } from '../workflow'
import {PortType} from "../workflow/enums.ts";

/**
 * 节点注册配置
 *
 * 单一配置源，但包含两层元数据：
 * - ui: 显示元数据（工具栏分组、图标、描述）
 * - data: DAG 元数据（节点角色、类型、端口定义）
 *
 * 与后端 NodeRegistryConfigDTO 对应
 */
export interface NodeRegistryConfig {
    // ==================== 技术标识 ====================
    /** VueFlow 组件类型标识，如 "textNode" */
    type: string

    /** Vue 组件 */
    component: Component

    // ==================== UI 配置（前端用，后端不关心） ====================
    ui: {
        /** 分组（工具栏分类） */
        group: NodeGroupEnum

        /** 显示名称（如"文本节点"） */
        typeName: string

        /** 图标 */
        icon?: string

        /** 描述 */
        description?: string

        /** 默认尺寸 */
        defaultWidth?: number
        defaultHeight?: number
    }

    // ==================== Data 配置（前后端共享，后端需要） ====================
    data: {
        /** 节点角色 */
        nodeRole: NodeRole

        /** 节点执行类型 */
        nodeType: NodeType

        /** 输入端口（声明式，无值） */
        inputPorts?: Record<string, PortDefinition>

        /** 输出端口（声明式，无值） */
        outputPorts?: Record<string, PortDefinition>

        /** 预设值端口（有默认值） */
        valuePorts?: Record<string, PortDefinition>

        /** 行为配置 */
        config?: Record<string, any>
    }
}
export const InputPortInputControl = {
    inputControl: { portType: PortType.CONTROL, dataType: DataType.ANY, description: '控制输入'},
}
export const OutputPortOutputControl = {
    outputControl: { portType: PortType.CONTROL, dataType: DataType.ANY, description: '控制输出'},
}
