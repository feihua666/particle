<script setup name="DataCompanyIprPatentManagePage" lang="ts">
/**
 * 企业知识产权专利管理页面
 */
import {reactive, ref} from 'vue'
import { page as dataCompanyIprPatentPageApi, remove as dataCompanyIprPatentRemoveApi} from "../../../api/company/admin/dataCompanyIprPatentAdminApi"
import {pageFormItems} from "../../../components/company/admin/dataCompanyIprPatentManage";


const tableRef = ref(null)

// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
  },
  formComps: pageFormItems,
  tableColumns: [
    {
      prop: 'title',
      label: '标题',
    },
    {
      prop: 'applyNo',
      label: '原始申请号',
    },
    {
      prop: 'applyDate',
      label: '申请日期',
    },
    {
      prop: 'publicNo',
      label: '原始公布号',
    },
    {
      prop: 'publicDate',
      label: '公布日',
    },
    {
      prop: 'authorizePublicNo',
      label: '授权公告号',
    },
    {
      prop: 'authorizePublicDate',
      label: '授权公告日',
    },
    {
      prop: 'priorityNo',
      label: '优先权号',
    },
    {
      prop: 'priorityDate',
      label: '优先权日',
    },
    {
      prop: 'ipcCategoryNos',
      label: 'IPC分类号',
    },
    {
      prop: 'ipcMainCategoryNo',
      label: '主IPC分类号',
    },
    {
      prop: 'cpcCategoryNos',
      label: 'CPC分类号',
    },
    {
      prop: 'cpcMainCategoryNo',
      label: '主CPC分类号',
    },
    {
      prop: 'patentTypeDictName',
      label: '专利类型',
    },
    {
      prop: 'receivingOfficeName',
      label: '受理局名称',
    },

    {
      prop: 'patentImageUrl',
      label: '专利图片地址',
    },
    {
      prop: 'isCurrentValid',
      label: '是否当前有效',
    },
    {
      prop: 'latestLegalStatusDictName',
      label: '最新法律状态',
    },
    {
      prop: 'instructionManualPageSize',
      label: '说明书页数',
    },
    {
      prop: 'locarnoCategoryNos',
      label: '洛迦诺分类号',
    },
    {
      prop: 'patentStrength',
      label: '专利强度',
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
  permission: 'admin:web:dataCompanyIprPatent:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doDataCompanyIprPatentPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return dataCompanyIprPatentPageApi({...reactiveData.form,...pageQuery})
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
      permission: 'admin:web:dataCompanyIprPatent:update',
      // 跳转到编辑
      route: {path: '/admin/DataCompanyIprPatentManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:dataCompanyIprPatent:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return dataCompanyIprPatentRemoveApi({id: row.id}).then(res => {
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
      <PtButton permission="admin:web:dataCompanyIprPatent:create" route="/admin/DataCompanyIprPatentManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doDataCompanyIprPatentPageApi"
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
