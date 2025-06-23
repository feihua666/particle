<script setup name="DataCompanyIprSoftwareCopyrightManagePage" lang="ts">
/**
 * 企业知识产权软件著作管理页面
 */
import {reactive, ref} from 'vue'
import { page as dataCompanyIprSoftwareCopyrightPageApi, remove as dataCompanyIprSoftwareCopyrightRemoveApi} from "../../../api/company/admin/dataCompanyIprSoftwareCopyrightAdminApi"
import {pageFormItems} from "../../../components/company/admin/dataCompanyIprSoftwareCopyrightManage";


const tableRef = ref(null)

// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
  },
  formComps: pageFormItems,
  tableColumns: [
    {
      prop: 'regNo',
      label: '注册号',
    },
    {
      prop: 'categoryNo',
      label: '分类号',
    },
    {
      prop: 'name',
      label: '软件全称',
    },
    {
      prop: 'nameSimple',
      label: '软件简称',
    },
    {
      prop: 'versionNo',
      label: '版本号',
    },
    {
      prop: 'copyrightOwner',
      label: '著作权人',
    },
    {
      prop: 'isCopyrightOwnerNaturalPerson',
      label: '是否著作权人为自然人',
    },
    {
      prop: 'copyrightOwnerCompanyId',
      label: '著作权人公司id',
    },
    {
      prop: 'copyrightOwnerCompanyPersonId',
      label: '著作权人个人id',
    },
    {
      prop: 'firstPublicDate',
      label: '首次发表日期',
    },
    {
      prop: 'regDate',
      label: '登记日期',
    },
    {
      prop: 'country',
      label: '国家',
    },
    {
      prop: 'latestHandleAt',
      label: '最后处理时间',
    },
  ],

})

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '查询',
  loading: false,
  permission: 'admin:web:dataCompanyIprSoftwareCopyright:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doDataCompanyIprSoftwareCopyrightPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return dataCompanyIprSoftwareCopyrightPageApi({...reactiveData.form,...pageQuery})
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
      permission: 'admin:web:dataCompanyIprSoftwareCopyright:update',
      // 跳转到编辑
      route: {path: '/admin/DataCompanyIprSoftwareCopyrightManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:dataCompanyIprSoftwareCopyright:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return dataCompanyIprSoftwareCopyrightRemoveApi({id: row.id}).then(res => {
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
      <PtButton permission="admin:web:dataCompanyIprSoftwareCopyright:create" route="/admin/DataCompanyIprSoftwareCopyrightManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doDataCompanyIprSoftwareCopyrightPageApi"
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