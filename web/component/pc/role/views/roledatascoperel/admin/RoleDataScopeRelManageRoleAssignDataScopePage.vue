<script setup name="RoleDataScopeRelManageRoleAssignDataScopePage" lang="ts">
/**
 * 角色分配数据范围页面
 */
import {reactive, ref} from 'vue'
import {
  queryDataScopeIdsByRoleId,
  roleAssignDataScope as roleAssignDataScopeApi
} from "../../../api/roledatascoperel/admin/roleDataScopeRelAdminApi"
// 以下两项为自动生成，不准确，请手动根据实际情况修改
import {remoteSelectRoleProps, useRemoteSelectRoleCompItem} from "../../../components/roleCompItem";
import {list as dataScopeListApi} from "../../../../dataconstraint/api/admin/dataScopeAdminApi";

// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  ...remoteSelectRoleProps,
})
// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
    checkedDataScopeIdAgainstDataObjectIds:{}
  },
  // 表单数据对象
  formData: {},
})
// 表单项
const formComps = ref(
    [
      useRemoteSelectRoleCompItem({props,required: true}),
      {
        field: {
          name: 'checkedDataScopeIds',
          value: []
        },
        element: {
          comp: 'PtTree',
          formItemProps: {
            label: '数据范围',
            required: true,
          },
          compProps: ({form})=> {
            return {
              // 加载初始化选中数据
              dataInitMethod: ({param}) => {
                let roleId = param.roleId
                if(roleId){
                  return queryDataScopeIdsByRoleId({id: roleId})
                }
                // 空函数不查询
                return {data: []}
              },
              // dataInitMethod 参数
              dataInitMethodParam: {roleId: form.roleId},
              // 可用数据列表
              dataMethod: ({param})=>{
                // 将数据转为一颗树
                return dataScopeListApi(param).then(res => {
                  let data = res.data.data
                  let parents = []
                  for (let i = 0; i < data.length; i++) {
                    // 顺便把 id 和 dataObjectId 的关系处理到表单中
                    reactiveData.form.checkedDataScopeIdAgainstDataObjectIds[data[i].id] = data[i].dataObjectId

                    data[i].parentId = data[i].dataObjectId
                    if (!parents.some(item => item.id == data[i].dataObjectId)) {
                      parents.push({
                        id: data[i].dataObjectId,
                        name: data[i].dataObjectName,
                        isDisabled: true,
                      })
                    }
                  }

                  for (let j = 0; j < parents.length; j++) {
                    data.push(parents[j])
                  }
                  return Promise.resolve(res)
                })
              },
              dataMethodResultHandleConvertToTree: true,
              showCheckbox: true,
              // 只选中叶子节点
              modelValueIncludeHalfCheckedKeys: false,
              modelValueIncludeLeafOnly: true
            }
          }
        }
      },
    ]
)

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '确认',
  permission: 'admin:web:roleDataScopeRel:roleAssignDataScope',
})
// 提交按钮
const submitMethod = () => {
  return roleAssignDataScopeApi
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
