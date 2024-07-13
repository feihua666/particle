<script setup name="ReportSegmentTemplateManagePage" lang="ts">
/**
 * 报告片段模板管理页面
 */
import {reactive, ref} from 'vue'
import {
  page as reportSegmentTemplatePageApi,
  refreshCache as reportSegmentTemplaterefreshCacheApi,
  remove as reportSegmentTemplateRemoveApi
} from "../../../api/template/admin/reportSegmentTemplateAdminApi"
import {pageFormItems} from "../../../components/template/admin/reportSegmentTemplateManage";

const tableRef = ref(null)

// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
  },
  formComps: pageFormItems,
  tableColumns: [
    {
      label: "模板名称",
      prop: "name",
      showOverflowTooltip: true,
      width: 250
    },
    {
      label: "编码",
      prop: "code",
      showOverflowTooltip: true
    },
    {
      label: "模板权限码",
      prop: "permissions",
      showOverflowTooltip: true
    },
    {
      label: "输出类型",
      prop: "outputTypeDictName",
      showOverflowTooltip: true
    },
    {
      label: "父级",
      prop: "parentName"
    },
    {
      label: "计算模板",
      prop: "computeTemplate",
      showOverflowTooltip: true
    },

    {
      label: "名称输出变量名",
      prop: "nameOutputVariable",
      showOverflowTooltip: true
    },

    {
      label: "内容输出变量名",
      prop: "outputVariable",
      showOverflowTooltip: true
    },
    {
      label: "引用模板",
      prop: "referenceSegmentTemplateName"
    },

    {
      label: "共享变量名",
      prop: "shareVariables"
    },
    {
      prop: 'seq',
      label: '排序',
      width: 50,
    },
    {
      label: "描述",
      prop: "remark",
      showOverflowTooltip: true
    }
  ],

})

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '查询',
  loading: false,
  permission: 'admin:web:reportSegmentTemplate:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doReportSegmentTemplatePageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return reportSegmentTemplatePageApi({...reactiveData.form,...pageQuery})
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
  let idAndParentIdData = {id: row.id,parentId: row.parentId}
  let tableRowButtons = [
    {
      txt: '编辑',
      text: true,
      permission: 'admin:web:reportSegmentTemplate:update',
      // 跳转到编辑
      route: {path: '/admin/ReportSegmentTemplateManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      position: 'more',
      permission: 'admin:web:reportSegmentTemplate:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return reportSegmentTemplateRemoveApi({id: row.id}).then(res => {
          // 删除成功后刷新一下表格
          submitMethod()
          return Promise.resolve(res)
        })
      }
    },
    {
      txt: '复制节点',
      text: true,
      position: 'more',
      permission: 'admin:web:reportSegmentTemplate:copy',
      // 跳转到添加
      route: {path: '/admin/reportSegmentTemplateManageCopy',query: idAndParentIdData}
    },
    {
      txt: '刷新缓存',
      text: true,
      position: 'more',
      permission: 'admin:web:reportSegmentTemplate:refreshCache',
      methodSuccess: (res) => '刷新缓存成功,如果部署多个实例可能要多次执行。 ' + res.data.data,
      // 刷新缓存
      method(){
        return reportSegmentTemplaterefreshCacheApi({id: row.id}).then(res => {
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
      <PtButton permission="admin:web:reportSegmentTemplate:create" route="/admin/ReportSegmentTemplateManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doReportSegmentTemplatePageApi"
           @dataMethodDataLoading="(loading) => submitAttrs.loading=loading"
           :dataMethodResultHandleConvertToTree="true"
           :paginationProps="tablePaginationProps"
           :columns="reactiveData.tableColumns">

    <!--  操作按钮  -->
    <template #defaultAppend>
      <el-table-column label="操作" width="220">
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