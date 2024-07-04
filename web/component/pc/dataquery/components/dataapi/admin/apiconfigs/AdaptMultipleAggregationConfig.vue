<script setup name="AdaptMultipleAggregationConfig" lang="ts">
import {onMounted, reactive, ref,nextTick} from 'vue'
import {list as datasourceApiListApi} from "../../../../api/datasource/admin/dataQueryDatasourceApiAdminApi";

/**
 * 示例项
 */
interface AggregationItem{
  // 聚合对象的key(聚合变量)
  outputKey: string,
  // 数据查询数据源id
  dataQueryDatasourceApiId: string
  // 数据查询数据源名称
  datasourceApiName: string
}
// 示例对象
// props.initJsonStr 需要符合该类型
interface InitJson{
  aggregationItems: AggregationItem[]
}


const tableRef = ref(null)

// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 初始化数据
  initJsonStr: {
    type: String
  }
})
// 属性
const reactiveData = reactive({
  initJson: {aggregationItems: []},
// 表单初始查询第一页
  form: {
    // 名称
    datasourceApiName: ''
  },
  formComps: [
    {
      field: {
        name: 'outputKey',
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '聚合变量',
          required: true,
          tips: '将以该变量做为输出对象的key'
        },
        compProps: {
          clearable: true,
        }
      }
    },
    {
      field: {
        name: 'dataQueryDatasourceApiId',
        valueChange({form,formData}){
          nextTick(()=>{
            if(formData.dataQueryDatasourceApiId){
              form.datasourceApiName = formData.dataQueryDatasourceApiId?.name
            }
          })


        },
      },
      element: {
        comp: 'PtSelect',
        formItemProps: {
          label: '数据查询数据源接口',
          required: true
        },
        compProps: {
          dataMethod: datasourceApiListApi
        }
      }
    },
  ],
  tableColumns: [
    {
      prop: 'outputKey',
      label: '聚合变量',
    },
    {
      prop: 'datasourceApiName',
      label: '数据查询数据源接口',
    },
  ],

})
onMounted(()=>{
  // 挂载后初始化数据
  if(props.initJsonStr){
    reactiveData.initJson = JSON.parse(props.initJsonStr)
  }
})
// 提交按钮属性
const submitAttrs = ref({
  buttonText: '添加示例',
})
// 添加示例按钮
const submitMethod = ():void => {
  let item = {
    outputKey: reactiveData.form.outputKey,
    dataQueryDatasourceApiId: reactiveData.form.dataQueryDatasourceApiId,
    datasourceApiName: reactiveData.form.datasourceApiName
  }
  reactiveData.initJson.aggregationItems.push(item)
}

// 表格操作按钮
const getTableRowButtons = ({row, column, $index}) => {
  if($index < 0){
    return []
  }
  let tableRowButtons = [
    {
      txt: '删除',
      text: true,
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        reactiveData.initJson.aggregationItems.splice($index,1)
      }
    }
  ]
  return tableRowButtons
}
// 暴露方法
defineExpose({
  getInitJson: () => reactiveData.initJson
})
</script>
<template>
  <!-- 添加表单 -->
  <PtForm :form="reactiveData.form"           :formData="reactiveData.formData"
          class="pt-wdith-100-pc"
          :method="submitMethod"
          defaultButtonsShow="submit,reset"
          :submitAttrs="submitAttrs"
          :layout="1"
          :comps="reactiveData.formComps">
  </PtForm>

  <PtTable ref="tableRef"
           :options="reactiveData.initJson.aggregationItems"
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