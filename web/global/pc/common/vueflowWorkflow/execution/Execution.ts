/**
 * 执行相关类型定义
 *
 * 与后端 VO 对齐：
 * - ExecutionVO → ExecutionInfo（基础，仅 executionId）
 * - ExecutionDetailVO → ExecutionDetailInfo（详情，含状态/时间/节点执行）
 * - NodeResultVO → NodeExecutionInfo（节点执行结果）
 *
 * 文件位置: global/pc/common/vueflowWorkflow/execution/
 */

// ==================== 枚举 ====================

/** 执行状态枚举 */
export enum ExecutionStatusEnum {
  /** 待执行 */
  PENDING = 'PENDING',
  /** 运行中 */
  RUNNING = 'RUNNING',
  /** 已完成 */
  COMPLETED = 'COMPLETED',
  /** 失败 */
  FAILED = 'FAILED',
  /** 已暂停 */
  PAUSED = 'PAUSED',
  /** 已停止 */
  STOPPED = 'STOPPED'
}

/** 触发类型枚举 */
export enum TriggerTypeEnum {
  /** 手动触发 */
  MANUAL = 'MANUAL',
  /** API 触发 */
  API = 'API',
  /** 定时触发 */
  SCHEDULE = 'SCHEDULE'
}

/** 执行状态值 */
export type ExecutionStatus = `${ExecutionStatusEnum}` | null

/** 触发类型值 */
export type TriggerType = `${TriggerTypeEnum}`

/** 终态集合 */
export const TERMINAL_STATUSES: ExecutionStatus[] = [
  ExecutionStatusEnum.COMPLETED,
  ExecutionStatusEnum.FAILED,
  ExecutionStatusEnum.STOPPED
]

// ==================== Node ====================

/**
 * 节点执行结果
 *
 * 对应后端: NodeResultVO
 */
export interface NodeExecutionDetail {
  /** 节点 ID */
  nodeId: string
  /** 节点类型 */
  nodeType?: string
  /** 执行状态值 */
  statusValue: ExecutionStatus
  /** 执行状态名称 */
  statusName?: string| null
  /** 输出数据（端口名 → 值） */
  output?: Record<string, any>
  /** 错误信息 */
  errorMsg?: string
  /** 开始时间 (ISO 8601) */
  startAt?: string
  /** 结束时间 (ISO 8601) */
  endAt?: string
}

// ==================== Execution ====================

/**
 * 基础执行信息
 *
 * 对应后端: ExecutionVO（仅 executionId）
 * 用于 execute 接口的返回值。
 */
export interface Execution {
  /** 执行记录 ID */
  executionId?: string
}

/**
 * 执行详情信息
 *
 * 对应后端: ExecutionDetailVO extends ExecutionVO
 * 用于 getExecutionInfo 接口的返回值。
 */
export interface ExecutionDetail extends Execution {
  /** 工作流定义 ID */
  definitionId?: string
  /** 执行状态值 */
  statusValue: ExecutionStatus
  /** 执行状态名称 */
  statusName?: string |  null
  /** 触发类型值 */
  triggerTypeValue?: TriggerType
  /** 触发类型名称 */
  triggerTypeName?: string
  /** 当前节点 ID（运行时有效） */
  currentNodeId?: string
  /** 开始时间 (ISO 8601) */
  startAt?: string
  /** 结束时间 (ISO 8601) */
  endAt?: string
  /** 错误信息 */
  errorMsg?: string
  /** 数据来源执行 ID */
  copiedExecutionId?: string
  /** 节点执行状态（key = nodeId） */
  nodeExecutions?: Record<string, NodeExecutionDetail>
}

