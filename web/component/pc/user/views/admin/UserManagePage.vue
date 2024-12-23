<script setup name="UserManagePage" lang="ts">
/**
 * 用户管理页面
 */
import {reactive, ref} from 'vue'
import {page as userPageApi, remove as userRemoveApi} from "../../api/admin/userAdminApi"
import {useRoute} from 'vue-router'
import {pageFormItems} from "../../components/admin/userManage";
import {componentEnabled} from "../../../../../common/config/componentsConfig";

const route = useRoute()
const tableRef = ref(null)
const columns = [
  {
    prop: 'nickname',
    label: '昵称',
    width: 100,
    showOverflowTooltip: true
  },
  {
    prop: 'name',
    label: '姓名',
    showOverflowTooltip: true
  },
  {
    prop: 'avatar',
    label: '头像',
    columnView: 'image',
    width: 80
  },
  {
    prop: 'serialNo',
    label: '编号',

    showOverflowTooltip: true
  },
  {
    prop: 'genderDictName',
    label: '性别',
    width: 60
  },
  {
    prop: 'isVirtual',
    label: '虚拟用户',
    width: 80,
    formatter: (row, column, cellValue, index) => {
      return cellValue ? '虚拟用户' : '真实用户'
    }
  },
  {
    prop: 'isLock',
    label: '是否锁定',
    width: 80,
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
    prop: 'categoryDictName',
    label: '分类',
  },
  {
    prop: 'groupFlag',
    label: '分组标识',

    showOverflowTooltip: true
  },
  {
    prop: 'sourceFromDictName',
    label: '来源',
    showOverflowTooltip: true
  },
  {
    prop: 'isExpired',
    label: '是否过期',
    width: 80,
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
    showOverflowTooltip: true
  },
  componentEnabled('dept') ? {
    prop: 'deptName',
    label: '归属部门',
    showOverflowTooltip: true
  }: null,
  {
    prop: 'remark',
    label: '备注',
    showOverflowTooltip: true
  },
]
// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
  },
  formComps: pageFormItems.filter(item => !!item),
  tableColumns: columns.filter(item => !!item),

})

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '查询',
  loading: false,
  permission: 'admin:web:user:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doUserPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return userPageApi({...reactiveData.form,...pageQuery})
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
  let routeQuery = {userId: row.id,nickname: row.nickname}
  let tableRowButtons: Array<any> = [
    {
      txt: '编辑',
      text: true,
      permission: 'admin:web:user:update',
      // 跳转到编辑
      route: {path: '/admin/userManageUpdate',query: routeQuery}
    },
    {
      txt: '删除',
      text: true,
      position: 'more',
      permission: 'admin:web:user:delete',
      methodConfirmText: `删除用户将可能造成不可预知的后果，一般不建议删除，可以将用户锁定处理，如果是新用户可以放心删除。确定要删除 ${row.nickname} 吗？`,
      // 删除操作
      method(){
        return userRemoveApi(idData).then(res => {
          // 删除成功后刷新一下表格
          submitMethod()
          return Promise.resolve(res)
        })
      }
    },

    {
      txt: '重置密码',
      text: true,
      position: 'more',
      permission: 'admin:web:userIdentifierPwd:user:resetPassword',
      methodConfirmText: `此操作会影响用户所有账号密码，确定要重置密码吗？`,
      // 跳转到编辑
      route: {path: '/admin/userPwdManageResetPassword',query: routeQuery},
    },
    {
      txt: '添加登录标识',
      text: true,
      permission: 'admin:web:userIdentifier:create',
      position: 'more',
      // 跳转到编辑
      route: {path: '/admin/userIdentifierManageAddFixed',query: routeQuery},
    },
    {
      txt: '绑定登录标识',
      text: true,
      permission: 'admin:web:userIdentifier:create',
      position: 'more',
      // 跳转到编辑
      route: {path: '/admin/userIdentifierManageBindFixed',query: routeQuery},
    },
    {
      txt: '登录标识',
      text: true,
      permission: 'admin:web:userIdentifier:pageQuery',
      position: 'more',
      icon: 'UserFilled',
      type: 'primary',
      // 跳转到编辑
      route: {path: '/admin/userIdentifierManagePage',query: routeQuery},
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
  let userAssignRoleRouteQuery = {userId: row.id,userNickname: row.nickname}

  if (componentEnabled('role')) {
    tableRowButtons.push(
        {
          txt: '分配角色',
          text: true,
          position: 'more',
          permission: 'admin:web:roleUserRel:userAssignRole',
          route: {path: '/admin/userManageUserAssignRole',query: userAssignRoleRouteQuery}
        }
    )
  }
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
          :comps="reactiveData.formComps">
    <template #buttons>
      <PtButton permission="admin:web:user:create" route="/admin/userManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           :dataMethod="doUserPageApi"
           @dataMethodDataLoading="(loading) => submitAttrs.loading=loading"
           :paginationProps="tablePaginationProps"
           :columns="reactiveData.tableColumns">

    <!--  操作按钮  -->
    <template #defaultAppend>
      <el-table-column label="操作" width="180">
        <template #default="{row, column, $index}">
          <PtButtonGroup :options="getTableRowButtons({row, column, $index})" :dropdownTriggerButtonOptions="dropdownTriggerButtonOptions">
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
