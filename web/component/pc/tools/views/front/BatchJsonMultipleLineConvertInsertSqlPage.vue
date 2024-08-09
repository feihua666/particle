<script setup name="BatchJsonMultipleLineConvertInsertSqlPage" lang="ts">
/**
 * 批量按行提取json，并转换为insert sql字符串
 * 注意：每一行必须是一个完善的json字符串
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
          name: 'handleLogical',
          value: 'convertInsertSql'
        },
        element: {
          comp: 'PtRadioGroup',
          formItemProps: {
            label: '处理逻辑',
            required: true
          },
          compProps: {
            options: [
              {id: 'convertInsertSql',name: '转换为insert sql'},
            ],
          }
        }
      },
      {
        field: {
          name: 'tableName',
          value: 't_test'
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '表名',
            required: true,
            tips: '表名，请确保表名正确，否则可能插入失败'
          },
          compProps: {
            clearable: true,
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
  buttonText: '确认转换',
})
// 提交按钮
const submitMethod = (form) => {

  // 表单数据
  handleLogicalMethods[form.handleLogical](form)
}

const handleLogicalMethods = {
  convertInsertSql: (form) => {

    // 表单数据
    let jsonStrLines = form.jsonStrLines.split('\n')
    let resultText = ''
    for (let i = 0; i < jsonStrLines.length; i++) {

      let resultFields = []
      let resultValues = []
      let lineStr = jsonStrLines[i]
      if (!lineStr) {
        continue
      }
      let jsonObj = JSON.parse(lineStr);
      for (let jsonObjKey in jsonObj) {
        resultFields.push(jsonObjKey)
        let value = jsonObj[jsonObjKey]
        if (typeof value === 'string') {
          value = value.replaceAll('\'', '\\\'')
          value = '\'' + value + '\''
        }
        if (null == value) {
          value = 'null'
        }

        resultValues.push(value);
      }
      resultText += 'INSERT INTO ' + form.tableName
      resultText += ' ('
      resultText += resultFields.join(', ')
      resultText += ') '

      resultText += ' VALUES ('
      resultText += resultValues.join(', ')
      resultText += ');'
      resultText += '\n'
    }
    form.text = resultText
  },
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