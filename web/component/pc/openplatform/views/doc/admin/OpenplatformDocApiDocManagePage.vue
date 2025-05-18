<script setup name="OpenplatformDocApiDocManagePage" lang="ts">
/**
 * 开放接口文档管理页面
 */
import {reactive, ref} from 'vue'
import {
  page as openplatformDocApiDocPageApi,
  remove as openplatformDocApiDocRemoveApi
} from "../../../api/doc/admin/openplatformDocApiDocAdminApi"
import {pageFormItems} from "../../../components/doc/admin/openplatformDocApiDocManage";


const tableRef = ref(null)

// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
  },
  formComps: pageFormItems,
  tableColumns: [
    {
      prop: 'openplatformDocApiName',
      label: '绑定接口',
      showOverflowTooltip: true
    },
    {
      prop: 'openplatformDocApiCode',
      label: '绑定接口编码',
      showOverflowTooltip: true
    },
    {
      prop: 'requestUrlPrefix',
      label: '请求地址前缀',
      showOverflowTooltip: true
    },
    {
      prop: 'requestUrlIntranetPrefix',
      label: '内网请求地址前缀',
      showOverflowTooltip: true
    },
    {
      prop: 'requestUrl',
      label: '请求地址',
      showOverflowTooltip: true
    },
    {
      prop: 'requestTypeDictName',
      label: '请求类型',
    },
    {
      prop: 'requestBodyTypeDictName',
      label: '请求体类型',
      showOverflowTooltip: true
    },
    {
      prop: 'requestParamTypeDictName',
      label: '请求参数类型',
    },
    {
      prop: 'requestParamNestTypeDictName',
      label: '请求参数嵌套类型',
    },
    {
      prop: 'responseBodyTypeDictName',
      label: '响应体类型',
      showOverflowTooltip: true
    },
    {
      prop: 'responseMaxDuration',
      label: '最大响应时长(ms)',
    },
    {
      prop: 'responseMaxDurationDesc',
      label: '响应时长文本',
      showOverflowTooltip: true
    },
    {
      prop: 'authenticationType',
      label: '认证方式',
      showOverflowTooltip: true
    },
    {
      prop: 'descriptionDetail',
      label: '详细描述',
      showOverflowTooltip: true
    },
    {
      prop: 'requestParamExample',
      label: '请求参数示例',
      showOverflowTooltip: true
    },
    {
      prop: 'responseParamExample',
      label: '响应参数示例',
      showOverflowTooltip: true
    },
    {
      prop: 'openplatformDocApiDocTemplateName',
      label: '文档内容模板',
      showOverflowTooltip: true
    },
  ],

})

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '查询',
  loading: false,
  permission: 'admin:web:openplatformDocApiDoc:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doOpenplatformDocApiDocPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return openplatformDocApiDocPageApi({...reactiveData.form,...pageQuery})
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
      permission: 'admin:web:openplatformDocApiDoc:update',
      // 跳转到编辑
      route: {path: '/admin/OpenplatformDocApiDocManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:openplatformDocApiDoc:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？删除后将级联删除关联的参数字段、代码示例、响应码数据`,
      // 删除操作
      method(){
        return openplatformDocApiDocRemoveApi({id: row.id}).then(res => {
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
      <PtButton permission="admin:web:openplatformDocApiDoc:create" route="/admin/OpenplatformDocApiDocManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doOpenplatformDocApiDocPageApi"
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
