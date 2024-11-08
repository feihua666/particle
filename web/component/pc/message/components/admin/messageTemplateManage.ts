import {treeQueryComps} from "../../../treeQueryComps";
import {useCascaderMessageTemplateCompItem} from "../messageCompItem";

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
        placeholder: '左前缀匹配',
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
        label: '模板名称',

      },
      compProps: {
        clearable: true,
        placeholder: '左前缀匹配',
      }
    }
  },
  {
    field: {
      name: 'typeDictId',
    },
    element: {
      comp: 'PtDictFrontSelect',
      formItemProps: {
        label: '消息模板分类',

      },
      compProps: {
        // 字典查询
        dictParam: {groupCode: 'message_template_type'}
      }
    }
  },
  useCascaderMessageTemplateCompItem({}),
  ...treeQueryComps
]
export const useAddPageFormItems = ({contentDetailJsonDialogVisible})=>{
  return [

    {
      field: {
        name: 'isGroup',
        value: false
      },
      element: {
        comp: 'el-switch',
        formItemProps: {
          label: '分组/模板',
          required: true,
        },
        compProps: {
          activeText: '分组',
          inactiveText: '模板',
        }
      }
    },
    {
      field: {
        name: 'code',
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '编码',
          required: ({form})=> form.isGroup === false
        },
        compProps: {
          clearable: true,
          placeholder: '编码唯一'
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
          label: '模板名称',
          required: true,
        },
        compProps: {
          clearable: true,
        }
      }
    },

    {
      field: {
        name: 'titleTpl',
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '消息标题模板',
          displayBlock: true,
          tips: '仅支持enjoy模板'
        },
        compProps: {
          clearable: true,
        }
      }
    },


    {
      field: {
        name: 'titleTplMemo',
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '消息标题模板说明',
          displayBlock: true
        },
        compProps: {
          clearable: true,
        }
      }
    },


    {
      field: {
        name: 'contentTpl',
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '消息内容模板',
          displayBlock: true,
          tips: '仅支持enjoy模板',
          required: ({form})=> form.isGroup === false
        },
        compProps: {
          clearable: true,
          type: 'textarea',
          rows: 15
        }
      }
    },


    {
      field: {
        name: 'contentTplMemo',
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '消息内容模板说明',
          displayBlock: true,
        },
        compProps: {
          clearable: true,
        }
      }
    },

    {
      field: {
        name: 'isContentHtml',
        value: false
      },
      element: {
        comp: 'el-switch',
        formItemProps: {
          label: '内容类型',
        },
        compProps: {
          activeText: 'html文本',
          inactiveText: '纯文本',
        }
      }
    },
    {
      field: {
        name: 'contentDetailJson',
      },
      element: {
        comp: 'PtButton',
        formItemProps: {
          label: '个性化内容详情配置',
        },
        compProps: ({form,formData})=>{
          return {
            text: true,
            type: form.contentDetailJson ? 'primary' : 'default',
            buttonText: '点击设置',
            method: ()=>{
              if(contentDetailJsonDialogVisible){
                contentDetailJsonDialogVisible.value = true
              }
            }
          }
        }
      }
    },
    {
      field: {
        name: 'typeDictId',
      },
      element: {
        comp: 'PtDictFrontSelect',
        formItemProps: {
          label: '消息模板分类',
          required: true
        },
        compProps: {
          // 字典查询
          dictParam: {groupCode: 'message_template_type'}
        }
      }
    },



    {
      field: {
        name: 'groupFlag',
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '分组标识',

        },
        compProps: {
          clearable: true,
        }
      }
    },


    {
      field: {
        name: 'groupFlagMemo',
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '分组标识备忘',

        },
        compProps: {
          clearable: true,
        }
      }
    },


    {
      field: {
        name: 'tags',
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '标签',

        },
        compProps: {
          clearable: true,
        }
      }
    },

    useCascaderMessageTemplateCompItem({
      required: ({form})=> form.isGroup === false
    }),


    {
      field: {
        name: 'seq',
        value: 1000
      },
      element: {
        comp: 'el-input-number',
        formItemProps: {
          label: '排序',
          required: true
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

        },
        compProps: {
          clearable: true,
        }
      }
    },
  ]
}

// 更新和添加一致
export const useUpdatePageFormItems = useAddPageFormItems

