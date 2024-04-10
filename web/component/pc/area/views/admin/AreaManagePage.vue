<script setup name="AreaManagePage" lang="ts">
/**
 * 区域管理页面
 */
import {reactive, ref} from 'vue'
import {page as areaPageApi, remove as areaRemoveApi} from "../../api/admin/areaAdminApi"
import {treeQueryComps} from '../../../treeQueryComps'
import {pageFormItems} from "../../compnents/admin/areaManage";

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
      width: 150,
      showOverflowTooltip: true
    },
    {
      prop: 'code',
      label: '编码',
      showOverflowTooltip: true
    },
    {
      prop: 'nameSimple',
      label: '简称'
    },
    {
      prop: 'spellFirst',
      label: '首字母',
      width: 60,
    },
    {
      prop: 'spellSimple',
      label: '简拼',
      width: 50,
    },

    {
      prop: 'spell',
      label: '全拼',
      showOverflowTooltip: true
    },
    {
      prop: 'parentName',
      label: '父级'
    },
    {
      prop: 'typeDictName',
      label: '类型',
      width: 50,
    },
    {
      prop: 'longitude',
      label: '经度',
      showOverflowTooltip: true
    },
    {
      prop: 'latitude',
      label: '纬度',
      showOverflowTooltip: true
    },
    {
      prop: 'seq',
      label: '排序',
      width: 50,
    },
    {
      prop: 'remark',
      label: '描述'
    }
  ],

})

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '查询',
  loading: false,
  permission: 'admin:web:area:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doAreaPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return areaPageApi({...reactiveData.form,...pageQuery})
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
      txt: '添加子级',
      text: true,
      permission: 'admin:web:area:create',
      // 跳转到编辑
      route: {path: '/admin/areaManageAdd',query: idData}
    },
    {
      txt: '编辑',
      position: 'more',
      text: true,
      permission: 'admin:web:area:update',
      // 跳转到编辑
      route: {path: '/admin/areaManageUpdate',query: idData}
    },
    {
      txt: '删除',
      position: 'more',
      text: true,
      permission: 'admin:web:area:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return areaRemoveApi({id: row.id}).then(res => {
          // 删除成功后刷新一下表格
          submitMethod()
          return Promise.resolve(res)
        })
      }
    },

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
      <PtButton permission="admin:web:area:create" route="/admin/areaManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doAreaPageApi"
           @dataMethodDataLoading="(loading) => submitAttrs.loading=loading"
           :dataMethodResultHandleConvertToTree="true"
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