<script setup name="LowcodeModelItemManageAddPage" lang="ts">
/**
 * 低代码模型项管理添加页面
 */
import {reactive ,ref} from 'vue'
import {create as lowcodeModelItemCreateApi,list as lowcodeModelItemListApi} from "../../../api/generator/admin/lowcodeModelItemAdminApi"
import {list as lowcodeModelListApi} from "../../../api/generator/admin/lowcodeModelAdminApi";

// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 加载数据初始化参数,路由传参
  lowcodeModelId: {
    type: String
  }
})
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
          name: 'columnName'
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '字段名称',
            required: true
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'propertyName'
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '实体属性名称',
            required: true
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'jdbcType'
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '字段类型',
            required: true
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'propertyType'
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '实体属性类型',
            required: true
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'propertyFullType'
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '实体属性类型全路径',
            required: true
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'commentFull'
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '字段全注释',
            required: true
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
      field: {
        name: 'commentSimple'
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '字段简洁注释',
          required: true
        },
        compProps: {
          clearable: true,
        }
      }
    },
      {
        field: {
          name: 'defaultValue'
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '默认值',
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
      field: {
        name: 'isUnique'
      },
      element: {
        comp: 'el-switch',
        formItemProps: {
          label: '是否唯一',
          required: true
        },
        compProps: {
          activeText: '唯一',
          inactiveText: '不唯一',
        }
      }
    },
    {
      field: {
        name: 'isRequired'
      },
      element: {
        comp: 'el-switch',
        formItemProps: {
          label: '是否必填',
          required: true
        },
        compProps: {
          activeText: '必填',
          inactiveText: '选填',
        }
      }
    },
    {
      field: {
        name: 'isKey'
      },
      element: {
        comp: 'el-switch',
        formItemProps: {
          label: '是否主键',
          required: true
        },
        compProps: {
          activeText: '主键',
          inactiveText: '非主键',
        }
      }
    },
      {
        field: {
          name: 'isKeyIdentity'
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否主键自增',
            required: true
          },
          compProps: {
            activeText: '自增',
            inactiveText: '非自增',
          }
        }
      },
      {
        field: {
          name: 'isKeyWord'
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否关键字',
            required: true
          },
          compProps: {
            activeText: '关键字',
            inactiveText: '非关键字',
          }
        }
      },
    {
      field: {
        name: 'columnLength',
        value: 0
      },
      element: {
        comp: 'el-input-number',
        formItemProps: {
          label: '字段长度',
          required: true
        },
        compProps: {
          clearable: true,
        }
      }
    },
      {
      field: {
        name: 'fractionLength',
        value: 0
      },
      element: {
        comp: 'el-input-number',
        formItemProps: {
          label: '字段小数位长度',
          required: true
        },
        compProps: {
          clearable: true,
        }
      }
    },
      {
      field: {
        name: 'isForeignKey'
      },
      element: {
        comp: 'el-switch',
        formItemProps: {
          label: '字段是否外键',
          required: true
        },
        compProps: {
          activeText: '外键',
          inactiveText: '非外键',
        }
      }
    },
      {
        field: {
          name: 'lowcodeModelId',
          value: props.lowcodeModelId
        },
        element: {
          comp: 'PtSelect',
          formItemProps: {
            label: '模型',
            required: true
          },
          compProps: {
            clearable: true,
            dataMethod: lowcodeModelListApi
          }
        }
      },
      {
      field: {
        name: 'remark'
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '描述',
        },
        compProps: {
          clearable: true,
        }
      }
    }]
)

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '确认添加',
  permission: 'admin:web:lowcodeModelItem:create',
})
// 提交按钮
const submitMethod = () => {
  return lowcodeModelItemCreateApi
}
// 成功提示语
const submitMethodSuccess = () => {
  return '添加成功，请刷新数据查看'
}

</script>
<template>
  <!-- 添加表单 -->
  <PtForm :form="reactiveData.form"
          :formData="reactiveData.formData"
          labelWidth="150"
          :method="submitMethod()"
          :methodSuccess="submitMethodSuccess"
          defaultButtonsShow="submit,reset"
          :submitAttrs="submitAttrs"
          :buttonsTeleportProps="$route.meta.formButtonsTeleportProps"
          inline
          :layout="2"
          :comps="formComps">
  </PtForm>
</template>


<style scoped>

</style>