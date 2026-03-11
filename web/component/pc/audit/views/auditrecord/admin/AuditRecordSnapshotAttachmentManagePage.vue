<script setup name="AuditRecordSnapshotAttachmentManagePage" lang="ts">
/**
 * 审核记录附件快照管理页面
 */
import {reactive, ref} from 'vue'
import { page as auditRecordSnapshotAttachmentPageApi, remove as auditRecordSnapshotAttachmentRemoveApi} from "../../../api/auditrecord/admin/auditRecordSnapshotAttachmentAdminApi"
import {pageFormItems} from "../../../components/auditrecord/admin/auditRecordSnapshotAttachmentManage";


const tableRef = ref(null)

// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
  },
  formComps: pageFormItems,
  tableColumns: [
    {
      prop: 'auditRecordId',
      label: '审核记录id',
    },
    {
      prop: 'dataId',
      label: '数据id',
    },
    {
      prop: 'snapshotDataId',
      label: '快照数据id',
    },
    {
      prop: 'attachmentName',
      label: '附件名称',
    },
    {
      prop: 'attachmentUrl',
      label: '附件地址',
    },
  ],

})

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '查询',
  loading: false,
  permission: 'admin:web:auditRecordSnapshotAttachment:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doAuditRecordSnapshotAttachmentPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return auditRecordSnapshotAttachmentPageApi({...reactiveData.form,...pageQuery})
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

  let tableRowButtons = [
    {
      txt: '编辑',
      text: true,
      permission: 'admin:web:auditRecordSnapshotAttachment:update',
      // 跳转到编辑
      route: {path: '/admin/AuditRecordSnapshotAttachmentManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:auditRecordSnapshotAttachment:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return auditRecordSnapshotAttachmentRemoveApi({id: row.id}).then(res => {
          // 删除成功后刷新一下表格
          submitMethod()
          return Promise.resolve(res)
        })
      }
    }
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
      <PtButton permission="admin:web:auditRecordSnapshotAttachment:create" route="/admin/AuditRecordSnapshotAttachmentManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doAuditRecordSnapshotAttachmentPageApi"
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