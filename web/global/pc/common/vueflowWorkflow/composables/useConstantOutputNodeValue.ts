import { computed} from 'vue'
import type {VueFlowStore} from "@vue-flow/core";
import {PortDefinitionName} from "../workflow/enums.ts";
import {getPortData} from "../tools/workflowTools.ts";

/**
 * 常量输出节点值管理 composable
 * 自动处理 inputPorts 的数据并展示
 *
 * @param id 节点 ID
 * @param data 节点数据
 * @param updateNodeData VueFlow 的更新方法
 * @returns value 计算属性
 */


/**
 * 常量输出节点值管理，目前仅适用于单 input，且输入端口为 input
 * @param id
 * @param props
 * @param updateNodeData
 * @param usedVueFlow
 */
export function useConstantOutputNodeValue(
    id: string,
    // 直接传data不是响应式的
    // data: WorkflowNodeData,
    props,
    updateNodeData: (id: string, data: any) => void,
    usedVueFlow: VueFlowStore,
    getFormatter?: (val: any) => any
)  {

  const value = computed({
    get: () => {
      const val = getPortData(props.data.inputPorts, PortDefinitionName.INPUT) ?? null
      return getFormatter ? getFormatter(val) : val
    },
    set: (val) => {
    }
  })
  return { value }
}
