<script setup name="DataCompanyMd5IdsManagePage" lang="ts">
/**
 * 企业md5ids管理页面
 */
import {reactive, ref} from 'vue'
import { page as dataCompanyMd5IdsPageApi, remove as dataCompanyMd5IdsRemoveApi} from "../../../api/temp/admin/dataCompanyMd5IdsAdminApi"
import {pageFormItems} from "../../../components/temp/admin/dataCompanyMd5IdsManage";


const tableRef = ref(null)

// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
  },
  formComps: pageFormItems,
  tableColumns: [
    {
      prop: 'companyId',
      label: '企业id',
    },
    {
      prop: 'parentIdUuid0',
      label: 'uuid0',
    },
    {
      prop: 'parentIdUuid1',
      label: 'uuid1',
    },
    {
      prop: 'parentIdUuid2',
      label: 'uuid2',
    },
    {
      prop: 'parentIdUuid3',
      label: 'uuid3',
    },
    {
      prop: 'parentIdUuid4',
      label: 'uuid4',
    },
    {
      prop: 'parentIdUuid5',
      label: 'uuid5',
    },
    {
      prop: 'parentIdUuid6',
      label: 'uuid6',
    },
    {
      prop: 'parentIdUuid7',
      label: 'uuid7',
    },
    {
      prop: 'parentIdUuid8',
      label: 'uuid8',
    },
    {
      prop: 'parentIdUuid9',
      label: 'uuid9',
    },
    {
      prop: 'parentIdUuid10',
      label: 'uuid10',
    },
    {
      prop: 'parentIdUuid11',
      label: 'uuid11',
    },
  ],

})

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '查询',
  loading: false,
  permission: 'admin:web:dataCompanyMd5Ids:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doDataCompanyMd5IdsPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return dataCompanyMd5IdsPageApi({...reactiveData.form,...pageQuery})
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
      permission: 'admin:web:dataCompanyMd5Ids:update',
      // 跳转到编辑
      route: {path: '/admin/DataCompanyMd5IdsManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:dataCompanyMd5Ids:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return dataCompanyMd5IdsRemoveApi({id: row.id}).then(res => {
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
      <PtButton permission="admin:web:dataCompanyMd5Ids:create" route="/admin/DataCompanyMd5IdsManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doDataCompanyMd5IdsPageApi"
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