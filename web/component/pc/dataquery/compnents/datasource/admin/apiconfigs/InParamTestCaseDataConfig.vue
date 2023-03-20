<script setup name="InParamTestCaseDataConfig" lang="ts">
import {onMounted, reactive, ref} from 'vue'
import {anyObj} from "../../../../../../../global/common/tools/ObjectTools";

/**
 * 用例项
 */
interface TestCase{
  // 名称，用来说明是什么用例
  name: string,
  // 用例内容,支持字符串、数字、对象、对象数组等多种方式，一般为 对象
  content: string|number|anyObj|anyObj[]
}
// 用例对象
// props.initJsonStr 需要符合该类型
interface InitJson{
  inParamTestCases: TestCase[]
}


const tableRef = ref(null)

// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 初始化数据
  initJsonStr: {
    type: String
  },
})
// 属性
const reactiveData = reactive({
  initJson: {inParamTestCases: []},
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
          label: '用例名称',
          required: true,
          rules: [
            { validator: (rule: any, value: any, callback: any) => {
                if (value) {
                  let exist = reactiveData.initJson.inParamTestCases.some(item => item.name == value)
                  if (exist) {
                    callback(new Error('用例名称已存在'))
                  }else {
                    callback()
                  }
                } else {
                  callback(new Error('用例名称不能为空'))
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
        name: 'content',
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '用例内容',
          required: true
        },
        compProps: {
          type: 'textarea',
          rows: 15,
          clearable: true,
        }
      }
    },
  ],
  tableColumns: [
    {
      prop: 'name',
      label: '用例名称',
    },
    {
      prop: 'content',
      label: '用例内容',
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
  buttonText: '添加用例',
})
// 添加用例按钮
const submitMethod = ():void => {
  reactiveData.initJson.inParamTestCases.push({
  name: reactiveData.form.name,
  content: reactiveData.form.content
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
        reactiveData.initJson.inParamTestCases.splice($index,1)
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
          :layout="1"
          :comps="reactiveData.formComps">
  </PtForm>

  <PtTable ref="tableRef"
           :options="reactiveData.initJson.inParamTestCases"
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