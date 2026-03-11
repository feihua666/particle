<script setup name="CmsChannelManagePage" lang="ts">
/**
 * 栏目管理页面
 */
import {reactive, ref} from 'vue'
import { page as cmsChannelPageApi, remove as cmsChannelRemoveApi,publish as cmsChannelPublishApi,unPublish as cmsChannelUnPublishApi} from "../../api/admin/cmsChannelAdminApi"
import {pageFormItems} from "../../components/admin/cmsChannelManage";


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
      label: '栏目名称',
      width: 150,
    },
    {
      prop: 'code',
      label: '栏目编码',
    },
    {
      prop: 'title',
      label: '站点标题',
      showOverflowTooltip: true,
    },
    {
      prop: 'cmsSiteName',
      label: '站点',
    },
    {
      prop: 'channelContextPath',
      label: '栏目上下文路径',
    },
    {
      prop: 'templatePath',
      label: '栏目模板路径',
    },
    {
      prop: 'templateIndex',
      label: '栏目模板',
    },
    {
      prop: 'staticSavePath',
      label: '静态化存储路径',
    },
    {
      prop: 'seq',
      label: '排序',
    },
    {
      prop: 'profile',
      label: '简介',
      showOverflowTooltip: true,
    },
    {
      prop: 'isPublic',
      label: '是否发布',
      formatter: (row, column, cellValue, index) => {
        return cellValue ? '是' : '否'
      }
    },
    {
      prop: 'publicAt',
      label: '发布时间',
    },
    {
      prop: 'remark',
      label: '备注',
      showOverflowTooltip: true,
    },
    {
      prop: 'pv',
      label: '页面访问量',
    },
    {
      prop: 'initPv',
      label: '初始页面访问量',
    },
    {
      prop: 'iv',
      label: '页面访问ip数',
    },
    {
      prop: 'uv',
      label: '页面访问用户数',
    },
    {
      prop: 'customUrl',
      label: '自定义url',
      showOverflowTooltip: true,
    },

  ],

})

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '查询',
  loading: false,
  permission: 'admin:web:cmsChannel:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doCmsChannelPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return cmsChannelPageApi({...reactiveData.form,...pageQuery})
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
  let editData = {
    id: row.id,
    relatedCmsContentId: row.relatedCmsContentId, relatedCmsContentTitle: row.relatedCmsContentTitle
  }
  let tableRowButtons = [
    {
      txt: '编辑',
      text: true,
      permission: 'admin:web:cmsChannel:update',
      // 跳转到编辑
      route: {path: '/admin/CmsChannelManageUpdate',query: editData}
    },
    {
      txt: '查看地址',
      text: true,
      position: 'more',
      // 跳转到编辑
      route: {path: '/admin/cmsChannelManageUrlPage',query: idData}
    },
    {
      txt: '发布',
      text: true,
      position: 'more',
      disabled: row.isPublic,
      permission: 'admin:web:cmsChannel:public',
      methodConfirmText: `确定要发布 ${row.name} 吗？该发布只针对栏目数据本身，不影响站点和内容的发布状态`,
      // 发布操作
      method(){
        return cmsChannelPublishApi({id: row.id}).then(res => {
          // 发布成功后刷新一下表格
          submitMethod()
          return Promise.resolve(res)
        })
      }
    },
    {
      txt: '取消发布',
      text: true,
      position: 'more',
      disabled: !row.isPublic,
      permission: 'admin:web:cmsChannel:unPublic',
      methodConfirmText: `确定要取消发布 ${row.name} 吗？该取消发布只针对栏目数据本身，不影响站点和内容的发布状态`,
      // 取消发布操作
      method(){
        return cmsChannelUnPublishApi({id: row.id}).then(res => {
          // 取消发布成功后刷新一下表格
          submitMethod()
          return Promise.resolve(res)
        })
      }
    },
    {
      txt: '删除',
      text: true,
      position: 'more',
      permission: 'admin:web:cmsChannel:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return cmsChannelRemoveApi({id: row.id}).then(res => {
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
      <PtButton permission="admin:web:cmsChannel:create" route="/admin/CmsChannelManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doCmsChannelPageApi"
           @dataMethodDataLoading="(loading) => submitAttrs.loading=loading"
           :dataMethodResultHandleConvertToTree="true"
           :paginationProps="tablePaginationProps"
           :columns="reactiveData.tableColumns">

    <!--  操作按钮  -->
    <template #defaultAppend>
      <el-table-column label="操作" width="180">
        <template #default="{row, column, $index}">
          <PtButtonGroup :options="getTableRowButtons({row, column, $index})" :dropdownTriggerButtonOptions="{  text: true,buttonText: '更多'}">
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
