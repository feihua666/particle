<script setup name="CmsContentManagePage" lang="ts">
/**
 * 内容管理页面
 */
import {reactive, ref} from 'vue'
import { page as cmsContentPageApi, remove as cmsContentRemoveApi} from "../../api/admin/cmsContentAdminApi"
import {pageFormItems} from "../../components/admin/cmsContentManage";


const tableRef = ref(null)

// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
  },
  formComps: pageFormItems,
  tableColumns: [
    {
      prop: 'cmsSiteName',
      label: '站点',
    },
    {
      prop: 'cmsChannelName',
      label: '栏目',
    },
    {
      prop: 'cmsContentCategoryName',
      label: '内容分类',
    },
    {
      prop: 'title',
      label: '标题',
    },
    {
      prop: 'author',
      label: '作者',
    },
    {
      prop: 'original',
      label: '来源',
    },
    {
      prop: 'profile',
      label: '简介',
      showOverflowTooltip: true,
    },
    {
      prop: 'auditStatusDictName',
      label: '审核状态',
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
      prop: 'contentTypeDictName',
      label: '内容类型',
    },
    {
      prop: 'imageUrl',
      label: '图片地址',
    },
    {
      prop: 'imageDescription',
      label: '图片描述',
    },
    {
      prop: 'imageUrl1',
      label: '图片地址1',
    },
    {
      prop: 'imageDescription1',
      label: '图片描述1',
    },
    {
      prop: 'imageUrl2',
      label: '图片地址2',
    },
    {
      prop: 'imageDescription2',
      label: '图片描述2',
    },
    {
      prop: 'templatePath',
      label: '模板路径',
      showOverflowTooltip: true,
    },
    {
      prop: 'templateIndex',
      label: '内容模板',
    },
    {
      prop: 'staticPath',
      label: '静态页路径',
      showOverflowTooltip: true,
    },
    {
      prop: 'pv',
      label: '页面访问量',
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
      prop: 'seq',
      label: '排序',
    },
  ],

})

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '查询',
  loading: false,
  permission: 'admin:web:cmsContent:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doCmsContentPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return cmsContentPageApi({...reactiveData.form,...pageQuery})
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
      permission: 'admin:web:cmsContent:update',
      // 跳转到编辑
      route: {path: '/admin/CmsContentManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:cmsContent:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return cmsContentRemoveApi({id: row.id}).then(res => {
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
      <PtButton permission="admin:web:cmsContent:create" route="/admin/CmsContentManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doCmsContentPageApi"
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
