<script setup name="OpenplatformDocApiDocParamFieldManagePage" lang="ts">
/**
 * 开放接口文档参数字段管理页面
 */
import {reactive, ref} from 'vue'
import {
  page as openplatformDocApiDocParamFieldPageApi,
  remove as openplatformDocApiDocParamFieldRemoveApi
} from "../../../api/doc/admin/openplatformDocApiDocParamFieldAdminApi"
import {pageFormItems} from "../../../components/doc/admin/openplatformDocApiDocParamFieldManage";


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
      label: '字段名称',
    },
    {
      prop: 'typeDictName',
      label: '字段类型',
    },
    {
      prop: 'nestTypeDictName',
      label: '嵌套字段类型',
    },
    {
      prop: 'parentName',
      label: '父级',
    },
    {
      prop: 'isRequired',
      label: '是否一定有值',
      formatter: (row, column, cellValue, index) => {
        return cellValue ? '是' : '否'
      }
    },
    {
      prop: 'explanation',
      label: '字段说明',
      showOverflowTooltip: true,
    },
    {
      prop: 'defaultValue',
      label: '默认值',
    },
    {
      prop: 'exampleValue',
      label: '示例值',
    },
    {
      prop: 'maxLength',
      label: '最大长度',
    },
    {
      prop: 'dictGroupDictName',
      label: '字典组',
    },
    {
      prop: 'dictTemTags',
      label: '字典标签',
    },
    {
      prop: 'openplatformDocApiName',
      label: '文档接口',
      showOverflowTooltip: true
    },
    {
      prop: 'openplatformDocApiCode',
      label: '文档接口编码',
      showOverflowTooltip: true
    },
    {
      prop: 'openplatformDocApiDocRequestUrl',
      label: '文档内容',
      showOverflowTooltip: true
    },
    {
      prop: 'categoryDictName',
      label: '分类',
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
  permission: 'admin:web:openplatformDocApiDocParamField:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doOpenplatformDocApiDocParamFieldPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return openplatformDocApiDocParamFieldPageApi({...reactiveData.form,...pageQuery})
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
      permission: 'admin:web:openplatformDocApiDocParamField:update',
      // 跳转到编辑
      route: {path: '/admin/OpenplatformDocApiDocParamFieldManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:openplatformDocApiDocParamField:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return openplatformDocApiDocParamFieldRemoveApi({id: row.id}).then(res => {
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
      <PtButton permission="admin:web:openplatformDocApiDocParamField:create" route="/admin/OpenplatformDocApiDocParamFieldManageAdd">添加</PtButton>
      <PtButton permission="admin:web:openplatformDocApiDocParamField:create" route="/admin/OpenplatformDocApiDocParamFieldManageParseAndAdd">解析并添加</PtButton>
      <PtButton permission="admin:web:openplatformDocApiDocParamField:delete" route="/admin/openplatformDocApiDocParamFieldManageConditionDelete">条件删除</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doOpenplatformDocApiDocParamFieldPageApi"
           @dataMethodDataLoading="(loading) => submitAttrs.loading=loading"
           :dataMethodResultHandleConvertToTree="true"
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
