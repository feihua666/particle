<script setup name="UserLoginRecordManagePage" lang="ts">
/**
 * 用户登录记录管理页面
 */
import {reactive, ref} from 'vue'
import { page as userLoginRecordPageApi, remove as userLoginRecordRemoveApi} from "../../api/admin/userLoginRecordAdminApi"
import {
  useRemoteSelectUserCompItem, useRemoteSelectUserIdentifierCompItem,
  remoteSelectUserIdentifierProps,
  remoteSelectUserProps
} from "../../components/userCompItem";
import {userLoginRecordColumns} from "../../components/userLoginRecordCompItem";

const tableRef = ref(null)
// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
...remoteSelectUserProps,
...remoteSelectUserIdentifierProps
})
// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
  },
  formComps: [
    useRemoteSelectUserCompItem({props,required: false}),
    useRemoteSelectUserIdentifierCompItem({props,required: false,fieldName: 'userIdentifierId'}),

    {
      field: {
        name: 'deviceId'
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '设备id'
        },
        compProps: {
        }
      }
    },
  ],
  tableColumns: userLoginRecordColumns,

})

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '查询',
  loading: false,
  permission: 'admin:web:userLoginRecord:pageQuery',
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doUserLoginRecordPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return userLoginRecordPageApi({...reactiveData.form,...pageQuery})
}
const tablePaginationProps = {
  permission: submitAttrs.value.permission
}
// 表格操作按钮
const getTableRowButtons = ({row, column, $index}) => {
  if($index < 0){
    return []
  }
  let idData = {id: row.id}

  let routeQuery = {identifierId: row.id,identifier: row.identifier}
  let tableRowButtons = [

    {
      txt: '删除',
      text: true,
      permission: 'admin:web:userLoginRecord:delete',
      methodConfirmText: `确定要删除 登录记录 吗？`,
      // 删除操作
      method(){
        return userLoginRecordRemoveApi(idData).then(res => {
          // 删除成功后刷新一下表格
          submitMethod()
          return Promise.resolve(res)
        })
      }
    },
  ]
  return tableRowButtons
}
const dropdownTriggerButtonOptions = {
  text: true,
  buttonText: '更多',
}
</script>
<template>
  <!-- 查询表单 -->
  <PtForm :form="reactiveData.form"
          :method="submitMethod"
          defaultButtonsShow="submit,reset"
          :submitAttrs="submitAttrs"
          inline
          labelWidth="80"
          :comps="reactiveData.formComps">
    <template #buttons>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doUserLoginRecordPageApi"
           @dataMethodDataLoading="(loading) => submitAttrs.loading=loading"

           :paginationProps="tablePaginationProps"
           :columns="reactiveData.tableColumns">

    <!--  操作按钮  -->
    <template #defaultAppend>
      <el-table-column label="操作">
        <template #default="{row, column, $index}">
          <PtButtonGroup :options="getTableRowButtons({row, column, $index})"  :dropdownTriggerButtonOptions="dropdownTriggerButtonOptions">
          </PtButtonGroup>
        </template>
      </el-table-column>
    </template>
  </PtTable>
<!-- 子级路由 -->
  <PtRouteViewPopover :level="3"></PtRouteViewPopover>
</template>


<style scoped>

</style>