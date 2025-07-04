<script setup name="DataCompanyDeliveryAnnouncementPartyManagePage" lang="ts">
/**
 * 企业送达公告当事人管理页面
 */
import {reactive, ref} from 'vue'
import { page as dataCompanyDeliveryAnnouncementPartyPageApi, remove as dataCompanyDeliveryAnnouncementPartyRemoveApi} from "../../../api/company/admin/dataCompanyDeliveryAnnouncementPartyAdminApi"
import {pageFormItems} from "../../../components/company/admin/dataCompanyDeliveryAnnouncementPartyManage";


const tableRef = ref(null)

// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
  },
  formComps: pageFormItems,
  tableColumns: [
    {
      prop: 'companyDeliveryAnnouncementId',
      label: '送达公告表id',
    },
    {
      prop: 'partyName',
      label: '当事人名称',
    },
    {
      prop: 'isPartyNaturalPerson',
      label: '是否法人为自然人',
    },
    {
      prop: 'partyCompanyId',
      label: '当事人公司id',
    },
    {
      prop: 'partyCompanyPersonId',
      label: '当事人个人id',
    },
    {
      prop: 'partyRoleDictName',
      label: '当事人角色',
    },
    {
      prop: 'partyDescription',
      label: '当事人描述信息',
    },
    {
      prop: 'dataMd5',
      label: '数据md5',
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
  permission: 'admin:web:dataCompanyDeliveryAnnouncementParty:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doDataCompanyDeliveryAnnouncementPartyPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return dataCompanyDeliveryAnnouncementPartyPageApi({...reactiveData.form,...pageQuery})
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
      permission: 'admin:web:dataCompanyDeliveryAnnouncementParty:update',
      // 跳转到编辑
      route: {path: '/admin/DataCompanyDeliveryAnnouncementPartyManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:dataCompanyDeliveryAnnouncementParty:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return dataCompanyDeliveryAnnouncementPartyRemoveApi({id: row.id}).then(res => {
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
      <PtButton permission="admin:web:dataCompanyDeliveryAnnouncementParty:create" route="/admin/DataCompanyDeliveryAnnouncementPartyManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doDataCompanyDeliveryAnnouncementPartyPageApi"
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