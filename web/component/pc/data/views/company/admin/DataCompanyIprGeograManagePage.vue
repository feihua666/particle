<script setup name="DataCompanyIprGeograManagePage" lang="ts">
/**
 * 企业知识产权地理标识管理页面
 */
import {reactive, ref} from 'vue'
import { page as dataCompanyIprGeograPageApi, remove as dataCompanyIprGeograRemoveApi} from "../../../api/company/admin/dataCompanyIprGeograAdminApi"
import {pageFormItems} from "../../../components/company/admin/dataCompanyIprGeograManage";


const tableRef = ref(null)

// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
  },
  formComps: pageFormItems,
  tableColumns: [
    {
      prop: 'publicNo',
      label: '公告号',
    },
    {
      prop: 'publicDate',
      label: '公告日期',
    },
    {
      prop: 'name',
      label: '产品名称',
    },
    {
      prop: 'nationalEconomicClassification',
      label: '国民经济行业分类',
    },
    {
      prop: 'publicTypeName',
      label: '公告类型',
    },
    {
      prop: 'publicDeptName',
      label: '公告单位',
    },
    {
      prop: 'areaAddress',
      label: '所在地域 ',
    },
    {
      prop: 'protectScope',
      label: '保护范围',
    },
    {
      prop: 'protectFile',
      label: '保护范围界定文件',
    },
    {
      prop: 'qualityTechnicalRequirement',
      label: '质量技术要求',
    },
    {
      prop: 'specialSign',
      label: '专用标志',
    },
    {
      prop: 'applicantName',
      label: '申请人名称',
    },
    {
      prop: 'isApplicantNameNaturalPerson',
      label: '是否申请人为自然人',
    },
    {
      prop: 'applicantNameCompanyId',
      label: '申请人公司id',
    },
    {
      prop: 'applicantNameCompanyPersonId',
      label: '申请人个人id',
    },
    {
      prop: 'applicantAddress',
      label: '申请人地址',
    },
    {
      prop: 'primaryReviewInstitute',
      label: '初审机构',
    },
    {
      prop: 'primaryReviewDate',
      label: '初审日期',
    },
    {
      prop: 'agencyName',
      label: '代理机构',
    },
    {
      prop: 'useProduct',
      label: '使用商品',
    },
    {
      prop: 'productGroup',
      label: '商品组别',
    },
    {
      prop: 'filePath',
      label: '文件存放路径',
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
  permission: 'admin:web:dataCompanyIprGeogra:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doDataCompanyIprGeograPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return dataCompanyIprGeograPageApi({...reactiveData.form,...pageQuery})
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
      permission: 'admin:web:dataCompanyIprGeogra:update',
      // 跳转到编辑
      route: {path: '/admin/DataCompanyIprGeograManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:dataCompanyIprGeogra:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return dataCompanyIprGeograRemoveApi({id: row.id}).then(res => {
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
      <PtButton permission="admin:web:dataCompanyIprGeogra:create" route="/admin/DataCompanyIprGeograManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doDataCompanyIprGeograPageApi"
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