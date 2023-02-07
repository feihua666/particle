<script setup name="LowCodeModelManagePage" lang="ts">
/**
 * 低代码模型管理页面
 */
import {reactive, ref} from 'vue'
import { page as lowCodeModelPageApi, remove as lowCodeModelRemoveApi} from "../../../api/generator/admin/lowCodeModelAdminApi"

const tableRef = ref(null)

// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
  },
  formComps: [

    {
      field: {
        name: 'name'
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '名称'
        },
        compProps: {
          clearable: true,
          placeholder: '左前缀匹配'
        }
      }
    },
    {
      field: {
        name: 'tableName'
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '表名称'
        },
        compProps: {
          clearable: true,
          placeholder: '左前缀匹配'
        }
      }
    },
  ],
  tableColumns: [
    {
      prop: 'name',
      label: '名称',
    },
    {
      prop: 'nameEn',
      label: '英文',
    },
    {
      prop: 'tableName',
      label: '表名称',
    },
    {
      prop: 'tableType',
      label: '模型类型',
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
  permission: 'admin:web:lowcodeModel:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doLowCodeModelPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return lowCodeModelPageApi({...reactiveData.form,...pageQuery})
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
      permission: 'admin:web:lowcodeModel:update',
      // 跳转到编辑
      route: {path: '/admin/lowCodeModelManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:lowcodeModel:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return lowCodeModelRemoveApi(idData).then(res => {
          // 删除成功后刷新一下表格
          submitMethod()
          return Promise.resolve(res)
        })
      }
    },
    {
      txt: '根据模型装载模型项',
      text: true,
      permission: 'admin:web:lowcodeModel:loadByModelAndDatasource',
      methodConfirmText: `如果存在已添加或装载的模型项将会被清空，确定要装载 ${row.name} 吗？`,
      // 跳转到编辑
      route: {path: '/admin/lowCodeModelManageLoadModelItemByModelAndDataSource',query: idData}
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
      <PtButton permission="admin:web:lowcodeModel:create" route="/admin/lowCodeModelManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doLowCodeModelPageApi"
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