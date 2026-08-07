/**
 * 边运行时数据 (存在 edge.data 中)
 *
 * 与后端 WorkflowEdgeDataDTO 对应
 */
export interface WorkflowEdgeData {
    /** 边类型: DEFAULT(默认) 或 CONDITIONAL(条件分支) */
    edgeType?: 'DEFAULT' | 'CONDITIONAL'

    /** 条件表达式 (CONDITIONAL 类型时使用) */
    condition?: string
}
