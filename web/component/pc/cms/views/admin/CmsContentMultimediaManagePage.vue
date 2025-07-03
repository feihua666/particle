<script setup name="CmsContentMultimediaManagePage" lang="ts">
/**
 * 内容多媒体管理页面
 */
import {reactive, ref} from 'vue'
import { page as cmsContentMultimediaPageApi, remove as cmsContentMultimediaRemoveApi} from "../../api/admin/cmsContentMultimediaAdminApi"
import {pageFormItems} from "../../components/admin/cmsContentMultimediaManage";


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
      prop: 'cmsContentTitle',
      label: '内容标题',
    },
    {
      prop: 'content',
      label: '文本内容',
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
      prop: 'fileName',
      label: '文件名称',
    },
    {
      prop: 'fileUrl',
      label: '文件地址',
    },
    {
      prop: 'fileSize',
      label: '文件大小',
    },
    {
      prop: 'fileSizeStr',
      label: '文件大小字符串',
    },
    {
      prop: 'mediaType',
      label: '媒体类型',
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
  permission: 'admin:web:cmsContentMultimedia:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doCmsContentMultimediaPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return cmsContentMultimediaPageApi({...reactiveData.form,...pageQuery})
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
  let updateIdData = {id: row.id,cmsContentId:  row.cmsContentId,cmsContentTitle: row.cmsContentTitle}

  let tableRowButtons = [
    {
      txt: '编辑',
      text: true,
      permission: 'admin:web:cmsContentMultimedia:update',
      // 跳转到编辑
      route: {path: '/admin/CmsContentMultimediaManageUpdate',query: updateIdData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:cmsContentMultimedia:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return cmsContentMultimediaRemoveApi({id: row.id}).then(res => {
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
      <PtButton permission="admin:web:cmsContentMultimedia:create" route="/admin/CmsContentMultimediaManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doCmsContentMultimediaPageApi"
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
