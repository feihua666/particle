<script setup name="TrackingPageManagePage" lang="ts">
/**
 * 埋点页面管理页面
 */
import {reactive, ref} from 'vue'
import { page as trackingPagePageApi, remove as trackingPageRemoveApi} from "../../api/admin/trackingPageAdminApi"
import {pageFormItems} from "../../compnents/admin/trackingPageManage";


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
      label: '页面名称',
    },
    {
      prop: 'code',
      label: '页面编码',
    },

    {
      prop: 'imageUrl',
      label: '页面图片地址',
      columnView: 'image'
    },
    {
      prop: 'absoluteUrl',
      label: '页面访问地址',
      showOverflowTooltip: true
    },
    {
      prop: 'pathMemo',
      label: '路径说明',
      showOverflowTooltip: true
    },
    {
      prop: 'pageVersion',
      label: '页面版本',
    },
    {
      prop: 'groupFlag',
      label: '分组标识',
    },
    {
      prop: 'parentName',
      label: '父级',
    },
    {
      prop: 'seq',
      label: '排序',
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
  permission: 'admin:web:TrackingPage:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doTrackingPagePageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return trackingPagePageApi({...reactiveData.form,...pageQuery})
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
  let codeData = {code: row.code}
  let tableRowButtons = [
    {
      txt: '编辑',
      text: true,
      permission: 'admin:web:TrackingPage:update',
      // 跳转到编辑
      route: {path: '/admin/TrackingPageManageUpdate',query: idData}
    },
    {
      txt: '埋点数据',
      text: true,
      icon: 'View',
      type: 'primary',
      permission: 'admin:web:TrackingPageRecord:pageQuery',
      // 跳转到埋点数据
      route: {path: '/admin/trackingPageRecordPopoverManagePage',query: codeData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:TrackingPage:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return trackingPageRemoveApi({id: row.id}).then(res => {
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
      <PtButton permission="admin:web:TrackingPage:create" route="/admin/TrackingPageManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doTrackingPagePageApi"
           @dataMethodDataLoading="(loading) => submitAttrs.loading=loading"
           :dataMethodResultHandleConvertToTree="true"
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