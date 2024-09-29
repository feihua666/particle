import {useCascaderOpenplatformDocDirCompItem} from "../../openplatformDocCompItem";
import {useCascaderOpenapiCompItem} from "../../openplatformOpenapiCompItem";

export const pageFormItems = [
      {
        field: {
          name: 'code',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '编码',
            
          },
          compProps: {
            clearable: true,
            placeholder: '左前缀匹配'
          }
        }
      },
      {
        field: {
          name: 'name',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '名称',
            
          },
          compProps: {
            clearable: true,
            placeholder: '左前缀匹配'
          }
        }
      },
      {
        field: {
          name: 'nameSimple',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '简称',
            
          },
          compProps: {
            clearable: true,
            placeholder: '左前缀匹配'
          }
        }
      },
  useCascaderOpenplatformDocDirCompItem({fieldName: 'openplatformDocDirId',label: '接口目录'}),
  useCascaderOpenapiCompItem({
    fieldName: 'openplatformOpenapiId',
    label: '开放接口',
    disableGroup: true
  }),
]
export const addPageFormItems = [

      {
        field: {
          name: 'code',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '编码',
            tips: '全局唯一，用来唯一标识一个接口',
            required: true,
          },
          compProps: {
            clearable: true,
            placeholder: '全局唯一'
          }
        }
      },

      {
        field: {
          name: 'name',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '名称',
            required: true,
            tips: '接口名称'
          },
          compProps: {
            clearable: true,
          }
        }
      },

      {
        field: {
          name: 'nameSimple',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '简称',
            tips: '接口名称的简称'
          },
          compProps: {
            clearable: true,
          }
        }
      },

  {
    field: {
      name: 'logoUrl',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '图标地址',
        tips: '一个图标地址，需要绝对路径',
      },
      compProps: {
        clearable: true,
      }
    }
  },

      {
        field: {
          name: 'pricePerTime',
        },
        element: {
          comp: 'el-input-number',
          formItemProps: {
            label: '每次价格(元)',
            tips: '单次的调用价格，单位元,在为空时可以直接点击+号填写初始值',
            required: ({form}) => !form.pricePerTimeDesc,
          },
          compProps: {
            precision: 1,
            step: 0.1
          }
        }
      },


      {
        field: {
          name: 'pricePerTimeDesc',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '价格文本',
            tips: '如果接口不是按次计费，可以填写该字段以文本形式展示',
            required: ({form}) => !form.pricePerTime,
          },
          compProps: {
            clearable: true,
          }
        }
      },



  {
    field: {
      name: 'seq',
      value: 10
    },
    element: {
      comp: 'el-input-number',
      formItemProps: {
        label: '排序'
      },
      compProps: {
        clearable: true,
      }
    }
  },
  useCascaderOpenplatformDocDirCompItem({
    fieldName: 'openplatformDocDirId',
    label: '接口目录',
    tips: '可以指定一个接口目录，以便于分类,仅添加时可用，修改时不回显，如果修改时，已经绑定了目录，选择无效'
  }),

  useCascaderOpenapiCompItem({
    fieldName: 'openplatformOpenapiId',
    label: '开放接口',
    disableGroup: true,
    tips: '关联开放接口，便于能确定关系，目前使用在接口查询中，用来根据开放接口获取文档配置，以方便在页面中渲染表单'
  }),
  {
    field: {
      name: 'description',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '描述',
        tips: '接口描述',
        displayBlock: true,
      },
      compProps: {
        type: 'textarea',
        clearable: true,
        rows: 15
      }
    }
  },
]

// 更新和添加一致
export const updatePageFormItems = addPageFormItems

