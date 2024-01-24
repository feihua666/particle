<script setup name="OpenplatformOpenapiRecordManagePage" lang="ts">
/**
 * 开放平台开放接口调用记录管理页面
 */
import {reactive, ref} from 'vue'
import { page as openplatformOpenapiRecordPageApi} from "../../../api/openapirecord/admin/openplatformOpenapiRecordAdminApi"
import {pageFormItems} from "../../../compnents/openapirecord/admin/openplatformOpenapiRecordManage";


const tableRef = ref(null)

// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
  },
  formComps: pageFormItems,
  tableColumns: [
    {
      prop: 'id',
      label: 'id',
      showOverflowTooltip: true
    },
    {
      prop: 'openplatformAppName',
      label: '应用',
      showOverflowTooltip: true
    },
    {
      prop: 'appId',
      label: 'appId',
      showOverflowTooltip: true
    },
    {
      prop: 'userNickname',
      label: '用户昵称',
      showOverflowTooltip: true
    },
    {
      prop: 'isApp',
      label: '调用方式',
      formatter: (row, column, cellValue, index) => {
        let r = cellValue ? '应用' : '用户'
        return r
      }
    },
    {
      prop: 'customerName',
      label: '客户',
      showOverflowTooltip: true
    },
    {
      prop: 'openplatformOpenapiName',
      label: '开放接口',
      showOverflowTooltip: true
    },
    {
      prop: 'requestUrl',
      label: '接口地址',
      showOverflowTooltip: true
    },
    {
      prop: 'requestTimestamp',
      label: '请求时间戳',
      showOverflowTooltip: true
    },
    {
      prop: 'requestNonce',
      label: '请求流水号',
      showOverflowTooltip: true
    },
    {
      prop: 'requestSignature',
      label: '请求签名',
      showOverflowTooltip: true
    },
    {
      prop: 'requestParameterMd5',
      label: '请求md5',
      showOverflowTooltip: true
    },
    //   请求时间戳对应的时间格式
    {
      prop: 'requestTimestampDateTimeStr',
      label: '请求时间',
      showOverflowTooltip: true
    },
    //   请求时间戳对应的时间格式
    {
      prop: 'requestHandleAt',
      label: '请求处理时间',
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
      label: '响应md5',
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
      showOverflowTooltip: true,
      formatter: (row, column, cellValue, index) => {
        let r = cellValue ? '正常响应数据' : '无数据'
        return r
      }
    },
    {
      prop: 'responseHttpStatus',
      label: 'http状态码',
    },
    {
      prop: 'responseBusinessStatus',
      label: '业务状态码',
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
  ],

})

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '查询',
  loading: false,
  permission: 'admin:web:OpenplatformOpenapiRecord:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doOpenplatformOpenapiRecordPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return openplatformOpenapiRecordPageApi({...reactiveData.form,...pageQuery})
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
  let openplatformOpenapiRecordIdData = {openplatformOpenapiRecordId: row.id}
  let tableRowButtons:Array<any> = [
    {
      txt: '查看参数',
      text: true,
      permission: 'admin:web:openplatformOpenapiRecordParam:detail',
      // 跳转到编辑
      route: {path: '/admin/openplatformOpenapiRecordManageParamView',query: idData}
    },

  ]
  if (row.isExistProviderRecord) {
   tableRowButtons.push(    {
     txt: '查看供应商调用记录',
     text: true,
     permission: 'admin:web:OpenplatformProviderRecord:pageQuery',
     // 跳转到编辑
     route: {path: '/admin/openplatformProviderRecordManagePage',query: openplatformOpenapiRecordIdData}
   })
  }
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
           :dataMethod="doOpenplatformOpenapiRecordPageApi"
           @dataMethodDataLoading="(loading) => submitAttrs.loading=loading"
           
           :paginationProps="tablePaginationProps"
           :columns="reactiveData.tableColumns">

    <!--  操作按钮  -->
    <template #defaultAppend>
      <el-table-column label="操作" width="200">
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