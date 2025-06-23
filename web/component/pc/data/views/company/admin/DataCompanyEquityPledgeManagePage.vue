<script setup name="DataCompanyEquityPledgeManagePage" lang="ts">
/**
 * 企业股权出质管理页面
 */
import {reactive, ref} from 'vue'
import { page as dataCompanyEquityPledgePageApi, remove as dataCompanyEquityPledgeRemoveApi} from "../../../api/company/admin/dataCompanyEquityPledgeAdminApi"
import {pageFormItems} from "../../../components/company/admin/dataCompanyEquityPledgeManage";


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
      prop: 'regNo',
      label: '登记编号',
    },
    {
      prop: 'pledgor',
      label: '出质人',
    },
    {
      prop: 'isPledgorNaturalPerson',
      label: '是否出质人为自然人',
    },
    {
      prop: 'pledgorCompanyId',
      label: '出质人公司id',
    },
    {
      prop: 'pledgorCompanyPersonId',
      label: '出质人个人id',
    },
    {
      prop: 'pledgorLicenseNo',
      label: '出质人证照/证件号码',
    },
    {
      prop: 'equityAmount',
      label: '出质股权数额（万股）',
    },
    {
      prop: 'pledgee',
      label: '质权人',
    },
    {
      prop: 'isPledgeeNaturalPerson',
      label: '是否质权人为自然人',
    },
    {
      prop: 'pledgeeCompanyId',
      label: '质权人公司id',
    },
    {
      prop: 'pledgeeCompanyPersonId',
      label: '质权人个人id',
    },
    {
      prop: 'pledgeeLicenseNo',
      label: '质权人证照/证件号码',
    },
    {
      prop: 'regDate',
      label: '股权出质设立登记日期',
    },
    {
      prop: 'statusName',
      label: '状态',
    },
    {
      prop: 'publishDate',
      label: '公示日期',
    },
    {
      prop: 'changeSituation',
      label: '变化情况',
    },
    {
      prop: 'cancelDate',
      label: '注销日期',
    },
    {
      prop: 'cancelReason',
      label: '注销原因',
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
  permission: 'admin:web:dataCompanyEquityPledge:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doDataCompanyEquityPledgePageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return dataCompanyEquityPledgePageApi({...reactiveData.form,...pageQuery})
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
      permission: 'admin:web:dataCompanyEquityPledge:update',
      // 跳转到编辑
      route: {path: '/admin/DataCompanyEquityPledgeManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:dataCompanyEquityPledge:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return dataCompanyEquityPledgeRemoveApi({id: row.id}).then(res => {
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
      <PtButton permission="admin:web:dataCompanyEquityPledge:create" route="/admin/DataCompanyEquityPledgeManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doDataCompanyEquityPledgePageApi"
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