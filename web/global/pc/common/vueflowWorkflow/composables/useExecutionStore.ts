/**
 * 通用节点编辑器 — 执行状态 Store
 *
 * 与后端 ExecutionDetailVO / NodeResultVO 对齐。
 * 独立于节点配置数据（node.data），不随编辑器保存。
 *
 * 文件位置: global/pc/common/vueflowWorkflow/composables/
 */
import { ref } from 'vue'
import { defineStore } from 'pinia'
import type { ExecutionDetail, NodeExecutionDetail } from '../execution'
import { TERMINAL_STATUSES, ExecutionStatusEnum } from '../execution'

/**
 * 创建执行状态 Store（通用，不绑定特定编辑器）
 *
 * @param storeName Store 名称（默认 'execution'，多编辑器场景需区分）
 */
export function createExecutionStore(storeName: string = 'execution') {
  return defineStore(storeName, () => {

    // ==================== State ====================

    /** 当前执行实例信息 */
    const execution = ref<ExecutionDetail>({
      statusValue: null,
      statusName: null,
    })

    /** 每个节点的执行状态，key = nodeId */
    const nodeExecution = ref<Record<string, NodeExecutionDetail>>({})

    /** 上次执行返回的 executionId（用于"从此节点运行"复用上游数据） */
    const lastExecutionId = ref<string | undefined>(undefined)

    /** 轮询定时器 */
    let pollingTimer: ReturnType<typeof setInterval> | null = null

    // ==================== Actions ====================

    /**
     * 重置为空闲状态
     */
    function resetAll() {
      stopPolling()
      execution.value = {
        executionId: undefined,
        definitionId: undefined,
        statusValue: null,
        statusName: null,
        triggerTypeValue: undefined,
        triggerTypeName: undefined,
        currentNodeId: undefined,
        startAt: undefined,
        endAt: undefined,
        errorMsg: undefined,
        copiedExecutionId: undefined,
      }
      nodeExecution.value = {}
      lastExecutionId.value = undefined
    }

    /**
     * 重置全部节点状态（用于全量执行前）
     * @param allNodeIds 所有节点 ID 列表
     */
    function resetForFullExecution(allNodeIds: string[]) {
      const newNodeExecution: Record<string, NodeExecutionDetail> = {}
      for (const nodeId of allNodeIds) {
        newNodeExecution[nodeId] = {
          nodeId,
          statusValue: null,
          statusName: null,
        }
      }
      nodeExecution.value = newNodeExecution
    }

    /**
     * 重置节点状态：从指定节点开始执行（上游保留 CACHED）
     * @param allNodeIds 所有节点 ID 列表
     * @param startNodeId 起始节点 ID
     */
    function resetForRunFromNode(allNodeIds: string[], startNodeId: string) {
      const newNodeExecution: Record<string, NodeExecutionDetail> = {}
      for (const nodeId of allNodeIds) {
        const existing = nodeExecution.value[nodeId]
        if (nodeId === startNodeId || !existing?.statusValue) {
          newNodeExecution[nodeId] = {
            nodeId,
            statusValue: null,
            statusName: null,
          }
        } else if (existing) {
          newNodeExecution[nodeId] = { ...existing }
        } else {
          newNodeExecution[nodeId] = {
            nodeId,
            statusValue: null,
            statusName: null,
          }
        }
      }
      nodeExecution.value = newNodeExecution
    }

    /**
     * 重置节点状态：运行到指定节点为止
     * @param allNodeIds 所有节点 ID 列表
     * @param _endNodeId 目标节点 ID
     */
    function resetForRunToNode(allNodeIds: string[], _endNodeId: string) {
      const newNodeExecution: Record<string, NodeExecutionDetail> = {}
      for (const nodeId of allNodeIds) {
        newNodeExecution[nodeId] = {
          nodeId,
          statusValue: null,
          statusName: null,
        }
      }
      nodeExecution.value = newNodeExecution
    }

    /**
     * 更新单个节点状态
     */
    function updateNodeStatus(nodeId: string, update: Partial<NodeExecutionDetail>) {
      const existing = nodeExecution.value[nodeId]
      if (existing) {
        nodeExecution.value[nodeId] = { ...existing, ...update }
      } else {
        nodeExecution.value[nodeId] = {
          nodeId,
          statusValue: null,
          statusName: null,
          ...update,
        }
      }
    }

    /**
     * 从后端返回数据更新整个 Store
     *
     * 后端 getExecutionInfo 接口直接返回 ExecutionDetailVO（含 nodeExecutions 字段）
     */
    function updateFromServer(data: ExecutionDetail) {
      // 更新执行实例信息
      execution.value = { ...execution.value, ...data }
      if (data.executionId) {
        lastExecutionId.value = data.executionId
      }

      // 更新各节点执行状态
      if (data.nodeExecutions) {
        for (const [nodeId, info] of Object.entries(data.nodeExecutions)) {
          const existing = nodeExecution.value[nodeId]
          if (existing) {
            // nodeExecution.value[nodeId] = { ...existing, ...info }
            Object.assign(existing, info)
          } else {
            nodeExecution.value[nodeId] = info
          }
        }
      }
    }

    /**
     * 判断是否有活跃执行（运行中）
     */
    function isExecuting(): boolean {
      return [ExecutionStatusEnum.RUNNING, ExecutionStatusEnum.PENDING].includes(execution.value.statusValue)
    }

    /**
     * 判断某个节点是否正在运行
     */
    function isNodeRunning(nodeId: string): boolean {
      return nodeExecution.value[nodeId]?.statusValue === ExecutionStatusEnum.RUNNING
    }

    // ==================== Polling ====================

    /**
     * 开始轮询执行状态
     * @param getExecutionInfoFn 查询执行状态的 API 函数（返回 Promise，res.data.data 为 ExecutionDetailVO）
     * @param executionId 执行记录 ID
     * @param intervalMs 轮询间隔（毫秒），默认 1000
     * @param onResult 可选回调，每次轮询成功后执行
     */
    function startPolling(
      getExecutionInfoFn: (executionId: string) => Promise<any>,
      executionId: string,
      intervalMs: number = 1000,
      onResult?: (data: ExecutionDetail) => void,
    ) {
      stopPolling()

      const poll = async () => {
        try {
          const res = await getExecutionInfoFn(executionId)
          if (res.data?.data) {
            updateFromServer(res.data.data)
            onResult?.(res.data.data)

            // 终态时停止轮询
            if (TERMINAL_STATUSES.includes(res.data.data.statusValue)) {
              stopPolling()
            }
          }
        } catch (e) {
          console.error('轮询执行状态失败', e)
        }
      }

      // 立即执行一次
      poll()
      pollingTimer = setInterval(poll, intervalMs)
    }

    /**
     * 停止轮询
     */
    function stopPolling() {
      if (pollingTimer) {
        clearInterval(pollingTimer)
        pollingTimer = null
      }
    }

    return {
      // State
      execution,
      nodeExecution,
      lastExecutionId,
      // Actions
      resetAll,
      resetForFullExecution,
      resetForRunFromNode,
      resetForRunToNode,
      updateNodeStatus,
      updateFromServer,
      isExecuting,
      isNodeRunning,
      // Polling
      startPolling,
      stopPolling,
    }
  })
}
