<script setup name="DynamicDataIndicatorCategoryManageDataPage" lang="ts">
/**
 * 动态数据指标分类管理数据页面
 * 先请求需要渲染的表格结构，然后请求数据
 */
import {reactive, ref} from 'vue'
import {
  dataPage as dynamicDataIndicatorCategoryDataPageApi,
  dataRemove as dynamicDataIndicatorCategoryDataRemoveApi
} from "../../../api/dynamicdata/admin/dynamicDataIndicatorCategoryAdminApi"
import {listWithDynamicTableField} from "../../../api/dynamicdata/admin/dynamicDataIndicatorAdminApi";


const tableRef = ref(null)
// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 加载数据初始化参数,路由传参
  dynamicDataIndicatorCategoryId: {
    type: String
  },
  batchId: {
    type: String
  },
})
// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
    dynamicDataIndicatorCategoryId: props.dynamicDataIndicatorCategoryId,
    batchId: props.batchId
  },
  formComps: [
    {
      field: {
        name: 'isPublic',
      },
      element: {
        comp: 'PtSelect',
        formItemProps: {
          label: '是否发布',
        },
        compProps: {
          dataMethod: () => {
            return {
              data: [
                {id: 'true',name: '已发布'},
                {id: 'false',name: '未发布'},
              ]
            }
          }
        }
      }
    },
  ],
  tableColumns: [
  ],

  tableFieldLoaded: false,
})
const loadDynamicTableFields = (dynamicDataIndicatorCategoryId) => {
  // 参见后端统一配置：boolean useDynamicTable = DynamicDataIndicatorCategoryCreateCommandExecutor.useDynamicTable;
  let useDynamicTable = false
  listWithDynamicTableField({dynamicDataIndicatorCategoryId})
      .then(res => {
        let data = res.data.data
        data.forEach(item => {
          reactiveData.tableColumns.push({
            // 不使用动态表格时，字段名需要与后端保持一致，参见：com.particle.data.app.dynamicdata.executor.DynamicDataIndicatorCreateCommandExecutor.wrapTableColumn
            prop: useDynamicTable ? item.dynamicTableField.name : ('c_' + item.id),
            label: item.name,
          })
        })
        reactiveData.tableColumns.push({
          prop: 'is_public',
          label: '是否发布',
          formatter: (row, column, cellValue, index) => {
            return cellValue ? '已发布' : '未发布'
          }
        })
        reactiveData.tableColumns.push({
          prop: 'create_by_nickname',
          label: '创建人昵称',
        })
        reactiveData.tableFieldLoaded = true
      })
}
loadDynamicTableFields(props.dynamicDataIndicatorCategoryId)
// 提交按钮属性
const submitAttrs = ref({
  buttonText: '查询',
  loading: false,
  permission: 'admin:web:dynamicDataIndicatorCategory:dataPageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doDynamicTablePageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return dynamicDataIndicatorCategoryDataPageApi({...reactiveData.form,...pageQuery})
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
      txt: '删除',
      text: true,
      permission: 'admin:web:dynamicDataIndicatorCategory:dataDelete',
      methodConfirmText: `确定要删除当前数据吗？`,
      // 删除操作
      method(){
        return dynamicDataIndicatorCategoryDataRemoveApi({id: row.id,dynamicDataIndicatorCategoryId: props.dynamicDataIndicatorCategoryId}).then(res => {
          // 删除成功后刷新一下表格
          submitMethod()
          return Promise.resolve(res)
        })
      }
    },
  ]

  return tableRowButtons
}
</script>
<template>
  <!-- 查询表单 -->
  <PtForm :form="reactiveData.form"
          :method="submitMethod"
          defaultButtonsShow="submit"
          :submitAttrs="submitAttrs"
          :showButtonItem="true"
          inline
          :comps="reactiveData.formComps">
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef" v-if="reactiveData.tableFieldLoaded"
           default-expand-all
           :dataMethod="doDynamicTablePageApi"
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
</template>


<style scoped>

</style>
