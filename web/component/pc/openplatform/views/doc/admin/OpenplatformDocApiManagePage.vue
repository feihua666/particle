<script setup name="OpenplatformDocApiManagePage" lang="ts">
/**
 * 开放接口文档接口管理页面
 */
import {reactive, ref} from 'vue'
import {
  downloadApiDoc,
  page as openplatformDocApiPageApi,
  remove as openplatformDocApiRemoveApi
} from "../../../api/doc/admin/openplatformDocApiAdminApi"
import {pageFormItems} from "../../../components/doc/admin/openplatformDocApiManage";
import {downloadFileByUrl} from "../../../../../../global/common/tools/FileTools";


const tableRef = ref(null)

// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
  },
  formComps: pageFormItems,
  tableColumns: [
    {
      prop: 'code',
      label: '编码',
    },
    {
      prop: 'name',
      label: '名称',
    },
    {
      prop: 'nameSimple',
      label: '简称',
    },
    {
      prop: 'logoUrl',
      label: '图标',
      columnView: 'image'
    },
    {
      prop: 'pricePerTime',
      label: '每次价格(元)',
    },
    {
      prop: 'pricePerTimeDesc',
      label: '价格文本',
      showOverflowTooltip: true,
    },
    {
      prop: 'description',
      label: '描述',
      showOverflowTooltip: true,
    },
    {
      prop: 'seq',
      label: '排序',
    },
  ],

})

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '查询',
  loading: false,
  permission: 'admin:web:openplatformDocApi:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doOpenplatformDocApiPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return openplatformDocApiPageApi({...reactiveData.form,...pageQuery})
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
      permission: 'admin:web:openplatformDocApi:update',
      // 跳转到编辑
      route: {path: '/admin/OpenplatformDocApiManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      position: 'more',
      permission: 'admin:web:openplatformDocApi:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？删除后将级联删除文档内容及文档内容关联的参数字段、代码示例、响应码数据`,
      // 删除操作
      method(){
        return openplatformDocApiRemoveApi({id: row.id}).then(res => {
          // 删除成功后刷新一下表格
          submitMethod()
          return Promise.resolve(res)
        })
      }
    },
    {
      txt: '下载接口文档',
      text: true,
      position: 'more',
      // 该权限码配置在报告接口管理页面中的开放接口文档
      permission: 'openplatform:doc:download',
      methodConfirmText: `确定要下载 ${row.name} 接口文档吗？`,
      // 删除操作
      method(){
        return downloadApiDoc({openplatformDocApiId: row.id,"convertToPdf": true}).then(res => {
          // 成功后发起下载操作
          downloadFileByUrl(res.data.data.url,row.name + '.pdf')
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
      <PtButton permission="admin:web:openplatformDocApi:create" route="/admin/OpenplatformDocApiManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doOpenplatformDocApiPageApi"
           @dataMethodDataLoading="(loading) => submitAttrs.loading=loading"

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
