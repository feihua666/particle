import type { WorkflowNodeData } from './WorkflowNodeData'
import type { WorkflowEdgeData } from './WorkflowEdgeData'
// ==================== 工作流数据结构 ====================
/**
 * 工作流数据
 * 对应后端的工作流数据
 */
export interface WorkflowData {
    nodes: WorkflowNode[];
    edges: WorkflowEdge[];
}

/**
 * 定义节点
 * 对应后端的工作流节点数据
 */
export interface WorkflowNode {
    id: string;
    data: WorkflowNodeData;
}

/**
 * 定义边
 * 对应后端的工作流边数据
 */
export interface WorkflowEdge {
    id: string;
    data: WorkflowEdgeData;
}
