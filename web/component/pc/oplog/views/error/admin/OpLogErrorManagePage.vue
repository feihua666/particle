<script setup name="OpLogErrorManagePage" lang="ts">
/**
 * 操作异常日志管理页面
 */
import {reactive, ref} from 'vue'
import {page as opLogErrorPageApi, remove as opLogErrorRemoveApi} from "../../../api/error/admin/opLogErrorAdminApi"
import {pageFormItems} from "../../../components/error/admin/opLogErrorManage";


const tableRef = ref(null)

// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
  },
  formComps: pageFormItems,
  tableColumns: [
    {
      prop: 'traceId',
      label: 'traceId',
      showOverflowTooltip: true
    },
    {
      prop: 'userId',
      label: '用户id',
      showOverflowTooltip: true
    },
    {
      prop: 'userName',
      label: '用户姓名',
      showOverflowTooltip: true
    },
    {
      prop: 'userNickname',
      label: '用户昵称',
      showOverflowTooltip: true
    },
    {
      prop: 'userAvatar',
      label: '用户头像',
      showOverflowTooltip: true
    },
    {
      prop: 'requestUrl',
      label: '请求地址',
      showOverflowTooltip: true
    },
    {
      prop: 'requestMethod',
      label: '请求方法',
      showOverflowTooltip: true
    },
    {
      prop: 'requestHeaders',
      label: '请求头信息',
      showOverflowTooltip: true
    },
    {
      prop: 'requestParams',
      label: '请求参数',
      showOverflowTooltip: true
    },
    {
      prop: 'requestBody',
      label: '请求内容',
      showOverflowTooltip: true
    },
    {
      prop: 'requestIp',
      label: '请求ip',
      showOverflowTooltip: true
    },
    {
      prop: 'responseStatus',
      label: '响应状态码',
      showOverflowTooltip: true
    },
    {
      prop: 'responseHeaders',
      label: '响应头信息',
      showOverflowTooltip: true
    },
    {
      prop: 'responseBody',
      label: '响应内容',
      showOverflowTooltip: true
    },
    {
      prop: 'localHostIp',
      label: '本地主机ip',
      showOverflowTooltip: true
    },
    {
      prop: 'localHostName',
      label: '本地主机名称',
      showOverflowTooltip: true
    },
    {
      prop: 'errorAt',
      label: '异常发生时间',
      showOverflowTooltip: true
    },
  ],

})

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '查询',
  loading: false,
  permission: 'admin:web:opLogError:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doOpLogErrorPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return opLogErrorPageApi({...reactiveData.form,...pageQuery})
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
      txt: '查看异常内容',
      text: true,
      permission: 'admin:web:opLogErrorContent:detail',
      // 跳转到编辑
      route: {path: '/admin/OpLogErrorContentViewPage',query: idData}
    },
    {
      txt: '删除',
      text: true,
      position: 'more',
      permission: 'admin:web:opLogError:delete',
      methodConfirmText: `确定要删除 traceId=${row.traceId} 的异常数据吗？`,
      // 删除操作
      method(){
        return opLogErrorRemoveApi({id: row.id}).then(res => {
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
          label-width="100"
          :comps="reactiveData.formComps">
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doOpLogErrorPageApi"
           @dataMethodDataLoading="(loading) => submitAttrs.loading=loading"

           :paginationProps="tablePaginationProps"
           :columns="reactiveData.tableColumns">

    <!--  操作按钮  -->
    <template #defaultAppend>
      <el-table-column label="操作" width="240">
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
