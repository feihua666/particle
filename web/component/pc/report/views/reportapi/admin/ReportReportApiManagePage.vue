<script setup name="ReportReportApiManagePage" lang="ts">
/**
 * 报告接口管理页面
 */
import {reactive, ref} from 'vue'
import {
  page as reportReportApiPageApi,
  refreshCache as reportSegmentTemplateRefreshCacheApi,
  remove as reportReportApiRemoveApi
} from "../../../api/reportapi/admin/reportReportApiAdminApi"
import {pageFormItems} from "../../../components/reportapi/admin/reportReportApiManage";

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
      label: '名称',
      showOverflowTooltip: true
    },
    {
      prop: 'code',
      label: '编码',
      showOverflowTooltip: true
    },

    {
      prop: 'isGroup',
      label: '分组/接口',
      formatter: (row, column, cellValue, index) => {
        let r = cellValue ? '分组' : '接口'
        return r
      }
    },
    {
      prop: 'permissions',
      label: '接口权限码',
    },
    {
      prop: 'url',
      label: '接口地址',
    },
    {
      prop: 'reportSegmentTemplateName',
      label: '报告片段模板',
    },
    {
      prop: 'inParamExampleConfigJson',
      label: '入参示例',
      showOverflowTooltip: true
    },
    {
      label: "父级",
      prop: "parentName"
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
  permission: 'admin:web:reportReportApi:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doReportReportApiPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return reportReportApiPageApi({...reactiveData.form,...pageQuery})
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
      permission: 'admin:web:reportReportApi:update',
      // 跳转到编辑
      route: {path: '/admin/ReportReportApiManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      position: 'more',
      permission: 'admin:web:reportReportApi:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return reportReportApiRemoveApi({id: row.id}).then(res => {
          // 删除成功后刷新一下表格
          submitMethod()
          return Promise.resolve(res)
        })
      }
    },
    {
      txt: '刷新缓存',
      text: true,
      position: 'more',
      permission: 'admin:web:reportReportApi:refreshCache',
      methodSuccess: (res) => '刷新缓存成功,如果部署多个实例可能要多次执行。 ' + res.data.data,
      // 刷新缓存
      method(){
        return reportSegmentTemplateRefreshCacheApi({url: row.url,isIncludeSegmentTemplate: true}).then(res => {
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
          labelWidth="100"
          defaultButtonsShow="submit,reset"
          :submitAttrs="submitAttrs"
          inline
          :comps="reactiveData.formComps">
    <template #buttons>
      <PtButton permission="admin:web:reportReportApi:create" route="/admin/ReportReportApiManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doReportReportApiPageApi"
           @dataMethodDataLoading="(loading) => submitAttrs.loading=loading"
           :dataMethodResultHandleConvertToTree="true"
           :paginationProps="tablePaginationProps"
           :columns="reactiveData.tableColumns">

    <!--  操作按钮  -->
    <template #defaultAppend>
      <el-table-column label="操作" width="180">
        <template #default="{row, column, $index}">
          <PtButtonGroup :options="getTableRowButtons({row, column, $index})" :dropdownTriggerButtonOptions="{  text: true,buttonText: '更多'}">
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