<script setup name="TenantCreateApplyFuncApplicationAssignFunc" lang="ts">
/**
 * 功能应用分配功能,主要用于创建租户申请时可选择应用下的功能
 */
import {reactive ,ref} from 'vue'
import {list as funcListApi} from "../../../../../func/api/admin/funcAdminApi";
import {
  funcApplicationAssignFunc,
} from "../../../../../func/api/admin/funcApplicationFuncRelAdminApi";
import {
  remoteSelectFuncApplicationCompItem,
  remoteSelectFuncApplicationProps
} from "../../../../../func/compnents/application/funcApplicationCompItem";
import {defineExpose} from "@vue/runtime-core";

// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  ...remoteSelectFuncApplicationProps,
  // 可用的功能，限制在该应用下
  limitFuncApplicationId: {
    type: String
  },
  // 选中的功能id
  checkedFuncIds:{
    type: Array,
    default: ()=>[]
  }
})
// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {},
  // 表单数据对象
  formData: {},
})
// 表单项
const formComps = ref(
    [
      remoteSelectFuncApplicationCompItem({props,required: true}),
      {
        field: {
          name: 'funcIds',
          value: []
        },
        element: {
          comp: 'PtTree',
          formItemProps: {
            label: '功能',
            required: true,
          },
          compProps: ({form})=> {
            return {
              // 加载初始化选中数据
              dataInitMethod: () => {
                // 空函数不查询
                return {data: props.checkedFuncIds}
              },
              // 可用数据列表
              dataMethod: ()=> {
                return funcListApi({funcApplicationId: props.limitFuncApplicationId})
              },
              dataMethodResultHandleConvertToTree: true,
              showCheckbox: true
            }
          }
        }
      },
    ]
)

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '确认',
  permission: 'admin:web:funcApplicationFuncRel:funcApplicationAssignFunc',
})
// 提交按钮
const submitMethod = () => {
  return funcApplicationAssignFunc
}
// 成功提示语
const submitMethodSuccess = () => {
  return '分配成功，请刷新数据查看'
}

defineExpose({
  form: reactiveData.form
})
</script>
<template>
  <!-- 添加表单 -->
  <PtForm :form="reactiveData.form"
          :formData="reactiveData.formData"
          labelWidth="80"
          :method="submitMethod()"
          :methodSuccess="submitMethodSuccess"
          defaultButtonsShow=""
          :submitAttrs="submitAttrs"
          inline
          :layout="1"
          :comps="formComps">
  </PtForm>
</template>


<style scoped>

</style>