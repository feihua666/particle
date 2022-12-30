<script setup name="RoleFuncRelManageFuncAssignRolePage" lang="ts">
/**
 * 角色分配功能菜单页面
 */
import {reactive ,ref} from 'vue'
import {queryFuncIdsByRoleId, roleAssignFunc as roleAssignFuncApi} from "../../api/admin/roleFuncRelAdminApi"
import {remoteSelectRoleCompItem, remoteSelectRoleProps} from "../../components/roleCompItem";
import {list as funcListApi} from "../../../func/api/admin/funcAdminApi";

// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  ...remoteSelectRoleProps,
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
      remoteSelectRoleCompItem({props,required: true}),
      {
        field: {
          name: 'checkedFuncIds',
          value: []
        },
        element: {
          comp: 'PtTree',
          formItemProps: {
            label: '功能菜单',
            required: true,
          },
          compProps: ({form})=> {
            return {
              // 加载初始化选中数据
              dataInitMethod: ({param}) => {
                let roleId = param.roleId
                if(roleId){
                  return queryFuncIdsByRoleId({id: roleId})
                }
                // 空函数不查询
                return {data: []}
              },
              // dataInitMethod 参数
              dataInitMethodParam: {roleId: form.roleId},
              // 可用数据列表
              dataMethod: funcListApi,
              dataMethodResultHandleConvertToTree: true,
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
  permission: 'admin:web:roleFuncRel:roleAssignFunc',
})
// 提交按钮
const submitMethod = () => {
  return roleAssignFuncApi
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