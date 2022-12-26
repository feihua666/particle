<script setup name="UserIdentifierManagePage" lang="ts">
/**
 * 用户登录标识管理页面
 */
import {reactive, ref} from 'vue'
import { page as userIdentifierPageApi, remove as userIdentifierRemoveApi} from "../../api/admin/userIdentifierAdminApi"
import {remoteSelectUserCompItem, remoteSelectUserProps} from "../../compnents/userCompItem";

const tableRef = ref(null)
// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
...remoteSelectUserProps
})
// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
  },
  formComps: [
    {
      field: {
        name: 'identifier'
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '登录标识'
        },
        compProps: {
          clearable: true,
        }
      }
    },
    {
      field: {
        name: 'identityTypeDictId'
      },
      element: {
        comp: 'PtDictFrontSelect',
        formItemProps: {
          label: '账号类型',
        },
        compProps: {
          clearable: true,
          // 字典查询
          dictParam: {groupCode: 'user_account_type'},
        }
      }
    },
    {
      field: {
        name: 'groupFlag'
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '分组标识'
        },
        compProps: {
          clearable: true,
        }
      }
    },
    remoteSelectUserCompItem({props,required: false}),
  ],
  tableColumns: [
    {
      prop: 'userNickname',
      label: '用户昵称',
    },
    {
      prop: 'identifier',
      label: '登录标识',
    },
    {
      prop: 'identityTypeDictName',
      label: '账号类型',
    },
    {
      prop: 'unionId',
      label: 'unionId'
    },

    {
      prop: 'isLock',
      label: '是否锁定',
      width: 70,
      formatter: (row, column, cellValue, index) => {
        let r = cellValue ? '锁定' : '正常'
        if(cellValue && row.lockReason){
          r = r + `(${row.lockReason})`
        }
        return r
      },
      showOverflowTooltip: true
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
      prop: 'groupFlag',
      label: '分组标识'
    },
    {
      prop: 'lastLoginAt',
      label: '最后登录时间'
    },
    {
      prop: 'lastLoginIp',
      label: '最后登录ip'
    },
  ],

})

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '查询',
  loading: false,
  permission: 'admin:web:userIdentifier:pageQuery',
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doUserIdentifierPageApi = (pageQuery: {pageNo: number,pageSize: number}) => {
  return userIdentifierPageApi({...reactiveData.form,...pageQuery})
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
      txt: '编辑',
      text: true,
      permission: 'admin:web:userIdentifier:update',
      // 跳转到编辑
      route: {path: '/admin/userIdentifierManageUpdate',query: routeQuery}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:userIdentifier:delete',
      methodConfirmText: `删除用户账号会造成用户当前账号不能登录，同时会删除当前账号的密码数据。确定要删除 ${row.identifier} 吗？`,
      // 删除操作
      method(){
        return userIdentifierRemoveApi(idData).then(res => {
          // 删除成功后刷新一下表格
          submitMethod()
          return Promise.resolve(res)
        })
      }
    },
    {
      txt: '重置密码',
      text: true,
      permission: 'admin:web:userIdentifierPwd:identifier:resetPassword',
      position: 'more',
      methodConfirmText: `此操作只影响当前账号密码，确定要重置密码吗？`,
      // 跳转到编辑
      route: {path: '/admin/userIdentifierPwdManageResetPassword',query: routeQuery}
    },
    {
      txt: '用户密码',
      text: true,
      permission: 'admin:web:userIdentifierPwd:pageQuery',
      position: 'more',
      icon: 'View',
      type: 'primary',
      // 跳转到编辑
      route: {path: '/admin/userIdentifierPwdManagePage',query: routeQuery},
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
      <PtButton permission="admin:web:userIdentifier:create" route="/admin/userIdentifierManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doUserIdentifierPageApi"
           @dataMethodDataLoading="(loading) => submitAttrs.loading=loading"
           :dataMethodResultHandleConvertToTree="true"
           :paginationProps="tablePaginationProps"
           :columns="reactiveData.tableColumns">

    <!--  操作按钮  -->
    <template #defaultAppend>
      <el-table-column label="操作" width="220">
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