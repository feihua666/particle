/**
 * workflow/ 统一导出
 *
 * 数据层：前后端数据契约
 */

// 枚举
export { NodeRole, NodeType, DataType } from './enums'

// 端口
export type { PortDefinition } from './PortDefinition'

// 运行时数据
export type { WorkflowNodeData } from './WorkflowNodeData'
export type { WorkflowEdgeData } from './WorkflowEdgeData'

// 图数据结构
export type {
    WorkflowGraphData,
    VueFlowGraphData,
} from './WorkflowGraphData'
// 工作流数据结构
export type {
    WorkflowData,
    WorkflowNode,
    WorkflowEdge
} from './WorkflowData'
