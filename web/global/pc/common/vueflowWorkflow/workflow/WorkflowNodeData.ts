import type { NodeRole, NodeType } from './enums'
import type { PortDefinition } from './PortDefinition'

/**
 * 节点运行时数据 (存在 node.data 中)
 *
 * 三种端口：
 * - inputPorts: 声明需要什么输入，值由引擎从上游注入
 * - outputPorts: 声明产生什么输出，值由 executor 执行后写入
 * - valuePorts: 节点自身携带的预设值，由用户在画布上填写
 *
 * config 只存行为配置（method、delay、operation 等）
 *
 * 与后端 WorkflowNodeDataDTO 对应
 */
export interface WorkflowNodeData {
    // ==================== UI 展示相关 ====================
    /** 节点类型名称（如"文本节点"），用于标识节点类型 */
    typeName: string

    /** 节点自定义名称/标题（如"系统提示词"），用户可编辑 */
    name: string

    // ==================== DAG 执行相关 ====================
    /** 节点角色: DATA / PROCESSOR */
    nodeRole: NodeRole

    /** 节点执行类型 */
    nodeType: NodeType

    /**
     * 节点行为配置（不含数据值，数据值走 valuePorts）
     *
     * PROCESSOR 节点 config 示例 (HTTP):
     *   { "method": "POST", "headers": {} }
     */
    config?: Record<string, any>

    // ==================== 端口定义（IO 声明）====================
    /** 输入端口声明（无值，引擎自动注入） */
    inputPorts?: Record<string, PortDefinition>

    /** 输出端口声明（无值，executor 执行后写入） */
    outputPorts?: Record<string, PortDefinition>

    /** 预设值端口（有值，用户在画布上填写） */
    valuePorts?: Record<string, PortDefinition>
}
