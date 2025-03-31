<script setup name="AgiVectorStoreRawDocumentManagePage" lang="ts">
/**
 * 知识存储原始文档管理页面
 */
import {reactive, ref} from 'vue'
import { page as agiVectorStoreRawDocumentPageApi,
  remove as agiVectorStoreRawDocumentRemoveApi,
  embedding as agiVectorStoreRawDocumentEmbeddingApi,
  reEmbedding as agiVectorStoreRawDocumentReEmbeddingApi,
} from "../../../api/rag/admin/agiVectorStoreRawDocumentAdminApi"
import {pageFormItems} from "../../../components/rag/admin/agiVectorStoreRawDocumentManage";
import {downloadFileByUrl} from "../../../../../../global/common/tools/FileTools";
import {getFinalDownloadUrl} from "../../../../../../global/common/api/globalApi";


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
      showOverflowTooltip: true,
    },
    {
      prop: 'fileName',
      label: '文件名称',
      showOverflowTooltip: true,
    },
    {
      prop: 'typeDictName',
      label: '类型',
    },
    {
      prop: 'url',
      label: '地址',
      showOverflowTooltip: true,
    },
    {
      prop: 'isEmbedded',
      label: '是否嵌入',
      formatter: (row, column, cellValue, index) => {
        return cellValue ? '已嵌入' : '未嵌入'
      }
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
  permission: 'admin:web:agiVectorStoreRawDocument:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doAgiVectorStoreRawDocumentPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return agiVectorStoreRawDocumentPageApi({...reactiveData.form,...pageQuery})
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
      txt: '下载',
      text: true,
      permission: 'admin:web:agiVectorStoreRawDocument:download',
      // 下载上传的原始文件
      method(){
        let url = row.url
        url = getFinalDownloadUrl(url)
        downloadFileByUrl(url,row.fileName)
      }
    },
    {
      txt: '嵌入未嵌入的片段',
      text: true,
      position: 'more',
      disabled: row.isEmbedded,
      disabledReason: row.isEmbedded ? '未嵌入的片段才可以嵌入' : undefined,
      permission: 'admin:web:agiVectorStoreRawDocument:embedding',
      methodConfirmText: `确定要嵌入 ${row.name} 吗？将嵌入未嵌入的片段，已经是嵌入状态的片段将不做处理`,
      // 删除操作
      method(){
        return agiVectorStoreRawDocumentEmbeddingApi({id: row.id}).then(res => {
          // 删除成功后刷新一下表格
          submitMethod()
          return Promise.resolve(res)
        })
      }
    },
    {
      txt: '重新嵌入所有片段',
      text: true,
      position: 'more',
      permission: 'admin:web:agiVectorStoreRawDocument:reEmbedding',
      methodConfirmText: `确定要重新嵌入 ${row.name} 吗？将重新嵌入所有的片段，已经是嵌入状态的片段将会先删除再重新嵌入`,
      // 删除操作
      method(){
        return agiVectorStoreRawDocumentReEmbeddingApi({id: row.id}).then(res => {
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
      permission: 'admin:web:agiVectorStoreRawDocument:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？删除后被拆分的片段和已嵌入的向量将会被一并删除！`,
      // 删除操作
      method(){
        return agiVectorStoreRawDocumentRemoveApi({id: row.id}).then(res => {
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
      <PtButton permission="admin:web:agiVectorStoreRawDocument:create" route="/admin/AgiVectorStoreRawDocumentManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doAgiVectorStoreRawDocumentPageApi"
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
