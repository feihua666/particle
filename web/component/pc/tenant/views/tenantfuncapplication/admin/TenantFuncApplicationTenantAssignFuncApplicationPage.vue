<script setup name="TenantFuncApplicationTenantAssignFuncApplicationPage" lang="ts">
/**
 * 租户分配功能应用页面
 */
import {reactive, ref} from 'vue'
import {selectTenantProps, useSelectTenantCompItem} from "../../../components/tenantCompItem";
import {list as funcApplicationListApi} from "../../../../func/api/application/admin/funcApplicationAdminApi";
import {
  queryFuncApplicationIdsByTenantId,
  tenantAssignFuncApplication
} from "../../../api/tenantfuncapplication/admin/tenantFuncApplicationAdminApi";

// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  ...selectTenantProps
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
      useSelectTenantCompItem({props,required: true}),
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
                let tenantId = param.tenantId
                if(tenantId){
                  return queryFuncApplicationIdsByTenantId({id: tenantId})
                }
                // 空函数不查询
                return {data: []}
              },
              // dataInitMethod 参数
              dataInitMethodParam: {tenantId: form.tenantId},
              // 可用数据列表
              dataMethod: funcApplicationListApi,
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
  permission: 'admin:web:tenantFuncApplication:tenantAssignFuncApplication',
})
// 提交按钮
const submitMethod = () => {
  return tenantAssignFuncApplication
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
