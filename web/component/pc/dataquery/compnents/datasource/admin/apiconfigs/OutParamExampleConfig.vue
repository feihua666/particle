<script setup name="OutParamExampleConfig" lang="ts">
import {onMounted, reactive, ref} from 'vue'

/**
 * 示例项
 */
interface Example{
  // 名称，用来说明是什么示例
  name: string,
  // 示例内容
  content: string
}
// 示例对象
// props.initJsonStr 需要符合该类型
interface InitJson{
  outParamExamples: Example[]
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
  initJson: {outParamExamples: []},
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
          label: '示例名称',
          required: true,
          rules: [
            { validator: (rule: any, value: any, callback: any) => {
                if (value) {
                  let exist = reactiveData.initJson.outParamExamples.some(item => item.name == value)
                  if (exist) {
                    callback(new Error('示例名称已存在'))
                  }else {
                    callback()
                  }
                } else {
                  callback(new Error('示例名称不能为空'))
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
          label: '示例内容',
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
      label: '示例名称',
    },
    {
      prop: 'content',
      label: '示例内容',
      showOverflowTooltip: true
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
  reactiveData.initJson.outParamExamples.push({
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
        reactiveData.initJson.outParamExamples.splice($index,1)
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
           :options="reactiveData.initJson.outParamExamples"
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