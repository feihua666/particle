<script setup name="RoleDataScopeRelManageDataScopeAssignRolePage" lang="ts">
/**
 * 数据范围分配角色页面
 */
import {reactive ,ref} from 'vue'
import {queryRoleIdsByDataScopeId, dataScopeAssignRole as dataScopeAssignRoleApi} from "../../../api/roledatascoperel/admin/roleDataScopeRelAdminApi"
import {
  useRemoteSelectDataScopeCompItem,
  remoteSelectDataScopeProps,
  useSelectDataConstraintDataObjectCompItem, useSelectDataScopeCompItem
} from "../../../../dataconstraint/components/dataconstraintCompItem";
import {list as roleListApi} from "../../../api/admin/roleAdminApi";

// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  ...remoteSelectDataScopeProps,
  // 加载数据初始化参数,路由传参
  dataObjectId: {
    type: String
  },
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
      useSelectDataConstraintDataObjectCompItem({props,required: true}),
      useSelectDataScopeCompItem({props,required: false}),
      {
        field: {
          name: 'checkedRoleIds',
          value: []
        },
        element: {
          comp: 'PtTree',
          formItemProps: {
            label: '角色',
            required: true,
          },
          compProps: ({form})=> {
            return {
              // 加载初始化选中数据
              dataInitMethod: ({param}) => {
                let dataScopeId = param.dataScopeId
                if(dataScopeId){
                  return queryRoleIdsByDataScopeId({id: dataScopeId})
                }
                // 空函数不查询
                return {data: []}
              },
              // dataInitMethod 参数
              dataInitMethodParam: {dataScopeId: form.dataScopeId},
              // 可用数据列表
              dataMethod: roleListApi,
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
  permission: 'admin:web:roleDataScopeRel:dataScopeAssignRole',
})
// 提交按钮
const submitMethod = () => {
  return dataScopeAssignRoleApi
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