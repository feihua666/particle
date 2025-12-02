<script setup name="DynamicTableUploadRecordManagePage" lang="ts">
/**
 * 动态数据表格上传记录管理页面
 */
import {reactive, ref} from 'vue'
import {
  page as dynamicTableUploadRecordPageApi,
  publish as dynamicTableUploadRecordPublishApi,
  remove as dynamicTableUploadRecordRemoveApi
} from "../../../api/dynamictable/admin/dynamicTableUploadRecordAdminApi"
import {pageFormItems} from "../../../components/dynamictable/admin/dynamicTableUploadRecordManage";
import {
  download,
  extractDownloadFileNameByUrl,
  getFinalDownloadUrl
} from "../../../../../../global/common/api/globalApi";
import {downloadFileByData, extractContentType} from "../../../../../../global/common/tools/FileTools";


const tableRef = ref(null)

// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
  },
  formComps: pageFormItems,
  tableColumns: [
    {
      prop: 'dynamicTableName',
      label: '动态数据表格名称',
    },
    {
      prop: 'uploadFileName',
      label: '上传文件名称',
      showOverflowTooltip: true
    },
    {
      prop: 'uploadFileUrl',
      label: '上传文件地址',
      showOverflowTooltip: true
    },
    {
      prop: 'uploadTableFieldNum',
      label: '上传字段数量',
    },
    {
      prop: 'uploadTableDataNum',
      label: '上传数据数量',
    },
    {
      prop: 'isPublic',
      label: '是否发布',
      formatter: (row, column, cellValue, index) => {
        return cellValue ? '已发布' : '未发布'
      }
    },
  ],

})

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '查询',
  loading: false,
  permission: 'admin:web:dynamicTableUploadRecord:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doDynamicTableUploadRecordPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return dynamicTableUploadRecordPageApi({...reactiveData.form,...pageQuery})
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
      txt: '下载导入文件',
      text: true,
      permission: 'adminDynamicTableDownloadImportFile',
      // 刷新操作
      method(){
        let downloadUrl = getFinalDownloadUrl(row.uploadFileUrl)
        let exportFileName = row.uploadFileName || extractDownloadFileNameByUrl(downloadUrl)
        return download(downloadUrl).then(res => {
          let data = res.data
          let contentType = extractContentType(res)
          let fileName = decodeURIComponent(exportFileName)
          downloadFileByData(data,contentType,fileName)
          return Promise.resolve(res)
        })
      }
    },
    {
      txt: '导入数据管理',
      text: true,
      position: 'more',
      permission: 'admin:web:dynamicTable:dataPageQuery',
      // 跳转到编辑
      route: {path: '/admin/dynamicTableUploadRecordManageData',query: {id: row.id,dynamicTableId: row.dynamicTableId}}
    },
    {
      txt: '发布数据',
      text: true,
      position: 'more',
      disabled: !!row.isPublic,
      disabledReason: !!row.isPublic ? '当前数据已发布，不可重复发布' : undefined,
      permission: 'admin:web:dynamicTableUploadRecord:publish',
      methodConfirmText: `确定要发布 ${row.uploadFileName} 吗？发布后对应导入的数据将会同步发布`,
      // 删除操作
      method(){
        return dynamicTableUploadRecordPublishApi({id: row.id}).then(res => {
          // 删除成功后刷新一下表格
          submitMethod()
          return Promise.resolve(res)
        })
      }
    },
    {
      txt: '删除',
      text: true,
      position: 'more',
      permission: 'admin:web:dynamicTableUploadRecord:delete',
      methodConfirmText: `确定要删除 ${row.uploadFileName} 吗？删除后已导入的数据不受影响`,
      // 删除操作
      method(){
        return dynamicTableUploadRecordRemoveApi({id: row.id}).then(res => {
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
           :dataMethod="doDynamicTableUploadRecordPageApi"
           @dataMethodDataLoading="(loading) => submitAttrs.loading=loading"

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
