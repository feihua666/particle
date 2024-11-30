<script setup name="DeptManagePage" lang="ts">
/**
 * 部门管理页面
 */
import {reactive, ref} from 'vue'
import { page as DeptPageApi, remove as DeptRemoveApi} from "../../api/admin/deptAdminApi"
import {pageFormItems} from "../../components/admin/deptManage";


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
      label: '部门名称',
      showOverflowTooltip: true
    },
    {
      prop: 'code',
      label: '部门编码',
      showOverflowTooltip: true
    },

    {
      prop: 'typeDictName',
      label: '类型',
      showOverflowTooltip: true
    },
    {
      prop: 'masterUserName',
      label: '负责人',
      formatter: (row, column, cellValue, index) => {
        let r = cellValue || row.masterUserNickname
        return r
      },
      showOverflowTooltip: true
    },
    {
      prop: 'isVirtual',
      label: '是否虚拟',
      formatter: (row, column, cellValue, index) => {
        return cellValue ? '虚拟部门' : '实体部门'
      }
    },
    {
      prop: 'isComp',
      label: '是否公司',
      formatter: (row, column, cellValue, index) => {
        return cellValue ? '公司' : '部门'
      }
    },
    {
      prop: 'parentName',
      label: '父级',
      showOverflowTooltip: true
    },
    {
      prop: 'remark',
      label: '描述',
      showOverflowTooltip: true
    },
  ],

})

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '查询',
  loading: false,
  permission: 'admin:web:dept:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doDeptPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return DeptPageApi({...reactiveData.form,...pageQuery})
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
      permission: 'admin:web:dept:update',
      // 跳转到编辑
      route: {path: '/admin/DeptManageUpdate',query: {id: row.id,masterUserId: row.masterUserId,masterUserName: row.masterUserName || row.masterUserNickname}}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:dept:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return DeptRemoveApi({id: row.id}).then(res => {
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
          labelWidth="120"
          :comps="reactiveData.formComps">
    <template #buttons>
      <PtButton permission="admin:web:dept:create" route="/admin/DeptManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doDeptPageApi"
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
