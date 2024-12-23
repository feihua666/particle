<script setup name="FuncApplicationFuncRelManageFuncApplicationAssignFuncPage" lang="ts">
/**
 * 功能应用分配功能页面
 */
import {reactive, ref} from 'vue'
import {list as funcListApi} from "../../api/admin/funcAdminApi";
import {
  funcApplicationAssignFunc,
  queryFuncIdsByFuncApplicationId
} from "../../api/admin/funcApplicationFuncRelAdminApi";
import {
  remoteSelectFuncApplicationCompItem,
  remoteSelectFuncApplicationProps
} from "../../components/application/funcApplicationCompItem";

// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  ...remoteSelectFuncApplicationProps
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
          name: 'checkedFuncIds',
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
              dataInitMethod: ({param}) => {
                let funcApplicationId = param.funcApplicationId
                if(funcApplicationId){
                  return queryFuncIdsByFuncApplicationId({id: funcApplicationId})
                }
                // 空函数不查询
                return {data: []}
              },
              // dataInitMethod 参数
              dataInitMethodParam: {funcApplicationId: form.funcApplicationId},
              // 可用数据列表
              dataMethod: funcListApi,
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
