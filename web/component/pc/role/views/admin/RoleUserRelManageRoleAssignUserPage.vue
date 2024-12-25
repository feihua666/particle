<script setup name="RoleUserRelManageUserAssignRolePage" lang="ts">
/**
 * 角色分配用户页面
 */
import {reactive, ref} from 'vue'
import {queryUserIdsByRoleId, roleAssignUser as roleAssignUserApi} from "../../api/admin/roleUserRelAdminApi"
import {remoteSelectRoleProps, useRemoteSelectRoleCompItem} from "../../components/roleCompItem";
import {list as userListApi} from "../../../user/api/admin/userAdminApi";
import {removeItems} from "../../../../../global/common/tools/ArrayTools";

// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  ...remoteSelectRoleProps,
})
// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
    isLazyLoad: true,
    uncheckedUserIds: null
  },
  // 表单数据对象
  formData: {},
  // 记录加载的角色，以便在提交时处理未选中的数据
  dataMethodAllData: null
})
// 表单项
const formComps = ref(
    [
      useRemoteSelectRoleCompItem({props,required: true}),
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
              dataMethod: () => {
                return userListApi().then(res => {
                  reactiveData.dataMethodAllData = res.data.data
                  return Promise.resolve(res)
                })
              },
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
const submitMethod = (form) => {
  reactiveData.form.uncheckedUserIds = removeItems((reactiveData.dataMethodAllData || []).map(item => item.id),reactiveData.form.checkedUserIds)
  return roleAssignUserApi(form)
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
          :method="submitMethod"
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
