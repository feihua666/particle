<script setup name="OutParamSuccessConfig" lang="ts">
import {onMounted, reactive, ref} from 'vue'
import {clone} from "../../../../../../../global/common/tools/ObjectTools";
import {paramType} from "../dataQueryDatasourceApiManage";

/**
 * 验证项
 */
interface ValidateItem{
  // 名称，用来说明是什么验证
  name: string,
  // 验证类型，这是一个入参验证字典 对应字典组 dataquery_datasource_api_param_validate_type 下面字典项的字典值
  type: string,
  // 验证内容
  contentTemplate: string,
  // 验证失败时提示信息
  errorMessage: string
}
// 验证对象
// props.initJsonStr 需要符合该类型
interface InitJson{
  outParamValidateItems: ValidateItem[]
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
  initJson: {outParamValidateItems: []},
// 表单初始查询第一页
  form: {
  },
  formComps: [
    {
      field: {
        name: 'name',
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '验证名称',
          required: true,
        },
        compProps: {
          clearable: true,
        }
      }
    },
    {
      field: {
        name: 'type',
      },
      element: {
        comp: 'PtDictFrontSelect',
        formItemProps: {
          label: '类型',
          required: true
        },
        compProps: {
          // 字典查询
          dictParam: {groupCode: 'dataquery_datasource_api_param_validate_type'},
          // 选取value为选重值
          props: {value: 'value'},
        }
      }
    },
    {
      field: {
        name: 'contentTemplate',
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '验证内容',
          required: true,
          displayBlock: true,
          tips: `enjoy示例：#(validateResult.setIsSuccess(data.name == 1)) validateResult变量为固定值，可调用方法设置成功与失败，data变量为参数句柄。<br/>javascript示例：data.name == 1，data变量为参数句柄`
        },
        compProps: {
          type: 'textarea',
          rows: 15,
          clearable: true,
        }
      }
    },
    {
      field: {
        name: 'errorMessage',
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '校验失败时提示信息',
          required: true,
        },
        compProps: {
          clearable: true,
        }
      }
    },
  ],
  tableColumns: [
    {
      prop: 'name',
      label: '验证名称',
    },
    {
      prop: 'type',
      label: '类型',
    },
    {
      prop: 'contentTemplate',
      label: '验证内容',
      showOverflowTooltip: true
    },
    {
      prop: 'errorMessage',
      label: '校验失败时提示信息',
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
  buttonText: '添加验证规则',
})
// 添加验证按钮
const submitMethod = ():void => {
  reactiveData.initJson.outParamValidateItems.push({
    name: reactiveData.form.name,
    type: reactiveData.form.type,
    contentTemplate: reactiveData.form.contentTemplate,
    errorMessage: reactiveData.form.errorMessage
  })
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
        reactiveData.initJson.outParamValidateItems.splice($index,1)
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
  <PtForm :form="reactiveData.form" class="pt-wdith-100-pc"
          :method="submitMethod"
          defaultButtonsShow="submit,reset"
          :submitAttrs="submitAttrs"
          :layout="[[8,8],1]"
          inline
          :comps="reactiveData.formComps">
  </PtForm>

  <PtTable ref="tableRef"
           :options="reactiveData.initJson.outParamValidateItems"
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