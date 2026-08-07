import type { Node } from '@vue-flow/core'
import type { Edge } from '@vue-flow/core'
import type { ViewportTransform } from '@vue-flow/core'

// ==================== graphDataJson 存储结构 ====================

/**
 * 工作流图数据
 * graphDataJson 的顶层存储结构
 * 后端无定义，仅前端使用
 */
export interface WorkflowGraphData {
    definition: VueFlowGraphData
}

// ==================== VueFlow 原生格式 ====================

/**
 * VueFlow 原生格式 — 通过 toObject() 获取的数据
 *
 * 前端画布直接操作的数据格式
 * 保存时需要转换为 WorkflowDefinition
 */
export interface VueFlowGraphData {
    nodes: Node[]
    edges: Edge[]
    viewport: ViewportTransform
}
