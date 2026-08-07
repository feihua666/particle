<script setup name="AuditRecordManagePage" lang="ts">
/**
 * 审核记录管理页面
 */
import {reactive, ref} from 'vue'
import { page as auditRecordPageApi, remove as auditRecordRemoveApi} from "../../../api/auditrecord/admin/auditRecordAdminApi"
import {pageFormItems} from "../../../components/auditrecord/admin/auditRecordManage";


const tableRef = ref(null)

// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
  },
  formComps: pageFormItems,
  tableColumns: [
    {
      prop: 'dataId',
      label: '数据id',
    },
    {
      prop: 'auditResultDictName',
      label: '审核结果类型',
    },
    {
      prop: 'auditComment',
      label: '审核意见',
    },
    {
      prop: 'auditAt',
      label: '审核时间',
    },
    {
      prop: 'auditByUserNickname',
      label: '审核人昵称',
    },
    {
      prop: 'dataPreStatusDictName',
      label: '数据审核之前状态',
    },
    {
      prop: 'dataPostStatusDictName',
      label: '数据审核之后状态',
    },
    {
      prop: 'groupFlag',
      label: '分组标识',
    },
    {
      prop: 'groupFlagMemo',
      label: '分组标识备忘',
    },
    {
      prop: 'remark',
      label: '描述',
    },
  ],

})

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '查询',
  loading: false,
  permission: 'admin:web:auditRecord:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doAuditRecordPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return auditRecordPageApi({...reactiveData.form,...pageQuery})
}
const tablePaginationProps = {
  permission: submitAttrs.value.permission
}
// 表格操作按钮
const getTableRowButtons = ({row, column, $index}) => {
  if($index < 0){
    return []
  }
    let dt = {__dt: row.name}
  let idData = {id: row.id,...dt}

  let tableRowButtons = [
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:auditRecord:delete',
      methodConfirmText: `确定要删除吗？`,
      // 删除操作
      method(){
        return auditRecordRemoveApi({id: row.id}).then(res => {
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
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doAuditRecordPageApi"
           @dataMethodDataLoading="(loading) => submitAttrs.loading=loading"

           :paginationProps="tablePaginationProps"
           :columns="reactiveData.tableColumns">

    <!--  操作按钮  -->
    <template #defaultAppend>
      <el-table-column label="操作" width="80">
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
