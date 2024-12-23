import {
    useSelectOpenplatformDocApiCompItem,
    useSelectOpenplatformDocApiDocCompItem
} from "../../openplatformDocCompItem";

export const pageFormItems = [
      {
        field: {
          name: 'code',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '业务编码',

          },
          compProps: {
            clearable: true,
            placeholder: '左前缀匹配'
          }
        }
      },
  {
    field: {
      name: 'codeStatus',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '业务状态码码',
      },
      compProps: {
        clearable: true,
        placeholder: '左前缀匹配'
      }
    }
  },
      {
        field: {
          name: 'explanation',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '字段说明',

          },
          compProps: {
            clearable: true,
            placeholder: '左前缀匹配'
          }
        }
      },
  useSelectOpenplatformDocApiCompItem({}),
  useSelectOpenplatformDocApiDocCompItem({}),
]
export const addPageFormItems = [




      {
        field: {
          name: 'code',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '业务编码',
            required: true,
            tips: '响应业务编码，如：user_not_found'
          },
          compProps: {
            clearable: true,
          }
        }
      },
  {
    field: {
      name: 'codeStatus',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '业务状态码',
        required: true,
        tips: '响应业务状态码，如：40300000001'
      },
      compProps: {
        clearable: true,
      }
    }
  },
  {
    field: {
      name: 'httpCode',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: 'http状态码',
        tips: 'http状态码，如：200、500，只能为数字'
      },
      compProps: {
        clearable: true,
      }
    }
  },


      {
        field: {
          name: 'explanation',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '字段说明',
            required: true,
          },
          compProps: {
            clearable: true,
          }
        }
      },


  {
    field: {
      name: 'isCharge',
    },
    element: {
      comp: 'el-switch',
      formItemProps: {
        label: '是否计费',
        required: true,
      },
      compProps: {
        activeText: '是',
        inactiveText: '否',
      }
    }
  },
  useSelectOpenplatformDocApiDocCompItem({required: true,tips: '绑定的文档内容'}),


      {
        field: {
          name: 'isGlobal',
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否全局通用码',
            required: true,
          },
          compProps: {
            activeText: '是',
            inactiveText: '否',
          }
        }
      },
  {
    field: {
      name: 'seq',
      value: 1000
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

      {
        field: {
          name: 'remark',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '描述',
            tips: '字符说明的额外描述'
          },
          compProps: {
            clearable: true,
          }
        }
      },












]

// 更新和添加一致
export const updatePageFormItems = addPageFormItems

