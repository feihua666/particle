<script setup name="TenantCreateApplyManagePage" lang="ts">
/**
 * 租户创建申请管理页面
 */
import {reactive, ref} from 'vue'
import { page as TenantCreateApplyPageApi, remove as TenantCreateApplyRemoveApi} from "../../../api/createapply/admin/tenantCreateApplyAdminApi"
import {pageFormItems} from "../../../compnents/createapply/admin/tenantCreateApplyManage";


const tableRef = ref(null)

// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
  },
  formComps: pageFormItems,
  tableColumns: [
    {
      prop: 'name',
      label: '租户名称',
      showOverflowTooltip: true
    },
    {
      prop: 'tenantTypeDictName',
      label: '租户类型',
    },
    {
      prop: 'applyUserNickname',
      label: '申请人',
    },
    {
      prop: 'applyUserAvatar',
      label: '申请人头像',
      columnView: 'image'
    },

    {
      prop: 'isFormal',
      label: '是否正式',
      formatter: (row, column, cellValue, index) => {
        return cellValue ? '正式' : '试用'
      }
    },
    {
      prop: 'userLimitCount',
      label: '用户数限制',
      formatter: (row, column, cellValue, index) => {
        let r = cellValue ? cellValue : '不限制'
        return r
      }
    },
    {
      prop: 'effectiveDays',
      label: '申请天数',
      formatter: (row, column, cellValue, index) => {
        let r = cellValue ? cellValue : '不限制'
        return r
      }
    },
    {
      prop: 'effectiveAt',
      label: '生效日期',
      formatter: (row, column, cellValue, index) => {
        let r = cellValue ? cellValue : '立即生效'
        return r
      }
    },
    {
      prop: 'expireAt',
      label: '过期时间',
      formatter: (row, column, cellValue, index) => {
        let r = cellValue ? cellValue : '不限制'
        return r
      }
    },
    {
      prop: 'email',
      label: '邮箱',
      showOverflowTooltip: true
    },
    {
      prop: 'userName',
      label: '姓名',
      showOverflowTooltip: true
    },
    {
      prop: 'mobile',
      label: '手机号',
      showOverflowTooltip: true
    },

    {
      prop: 'auditStatusDictName',
      label: '审核状态',
    },
    {
      prop: 'auditStatusComment',
      label: '审核意见',
      showOverflowTooltip: true
    },
    {
      prop: 'auditUserNickname',
      label: '审核人',
      showOverflowTooltip: true
    },
    {
      prop: 'remark',
      label: '描述',
      showOverflowTooltip: true
    },
  ],

})

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '查询',
  loading: false,
  permission: 'admin:web:tenantCreateApply:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doTenantCreateApplyPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return TenantCreateApplyPageApi({...reactiveData.form,...pageQuery})
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
  let editIdData = {id: row.id, applyUserId: row.applyUserId, applyUserNickname: row.applyUserNickname}

  let isAuditPass = row.auditStatusDictValue == 'audit_pass'
  let editDisabledProps = {}
  if(isAuditPass){
    editDisabledProps = {
      disabled: true,
      disabledReason: '已审核通过不能编辑'
    }
  }
  let tableRowButtons = [
    {
      txt: '编辑',
      text: true,
      ...editDisabledProps,
      permission: 'admin:web:tenantCreateApply:update',
      // 跳转到编辑
      route: {path: '/admin/TenantCreateApplyManageUpdate',query: editIdData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:tenantCreateApply:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return TenantCreateApplyRemoveApi({id: row.id}).then(res => {
          // 删除成功后刷新一下表格
          submitMethod()
          return Promise.resolve(res)
        })
      }
    }
  ]
  // 状态为待审核时添加审核
  if('un_audit' == row.auditStatusDictValue){
    tableRowButtons.push(    {
      txt: '审核',
      text: true,
      permission: 'admin:web:tenantCreateApply:audit',
      // 跳转到审核
      route: {path: '/admin/TenantCreateApplyManageAudit',query: editIdData}
    })
  }

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
      <PtButton permission="admin:web:tenantCreateApply:create" route="/admin/TenantCreateApplyManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doTenantCreateApplyPageApi"
           @dataMethodDataLoading="(loading) => submitAttrs.loading=loading"

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