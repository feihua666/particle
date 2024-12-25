<script setup name="RoleUserRelManageUserAssignRolePage" lang="ts">
/**
 * 用户分配角色页面
 */
import {reactive, ref} from 'vue'
import {queryRoleIdsByUserId, userAssignRole as userAssignRoleApi} from "../../api/admin/roleUserRelAdminApi"
import {remoteSelectUserProps, useRemoteSelectUserCompItem} from "../../../user/components/userCompItem";
import {currentUserRoleList} from "../../api/roleLoginApi";
import {removeItems} from "../../../../../global/common/tools/ArrayTools";

// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  ...remoteSelectUserProps,
})
// 属性
const reactiveData = reactive({
  // 表单初始查询第一页,这里使用懒加载分配，因为有可能用户有多个角色，但分配用户的人可拥有的角色更少
  form: {
    isLazyLoad: true,
    uncheckedRoleIds: null
  },
  // 表单数据对象
  formData: {},
  // 记录加载的角色，以便在提交时处理未选中的数据
  dataMethodAllData: null
})
// 表单项
const formComps = ref(
    [
      useRemoteSelectUserCompItem({props,required: true}),
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
                let userId = param.userId
                if(userId){
                  return queryRoleIdsByUserId({id: userId})
                }
                // 空函数不查询
                return {data: []}
              },
              // dataInitMethod 参数
              dataInitMethodParam: {userId: form.userId},
              // 可用数据列表
              dataMethod: () => {
                return currentUserRoleList().then(res => {
                  reactiveData.dataMethodAllData = res.data.data
                  return Promise.resolve(res)
                })
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
  permission: 'admin:web:roleUserRel:userAssignRole',
})
// 提交按钮
const submitMethod = (form) => {
  reactiveData.form.uncheckedRoleIds = removeItems((reactiveData.dataMethodAllData || []).map(item => item.id),reactiveData.form.checkedRoleIds)
  return userAssignRoleApi(form)
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
