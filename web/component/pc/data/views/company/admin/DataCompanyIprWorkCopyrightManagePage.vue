<script setup name="DataCompanyIprWorkCopyrightManagePage" lang="ts">
/**
 * 企业知识产权作品著作管理页面
 */
import {reactive, ref} from 'vue'
import { page as dataCompanyIprWorkCopyrightPageApi, remove as dataCompanyIprWorkCopyrightRemoveApi} from "../../../api/company/admin/dataCompanyIprWorkCopyrightAdminApi"
import {pageFormItems} from "../../../components/company/admin/dataCompanyIprWorkCopyrightManage";


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
      prop: 'name',
      label: '作品名称',
    },
    {
      prop: 'typeName',
      label: '作品类别',
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
      prop: 'country',
      label: '国家',
    },
    {
      prop: 'province',
      label: '省',
    },
    {
      prop: 'city',
      label: '市',
    },
    {
      prop: 'author',
      label: '作者',
    },
    {
      prop: 'completeDate',
      label: '创作完成日期',
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
      prop: 'publicDate',
      label: '发布日期',
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
  permission: 'admin:web:dataCompanyIprWorkCopyright:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doDataCompanyIprWorkCopyrightPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return dataCompanyIprWorkCopyrightPageApi({...reactiveData.form,...pageQuery})
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
      permission: 'admin:web:dataCompanyIprWorkCopyright:update',
      // 跳转到编辑
      route: {path: '/admin/DataCompanyIprWorkCopyrightManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:dataCompanyIprWorkCopyright:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return dataCompanyIprWorkCopyrightRemoveApi({id: row.id}).then(res => {
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
      <PtButton permission="admin:web:dataCompanyIprWorkCopyright:create" route="/admin/DataCompanyIprWorkCopyrightManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doDataCompanyIprWorkCopyrightPageApi"
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