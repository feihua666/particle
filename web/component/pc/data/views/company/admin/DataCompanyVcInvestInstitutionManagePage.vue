<script setup name="DataCompanyVcInvestInstitutionManagePage" lang="ts">
/**
 * 企业投资机构管理页面
 */
import {reactive, ref} from 'vue'
import { page as dataCompanyVcInvestInstitutionPageApi, remove as dataCompanyVcInvestInstitutionRemoveApi} from "../../../api/company/admin/dataCompanyVcInvestInstitutionAdminApi"
import {pageFormItems} from "../../../components/company/admin/dataCompanyVcInvestInstitutionManage";


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
      prop: 'isRelatedCompany',
      label: '是否对应公司',
    },
    {
      prop: 'name',
      label: '机构名称',
    },
    {
      prop: 'enName',
      label: '机构英文名称',
    },
    {
      prop: 'logoUrl',
      label: '机构logo地址',
    },
    {
      prop: 'websiteUrl',
      label: '机构网址',
    },
    {
      prop: 'establishYear',
      label: '成立年份',
    },
    {
      prop: 'establishDate',
      label: '成立日期',
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
      prop: 'address',
      label: '机构地址',
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
      prop: 'weiboUrl',
      label: '微博地址',
    },
    {
      prop: 'wechatNo',
      label: '微信号',
    },
    {
      prop: 'description',
      label: '机构介绍',
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
  permission: 'admin:web:dataCompanyVcInvestInstitution:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doDataCompanyVcInvestInstitutionPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return dataCompanyVcInvestInstitutionPageApi({...reactiveData.form,...pageQuery})
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
      permission: 'admin:web:dataCompanyVcInvestInstitution:update',
      // 跳转到编辑
      route: {path: '/admin/DataCompanyVcInvestInstitutionManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:dataCompanyVcInvestInstitution:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return dataCompanyVcInvestInstitutionRemoveApi({id: row.id}).then(res => {
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
      <PtButton permission="admin:web:dataCompanyVcInvestInstitution:create" route="/admin/DataCompanyVcInvestInstitutionManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doDataCompanyVcInvestInstitutionPageApi"
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