<script setup name="WorkflowDefinitionHistoryWorkflowEditPage" lang="ts">
/**
 * 工作流定义历史画布编辑页面
 */
import {onMounted, reactive, ref} from 'vue'
import {
  detail as workflowDefinitionHistoryDetailApi,
  update as workflowDefinitionHistoryUpdateApi,
} from "../../../api/definition/admin/workflowDefinitionHistoryAdminApi";
import {initData} from "../../../../../../global/pc/common/vueflowWorkflow/tools/vueFlowTools";
import {showMsg} from "../../../../../../global/pc/element-plus/ElmessageTools";
import {
  execute,
  executeFromNode,
  executeUpToNode,
  getExecutionInfo
} from '../../../api/execution/front/workflowExecutionApi';
import { createExecutionStore } from '../../../../../../global/pc/common/vueflowWorkflow/composables/useExecutionStore'
import type {VueFlowGraphData, WorkflowGraphData} from "../../../../../../global/pc/common/vueflowWorkflow/workflow";
import type {VueFlowStore} from "@vue-flow/core";
const createdExecutionStore = createExecutionStore('execution')
const executionStore = createdExecutionStore()

let alert = showMsg
// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 加载数据初始化参数,路由传参
  workflowDefinitionHistoryId: {
    type: String,
    required: true
  },
  // 用于绑定执行实例
  workflowExecutionId: {
    type: String,
    required: false
  },
})
// 属性
const reactiveData = reactive({
  // 表单
  form: {
    id: props.workflowDefinitionHistoryId,
    workflowDefinitionId: null,
    workflowDefinitionVersion: null,
    graphDataJson: '',
    configJson: '',
    isPublish: false,
    // 版本默认为1，在数据初始化后会设置
    version: 1
  },
  // 表单数据对象
  formData: {},
})
// 保存时调用 — 接收 VueFlow 原始数据
const onSave = ({command, graphData}:{command: string, graphData: VueFlowGraphData}) => {
  if (command === 'saveDraft') {
    reactiveData.form.isPublish = false
  }else if(command === 'saveAndPublish'){
    reactiveData.form.isPublish = true
  }else{
    alert('不支持的操作','error')
    return
  }
  // 将原始数据转为 workflowData
  let workflowGraphData = {
    definition: graphData
  }
  let workflowGrapDataStr = JSON.stringify(workflowGraphData)
  reactiveData.form.graphDataJson = workflowGrapDataStr
  return workflowDefinitionHistoryUpdateApi(reactiveData.form)
      .then(res => {
        // 提示保存成功
        if (reactiveData.form.isPublish) {
          alert('保存并发布成功，已自动转为查看模式');
        }else{
          alert('保存草稿成功');
        }

        reactiveData.form.version = reactiveData.form.version + 1
      })
}
const instance = ref()


// 自动保存草稿（草稿模式下执行前调用，保存成功后再执行）
// 返回 Promise，保存成功后 resolve
const autoSaveDraft = (graphData: VueFlowGraphData) => {
  if (reactiveData.form.isPublish) {
    return Promise.resolve()
  }
  let workflowGraphData = {
    definition: graphData
  };
  let workflowGrapDataStr = JSON.stringify(workflowGraphData)
  reactiveData.form.graphDataJson = workflowGrapDataStr
  reactiveData.form.isPublish = false
  return workflowDefinitionHistoryUpdateApi(reactiveData.form).then(res => {
    // 草稿保存成功，version 自增
    reactiveData.form.version = reactiveData.form.version + 1
  })
}

const onRun = ({command, selectedNodeIds, graphData}:{command: string, selectedNodeIds: string[], graphData: VueFlowGraphData}) => {
  // 根据不同执行类型构建命令并调用
  // 草稿模式下先保存再执行，确保后端拿到最新 graphData
  if (command === 'runAll') {
    return autoSaveDraft(graphData).then(() => {
    // 全部执行
    const cmd = {
      definitionId: reactiveData.form.workflowDefinitionId,
      historyId: reactiveData.form.id,
      triggerType: 'MANUAL',
      sync: false
    }
    executionStore.resetAll()
    execute(cmd).then(res => {
      const executionId = res.data.data.executionId
      executionStore.execution.statusValue = 'RUNNING'
      executionStore.startPolling(getExecutionInfo, executionId, 5000, (data) => {
        if (data.statusValue === 'COMPLETED') {
          showMsg('工作流执行成功')
        } else if (['FAILED', 'STOPPED'].includes(data.statusValue)) {
          showMsg('工作流执行失败: ' + (data.errorMsg || '未知错误'), 'error')
        }
      })
    }).catch(err => {
      showMsg('启动执行失败: ' + (err.message || '未知错误'), 'error')
    })
    })
  }

  if (command === 'runFromNode') {
    if (!selectedNodeIds || selectedNodeIds.length === 0) {
      showMsg('请先选择一个节点', 'warning')
      return
    }
    return autoSaveDraft(graphData).then(() => {
    const cmd = {
      definitionId: reactiveData.form.workflowDefinitionId,
      historyId: reactiveData.form.id,
      triggerType: 'MANUAL',
      startNodeId: selectedNodeIds[0],
      copiedExecutionId: executionStore.lastExecutionId,
      sync: false
    }
    executionStore.resetForRunFromNode([], cmd.startNodeId)
    executeFromNode(cmd).then(res => {
      const executionId = res.data.data.executionId
      executionStore.execution.statusValue = 'RUNNING'
      executionStore.startPolling(getExecutionInfo, executionId, 5000, (data) => {
        if (data.statusValue === 'COMPLETED') {
          showMsg('节点执行成功')
        } else if (['FAILED', 'STOPPED'].includes(data.statusValue)) {
          showMsg('节点执行失败: ' + (data.errorMsg || '未知错误'), 'error')
        }
      })
    }).catch(err => {
      showMsg('启动执行失败: ' + (err.message || '未知错误'), 'error')
    })
    })

  }
  // 运行到此节点
  if (command === 'runToNode') {
    if (!selectedNodeIds || selectedNodeIds.length === 0) {
      showMsg('请先选择一个节点', 'warning')
      return
    }
    return autoSaveDraft(graphData).then(() => {
      const cmd = {
        definitionId: reactiveData.form.workflowDefinitionId,
        historyId: reactiveData.form.id,
        triggerType: 'MANUAL',
        endNodeId: selectedNodeIds[0],
        copiedExecutionId: executionStore.lastExecutionId,
        sync: false
      }
      executionStore.resetForRunToNode([], cmd.endNodeId)
      executeUpToNode(cmd).then(res => {
        const executionId = res.data.data.executionId
        executionStore.execution.statusValue = 'RUNNING'
        executionStore.startPolling(getExecutionInfo, executionId, 5000, (data) => {
          if (data.statusValue === 'COMPLETED') {
            showMsg('节点执行成功')
          } else if (['FAILED', 'STOPPED'].includes(data.statusValue)) {
            showMsg('节点执行失败: ' + (data.errorMsg || '未知错误'), 'error')
          }
        })
      }).catch(err => {
        showMsg('启动执行失败: ' + (err.message || '未知错误'), 'error')
      })
    })

  }
  if (command === 'runSingleNode') {
    if (!selectedNodeIds || selectedNodeIds.length === 0) {
      showMsg('请先选择一个节点', 'warning')
      return
    }
    return autoSaveDraft(graphData).then(() => {
    // 单节点执行：startNodeId = endNodeId
    const cmd = {
      definitionId: reactiveData.form.workflowDefinitionId,
      historyId: reactiveData.form.id,
      triggerType: 'MANUAL',
      startNodeId: selectedNodeIds[0],
      endNodeId: selectedNodeIds[0],
      copiedExecutionId: executionStore.lastExecutionId,
      sync: false
    }
    executionStore.resetForRunFromNode([], cmd.startNodeId)
    executeFromNode(cmd).then(res => {
      const executionId = res.data.data.executionId
      executionStore.execution.statusValue = 'RUNNING'
      executionStore.startPolling(getExecutionInfo, executionId, 5000, (data) => {
        if (data.statusValue === 'COMPLETED') {
          showMsg('节点执行成功')
        } else if (['FAILED', 'STOPPED'].includes(data.statusValue)) {
          showMsg('节点执行失败: ' + (data.errorMsg || '未知错误'), 'error')
        }
      })
    }).catch(err => {
      showMsg('启动执行失败: ' + (err.message || '未知错误'), 'error')
    })
    })

  }

  showMsg('不支持的执行类型', 'error')
}
// 实例初始化完成
const onUsedVueFlowReady = (usedVueFlow: VueFlowStore) => {
  instance.value = usedVueFlow
  // 获取数据
  workflowDefinitionHistoryDetailApi({id: props.workflowDefinitionHistoryId})
  .then(res => {
    let workflowGraphDataStr = res.data.data.graphDataJson
    reactiveData.form.version = res.data.data.version
    reactiveData.form.workflowDefinitionId = res.data.data.workflowDefinitionId
    reactiveData.form.workflowDefinitionVersion = res.data.data.workflowDefinitionVersion
    reactiveData.form.isPublish = res.data.data.isPublish
    if (workflowGraphDataStr) {
      let workflowGraphData: WorkflowGraphData = JSON.parse(workflowGraphDataStr)
      if (workflowGraphData) {
        // 使用 initData 直接初始化画布（VueFlow 原始格式）
        initData(workflowGraphData.definition, usedVueFlow)
      }
    }
  })
}
onMounted(()=>{
  if (props.workflowExecutionId) {
    executionStore.resetAll()
    const executionId = props.workflowExecutionId
    executionStore.startPolling(getExecutionInfo, executionId, 5000, (data) => {
      if (data.statusValue === 'COMPLETED') {
        showMsg('工作流执行成功')
      } else if (['FAILED', 'STOPPED'].includes(data.statusValue)) {
        showMsg('工作流执行失败: ' + (data.errorMsg || '未知错误'), 'error')
      }
    })
  }
})
</script>
<template>
  <PtWorkflowEditor :onSave="onSave"
                    :onRun="onRun"
                    :onUsedVueFlowReady="onUsedVueFlowReady"
                    :isPublish="reactiveData.form.isPublish"
                    :editable="!reactiveData.form.isPublish"
                    :workflowExecutionId="workflowExecutionId"
  />
</template>


<style scoped>

</style>
