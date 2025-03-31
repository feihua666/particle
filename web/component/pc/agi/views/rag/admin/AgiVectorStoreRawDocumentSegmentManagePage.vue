<script setup name="AgiVectorStoreRawDocumentSegmentManagePage" lang="ts">
/**
 * 知识存储原始文档片段管理页面
 */
import {reactive, ref} from 'vue'
import {
  embedding as agiVectorStoreRawDocumentSegmentEmbeddingApi,
  page as agiVectorStoreRawDocumentSegmentPageApi,
  remove as agiVectorStoreRawDocumentSegmentRemoveApi
} from "../../../api/rag/admin/agiVectorStoreRawDocumentSegmentAdminApi"
import {pageFormItems} from "../../../components/rag/admin/agiVectorStoreRawDocumentSegmentManage";


const tableRef = ref(null)

// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
  },
  formComps: pageFormItems,
  tableColumns: [
    {
      prop: 'agiVectorStoreRawDocumentName',
      label: '知识存储原始文档名称',
      showOverflowTooltip: true,
    },
    {
      prop: 'content',
      label: '片段内容',
      showOverflowTooltip: true,
    },
    {
      prop: 'metadataJson',
      label: '元数据json',
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
  permission: 'admin:web:agiVectorStoreRawDocumentSegment:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doAgiVectorStoreRawDocumentSegmentPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return agiVectorStoreRawDocumentSegmentPageApi({...reactiveData.form,...pageQuery})
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
  let editIdData = {id: row.id,agiVectorStoreRawDocumentId: row.agiVectorStoreRawDocumentId,agiVectorStoreRawDocumentName:row.agiVectorStoreRawDocumentName}

  let tableRowButtons = [
    {
      txt: '编辑',
      text: true,
      permission: 'admin:web:agiVectorStoreRawDocumentSegment:update',
      // 跳转到编辑
      route: {path: '/admin/AgiVectorStoreRawDocumentSegmentManageUpdate',query: editIdData}
    },
    {
      txt: '嵌入',
      text: true,
      position: 'more',
      disabled: row.isEmbedded,
      disabledReason: row.isEmbedded ? '未嵌入的片段才可以嵌入' : undefined,
      permission: 'admin:web:agiVectorStoreRawDocumentSegment:embedding',
      methodConfirmText: `确定要嵌入吗？将尝试删除已嵌入的向量并重新嵌入`,
      // 删除操作
      method(){
        return agiVectorStoreRawDocumentSegmentEmbeddingApi({id: row.id}).then(res => {
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
      permission: 'admin:web:agiVectorStoreRawDocumentSegment:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return agiVectorStoreRawDocumentSegmentRemoveApi({id: row.id}).then(res => {
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
      <PtButton permission="admin:web:agiVectorStoreRawDocumentSegment:create" route="/admin/AgiVectorStoreRawDocumentSegmentManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doAgiVectorStoreRawDocumentSegmentPageApi"
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
