<script setup name="LowcodeSegmentTemplateManagePage" lang="ts">
/**
 * 低代码片段模板管理页面
 */
import {reactive, ref} from 'vue'
import {
  page as lowcodeSegmentTemplatePageApi,
  remove as lowcodeSegmentTemplateRemoveApi
} from "../../../api/generator/admin/lowcodeSegmentTemplateAdminApi"
import {pageFormItems} from "../../../compnents/admin/lowcodeSegmentTemplateManage";

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
      label: "输出类型",
      prop: "outputTypeDictName"
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
      label: "名称模板",
      prop: "nameTemplate",
      showOverflowTooltip: true
    },
    {
      label: "名称输出变量名",
      prop: "nameOutputVariable",
      showOverflowTooltip: true
    },
    {
      label: "内容模板",
      prop: "contentTemplate",
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
  permission: 'admin:web:lowcodeSegmentTemplate:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doLowcodeSegmentTemplatePageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return lowcodeSegmentTemplatePageApi({...reactiveData.form,...pageQuery})
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
      permission: 'admin:web:lowcodeSegmentTemplate:update',
      // 跳转到编辑
      route: {path: '/admin/lowcodeSegmentTemplateManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:lowcodeSegmentTemplate:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return lowcodeSegmentTemplateRemoveApi(idData).then(res => {
          // 删除成功后刷新一下表格
          submitMethod()
          return Promise.resolve(res)
        })
      }
    },
    {
      txt: '渲染测试',
      position: 'more',
      text: true,
      permission: 'admin:web:lowcodeSegmentTemplate:renderTest',
      // 跳转到渲染测试
      route: {path: '/admin/lowcodeSegmentTemplateManageRenderTest',query: idData}
    },
    {
      txt: '添加子级',
      position: 'more',
      text: true,
      permission: 'admin:web:lowcodeSegmentTemplate:create',
      // 跳转到添加
      route: {path: '/admin/lowcodeSegmentTemplateManageAdd',query: idData}
    },
    {
      txt: '复制节点',
      text: true,
      position: 'more',
      permission: 'admin:web:lowcodeSegmentTemplate:copy',
      // 跳转到添加
      route: {path: '/admin/lowcodeSegmentTemplateManageCopy',query: idAndParentIdData}
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
      <PtButton permission="admin:web:lowcodeSegmentTemplate:create" route="/admin/lowcodeSegmentTemplateManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doLowcodeSegmentTemplatePageApi"
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