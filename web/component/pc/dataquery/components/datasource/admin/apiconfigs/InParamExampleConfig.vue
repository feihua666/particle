<script setup name="InParamExampleConfig" lang="ts">
import {nextTick, onMounted, reactive, ref, watch} from 'vue'

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
  inParamExamples: Example[]
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
  initJson: {inParamExamples: []},
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
                  let exist = reactiveData.initJson.inParamExamples.some(item => item.name == value)
                  if (exist && isAdd.value) {
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
  buttonText: isAdd.value ? '添加示例' : '修改示例',
})
// 侦听
watch(
    () => isAdd.value,
    (val,valOld) => {
      submitAttrs.value = ({
        buttonText: val ? '添加示例' : '修改示例',
      })
    }
)
// 添加示例按钮
const submitMethod = ():void => {
  let data = {
    name: reactiveData.form.name,
    content: reactiveData.form.content
  }

  if(isAdd.value){
    reactiveData.initJson.inParamExamples.push(data)
  }else {
    reactiveData.initJson.inParamExamples.splice(updateIndex.value,1,data)
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
          reactiveData.form.content = item.content
        })
      }
    },
    {
      txt: '删除',
      text: true,
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        reactiveData.initJson.inParamExamples.splice($index,1)
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
          :layout="1"
          :comps="reactiveData.formComps">
  </PtForm>

  <PtTable ref="tableRef"
           :options="reactiveData.initJson.inParamExamples"
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
