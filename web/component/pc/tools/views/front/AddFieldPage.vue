<script setup name="AddFieldPage" lang="ts">
/**
 * 添加字段，主要是对使用低代码生成的模型添加字段
 */
import {reactive,getCurrentInstance, ref,nextTick} from 'vue'
import {lowerFirst, replace, underlineToHump, upperFirst} from "../../../../../global/common/tools/StringTools";
import {addField} from "../../api/front/toolsFrontApi";

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
          name: 'afterFieldName',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '在哪个字段之后添加',
            tips: '注意是字段名称不是数据库字段名称'
          },
          compProps: {
            clearable: true,
            placeholder: '支持数据库字段名自动转换为驼峰命名',
          }
        }
      },
      {
        field: {
          name: 'domainName'
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '领域模型名称',
            required: true,
          },
          compProps: {
            clearable: true,
            placeholder: '领域模型名称，一般为类名称'
          }
        }
      },
      {
        field: {
          name: 'componentBackendAbsolutePath'
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '后端组件绝对路径',
            required: true,
            tips: '如：/Users/yw/fh/git-source/particle/component/tenant'
          },
          compProps: {
            clearable: true,
            placeholder: '如：/Users/yw/fh/git-source/particle/component/tenant'
          }
        }
      },
      {
        field: {
          name: 'items1fieldName',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '要添加的字段1名称',
            required: true,
          },
          compProps: {
            clearable: true,
            placeholder: '支持数据库字段名自动转换为驼峰命名',
          }
        }
      },
      {
        field: {
          name: 'items1fieldComment',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '要添加的字段1注释',
            required: true,
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'items1fieldType',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '要添加的字段1类型',
            required: true,
          },
          compProps: {
            placeholder: '如：String',
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'items2fieldName',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '要添加的字段2名称',
          },
          compProps: {
            clearable: true,
            placeholder: '支持数据库字段名自动转换为驼峰命名',
          }
        }
      },
      {
        field: {
          name: 'items2fieldComment',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '要添加的字段2注释',
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'items2fieldType',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '要添加的字段2类型',
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'items3fieldName',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '要添加的字段3名称',
          },
          compProps: {
            clearable: true,
            placeholder: '支持数据库字段名自动转换为驼峰命名',
          }
        }
      },
      {
        field: {
          name: 'items3fieldComment',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '要添加的字段3注释',
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'items3fieldType',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '要添加的字段3类型',
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'items4fieldName',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '要添加的字段4名称',
          },
          compProps: {
            clearable: true,
            placeholder: '支持数据库字段名自动转换为驼峰命名',
          }
        }
      },
      {
        field: {
          name: 'items4fieldComment',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '要添加的字段4注释',
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'items4fieldType',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '要添加的字段4类型',
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'items5fieldName',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '要添加的字段5名称',
          },
          compProps: {
            clearable: true,
            placeholder: '支持数据库字段名自动转换为驼峰命名',
          }
        }
      },
      {
        field: {
          name: 'items5fieldComment',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '要添加的字段5注释',
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'items5fieldType',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '要添加的字段5类型',
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
  buttonText: '确认添加',
})
// 提交按钮
const submitMethod = (form) => {
  let formData = {
    afterFieldName: underlineToHump(form.afterFieldName),
    componentBackendAbsolutePath: form.componentBackendAbsolutePath,
    domainName: form.domainName,
    items: []
  }
  for (let i = 0; i < 5; i++) {
    let index = i + 1
    let item = {
      fieldName: underlineToHump(form['items' + index + 'fieldName']),
      fieldComment: form['items' + index + 'fieldComment'],
      fieldType: form['items' + index + 'fieldType']
    }
    if (item.fieldName && item.fieldComment && item.fieldType) {
      formData.items.push(item)
    }
  }
  return addField(formData)
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
  return '添加成功'
}
</script>
<template>
  <!-- 添加表单 -->
  <PtForm :form="reactiveData.form"
          :formData="reactiveData.formData"
          labelWidth="150"
          :method="submitMethod"
          :methodSuccess="submitMethodSuccess"
          defaultButtonsShow="submit,reset"
          :submitAttrs="submitAttrs"
          :layout="[2,1]"
          :comps="formComps">
  </PtForm>
  <div><el-text type="success">常用字段类型：一般建议要添加的字段类型输入简单名称如：输入String即可</el-text></div>
  <div><el-text>java.lang.String</el-text></div>
  <div><el-text>java.lang.Integer</el-text></div>
  <div><el-text>java.lang.Boolean</el-text></div>
  <div><el-text>java.lang.Long</el-text></div>
  <div><el-text>java.math.BigDecimal</el-text></div>
</template>


<style scoped>

</style>