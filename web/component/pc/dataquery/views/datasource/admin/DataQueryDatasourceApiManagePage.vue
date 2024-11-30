<script setup name="DataQueryDatasourceApiManagePage" lang="ts">
/**
 * 数据查询数据源接口管理页面
 */
import {reactive, ref} from 'vue'
import { page as DataQueryDatasourceApiPageApi, remove as DataQueryDatasourceApiRemoveApi,deleteCache as DataQueryDatasourceApiRemoveCacheApi,refreshCache as DataQueryDatasourceApiRefreshCacheApi, copy as DataQueryDatasourceApiCopyApi, copydev as DataQueryDatasourceApiCopydevApi,devMergeToMaster as DataQueryDatasourceApidevMergeToMasterApi} from "../../../api/datasource/admin/dataQueryDatasourceApiAdminApi"
import {pageFormItems} from "../../../components/datasource/admin/dataQueryDatasourceApiManage";


const tableRef = ref(null)
// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 加载数据初始化参数,路由传参
  name: {
    type: String
  }
})
// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
    name: props.name
  },
  formComps: pageFormItems,
  tableColumns: [
    {
      prop: 'code',
      label: '编码',
    },
    {
      prop: 'name',
      label: '名称',
    },
    {
      prop: 'categoryDictName',
      label: '分类',
    },
    {
      prop: 'dataQueryProviderName',
      label: '数据查询供应商',
    },
    {
      prop: 'dataQueryProviderDocLinkUrl',
      label: '文档链接',
      showOverflowTooltip: true
    },
    {
      prop: 'dataQueryDatasourceName',
      label: '数据查询数据源',
    },
    {
      prop: 'readTimeout',
      label: '读取等待时间(ms)',
    },
    {
      prop: 'connectTimeout',
      label: '连接等待时间(ms)',
    },
    {
      prop: 'sameTag',
      label: '等同标签',
    },
    {
      prop: 'isSupportTrans',
      label: '翻译支持',
      formatter: (row, column, cellValue, index) => {
        return cellValue ? '支持' : '不支持'
      }
    },
    {
      prop: 'isPublished',
      label: '是否发布',
      formatter: (row, column, cellValue, index) => {
        return cellValue ? '已发布' : '未发布'
      }
    },
    {
      prop: 'isMaster',
      label: '配置分支',
      formatter: (row, column, cellValue, index) => {
        return cellValue ? 'master' : 'dev'
      }
    },
    {
      prop: 'isTestPassed',
      label: '是否测试通过',
      formatter: (row, column, cellValue, index) => {
        return cellValue ? '测试通过' : '未测试通过'
      }
    },
    {
      prop: 'remark',
      label: '描述',
    },
  ],

})

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '查询',
  loading: false,
  permission: 'admin:web:dataQueryDatasourceApi:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doDataQueryDatasourceApiPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return DataQueryDatasourceApiPageApi({...reactiveData.form,...pageQuery})
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
      txt: '接口测试',
      text: true,
      permission: 'admin:web:dataQueryDatasourceApi:test',
      // 跳转到编辑
      route: {path: '/admin/DataQueryDatasourceApiManageTest',query: idData}
    },
    {
      txt: '编辑',
      text: true,
      disabled: row.isPublished,
      disabledReason: row.isPublished ? '已发布不能编辑，可以通过复制dev来修改提交': undefined,
      permission: 'admin:web:dataQueryDatasourceApi:update',
      position: 'more',
      // 跳转到编辑
      route: {path: '/admin/DataQueryDatasourceApiManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      disabled: row.isPublished,
      disabledReason: row.isPublished ? '已发布不能删除': undefined,
      position: 'more',
      permission: 'admin:web:dataQueryDatasourceApi:delete',
      methodConfirmText: `可能存在数据查询引用（包括直连引用或通过编码脚本调用）请确保安全，确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return DataQueryDatasourceApiRemoveApi({id: row.id}).then(res => {
          // 删除成功后刷新一下表格
          submitMethod()
          return Promise.resolve(res)
        })
      }
    },
    {
      txt: '删除缓存',
      text: true,
      position: 'more',
      permission: 'admin:web:dataQueryDatasourceApi:deleteCache',
      methodConfirmText: `确定要删除 ${row.name} 缓存吗？如果部署多个实例可能要多次执行`,
      methodSuccess: (res) => '删除缓存成功 ' + res.data.data,
      // 删除操作
      method(){
        return DataQueryDatasourceApiRemoveCacheApi({id: row.id}).then(res => {
          return Promise.resolve(res)
        })
      }
    },
    {
      txt: '刷新缓存',
      text: true,
      position: 'more',
      permission: 'admin:web:dataQueryDatasourceApi:refreshCache',
      methodSuccess: (res) => '刷新缓存成功，如果部署多个实例可能要多次执行。 ' + res.data.data,
      // 刷新缓存
      method(){
        return DataQueryDatasourceApiRefreshCacheApi({id: row.id}).then(res => {
          return Promise.resolve(res)
        })
      }
    },
    {
      txt: '复制',
      text: true,
      disabled: !row.isMaster,
      disabledReason: !row.isMaster ? '仅支持master复制': undefined,
      position: 'more',
      permission: 'admin:web:dataQueryDatasourceApi:copy',
      methodConfirmText: `确定要复制 ${row.name} 吗？`,
      methodSuccess: (res) => {
        reactiveData.form.name = res.data.data.name
        // 复制成功后刷新一下表格
        submitMethod()
      },
      // 复制操作
      method(){
        return DataQueryDatasourceApiCopyApi({id: row.id}).then(res => {
          return Promise.resolve(res)
        })
      }
    },
    {
      txt: '复制dev',
      text: true,
      disabled: !row.isMaster,
      disabledReason: !row.isMaster ? '仅支持master复制': undefined,
      position: 'more',
      permission: 'admin:web:dataQueryDatasourceApi:copydev',
      methodConfirmText: `确定要复制 ${row.name} 吗？复制后可以修改并提交到主分支`,
      methodSuccess: (res) => {
        reactiveData.form.name = res.data.data.name
        // 复制成功后刷新一下表格
        submitMethod()
      },
      // 复制操作
      method(){
        return DataQueryDatasourceApiCopydevApi({id: row.id}).then(res => {
          return Promise.resolve(res)
        })
      }
    },
    {
      txt: '查看',
      text: true,
      permission: 'admin:web:dataQueryDatasourceApi:detail',
      position: 'more',
      // 跳转到查看
      route: {path: '/admin/DataQueryDatasourceApiManageView',query: idData}
    },

  ]
  if (!row.isMaster) {
    tableRowButtons.push(
        {
          txt: '提交至master',
          text: true,
          disabled: row.isMaster,
          disabledReason: row.isMaster ? '仅支持dev': undefined,
          position: 'more',
          permission: 'admin:web:dataQueryDatasourceApi:devMergeToMaster',
          methodConfirmText: `确定要将 ${row.name} 提交至master吗？提交成功后本数据将会被删除！`,
          methodSuccess: (res) => {
            // 复制成功后刷新一下表格
            submitMethod()
          },
          // 复制操作
          method(){
            return DataQueryDatasourceApidevMergeToMasterApi({id: row.id}).then(res => {
              return Promise.resolve(res)
            })
          }
        },
    )
  }
  return tableRowButtons;
}
</script>
<template>
  <!-- 查询表单 -->
  <PtForm :form="reactiveData.form"
          :method="submitMethod"
          defaultButtonsShow="submit,reset"
          :submitAttrs="submitAttrs"
          labelWidth="120"
          inline
          :comps="reactiveData.formComps">
    <template #buttons>
      <PtButton permission="admin:web:dataQueryDatasourceApi:create" route="/admin/DataQueryDatasourceApiManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doDataQueryDatasourceApiPageApi"
           @dataMethodDataLoading="(loading) => submitAttrs.loading=loading"

           :paginationProps="tablePaginationProps"
           :columns="reactiveData.tableColumns">

    <!--  操作按钮  -->
    <template #defaultAppend>
      <el-table-column label="操作" width="220">
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
