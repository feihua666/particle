<script setup name="RoleFuncRelManageRoleAssignFuncPage" lang="ts">
/**
 * 角色分配功能菜单页面
 */
import {reactive, ref} from 'vue'
import {queryFuncIdsByRoleId, roleAssignFunc as roleAssignFuncApi} from "../../api/admin/roleFuncRelAdminApi"
import {remoteSelectRoleProps, useRemoteSelectRoleCompItem} from "../../components/roleCompItem";
import {loginUserFuncList} from "../../../func/api/funcLoginApi";
import {removeItems} from "../../../../../global/common/tools/ArrayTools";

// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  ...remoteSelectRoleProps,
})
// 属性
const reactiveData = reactive({
  // 表单初始查询第一页,这里使用懒加载分配，因为有可能角色有多个功能，但分配用户的人可拥有的功能更少
  form: {
    isLazyLoad: true,
    uncheckedFuncIds: null
  },
  // 表单数据对象
  formData: {},
  // 记录加载的功能，以便在提交时处理未选中的数据
  dataMethodAllData: null
})
// 表单项
const formComps = ref(
    [
      useRemoteSelectRoleCompItem({props,required: true}),
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
              dataMethod: () => {
                return loginUserFuncList().then(res => {
                  // 注意这里直接取数据层，否则因为会转为树导致数据变少
                  reactiveData.dataMethodAllData = res.data.data
                  return Promise.resolve(res)
                })
              },
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
const submitMethod = (form) => {
  let allIds = (reactiveData.dataMethodAllData || []).map(item => item.id)
  reactiveData.form.uncheckedFuncIds = removeItems(allIds,reactiveData.form.checkedFuncIds)
  return roleAssignFuncApi(form)
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
