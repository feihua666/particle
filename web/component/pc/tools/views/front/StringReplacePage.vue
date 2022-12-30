<script setup name="StringReplacePage" lang="ts">
/**
 * 字符串替换工具页面
 */
import {reactive,getCurrentInstance, ref} from 'vue'
import {lowerFirst, replace, upperFirst} from "../../../../../global/common/tools/StringTools";

const { proxy } = getCurrentInstance()
// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {},
  // 表单数据对象
  formData: {},
})
// 表单项
const formComps = ref(
    [
      {
        field: {
          name: 'rawText'
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '原文本',
            required: true
          },
          compProps: {
            clearable: true,
            rows: 10,
            type: 'textarea'
          }
        }
      },
      {
        field: {
          name: 'fromReplaceText'
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '待替换文本',
            required: true,
          },
          compProps: {
            clearable: true,
            placeholder: '待替换文本'
          }
        }
      },
      {
        field: {
          name: 'toReplaceText'
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '替换文本',
            required: true,
          },
          compProps: {
            clearable: true,
            placeholder: '替换文本'
          }
        }
      },
      {
        field: {
          name: 'firstLetterCheck',
          value: true
        },
        element: {
          comp: 'el-checkbox',
          formItemProps: {
            label: '首字母转换后再次替换'
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'exchangeReplace',
          value: true
        },
        element: {
          comp: 'el-checkbox',
          formItemProps: {
            label: '交换替换'
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'middleText',
          value: 'middleText'
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '中间文本',
            required: ({form})=> form.exchangeReplace
          },
          compProps: {
            clearable: true,
            placeholder: '中间文本',
            tips: '交换替换时必填'
          }
        }
      },
      {
        field: {
          name: 'text'
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '结果'
          },
          compProps: {
            clearable: true,
            readonly: true,
            placeholder: '结果这里显示完成的结果',
            rows: 10,
            type: 'textarea'
          }
        }
      },
    ]
)

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '确认替换',
})
// 提交按钮
const submitMethod = (form) => {
  // 表单数据
  let rawText = form.rawText
  let fromReplaceText = form.fromReplaceText
  let middleText = form.middleText
  let exchangeReplace = form.exchangeReplace
  let toReplaceText = form.toReplaceText
  let firstLetterCheck = form.firstLetterCheck

  // 处理
  let result = rawText
  if (firstLetterCheck) {
    if (exchangeReplace) {
      if(result.indexOf(upperFirst(middleText)) >=0 || result.indexOf(lowerFirst(middleText)) >=0){
        alert('原文本中包括中间文本，这会导致替换错误，请更换一个原始文本中不存在的文本')
        return
      }
      result = doReplace(result,fromReplaceText,middleText,true)
      result = doReplace(result,toReplaceText,fromReplaceText,true)
      result = doReplace(result,middleText,toReplaceText,true)

    }else {
      result = doReplace(result,fromReplaceText,toReplaceText,true)
    }
  }else {
    if (exchangeReplace) {
      if(result.indexOf(middleText) >=0){
        alert('原文本中包括中间文本，这会导致替换错误，请更换一个原始文本中不存在的文本')
        return
      }
      result = doReplace(result,fromReplaceText,middleText,false)
      result = doReplace(result,toReplaceText,fromReplaceText,false)
      result = doReplace(result,middleText,toReplaceText,false)

    }else {
      result = doReplace(result,fromReplaceText,toReplaceText,false)
    }
  }

  form.text = result
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
const doReplace = (text: string,from: string,to: string,upperAndLower?: boolean): string => {
  let result = text
  if (upperAndLower) {
    result = replace(result,upperFirst(from),upperFirst(to),true)
    result = replace(result,lowerFirst(from),lowerFirst(to),true)
  }else {
    result = replace(result,from,to,true)
  }
  return result
}

// 成功提示语
const submitMethodSuccess = () => {
  return '替换成功'
}
</script>
<template>
  <!-- 添加表单 -->
  <PtForm :form="reactiveData.form"
          :formData="reactiveData.formData"
          labelWidth="80"
          :method="submitMethod"
          :methodSuccess="submitMethodSuccess"
          defaultButtonsShow="submit,reset"
          :submitAttrs="submitAttrs"
          :layout="1"
          label-position="top"
          :comps="formComps">
  </PtForm>
</template>


<style scoped>

</style>