<script setup name="DictManagePage" lang="ts">
/**
 * 字典管理页面
 */
import {reactive, ref} from 'vue'
import {page as dictPageApi, remove as dictRemoveApi} from "../../api/admin/dictAdminApi"
import {pageFormItems} from "../../components/admin/dictManage";

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
      prop: 'value',
      label: '字典值',
      showOverflowTooltip: true
    },
    {
      prop: 'valueUnit',
      label: '字典值单位',
      width: 100,
    },
    {
      prop: 'parentName',
      label: '父级',
      showOverflowTooltip: true
    },

    {
      prop: 'isSystem',
      label: '是否系统',
      width: 80,
      formatter: (row, column, cellValue, index) => {
        return cellValue ? '系统' : '自定义'
      }
    },
    {
      prop: 'isPublic',
      label: '是否公共',
      width: 80,
      formatter: (row, column, cellValue, index) => {
        return cellValue ? '公共' : '私有'
      }
    },
    {
      prop: 'isGroup',
      label: '是否字典组',
      width: 100,
      formatter: (row, column, cellValue, index) => {
        return cellValue ? '字典组' : '非字典组'
      }
    },
    {
      prop: 'isItem',
      label: '是否字典项',
      width: 100,
      formatter: (row, column, cellValue, index) => {
        return cellValue ? '字典项' : '非字典项'
      }
    },
    {
      prop: 'isDisabled',
      label: '是否禁用',
      width: 80,
      formatter: (row, column, cellValue, index) => {
        let r = cellValue ? '禁用' : '启用'
        if(cellValue && row.disabledReason){
          r = r + `(${row.disabledReason})`
        }
        return r
      }
    },
    {
      prop: 'privateFlag',
      label: '私有标识',
      formatter: (row, column, cellValue, index) => {
        let r = cellValue
        if(cellValue && row.privateFlagMemo){
          r = r + `(${row.privateFlagMemo})`
        }
        return r
      },
      showOverflowTooltip: true
    },
    {
      prop: 'groupFlag',
      label: '分组标识',
      formatter: (row, column, cellValue, index) => {
        let r = cellValue
        if(cellValue && row.groupFlagMemo){
          r = r + `(${row.groupFlagMemo})`
        }
        return r
      },
      showOverflowTooltip: true
    },
    {
      prop: 'tags',
      label: '标签',
      showOverflowTooltip: true
    },
    {
      prop: 'seq',
      label: '排序',
      width: 60,
    },
    {
      prop: 'relatedGroupCode',
      label: '关联字典组编码',
      showOverflowTooltip: true
    },
    {
      prop: 'remark',
      label: '描述',
      showOverflowTooltip: true
    }
  ],

})

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '查询',
  loading: false,
  permission: 'admin:web:dict:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doDictPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return dictPageApi({...reactiveData.form,...pageQuery})
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
      permission: 'admin:web:dict:create',
      // 跳转到编辑
      route: {path: '/admin/dictManageAdd',query: idData}
    },
    {
      txt: '编辑',
      position: 'more',
      text: true,
      permission: 'admin:web:dict:update',
      // 跳转到编辑
      route: {path: '/admin/dictManageUpdate',query: idData}
    },
    {
      txt: '删除',
      position: 'more',
      text: true,
      permission: 'admin:web:dict:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return dictRemoveApi({id: row.id}).then(res => {
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
          :layout="[[8,8],3]"
          :comps="reactiveData.formComps">
    <template #buttons>
      <PtButton permission="admin:web:dict:create" route="/admin/dictManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doDictPageApi"
           @dataMethodDataLoading="(loading) => submitAttrs.loading=loading"
           :dataMethodResultHandleConvertToTree="true"
           :paginationProps="tablePaginationProps"
           :columns="reactiveData.tableColumns">

    <!--  操作按钮  -->
    <template #defaultAppend>
      <el-table-column label="操作" width="210">
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
