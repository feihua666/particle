<script setup name="StringMultipleLineCompareToolPage" lang="ts">
/**
 * 字符串多行比较操作工具页面
 */
import {reactive,getCurrentInstance, ref} from 'vue'
import {lowerFirst, replace, upperFirst} from "../../../../../global/common/tools/StringTools";
import {removeDuplicate} from "../../../../../global/common/tools/ArrayTools";

const { proxy } = getCurrentInstance()
// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {},
  resultForm: {leftText: '', rightText: ''},
  // 表单数据对象
  formData: {},
})
// 表单项
const formComps = ref(
    [
      {
        field: {
          name: 'leftText'
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '左侧多行文本',
            required: true
          },
          compProps: {
            clearable: true,
            rows: 20,
            type: 'textarea'
          }
        }
      },
      {
        field: {
          name: 'rightText'
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '右侧多行文本',
          },
          compProps: {
            clearable: true,
            rows: 20,
            type: 'textarea'
          }
        }
      },
      {
        field: {
          name: 'handleLogical',
          value: 'removeIntersection'
        },
        element: {
          comp: 'PtRadioGroup',
          formItemProps: {
            label: '处理逻辑',
            required: true
          },
          compProps: {
            options: [
              {id: 'removeIntersection',name: '去除交集'},
              {id: 'fetchIntersection',name: '获取交集'},
              {id: 'fetchUnion',name: '获取并集'},
              {id: 'removeDuplicate',name: '按行去重'},
            ],
          }
        }
      },
    ]
)
const resultFormComps= ref(
    [
      {
        field: {
          name: 'leftText'
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '左侧处理结果',
          },
          compProps: {
            clearable: true,
            type: 'textarea',
            rows: 20
          }
        }
      },
      {
        field: {
          name: 'rightText'
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '右侧处理结果',
          },
          compProps: {
            clearable: true,
            type: 'textarea',
            rows: 20
          }
        }
      },
    ]
)
// 提交按钮属性
const submitAttrs = ref({
  buttonText: '确认处理',
})
const handleLogicalMethods = {
  removeIntersection: (form) => {
    let leftText = form.leftText||''
    let rightText = form.rightText||''
    let leftTextLines = leftText.split('\n')
    let rightTextLines = rightText.split('\n')
    let resultLeft = ''
    let resultRight = ''
    leftTextLines.forEach(leftTextLine => {
      if (rightTextLines.indexOf(leftTextLine) >= 0) {
        return
      }
      resultLeft += leftTextLine +'\n'
    })
    rightTextLines.forEach(rightTextLine => {
      if (leftTextLines.indexOf(rightTextLine) >= 0) {
        return
      }
      resultRight += rightTextLine +'\n'
    })
    reactiveData.resultForm.leftText = resultLeft
    reactiveData.resultForm.rightText = resultRight
  },
  fetchIntersection: (form) => {
    let leftText = form.leftText||''
    let rightText = form.rightText||''
    let leftTextLines = leftText.split('\n')
    let rightTextLines = rightText.split('\n')

    let result = leftTextLines.filter(leftTextLine => {
      return rightTextLines.indexOf(leftTextLine) >= 0
    })
    reactiveData.resultForm.leftText = result
    reactiveData.resultForm.rightText = result

  },
  fetchUnion: (form) => {
    let leftText = form.leftText||''
    let rightText = form.rightText||''
    let leftTextLines = leftText.split('\n')
    let rightTextLines = rightText.split('\n')
    let resultLeft = leftTextLines.concat(rightTextLines)
    let resultRight = rightTextLines.concat(leftTextLines)
    reactiveData.resultForm.leftText = removeDuplicate(resultLeft).join('\n')
    reactiveData.resultForm.rightText = removeDuplicate(resultRight).join('\n')
  },
  removeDuplicate: (form) => {
    let leftText = form.leftText||''
    let rightText = form.rightText||''
    let leftTextLines = leftText.split('\n')
    let rightTextLines = rightText.split('\n')
    let resultLeft = removeDuplicate(leftTextLines).join('\n')
    let resultRight = removeDuplicate(rightTextLines).join('\n')
    reactiveData.resultForm.leftText = resultLeft
    reactiveData.resultForm.rightText = resultRight
  }
 }
// 提交按钮
const submitMethod = (form) => {
  handleLogicalMethods[form.handleLogical](form)
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
  return '处理成功'
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
          :layout="2"
          :comps="formComps">
  </PtForm>
  <!-- 结果表单 -->
  <PtForm :form="reactiveData.resultForm"
          labelWidth="80"
          defaultButtonsShow=""
          :layout="2"
          :comps="resultFormComps">
  </PtForm>
</template>


<style scoped>

</style>