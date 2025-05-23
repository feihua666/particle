<script setup name="DataQueryDataApiManagePage" lang="ts">
/**
 * 数据查询数据接口管理页面
 */
import {reactive, ref} from 'vue'
import {
  copy as DataQueryDataApiCopyApi,
  copydev as DataQueryDataApiCopydevApi,
  deleteCache as DataQueryDataApiRemoveCacheApi,
  devMergeToMaster as DataQueryDataApiDevMergeToMasterApi,
  page as DataQueryDataApiPageApi,
  refreshCache as DataQueryDataApiRefreshCacheApi, refreshOpenapiExecuteProviderCache,
  remove as DataQueryDataApiRemoveApi
} from "../../../api/dataapi/admin/dataQueryDataApiAdminApi"
import {pageFormItems} from "../../../components/dataapi/admin/dataQueryDataApiManage";


const tableRef = ref(null)

// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 加载数据初始化参数,路由传参
  url: {
    type: String
  }
})
// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
    url: props.url || null
  },
  formComps: pageFormItems,
  tableColumns: [
    {
      prop: 'url',
      label: '接口地址',
      showOverflowTooltip: true,
    },
    {
      prop: 'name',
      label: '接口名称',
      showOverflowTooltip: true,
    },
    {
      prop: 'dataQueryDatasourceApiName',
      label: '直连数据源接口',
      showOverflowTooltip: true,
    },
    {
      prop: 'adaptTypeDictName',
      label: '适配类型',
    },

    {
      prop: 'inParamTypeDictName',
      label: '入参类型',
    },

    {
      prop: 'outParamTypeDictName',
      label: '出参类型',
    },

    {
      prop: 'responseTypeDictName',
      label: '输出类型',
      showOverflowTooltip: true,
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
      prop: 'dataQueryProviderName',
      label: '数据查询供应商',
      showOverflowTooltip: true,
    },
    {
      prop: 'apiIdentifier',
      label: '接口标识',
      showOverflowTooltip: true,
    },
    {
      prop: 'apiVersion',
      label: '接口版本标识',
      showOverflowTooltip: true,
    },
    {
      prop: 'isSupportWarehouse',
      label: '支持入库',
      formatter: (row, column, cellValue, index) => {
        return cellValue ? '支持' : '不支持'
      }
    },
    {
      prop: 'remark',
      label: '描述',
      showOverflowTooltip: true,
    },
  ],

})

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '查询',
  loading: false,
  permission: 'admin:web:dataQueryDataApi:pageQuery'
})
const refreshOpenapiExecuteProviderCacheConfig = {
  permission: 'admin:web:dataQueryDataApi:refreshOpenapiExecuteProviderCache',
  methodSuccess: (res) => '刷新缓存成功,如果部署多个实例可能要多次执行。 ' + res.data.data,
  // 刷新缓存
  method(){
    return refreshOpenapiExecuteProviderCache().then(res => {
      return Promise.resolve(res)
    })
  }
}
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doDataQueryDataApiPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return DataQueryDataApiPageApi({...reactiveData.form,...pageQuery})
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
  let tableRowButtons: Array<any> = [
    {
      txt: '接口测试',
      text: true,
      permission: 'admin:web:dataQueryDataApi:test',
      // 跳转到编辑
      route: {path: '/admin/DataQueryDataApiManageTest',query: idData}
    },
    {
      txt: '编辑',
      text: true,
      disabled: row.isPublished,
      disabledReason: row.isPublished ? '已发布不能编辑，可以通过复制dev来修改提交': undefined,
      position: 'more',
      permission: 'admin:web:dataQueryDataApi:update',
      // 跳转到编辑
      route: {path: '/admin/DataQueryDataApiManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      disabled: row.isPublished,
      disabledReason: row.isPublished ? '已发布不能删除': undefined,
      position: 'more',
      permission: 'admin:web:dataQueryDataApi:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return DataQueryDataApiRemoveApi({id: row.id}).then(res => {
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
      permission: 'admin:web:dataQueryDataApi:deleteCache',
      methodConfirmText: `确定要删除 ${row.name} 缓存吗？如果部署多个实例可能要多次执行`,
      methodSuccess: (res) => '删除缓存成功 ' + res.data.data,
      // 删除缓存操作
      method(){
        return DataQueryDataApiRemoveCacheApi({id: row.id}).then(res => {
          return Promise.resolve(res)
        })
      }
    },
    {
      txt: '刷新缓存',
      text: true,
      position: 'more',
      permission: 'admin:web:dataQueryDataApi:refreshCache',
      methodSuccess: (res) => '刷新缓存成功,如果部署多个实例可能要多次执行。 ' + res.data.data,
      // 刷新缓存
      method(){
        return DataQueryDataApiRefreshCacheApi({id: row.id}).then(res => {
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
      permission: 'admin:web:dataQueryDataApi:copy',
      methodConfirmText: `确定要复制 ${row.name} 吗？`,
      methodSuccess: (res) => {
        reactiveData.form.name = res.data.data.name
        // 复制成功后刷新一下表格
        submitMethod()
      },
      // 复制操作
      method(){
        return DataQueryDataApiCopyApi({id: row.id}).then(res => {
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
      permission: 'admin:web:dataQueryDataApi:copydev',
      methodConfirmText: `确定要复制 ${row.name} 吗？复制后可以修改并提交到主分支`,
      methodSuccess: (res) => {
        reactiveData.form.name = res.data.data.name
        // 复制成功后刷新一下表格
        submitMethod()
      },
      // 复制操作
      method(){
        return DataQueryDataApiCopydevApi({id: row.id}).then(res => {
          return Promise.resolve(res)
        })
      }
    },
    {
      txt: '查看',
      text: true,
      position: 'more',
      permission: 'admin:web:dataQueryDataApi:detail',
      // 跳转到查看
      route: {path: '/admin/DataQueryDataApiManageView',query: idData}
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
            if (res.data.data.name) {
              // 如果成功后有返回说明是以__dev 结尾的名称，后端已经处理了
              reactiveData.form.name = res.data.data.name;
            }else if(reactiveData.form.name){
              // 如果查询表单中有值，则认为是复制dev后填充的，还原为原来名称
              reactiveData.form.name = reactiveData.form.name.replace('Copydev', '')
            }else{
              // 如果查询表单中没有值，则认为是当前复制dev后没有编辑，这里直接取出名称
              reactiveData.form.name = row.name.replace('Copydev', '')
            }
            // 提交至master成功后刷新一下表格
            submitMethod()
          },
          // 提交至master操作
          method(){
            return DataQueryDataApiDevMergeToMasterApi({id: row.id}).then(res => {
              return Promise.resolve(res)
            })
          }
        },
    )
  }
  if('single_direct' == row.adaptTypeDictValue){
    tableRowButtons.push(
        {
          txt: '查看数据源接口',
          text: true,
          position: 'more',
          permission: 'admin:web:dataQueryDatasourceApi:pageQuery',
          // 跳转到编辑
          route: {path: '/admin/dataQueryDatasourceApiManagePage',query: {dataQueryDatasourceApiName: row.dataQueryDatasourceApiName}}
        }
    )
  }
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
          labelWidth="120"
          :comps="reactiveData.formComps">
    <template #buttons>
      <PtButton permission="admin:web:dataQueryDataApi:create" route="/admin/DataQueryDataApiManageAdd">添加</PtButton>
      <PtButton v-bind="refreshOpenapiExecuteProviderCacheConfig">刷新执行器供应商缓存</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doDataQueryDataApiPageApi"
           @dataMethodDataLoading="(loading) => submitAttrs.loading=loading"
           :dataMethodResultHandleConvertToTree="true"
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
