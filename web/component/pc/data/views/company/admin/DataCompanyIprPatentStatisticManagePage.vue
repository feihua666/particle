<script setup name="DataCompanyIprPatentStatisticManagePage" lang="ts">
/**
 * 企业知识产权专利统计管理页面
 */
import {reactive, ref} from 'vue'
import { page as dataCompanyIprPatentStatisticPageApi, remove as dataCompanyIprPatentStatisticRemoveApi} from "../../../api/company/admin/dataCompanyIprPatentStatisticAdminApi"
import {pageFormItems} from "../../../components/company/admin/dataCompanyIprPatentStatisticManage";


const tableRef = ref(null)

// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
  },
  formComps: pageFormItems,
  tableColumns: [
    {
      prop: 'companyIprPatentId',
      label: '企业知识产权专利表id',
    },
    {
      prop: 'familyNum',
      label: '同族专利数量',
    },
    {
      prop: 'extFamilyNum',
      label: '扩展同族专利数量',
    },
    {
      prop: 'citedNum',
      label: '被引证数量',
    },
    {
      prop: 'quoteNum',
      label: '引证专利数量',
    },
    {
      prop: 'claimNum',
      label: '权利要求数量',
    },
    {
      prop: 'independentClaimNum',
      label: '独权数',
    },
    {
      prop: 'dependentClaimNum',
      label: '从权数',
    },
    {
      prop: 'transferNum',
      label: '转让次数',
    },
    {
      prop: 'licenseNum',
      label: '许可次数',
    },
    {
      prop: 'pledgeNum',
      label: '质押次数',
    },
    {
      prop: 'invalidNum',
      label: '无效次数',
    },
    {
      prop: 'litigationNum',
      label: '诉讼次数',
    },
    {
      prop: 'ipcCategoryNum',
      label: 'IPC分类数量',
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
  permission: 'admin:web:dataCompanyIprPatentStatistic:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doDataCompanyIprPatentStatisticPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return dataCompanyIprPatentStatisticPageApi({...reactiveData.form,...pageQuery})
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
      permission: 'admin:web:dataCompanyIprPatentStatistic:update',
      // 跳转到编辑
      route: {path: '/admin/DataCompanyIprPatentStatisticManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:dataCompanyIprPatentStatistic:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return dataCompanyIprPatentStatisticRemoveApi({id: row.id}).then(res => {
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
      <PtButton permission="admin:web:dataCompanyIprPatentStatistic:create" route="/admin/DataCompanyIprPatentStatisticManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doDataCompanyIprPatentStatisticPageApi"
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