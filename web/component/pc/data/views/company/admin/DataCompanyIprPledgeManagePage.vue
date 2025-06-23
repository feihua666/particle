<script setup name="DataCompanyIprPledgeManagePage" lang="ts">
/**
 * 企业知识产权出质管理页面
 */
import {reactive, ref} from 'vue'
import { page as dataCompanyIprPledgePageApi, remove as dataCompanyIprPledgeRemoveApi} from "../../../api/company/admin/dataCompanyIprPledgeAdminApi"
import {pageFormItems} from "../../../components/company/admin/dataCompanyIprPledgeManage";


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
      label: '知识产权登记证号',
    },
    {
      prop: 'name',
      label: '知识产权名称',
    },
    {
      prop: 'typeName',
      label: '知识产权种类',
    },
    {
      prop: 'pledgor',
      label: '出质人名称',
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
      prop: 'pledgee',
      label: '质权人名称',
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
      prop: 'pledgeFromDate',
      label: '质权登记期限自',
    },
    {
      prop: 'pledgeToDate',
      label: '质权登记期限至',
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
  permission: 'admin:web:dataCompanyIprPledge:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doDataCompanyIprPledgePageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return dataCompanyIprPledgePageApi({...reactiveData.form,...pageQuery})
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
      permission: 'admin:web:dataCompanyIprPledge:update',
      // 跳转到编辑
      route: {path: '/admin/DataCompanyIprPledgeManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:dataCompanyIprPledge:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return dataCompanyIprPledgeRemoveApi({id: row.id}).then(res => {
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
      <PtButton permission="admin:web:dataCompanyIprPledge:create" route="/admin/DataCompanyIprPledgeManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doDataCompanyIprPledgePageApi"
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