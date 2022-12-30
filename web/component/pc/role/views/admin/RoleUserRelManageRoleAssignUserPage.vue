<script setup name="RoleUserRelManageUserAssignRolePage" lang="ts">
/**
 * 角色分配用户页面
 */
import {reactive ,ref} from 'vue'
import {queryUserIdsByRoleId, roleAssignUser as roleAssignUserApi} from "../../api/admin/roleUserRelAdminApi"
import {remoteSelectRoleCompItem, remoteSelectRoleProps} from "../../components/roleCompItem";
import {list as userListApi} from "../../../user/api/admin/userAdminApi";

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
          name: 'checkedUserIds',
          value: []
        },
        element: {
          comp: 'PtTree',
          formItemProps: {
            label: '用户',
            required: true,
          },
          compProps: ({form})=> {
            return {
              // 加载初始化选中数据
              dataInitMethod: ({param}) => {
                let roleId = param.roleId
                if(roleId){
                  return queryUserIdsByRoleId({id: roleId})
                }
                // 空函数不查询
                return {data: []}
              },
              // dataInitMethod 参数
              dataInitMethodParam: {roleId: form.roleId},
              // 可用数据列表
              dataMethod: userListApi,
              showCheckbox: true,
              props: {
                label: 'nickname'
              }
            }
          }
        }
      },
    ]
)

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '确认',
  permission: 'admin:web:roleUserRel:roleAssignUser',
})
// 提交按钮
const submitMethod = () => {
  return roleAssignUserApi
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
          inline
          :layout="1"
          :comps="formComps">
  </PtForm>
</template>


<style scoped>

</style>