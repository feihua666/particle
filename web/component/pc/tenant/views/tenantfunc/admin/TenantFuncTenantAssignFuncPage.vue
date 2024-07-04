<script setup name="TenantFuncTenantAssignFuncPage" lang="ts">
/**
 * 租户分配功能页面
 */
import {reactive ,ref} from 'vue'
import {list as funcListApi} from "../../../../func/api/admin/funcAdminApi";

import {selectTenantProps, useSelectTenantCompItem} from "../../../components/tenantCompItem";
import {tenantAssignFunc, queryFuncIdsByTenantId} from "../../../api/tenantfunc/admin/tenantFuncAdminApi";

// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  ...selectTenantProps,
  // 用于过滤和分配，都有使用，所以必填
  funcApplicationId: {
    type: String,
    required: true
  },
})
// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
    funcApplicationId: props.funcApplicationId
  },
  // 表单数据对象
  formData: {},
})
// 表单项
const formComps = ref(
    [
      useSelectTenantCompItem({props,required: true}),
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
                let tenantId = param.tenantId
                if(tenantId){
                  return queryFuncIdsByTenantId({id: tenantId,funcApplicationId: props.funcApplicationId})
                }
                // 空函数不查询
                return {data: []}
              },
              // dataInitMethod 参数
              dataInitMethodParam: {tenantId: form.tenantId},
              // 可用数据列表
              dataMethod: ()=>{ return  funcListApi({funcApplicationId: props.funcApplicationId}) },
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
  methodConfirmText: '注意：该功能将影响租户已分配的所有功能，一旦功能减少已分配的角色功能将同步减少操作不可逆！',
  permission: 'admin:web:tenantFunc:tenantAssignFunc',
})
// 提交按钮
const submitMethod = () => {
  return tenantAssignFunc
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