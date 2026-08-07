import { computed } from 'vue'
import type {VueFlowStore} from "@vue-flow/core";
import {PortDefinitionName} from "../workflow/enums.ts";
import type {PortDefinition} from "../workflow";
import {getPortData, updatePortData} from "../tools/workflowTools.ts";

/**
 * 常量输入节点值管理 composable
 * 自动处理 valuePorts → outputPorts 的数据流
 *
 * @param id 节点 ID
 * @param data 节点数据
 * @param updateNodeData VueFlow 的更新方法
 * @returns value 计算属性
 */

/**
 *
 * @param id
 * @param props
 * @param updateNodeData
 * @param usedVueFlow
 */
export function useConstantInputNodeValue(
    id: string,
    // 直接传data不是响应式的
    // data: WorkflowNodeData,
    props,
    updateNodeData: (id: string, data: any) => void,
    usedVueFlow: VueFlowStore
)  {

  const value = computed({
    get: () => {
      return getPortData(props.data.valuePorts, PortDefinitionName.CONTENT) ?? ''

    },
    set: (val) => {
      // 避免重复更新（非常关键）
      const oldVal = getPortData(props.data.valuePorts, PortDefinitionName.CONTENT)
      if (oldVal === val) return
      updateNodeData(id, {
        // 本地值（UI状态）
        valuePorts: updatePortData(props.data.valuePorts, PortDefinitionName.CONTENT, val),
        // 输出值（向下游传播）
        outputPorts: updatePortData(props.data.outputPorts, PortDefinitionName.OUTPUT, val)
      })
      // 数据变更后，没有自动更新下游节点的 inputPorts，需要执行节点，或者尝试删除连接线再重新连接
    }
  })

  return { value }
}
