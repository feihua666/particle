<script setup name="InParamValidateConfig" lang="ts">
import {onMounted, reactive, ref,nextTick,watch} from 'vue'
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
  inParamValidateItems: ValidateItem[]
}


const selfFormRef = ref(null)
const tableRef = ref(null)

// 用来判断是否添加，true=添加，false=修改
const isAdd = ref(true)
// 修改时，记录修改的位置
const updateIndex = ref(null)

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
  initJson: {inParamValidateItems: []},
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
          rules: [
            { validator: (rule: any, value: any, callback: any) => {
                if (value) {
                  let exist = reactiveData.initJson.inParamValidateItems.some(item => item.name == value)
                  if (exist && isAdd.value) {
                    callback(new Error('验证名称已存在'))
                  }else {
                    callback()
                  }
                } else {
                  callback(new Error('验证名称不能为空'))
                }
              }, trigger: 'blur'
            }
          ]
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
          tips: "enjoy示例：#(validateResult.setIsSuccess(data.name == 1)) validateResult变量为固定值，可调用方法设置成功与失败，data变量为参数句柄。<br/>" +
              "javascript示例：data.name == 1，data变量为参数句柄<br/>" +
              "groovy示例：data.name == 1，data变量为参数句柄 "
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
  buttonText: isAdd.value ? '添加验证规则' : '修改验证规则',
})
// 侦听
watch(
    () => isAdd.value,
    (val,valOld) => {
      submitAttrs.value = ({
        buttonText: val ? '添加验证规则' : '修改验证规则',
      })
    }
)
// 添加验证按钮
const submitMethod = ():void => {
  let data = {
    name: reactiveData.form.name,
    type: reactiveData.form.type,
    contentTemplate: reactiveData.form.contentTemplate,
    errorMessage: reactiveData.form.errorMessage
  }

  if(isAdd.value){
    reactiveData.initJson.inParamValidateItems.push(data)
  }else {
    reactiveData.initJson.inParamValidateItems.splice(updateIndex.value,1,data)
  }
}

// 表格操作按钮
const getTableRowButtons = ({row, column, $index}) => {
  if($index < 0){
    return []
  }
  let tableRowButtons = [
    {
      txt: '修改',
      text: true,
      // 删除操作
      method(){
        let item = row
        resetForm()

        updateIndex.value = $index
        nextTick(()=>{
          isAdd.value = false
          reactiveData.form.name = item.name
          reactiveData.form.type = item.type
          reactiveData.form.contentTemplate = item.contentTemplate
          reactiveData.form.errorMessage = item.errorMessage
        })
      }
    },
    {
      txt: '删除',
      text: true,
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        reactiveData.initJson.inParamValidateItems.splice($index,1)
      }
    }
  ]
  return tableRowButtons
}
const resetForm = ()=> {
  // selfFormRef.value?.resetForm()
  selfFormRef.value?.formRef.value?.resetFields()
}
const onResetForm = () => {
  isAdd.value = true
}
// 暴露方法
defineExpose({
  getInitJson: () => reactiveData.initJson
})
</script>
<template>
  <!-- 添加表单 -->
  <PtForm ref="selfFormRef" :form="reactiveData.form" class="pt-wdith-100-pc"
          :method="submitMethod"
          defaultButtonsShow="submit,reset"
          :submitAttrs="submitAttrs"
          :onResetForm="onResetForm"
          :layout="[[8,8],1]"
          inline
          :comps="reactiveData.formComps">
  </PtForm>

  <PtTable ref="tableRef"
           :options="reactiveData.initJson.inParamValidateItems"
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