<script setup name="BatchJsonMultipleLineExtractFieldsPage" lang="ts">
/**
 * 批量提取json中的字段，并转换为json字符串
 */
import {reactive,getCurrentInstance, ref} from 'vue'
import {lowerFirst, replace, upperFirst} from "../../../../../global/common/tools/StringTools";
import {loadScriptCode} from "../../../../../global/common/tools/DocumentTools";

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
          name: 'jsonStrLines'
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: 'json多行文本',
            required: true,
            tips: '注意：每一行应该是一个完整的json字符串'
          },
          compProps: {
            clearable: true,
            rows: 10,
            type: 'textarea',
          }
        }
      },
      {
        field: {
          name: 'fields'
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '提取的字段',
            tips: '多个以英文逗号分割',
            required: true
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'handleLogical',
          value: 'newJson'
        },
        element: {
          comp: 'PtRadioGroup',
          formItemProps: {
            label: '处理逻辑',
            required: true
          },
          compProps: {
            options: [
              {id: 'newJson',name: '提取后转为新的json'},
              {id: 'valueOnly',name: '仅提取值'},
            ],
          }
        }
      },
      {
        field: {
          name: 'multipleFieldsSeparator',
          value: '|'
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '多个字段时的分隔符',
            required: true,
            tips: '在选择 仅提取值 时有效'
          },
          compProps: {
            clearable: true,
            rows: 2,
            type: 'textarea'
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
            rows: 10,
            type: 'textarea'
          }
        }
      },
    ]
)

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '确认提取',
})
// 提交按钮
const submitMethod = (form) => {

  // 表单数据
  handleLogicalMethods[form.handleLogical](form)
}

const handleLogicalMethods = {
  newJson: (form) => {
    // 表单数据
    let jsonStrLines = form.jsonStrLines.split('\n')
    let resultText = ''
    for (let i = 0; i < jsonStrLines.length; i++) {
      let lineStr = jsonStrLines[i]
      if (!lineStr) {
        continue
      }
      let jsonObj = JSON.parse(lineStr)
      let newJsonObj = {}
      form.fields.split(',').forEach(field => {
        let value = jsonObj[field]
        newJsonObj[field] = value
      })

      resultText += JSON.stringify(newJsonObj) + '\n'
    }
    form.text = resultText
  },
  valueOnly: (form) => {
    // 表单数据
    let jsonStrLines = form.jsonStrLines.split('\n')
    let resultText = ''
    for (let i = 0; i < jsonStrLines.length; i++) {
      let lineStr = jsonStrLines[i]
      if (!lineStr) {
        continue
      }
      let jsonObj = JSON.parse(lineStr)
      let resultLineText = []
      form.fields.split(',').forEach(field => {
        let value = jsonObj[field]
        resultLineText.push(JSON.stringify(value))
      })
      resultText += resultLineText.join(form.multipleFieldsSeparator) + '\n'
    }
    form.text = resultText
  }
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


// 成功提示语
const submitMethodSuccess = () => {
  return '提取成功'
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