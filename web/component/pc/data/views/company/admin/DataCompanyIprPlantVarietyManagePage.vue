<script setup name="DataCompanyIprPlantVarietyManagePage" lang="ts">
/**
 * 企业知识产权植物新品种管理页面
 */
import {reactive, ref} from 'vue'
import { page as dataCompanyIprPlantVarietyPageApi, remove as dataCompanyIprPlantVarietyRemoveApi} from "../../../api/company/admin/dataCompanyIprPlantVarietyAdminApi"
import {pageFormItems} from "../../../components/company/admin/dataCompanyIprPlantVarietyManage";


const tableRef = ref(null)

// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
  },
  formComps: pageFormItems,
  tableColumns: [
    {
      prop: 'applyNo',
      label: '申请号',
    },
    {
      prop: 'applyDate',
      label: '申请日期',
    },
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
      label: '品种名称',
    },
    {
      prop: 'applyPublicNo',
      label: '申请公告号',
    },
    {
      prop: 'applyPublicDate',
      label: '申请公告日期',
    },
    {
      prop: 'publicTypeName',
      label: '公告类型',
    },
    {
      prop: 'plantKindName',
      label: '植物种类',
    },
    {
      prop: 'varietyRightType',
      label: '品种权事务分类',
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
      prop: 'cultivateName',
      label: '培育人',
    },
    {
      prop: 'coVarietyRightName',
      label: '共同品种权人',
    },
    {
      prop: 'agency',
      label: '代理机构',
    },
    {
      prop: 'agencyAddress',
      label: '代理机构地址',
    },
    {
      prop: 'agent',
      label: '代理人',
    },
    {
      prop: 'priorityNo',
      label: '优先权号',
    },
    {
      prop: 'varietySourceFrom',
      label: '品种来源',
    },
    {
      prop: 'batchNo',
      label: '批次号',
    },
    {
      prop: 'description',
      label: '说明',
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
  permission: 'admin:web:dataCompanyIprPlantVariety:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doDataCompanyIprPlantVarietyPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return dataCompanyIprPlantVarietyPageApi({...reactiveData.form,...pageQuery})
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
      permission: 'admin:web:dataCompanyIprPlantVariety:update',
      // 跳转到编辑
      route: {path: '/admin/DataCompanyIprPlantVarietyManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:dataCompanyIprPlantVariety:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return dataCompanyIprPlantVarietyRemoveApi({id: row.id}).then(res => {
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
      <PtButton permission="admin:web:dataCompanyIprPlantVariety:create" route="/admin/DataCompanyIprPlantVarietyManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doDataCompanyIprPlantVarietyPageApi"
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