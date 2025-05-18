<script setup name="DataCompanyVcFinancingManagePage" lang="ts">
/**
 * 企业融资管理页面
 */
import {reactive, ref} from 'vue'
import { page as dataCompanyVcFinancingPageApi, remove as dataCompanyVcFinancingRemoveApi} from "../../../api/company/admin/dataCompanyVcFinancingAdminApi"
import {pageFormItems} from "../../../components/company/admin/dataCompanyVcFinancingManage";


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
      label: '企业表ID',
    },
    {
      prop: 'companyVcProductId',
      label: '公司产品id',
    },
    {
      prop: 'roundDictName',
      label: '融资轮次',
    },
    {
      prop: 'amount',
      label: '融资金额（万元）',
    },
    {
      prop: 'amountCurrencyDictName',
      label: '融资金额币种',
    },
    {
      prop: 'valuation',
      label: '估值',
    },
    {
      prop: 'financingDate',
      label: '融资日期',
    },
    {
      prop: 'publishAt',
      label: '报道时间',
    },
    {
      prop: 'publishTitle',
      label: '报道标题',
    },
    {
      prop: 'publishUrl',
      label: '报道链接地址',
    },
    {
      prop: 'publishSnapshotUrl',
      label: '报道快照链接地址',
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
  permission: 'admin:web:dataCompanyVcFinancing:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doDataCompanyVcFinancingPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return dataCompanyVcFinancingPageApi({...reactiveData.form,...pageQuery})
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
      permission: 'admin:web:dataCompanyVcFinancing:update',
      // 跳转到编辑
      route: {path: '/admin/DataCompanyVcFinancingManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:dataCompanyVcFinancing:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return dataCompanyVcFinancingRemoveApi({id: row.id}).then(res => {
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
      <PtButton permission="admin:web:dataCompanyVcFinancing:create" route="/admin/DataCompanyVcFinancingManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doDataCompanyVcFinancingPageApi"
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
