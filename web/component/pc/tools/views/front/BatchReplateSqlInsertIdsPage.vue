<script setup name="BatchReplateSqlInsertIdsPage" lang="ts">
/**
 * 批量替换id，主要是将复制的insert sql语句中的id替换成新的id
 */
import {reactive,getCurrentInstance, ref} from 'vue'
import {batchGenIds} from "../../api/front/toolsFrontApi";
import {match} from "../../../../../global/common/tools/RegTools";

const { proxy } = getCurrentInstance()
// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {},
  resultForm: {result: ''},
  // 表单数据对象
  formData: {},
})
// 表单项
const formComps = ref(
    [
      {
        field: {
          name: 'insertSql',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: 'sql插入语句',
            required: true
          },
          compProps: {
            clearable: true,
            type: 'textarea',
            rows: 10
          }
        }
      },
      {
        field: {
          name: 'idExtractPattern',
          value: 'VALUES \\(([^,]+),'
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: 'id提取正则表达式',
            required: true,
            tips: '经测试，默认的正则表达式支持datagrip和navicat复制的sql插入语句'
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'valueSeparator',
          value: '='
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '值分隔符',
            tips: '如果被替换的文本包括分隔符时，请填写其它分隔符',
            required: true
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'groupSeparator',
          value: ';'
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '组分隔符',
            tips: '如果被替换的文本包括分隔符时，请填写其它分隔符',
            required: true
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'replaceText',
          value: 'VALUES (=;,='
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '提取完成后替换',
            tips: '支持多组替换如：a=b,c=d将会把a替换成b，c替换成d',
          },
          compProps: {
            clearable: true,
          }
        }
      },

    ]
)
const resultFormComps= ref(
    [
      {
        field: {
          name: 'result'
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '替换结果',
          },
          compProps: {
            clearable: true,
            type: 'textarea',
            rows: 25
          }
        }
      },

    ]
)

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '确认生成',
})

// 提交按钮
const submitMethod = () => {
  batchIds.value = []
  batchIdsIndex.value = 0
  reactiveData.resultForm.result = ''
  replaceIds()
}
const generateIds = (num,callback) => {
  batchGenIds({num: num}).then(res => {
    for (let i = 0; i < res.data.data.length; i++) {
      batchIds.value.push(res.data.data[i])
    }
    callback()
  });
}
/**
 * 替换
 */
const replaceIds = () => {
  // 将原始数据拆分成行
  let lines = reactiveData.form.insertSql.split('\n')
  let num = 100
  let times = Math.ceil(lines.length / num)  + 1

  // 根据行替换
  const doReplaceIds = (lines) => {
    lines.forEach (splitKey => {
      if (!splitKey) {
        return
      }
      if (!splitKey.trim()) {
        return
      }
      let matched = match(reactiveData.form.idExtractPattern, splitKey);
      if (matched.length === 0) {
        alert('没有匹配到内容')
        return
      }
      let finalId = replaceMatched(matched[0])

      let newId = nextNewId()
      replaceResult(finalId,newId)
    })
  }
  // 根据行加载需要的足够的id
  const loadAllIds = (times,callback) => {
    generateIds(num,() => {
      times -= 1
      if (times > 0) {
        loadAllIds(times,callback)
      }else {
        callback()
      }
    })
  }

  loadAllIds(times,() => {doReplaceIds(lines)})

}
const batchIds = ref([])
const batchIdsIndex = ref(0)

/**
 * 获取下一个可用的id
 */
const nextNewId = () => {
  return batchIds.value[batchIdsIndex.value++]

}
/**
 * 替换提取后的结果
 * @param text
 */
const replaceMatched = (text) => {
  let result = ''
  let replaceText = reactiveData.form.replaceText
  if (replaceText) {
    replaceText.split(reactiveData.form.groupSeparator).forEach(splitKey =>
    {
      let split = splitKey.split(reactiveData.form.valueSeparator)
      if (!result) {
        result = text.replaceAll(split[0], split[1]);
      } else {
        result = result.replaceAll(split[0], split[1]);
      }
    })
  }
  return result;
}
/**
 * 替换结果
 * @param oldText
 * @param newText
 */
const replaceResult = (oldText,newText) => {
  if (!reactiveData.resultForm.result) {
    reactiveData.resultForm.result = reactiveData.form.insertSql.replaceAll(oldText, newText);
  } else {
    reactiveData.resultForm.result = reactiveData.resultForm.result.replaceAll(oldText, newText);
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
          :layout="[1,3,1]"
          :comps="formComps">
  </PtForm>
  <!-- 结果表单 -->
  <PtForm :form="reactiveData.resultForm"
          labelWidth="80"
          defaultButtonsShow=""
          :layout="1"
          :comps="resultFormComps">
  </PtForm>
</template>


<style scoped>

</style>