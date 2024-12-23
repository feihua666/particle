<script setup name="OpenplatformDocApiDocTemplateExampleCodeManagePage" lang="ts">
/**
 * 开放接口文档模板示例代码管理页面
 */
import {reactive, ref} from 'vue'
import {
  page as openplatformDocApiDocTemplateExampleCodePageApi,
  remove as openplatformDocApiDocTemplateExampleCodeRemoveApi
} from "../../../api/doc/admin/openplatformDocApiDocTemplateExampleCodeAdminApi"
import {pageFormItems} from "../../../components/doc/admin/openplatformDocApiDocTemplateExampleCodeManage";


const tableRef = ref(null)

// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
  },
  formComps: pageFormItems,
  tableColumns: [
    {
      prop: 'langDictName',
      label: '开发语言',
    },
    {
      prop: 'exampleCode',
      label: '示例代码片段',
      showOverflowTooltip: true
    },
    {
      prop: 'exampleDownloadUrl',
      label: '示例代码下载地址',
      showOverflowTooltip: true
    },
    {
      prop: 'openplatformDocApiDocTemplateName',
      label: '文档内容模板',
    },
    {
      prop: 'seq',
      label: '排序',
    },
    {
      prop: 'remark',
      label: '描述',
      showOverflowTooltip: true,
    },
  ],

})

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '查询',
  loading: false,
  permission: 'admin:web:openplatformDocApiDocTemplateExampleCode:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doOpenplatformDocApiDocTemplateExampleCodePageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return openplatformDocApiDocTemplateExampleCodePageApi({...reactiveData.form,...pageQuery})
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
      permission: 'admin:web:openplatformDocApiDocTemplateExampleCode:update',
      // 跳转到编辑
      route: {path: '/admin/OpenplatformDocApiDocTemplateExampleCodeManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:openplatformDocApiDocTemplateExampleCode:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return openplatformDocApiDocTemplateExampleCodeRemoveApi({id: row.id}).then(res => {
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
      <PtButton permission="admin:web:openplatformDocApiDocTemplateExampleCode:create" route="/admin/OpenplatformDocApiDocTemplateExampleCodeManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doOpenplatformDocApiDocTemplateExampleCodePageApi"
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
