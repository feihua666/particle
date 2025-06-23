<script setup name="OpenplatformProviderRecordManagePage" lang="ts">
/**
 * 开放平台开放接口供应商调用记录管理页面
 */
import {reactive, ref} from 'vue'
import {
  page as openplatformProviderRecordPageApi
} from "../../../api/providerrecord/admin/openplatformProviderRecordAdminApi"
import {pageFormItems} from "../../../components/providerrecord/admin/openplatformProviderRecordManage";


const tableRef = ref(null)
// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 加载数据初始化参数,路由传参
  openplatformOpenapiRecordId: {
    type: String
  }
})
// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
    openplatformOpenapiRecordId: props.openplatformOpenapiRecordId
  },
  formComps: pageFormItems,
  tableColumns: [
    {
      prop: 'customerName',
      label: '客户',
      showOverflowTooltip: true
    },
    {
      prop: 'requestName',
      label: '接口名称',
      showOverflowTooltip: true
    },
    {
      prop: 'requestUrl',
      label: '接口地址',
      showOverflowTooltip: true
    },
    {
      prop: 'requestParameterMd5',
      label: '请求参数md5',
      showOverflowTooltip: true
    },
    {
      prop: 'requestAt',
      label: '请求时间',
      showOverflowTooltip: true
    },
    //   插入数据库时间
    {
      prop: 'createAt',
      label: '创建时间',
      showOverflowTooltip: true
    },
    {
      prop: 'responseResultMd5',
      label: '响应结果md5',
      showOverflowTooltip: true
    },
    {
      prop: 'traceId',
      label: 'traceId',
      showOverflowTooltip: true
    },
    {
      prop: 'handleDuration',
      label: '处理时长(ms)',
    },
    {
      prop: 'isResponseHasEffectiveValue',
      label: '响应数据',
      formatter: (row, column, cellValue, index) => {
        let r = cellValue ? '正常响应数据' : '无数据'
        return r
      },
      showOverflowTooltip: true
    },
    {
      prop: 'responseHttpStatus',
      label: 'http状态码',
    },
    {
      prop: 'responseBusinessStatus',
      label: '业务编码',
      showOverflowTooltip: true
    },
    {
      prop: 'openplatformProviderName',
      label: '供应商',
      showOverflowTooltip: true
    },
    {
      prop: 'dataQueryProviderName',
      label: '数据查询供应商',
      showOverflowTooltip: true
    },
    {
      prop: 'isCacheHit',
      label: '缓存命中',
      formatter: (row, column, cellValue, index) => {
        let r = cellValue ? '是' : '否'
        return r
      },
    },
    {
      prop: 'feeAmount',
      label: '消费金额',
    },
    {
      prop: 'feeReasonDictName',
      label: '消费金额缘由',
      showOverflowTooltip: true
    },
  ],

})

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '查询',
  loading: false,
  permission: 'admin:web:openplatformProviderRecord:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doOpenplatformProviderRecordPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return openplatformProviderRecordPageApi({...reactiveData.form,...pageQuery})
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
      txt: '查看参数',
      text: true,
      permission: 'admin:web:openplatformProviderRecordParam:detail',
      // 跳转到编辑
      route: {path: '/admin/openplatformProviderRecordManageParamView',query: idData}
    },
  ]
  return tableRowButtons
}
</script>
<template>
  <!-- 查询表单 -->
  <PtForm :form="reactiveData.form"
          labelWidth="100"
          :method="submitMethod"
          defaultButtonsShow="submit,reset"
          :submitAttrs="submitAttrs"
          inline
          :comps="reactiveData.formComps">
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doOpenplatformProviderRecordPageApi"
           @dataMethodDataLoading="(loading) => submitAttrs.loading=loading"
           :doDataMethodOnMounted="false"
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
