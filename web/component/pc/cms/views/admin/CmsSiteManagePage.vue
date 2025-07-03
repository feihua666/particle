<script setup name="CmsSiteManagePage" lang="ts">
/**
 * 站点管理页面
 */
import {reactive, ref} from 'vue'
import { page as cmsSitePageApi, remove as cmsSiteRemoveApi} from "../../api/admin/cmsSiteAdminApi"
import {pageFormItems} from "../../components/admin/cmsSiteManage";


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
      label: '站点编码',
    },
    {
      prop: 'name',
      label: '站点名称',
    },
    {
      prop: 'domain',
      label: '站点域名',
    },
    {
      prop: 'path',
      label: '上下文路径',
    },
    {
      prop: 'templatePath',
      label: '站点模板路径',
    },
    {
      prop: 'templateIndex',
      label: '站点首页模板',
    },
    {
      prop: 'staticPath',
      label: '静态页路径',
    },
    {
      prop: 'isPrimeSite',
      label: '是否主站点',
      formatter: (row, column, cellValue, index) => {
        return cellValue ? '是' : '否'
      }
    },
    {
      prop: 'pv',
      label: '页面访问量',
    },
    {
      prop: 'iv',
      label: '页面访问ip数',
    },
    {
      prop: 'uv',
      label: '页面访问用户数',
    },
  ],

})

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '查询',
  loading: false,
  permission: 'admin:web:cmsSite:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doCmsSitePageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return cmsSitePageApi({...reactiveData.form,...pageQuery})
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
      permission: 'admin:web:cmsSite:update',
      // 跳转到编辑
      route: {path: '/admin/CmsSiteManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:cmsSite:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return cmsSiteRemoveApi({id: row.id}).then(res => {
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
      <PtButton permission="admin:web:cmsSite:create" route="/admin/CmsSiteManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doCmsSitePageApi"
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
