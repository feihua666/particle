<script setup name="UserIdentifierPwdManagePage" lang="ts">
/**
 * 用户登录标识密码管理页面
 */
import {reactive, ref} from 'vue'
import { page as userIdentifierPwdPageApi, remove as userIdentifierPwdRemoveApi} from "../../api/admin/userIdentifierPwdAdminApi"
import {
  remoteSelectUserIdentifierProps,
  remoteSelectUserProps
} from "../../compnents/userCompItem";
import {usePageFormItems} from "../../compnents/admin/userIdentifierPwdManage";

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
  formComps: usePageFormItems({props}),
  tableColumns: [
    {
      prop: 'userNickname',
      label: '用户昵称',
    },
    {
      prop: 'userIdentifier',
      label: '登录标识',
    },
    {
      prop: 'pwdEncryptFlag',
      label: '加密方式',
    },

    {
      prop: 'isExpired',
      label: '是否过期',
      width: 70,
      formatter: (row, column, cellValue, index) => {
        let r = cellValue ? '已过期' : '正常'
        if(cellValue && row.expiredReason){
          r = r + `(${row.expiredReason})`
        }
        return r
      }
    },
    {
      prop: 'expireAt',
      label: '过期时间',
      width: 80,
    },

    {
      prop: 'isNeedUpdate',
      label: '是否需要提示修改密码',
      width: 70,
      formatter: (row, column, cellValue, index) => {
        let r = cellValue ? '提示' : '不提示'
        if(cellValue && row.needUpdateMessage){
          r = r + `(${row.needUpdateMessage})`
        }
        return r
      },
      showOverflowTooltip: true
    },
    {
      prop: 'groupFlag',
      label: '分组标识'
    },
    {
      prop: 'pwdModifiedAt',
      label: '密码修改时间'
    },
    {
      prop: 'complexity',
      label: '密码复杂度'
    },
  ],

})

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '查询',
  loading: false,
  permission: 'admin:web:userIdentifierPwd:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doUserIdentifierPwdPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return userIdentifierPwdPageApi({...reactiveData.form,...pageQuery})
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
  let routeQuery = {id: row.id,userId: row.userId,nickname: row.userNickname,identifierId: row.identifierId,identifier: row.userIdentifier}
  let tableRowButtons = [
    {
      txt: '编辑',
      text: true,
      permission: 'admin:web:userIdentifierPwd:update',
      // 跳转到编辑
      route: {path: '/admin/userIdentifierPwdManageUpdate',query: routeQuery}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:userIdentifierPwd:delete',
      methodConfirmText: `删除用户密码会造成用户当前账号无法登录，但不影响其它账号登录。确定要删除 ${row.userIdentifier} 的密码吗？`,
      // 删除操作
      method(){
        return userIdentifierPwdRemoveApi(idData).then(res => {
          // 删除成功后刷新一下表格
          submitMethod()
          return Promise.resolve(res)
        })
      }
    },
  ]
  return tableRowButtons
}
</script>
<template>
  <!-- 查询表单 -->
  <PtForm :form="reactiveData.form"
          :method="submitMethod"
          defaultButtonsShow="submit,reset"
          :submitAttrs="submitAttrs"
          inline
          :comps="reactiveData.formComps">
    <template #buttons>
      <PtButton permission="admin:web:userIdentifierPwd:create" route="/admin/userIdentifierPwdManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doUserIdentifierPwdPageApi"
           @dataMethodDataLoading="(loading) => submitAttrs.loading=loading"
           :dataMethodResultHandleConvertToTree="true"
           :paginationProps="tablePaginationProps"
           :columns="reactiveData.tableColumns">

    <!--  操作按钮  -->
    <template #defaultAppend>
      <el-table-column label="操作" width="180">
        <template #default="{row, column, $index}">
          <PtButtonGroup :options="getTableRowButtons({row, column, $index})">
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