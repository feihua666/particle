<script setup name="DataCompanyBasicManagePage" lang="ts">
/**
 * 企业基本信息管理页面
 */
import {reactive, ref} from 'vue'
import { page as dataCompanyBasicPageApi, remove as dataCompanyBasicRemoveApi} from "../../../api/company/admin/dataCompanyBasicAdminApi"
import {pageFormItems} from "../../../components/company/admin/dataCompanyBasicManage";


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
      prop: 'tin',
      label: '纳税人识别号',
    },
    {
      prop: 'statusDictName',
      label: '登记状态',
    },
    {
      prop: 'natureDictName',
      label: '性质',
    },
    {
      prop: 'legalPersonName',
      label: '法人名称',
    },
    {
      prop: 'isLegalPersonNaturalPerson',
      label: '是否法人为自然人',
    },
    {
      prop: 'legalPersonCompanyId',
      label: '法人公司id',
    },
    {
      prop: 'legalPersonCompanyPersonId',
      label: '法人个人id',
    },
    {
      prop: 'typeDictName',
      label: '企业类型',
    },
    {
      prop: 'regAddress',
      label: '注册地址',
    },
    {
      prop: 'regAddressPostalCode',
      label: '注册地址',
    },
    {
      prop: 'businessAddress',
      label: '经营地址',
    },
    {
      prop: 'businessAddressPostalCode',
      label: '经营地址',
    },
    {
      prop: 'establishDate',
      label: '成立日期',
    },
    {
      prop: 'businessFromDate',
      label: '营业期限开始日期',
    },
    {
      prop: 'businessToDate',
      label: '营业期限终止日期',
    },
    {
      prop: 'approveDate',
      label: '核准日期',
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
      prop: 'revokeDate',
      label: '吊销日期',
    },
    {
      prop: 'revokeReason',
      label: '吊销原因',
    },
    {
      prop: 'businessScope',
      label: '经营范围',
    },
    {
      prop: 'regInstituteCompanyId',
      label: '注册机关公司id',
    },
    {
      prop: 'regInstituteName',
      label: '注册机关名称',
    },
    {
      prop: 'regCapital',
      label: '注册资本（万元）',
    },
    {
      prop: 'regCapitalCurrencyDictName',
      label: '注册资金币种',
    },
    {
      prop: 'subCapital',
      label: '认缴出资金额（万元）',
    },
    {
      prop: 'subCapitalCurrencyDictName',
      label: '认缴出资金额币种',
    },
    {
      prop: 'subCapitalTypeDictName',
      label: '认缴出资方式',
    },
    {
      prop: 'subCapitalDate',
      label: '认缴出资日期',
    },
    {
      prop: 'actualCapital',
      label: '实缴出资金额（万元）',
    },
    {
      prop: 'actualCapitalCurrencyDictName',
      label: '实缴出资金额币种',
    },
    {
      prop: 'capitalTypeDictName',
      label: '实缴出资方式',
    },
    {
      prop: 'actualCapitalDate',
      label: '实缴出资日期',
    },
    {
      prop: 'mobile',
      label: '手机号码',
    },
    {
      prop: 'telephone',
      label: '电话号码',
    },
    {
      prop: 'email',
      label: '邮箱',
    },
    {
      prop: 'employeeNum',
      label: '从业人数',
    },
    {
      prop: 'socialSecurityNum',
      label: '社保人数',
    },
    {
      prop: 'latestYearReportYear',
      label: '最新年报年份',
    },
    {
      prop: 'scaleTypeDictName',
      label: '规模类型',
    },
    {
      prop: 'longitude',
      label: '经度',
    },
    {
      prop: 'latitude',
      label: '纬度',
    },
    {
      prop: 'industryMainDictName',
      label: '所属行业',
    },
    {
      prop: 'industryBigDictName',
      label: '所属行业',
    },
    {
      prop: 'industryMiddleDictName',
      label: '所属行业',
    },
    {
      prop: 'industrySmallDictName',
      label: '所属行业',
    },
    {
      prop: 'provinceAreaId',
      label: '所在省份',
    },
    {
      prop: 'cityAreaId',
      label: '所在城市',
    },
    {
      prop: 'countyAreaId',
      label: '所在区县',
    },
    {
      prop: 'isListed',
      label: '是否上市',
    },
    {
      prop: 'stockCode',
      label: '股票代码',
    },
    {
      prop: 'listedTypeDictName',
      label: '上市类型',
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
  permission: 'admin:web:dataCompanyBasic:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doDataCompanyBasicPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return dataCompanyBasicPageApi({...reactiveData.form,...pageQuery})
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
      permission: 'admin:web:dataCompanyBasic:update',
      // 跳转到编辑
      route: {path: '/admin/DataCompanyBasicManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:dataCompanyBasic:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return dataCompanyBasicRemoveApi({id: row.id}).then(res => {
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
      <PtButton permission="admin:web:dataCompanyBasic:create" route="/admin/DataCompanyBasicManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doDataCompanyBasicPageApi"
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
