<script setup name="OpenplatformOpenapiBatchQueryRecordDetailManagePage" lang="ts">
/**
 * 开放接口批量查询记录明细管理页面
 */
import {reactive, ref} from 'vue'
import {
  page as openplatformOpenapiBatchQueryRecordDetailPageApi,
  remove as openplatformOpenapiBatchQueryRecordDetailRemoveApi
} from "../../../api/openapi/admin/openplatformOpenapiBatchQueryRecordDetailAdminApi"
import {pageFormItems} from "../../../components/openapi/admin/openplatformOpenapiBatchQueryRecordDetailManage";


const tableRef = ref(null)
// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 加载数据初始化参数,路由传参
  openplatformOpenapiBatchQueryRecordId: {
    type: String
  }
})
// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
    openplatformOpenapiBatchQueryRecordId: props.openplatformOpenapiBatchQueryRecordId
  },
  formComps: pageFormItems,
  tableColumns: [

    {
      prop: 'executeStatusDictName',
      label: '执行状态',
    },
    {
      prop: 'isSuccess',
      label: '是否成功',
      formatter: (row, column, cellValue, index) => {
        if (cellValue == null) {
          return null
        }
        let r = cellValue ? '查询成功' : '查询失败'
        return r
      },
    },
    {
      prop: 'queryAt',
      label: '查询时间',
    },
    {
      prop: 'traceId | spanId',
      label: 'traceId | spanId',
      formatter: (row, column, cellValue, index) => {
        if (!row.traceId && !row.spanId) {
          return null
        }
        let r = `${row.traceId} | ${row.spanId}`;
        return r
      },
      showOverflowTooltip: true,
    },
    {
      prop: 'requestParam',
      label: '请求参数',
      showOverflowTooltip: true,
    },
    {
      prop: 'queryString',
      label: '请求查询字符串',
      showOverflowTooltip: true,
    },
    {
      prop: 'responseResult',
      label: '响应数据',
      showOverflowTooltip: true,
    },
    {
      prop: 'requestNonce',
      label: '请求流水号',
      showOverflowTooltip: true,
    },
  ],

})

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '查询',
  loading: false,
  permission: 'admin:web:openplatformOpenapiBatchQueryRecordDetail:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doOpenplatformOpenapiBatchQueryRecordDetailPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return openplatformOpenapiBatchQueryRecordDetailPageApi({...reactiveData.form,...pageQuery})
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
      txt: '删除',
      text: true,
      permission: 'admin:web:openplatformOpenapiBatchQueryRecordDetail:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return openplatformOpenapiBatchQueryRecordDetailRemoveApi({id: row.id}).then(res => {
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
           :dataMethod="doOpenplatformOpenapiBatchQueryRecordDetailPageApi"
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
</template>


<style scoped>

</style>
