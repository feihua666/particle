<script setup name="DictEnumGenPage" lang="ts">
/**
 * 根据字典组生成对应java枚举
 */
import {reactive,getCurrentInstance, ref} from 'vue'
import {list as dictListApi} from "../../../dict/api/admin/dictAdminApi";
import {genDictEnumJavaClass} from "../../components/front/dictEnumGen";
import {getItems} from "../../../dict/api/front/dictFrontApi";
import {underlineToHump, upperFirst} from "../../../../../global/common/tools/StringTools";

const { proxy } = getCurrentInstance()
// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {},
  // 表单数据对象
  formData: {},

  result: ''
})
// 表单项
const formComps = ref(
    [
      {
        field: {
          name: 'dictId',
          valueChange({form,formData}){
            setTimeout(()=> {
              form.className = upperFirst(underlineToHump(formData.dictId?.code,['-']))
            },400)
          }
        },
        element: {
          comp: 'PtCascader',
          formItemProps: {
            label: '选择一个字典组',
            required: true
          },
          compProps: {
            clearable: true,
            // 加载数据
            dataMethod: () => { return dictListApi({})},
            dataMethodResultHandleConvertToTree: true,
          }
        }
      },
      {
        field: {
          name: 'className'
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '类名称',
            required: true
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'author',
          value: 'yw'
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '作者',
            required: true
          },
          compProps: {
            clearable: true,
          }
        }
      },
    ]
)

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '确认生成',
  beforeMethod: ()=>{
    if(reactiveData.form.dictId && !reactiveData.formData.dictId?.isGroup){
      alert('仅支持选择字典组')
      return false
    }
    return true
  }
})
// 提交按钮
const submitMethod = (form) => {
  getItems({groupCode: reactiveData.formData.dictId.code}).then(res=>{

    reactiveData.result = doGen(
        reactiveData.form.className,
        reactiveData.form.author,
        res.data.data)
  })
}
const alert = (message) =>{
  proxy.$message({
    showClose: true,
    message: message,
    type: 'error',
    showIcon: true,
    grouping: true
  })
}
const doGen = (className: string,author: string,dictItems: any[]): string => {
  let result = genDictEnumJavaClass({className,author,dictGroupData: reactiveData.formData.dictId,dictItems})
  return result
}

// 成功提示语
const submitMethodSuccess = () => {
  return '生成成功'
}
</script>
<template>
  <!-- 添加表单 -->
  <PtForm :form="reactiveData.form"
          :formData="reactiveData.formData"
          labelWidth="120"
          :method="submitMethod"
          :methodSuccess="submitMethodSuccess"
          defaultButtonsShow="submit,reset"
          :submitAttrs="submitAttrs"
          inline
          :comps="formComps">
  </PtForm>

  <el-input v-model="reactiveData.result" type="textarea" rows="20" placeholder="生成结果这里显示"></el-input>
</template>


<style scoped>

</style>