<script setup name="WorkflowDefinitionManageAddPage" lang="ts">
/**
 * http 通用配置
 */
import {computed, reactive, ref, watch} from 'vue'
import BaseNode from '../BaseNode.vue'
import {useVueFlow} from "@vue-flow/core";
import type {WorkflowNodeData} from "../../../workflow";
import {getPortData, updatePortData} from "../../../tools/workflowTools.ts";
import {PortDefinitionName} from "../../../workflow/enums.ts";
const { updateNodeData, onNodeDragStart } = useVueFlow()

const valuePortNameIsUseInput = 'isUseInput'

const props = withDefaults(defineProps<{
  id: string
  data: WorkflowNodeData
}>(), {})
// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
    delayMs: 1000,
    isUseInput: false,
  },
  // 表单数据对象
  formData: {},
})

const dragControl = {
  class: computed(()=> ({nodrag : true, nowheel : true}))
}
// 表单项
const formComps = ref(
    [
      {
        field: {
          name: 'delayMs',
          value: 1000
        },
        element: {
          comp: 'el-input-number',
          formItemProps: {
            label: '延迟时间',
            labelTips: '单位毫秒如：1秒=1000、10秒=10000',
          },
          compProps: {
            // 最小100毫秒
            min: 100,
            // 最大60秒
            max: 60000,

            ...dragControl
          }
        }
      },
      {
        field: {
          name: 'isUseInput',
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '优先输入',
            labelTips: '开启后优先使用连线输入的值作为延迟时间',
          },
          compProps: {
            activeText: '是',
            inactiveText: '否',
            ...dragControl
          }
        }
      },
    ]
)

watch(() => reactiveData.form.delayMs,
    (val) => {
      // 避免重复更新（非常关键）
      const oldVal = getPortData(props.data.valuePorts, PortDefinitionName.CONTENT)
      if (oldVal === val) return
      updateNodeData(props.id, {
        // 本地值（UI状态）
        valuePorts: updatePortData(props.data.valuePorts, PortDefinitionName.CONTENT, val)
      })
    }
)
watch(() => reactiveData.form.isUseInput,
    (val) => {
      // 避免重复更新（非常关键）
      const oldVal = getPortData(props.data.valuePorts, valuePortNameIsUseInput)
      if (oldVal === val) return
      updateNodeData(props.id, {
        // 本地值（UI状态）
        valuePorts: updatePortData(props.data.valuePorts, valuePortNameIsUseInput, val)
      })
    }
)
</script>
<template>
  <BaseNode :id="id" :data="data" class="pt-cl-delay-node" :isShowResizer="false">
    <!-- 表单 -->
    <PtForm class="pt-cl-delay-node-form"
            :form="reactiveData.form"
            :formData="reactiveData.formData"
            labelWidth="100"
            defaultButtonsShow=""
            inline
            size="small"
            :layout="1"
            :comps="formComps">
    </PtForm>
  </BaseNode>
</template>

<style scoped>

</style>
<style>
.pt-cl-delay-node-form .el-form-item{
  margin-right: 0;
  margin-bottom: 5px;
  display: flex;
}
</style>
