<script setup name="FuncApplicationFuncRelManageFuncAssignFuncApplicationPage" lang="ts">
/**
 * 功能分配功能应用页面
 */
import {reactive ,ref} from 'vue'
import {useRemoteSelectFuncCompItem, remoteSelectFuncProps} from "../../compnents/funcCompItem";
import {list as funcApplicationListApi} from "../../api/application/admin/funcApplicationAdminApi";
import {
  funcAssignFuncApplication,
  queryFuncApplicationIdsByFuncId
} from "../../api/admin/funcApplicationFuncRelAdminApi";

// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  ...remoteSelectFuncProps,
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
      useRemoteSelectFuncCompItem({props,required: true}),
      {
        field: {
          name: 'checkedFuncApplicationIds',
          value: []
        },
        element: {
          comp: 'PtTree',
          formItemProps: {
            label: '功能应用',
            required: true,
          },
          compProps: ({form})=> {
            return {
              // 加载初始化选中数据
              dataInitMethod: ({param}) => {
                let funcId = param.funcId
                if(funcId){
                  return queryFuncApplicationIdsByFuncId({id: funcId})
                }
                // 空函数不查询
                return {data: []}
              },
              // dataInitMethod 参数
              dataInitMethodParam: {funcId: form.funcId},
              // 可用数据列表
              dataMethod: funcApplicationListApi,
              showCheckbox: true,
            }
          }
        }
      },
    ]
)

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '确认',
  permission: 'admin:web:funcApplicationFuncRel:funcAssignFuncApplication',
})
// 提交按钮
const submitMethod = () => {
  return funcAssignFuncApplication
}
// 成功提示语
const submitMethodSuccess = () => {
  return '分配成功，请刷新数据查看'
}

</script>
<template>
  <!-- 添加表单 -->
  <PtForm :form="reactiveData.form"
          :formData="reactiveData.formData"
          labelWidth="80"
          :method="submitMethod()"
          :methodSuccess="submitMethodSuccess"
          defaultButtonsShow="submit,reset"
          :submitAttrs="submitAttrs"
          :buttonsTeleportProps="$route.meta.formButtonsTeleportProps"
          inline
          :layout="1"
          :comps="formComps">
  </PtForm>
</template>


<style scoped>

</style>