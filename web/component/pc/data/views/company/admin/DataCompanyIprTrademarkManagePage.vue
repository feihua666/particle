<script setup name="DataCompanyIprTrademarkManagePage" lang="ts">
/**
 * 企业知识产权商标管理页面
 */
import {reactive, ref} from 'vue'
import { page as dataCompanyIprTrademarkPageApi, remove as dataCompanyIprTrademarkRemoveApi} from "../../../api/company/admin/dataCompanyIprTrademarkAdminApi"
import {pageFormItems} from "../../../components/company/admin/dataCompanyIprTrademarkManage";


const tableRef = ref(null)

// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
  },
  formComps: pageFormItems,
  tableColumns: [
    {
      prop: 'name',
      label: '商标名称',
    },
    {
      prop: 'nameCn',
      label: '商标名称',
    },
    {
      prop: 'nameEn',
      label: '商标名称',
    },
    {
      prop: 'regNo',
      label: '注册号',
    },
    {
      prop: 'applyNo',
      label: '申请号',
    },
    {
      prop: 'niceCategoryNo',
      label: '尼斯分类号',
    },
    {
      prop: 'rightStatusName',
      label: '当前权利状态名称',
    },
    {
      prop: 'typeName',
      label: '商标类型名称',
    },
    {
      prop: 'trademarkImageUrl',
      label: '商标图片地址',
    },
    {
      prop: 'regDate',
      label: '注册日期',
    },
    {
      prop: 'applyDate',
      label: '申请日期',
    },
    {
      prop: 'isShare',
      label: '是否共享',
    },
    {
      prop: 'isWellKnown',
      label: '是否驰名商标',
    },
    {
      prop: 'isSpecifyColor',
      label: '是否指定颜色',
    },
    {
      prop: 'priorityDate',
      label: '优先权日期',
    },
    {
      prop: 'specialStartDate',
      label: '专用权期限开始日期',
    },
    {
      prop: 'specialEndDate',
      label: '专用权期限截止日期',
    },
    {
      prop: 'firstTrialPublicDate',
      label: '初审公告日期',
    },
    {
      prop: 'firstTrialPublicNo',
      label: '初审公告号',
    },
    {
      prop: 'lateSpecifyDate',
      label: '后期指定日期',
    },
    {
      prop: 'dissentEndDate',
      label: '异议截止日期',
    },
    {
      prop: 'originLang',
      label: '原始语种',
    },
    {
      prop: 'regPublicIssueNo',
      label: '注册公告期号',
    },
    {
      prop: 'internationalRegDate',
      label: '国际注册日期',
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
  permission: 'admin:web:dataCompanyIprTrademark:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doDataCompanyIprTrademarkPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return dataCompanyIprTrademarkPageApi({...reactiveData.form,...pageQuery})
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
      permission: 'admin:web:dataCompanyIprTrademark:update',
      // 跳转到编辑
      route: {path: '/admin/DataCompanyIprTrademarkManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:dataCompanyIprTrademark:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return dataCompanyIprTrademarkRemoveApi({id: row.id}).then(res => {
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
      <PtButton permission="admin:web:dataCompanyIprTrademark:create" route="/admin/DataCompanyIprTrademarkManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doDataCompanyIprTrademarkPageApi"
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
