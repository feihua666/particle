<script setup name="DataCompanyIprIntegratedCircuitManagePage" lang="ts">
/**
 * 企业知识产权集成电路管理页面
 */
import {reactive, ref} from 'vue'
import { page as dataCompanyIprIntegratedCircuitPageApi, remove as dataCompanyIprIntegratedCircuitRemoveApi} from "../../../api/company/admin/dataCompanyIprIntegratedCircuitAdminApi"
import {pageFormItems} from "../../../components/company/admin/dataCompanyIprIntegratedCircuitManage";


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
      label: '布图设计名称',
    },
    {
      prop: 'regNo',
      label: '布图设计登记号',
    },
    {
      prop: 'applyDate',
      label: '布图设计申请日',
    },
    {
      prop: 'rightHolder',
      label: '布图设计权利人名称',
    },
    {
      prop: 'isRightHolderNaturalPerson',
      label: '是否权利人为自然人',
    },
    {
      prop: 'rightHolderCompanyId',
      label: '权利人公司id',
    },
    {
      prop: 'rightHolderCompanyPersonId',
      label: '权利人个人id',
    },
    {
      prop: 'rightHolderCountry',
      label: '布图设计权利人国籍',
    },
    {
      prop: 'rightHolderAddress',
      label: '布图设计权利人地址',
    },
    {
      prop: 'designCreator',
      label: '布图设计创作人',
    },
    {
      prop: 'completeDate',
      label: '布图设计创作完成日',
    },
    {
      prop: 'typeName',
      label: '布图设计类别',
    },
    {
      prop: 'firstBusinessDate',
      label: '首次商业利用日期 ',
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
  permission: 'admin:web:dataCompanyIprIntegratedCircuit:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doDataCompanyIprIntegratedCircuitPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return dataCompanyIprIntegratedCircuitPageApi({...reactiveData.form,...pageQuery})
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
      permission: 'admin:web:dataCompanyIprIntegratedCircuit:update',
      // 跳转到编辑
      route: {path: '/admin/DataCompanyIprIntegratedCircuitManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:dataCompanyIprIntegratedCircuit:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return dataCompanyIprIntegratedCircuitRemoveApi({id: row.id}).then(res => {
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
      <PtButton permission="admin:web:dataCompanyIprIntegratedCircuit:create" route="/admin/DataCompanyIprIntegratedCircuitManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doDataCompanyIprIntegratedCircuitPageApi"
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