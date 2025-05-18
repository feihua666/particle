<script setup name="DataCompanyAnnualReportManagePage" lang="ts">
/**
 * 企业年报管理页面
 */
import {reactive, ref} from 'vue'
import { page as dataCompanyAnnualReportPageApi, remove as dataCompanyAnnualReportRemoveApi} from "../../../api/company/admin/dataCompanyAnnualReportAdminApi"
import {pageFormItems} from "../../../components/company/admin/dataCompanyAnnualReportManage";


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
      prop: 'year',
      label: '年报年度',
    },
    {
      prop: 'companyName',
      label: '企业名称',
    },
    {
      prop: 'uscc',
      label: '统一社会信用代码',
    },
    {
      prop: 'regNo',
      label: '注册号',
    },
    {
      prop: 'capital',
      label: '资金数额（万元）',
    },
    {
      prop: 'capitalCurrencyDictName',
      label: '资金数额币种',
    },
    {
      prop: 'operatorCompanyPersonId',
      label: '经营者名称',
    },
    {
      prop: 'operatorName',
      label: '经营者名称',
    },
    {
      prop: 'postalAddress',
      label: '企业通信地址',
    },
    {
      prop: 'postCode',
      label: '邮政编码',
    },
    {
      prop: 'contactPhone',
      label: '企业联系电话',
    },
    {
      prop: 'email',
      label: '企业电子邮箱',
    },
    {
      prop: 'employeeNum',
      label: '从业人数',
    },
    {
      prop: 'femaleEmployeeNum',
      label: '其中女性从业人数',
    },
    {
      prop: 'statusDictName',
      label: '企业经营状态',
    },
    {
      prop: 'holdingControlInfo',
      label: '企业控股情况',
    },
    {
      prop: 'isHasInvestOrBugEquity',
      label: '是否有投资信息或购买其他公司股权',
    },
    {
      prop: 'isHasWebsite',
      label: '是否有网站或网店',
    },
    {
      prop: 'isHasForeignGuarantee',
      label: '是否有对外提供担保信息',
    },
    {
      prop: 'isHasEquityTransfer',
      label: '有限责任公司本年度是否发生股东股权转让',
    },
    {
      prop: 'normalBusinessScope',
      label: '经营范围（一般项目）',
    },
    {
      prop: 'approvedBusinessScope',
      label: '经营范围（许可项目）',
    },
    {
      prop: 'isIsHasForeignGuaranteePublic',
      label: '是否对外提供担保信息公示',
    },
    {
      prop: 'isFemaleEmployeeNumPublic',
      label: '是否其中女性从业人数公示',
    },
    {
      prop: 'isHoldingControlInfoPublic',
      label: '是否企业控股情况公示',
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
  permission: 'admin:web:dataCompanyAnnualReport:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doDataCompanyAnnualReportPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return dataCompanyAnnualReportPageApi({...reactiveData.form,...pageQuery})
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
      permission: 'admin:web:dataCompanyAnnualReport:update',
      // 跳转到编辑
      route: {path: '/admin/DataCompanyAnnualReportManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:dataCompanyAnnualReport:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return dataCompanyAnnualReportRemoveApi({id: row.id}).then(res => {
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
      <PtButton permission="admin:web:dataCompanyAnnualReport:create" route="/admin/DataCompanyAnnualReportManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doDataCompanyAnnualReportPageApi"
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