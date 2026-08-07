import axios, { type AxiosPromise } from 'axios'
import type { Recordable } from '../../../../../common/tools/ObjectTools'

const workflowExecutionPrefix = '/api/workflow/execution'

// ==================== 请求参数类型 ====================

export interface ExecuteParams extends Recordable {
  definitionId: string
  historyId?: string
  triggerType: string
  inputVariables?: Record<string, any>
  sync?: boolean
}

export interface ExecuteFromNodeParams extends ExecuteParams {
  startNodeId: string
  endNodeId?: string
  copiedExecutionId?: string
}

// ==================== 执行 API ====================

/**
 * 执行工作流
 * sync=true：阻塞直到完成，返回 executionId
 * sync=false：立即返回 executionId，异步执行
 */
export function execute(data: ExecuteParams): AxiosPromise {
  return axios.post(workflowExecutionPrefix + '/execute', data)
}

/**
 * 运行到此节点
 * sync=true：阻塞直到执行到目标节点完成，返回 executionId
 * sync=false：立即返回 executionId，异步执行
 */
export function executeUpToNode(data: ExecuteParams & { endNodeId: string }): AxiosPromise {
  return axios.post(workflowExecutionPrefix + '/execute-up-to-node', data)
}

/**
 * 从此节点运行（替换原断点续跑 resume-from-node）
 * 每次调用创建新的执行实例，可选择性拷贝已有实例的数据。
 * sync=true：阻塞直到完成，返回 executionId
 * sync=false：立即返回 executionId，异步执行
 */
export function executeFromNode(data: ExecuteFromNodeParams): AxiosPromise {
  return axios.post(workflowExecutionPrefix + '/execute-from-node', data)
}

// ==================== 执行控制 API ====================

/**
 * 暂停执行
 */
export function pause(executionId: string): AxiosPromise {
  return axios.post(workflowExecutionPrefix + `/${executionId}/pause`)
}

/**
 * 恢复执行
 */
export function resume(executionId: string): AxiosPromise {
  return axios.post(workflowExecutionPrefix + `/${executionId}/resume`)
}

/**
 * 停止执行
 */
export function stop(executionId: string): AxiosPromise {
  return axios.post(workflowExecutionPrefix + `/${executionId}/stop`)
}

// ==================== 查询 API ====================

/**
 * 查询执行状态（含节点执行状态）
 */
export function getExecutionInfo(executionId: string): AxiosPromise {
  return axios.get(workflowExecutionPrefix + `/${executionId}`)
}
