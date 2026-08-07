import {useSelectAgiModelProviderCompItem} from "../../agiCompItem";

export const pageFormItems = [
      {
        field: {
          name: 'code',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '模型编码',

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
            label: '模型显示名称',

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
            label: '模型类型',

          },
          compProps: {
            // 字典查询
            dictParam: {groupCode: 'agi_model_type'}
          }
        }
      },
  useSelectAgiModelProviderCompItem({})
]
export const addPageFormItems = [

      {
        field: {
          name: 'code',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '模型编码',
            required: true,
            tips: '同一个模型提供商下编码唯一，请勿重复，用于参数指定模型如：gpt-4o, qwen-plus, qwen2:7b',
          },
          compProps: {
            clearable: true,
            placeholder: '同一个模型提供商下编码唯一',
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
            label: '模型名称',
            required: true,
            tips: '用于显示',
          },
          compProps: {
            clearable: true,
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
        label: '模型类型',
        required: true,
      },
      compProps: {
        // 字典查询
        dictParam: {groupCode: 'agi_model_type'}
      }
    }
  },


      {
        field: {
          name: 'maxTokens',
        },
        element: {
          comp: 'el-input-number',
          formItemProps: {
            label: '最大 Token 数',
            tips: '模型请求包含的最大 Token 数，不填写为不限制，如果填写，请求时超过会提示',
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'defaultTemperature',
        },
        element: {
          comp: 'el-input-number',
          formItemProps: {
            label: '默认温度值',
            tips: '一般范围是 0.00-2.00,不填写可能将使用厂商的默认设置，越低越确定、保守，倾向于选择概率最高的词，结果可重复性高（适合问答、翻译），越高越随机、多样、有创造性，可能产生意想不到的内容（适合创意写作、头脑风暴）'
          },
          compProps: {
            step: 0.01,
          }
        }
      },

      {
        field: {
          name: 'defaultTopP',
        },
        element: {
          comp: 'el-input-number',
          formItemProps: {
            label: '默认TopP值',
            tips: '一般范围是 0.00-1.00,不填写可能将使用厂商的默认设置，值越低候选词越少，输出更聚焦、合理，值越高候选词越多，包含更多低概率词，增加多样性'
          },
          compProps: {
            step: 0.01,
          }
        }
      },

  {
    field: {
      name: 'isDisabled',
      value: false
    },
    element: {
      comp: 'el-switch',
      formItemProps: {
        label: '是否禁用'
      },
      compProps: {
        activeText: '禁用',
        inactiveText: '启用',
      }
    }
  },


      {
        field: {
          name: 'isDefault',
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否默认',
            required: true,
          },
          compProps: {
            activeText: '是',
            inactiveText: '否',
          }
        }
      },

  useSelectAgiModelProviderCompItem({required: true}),


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
        tips: '描述，可填写一些备忘或说明信息'
      },
      compProps: {
        clearable: true,
      }
    }
  },

  {
    field: {
      name: 'extraConfigJson',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '扩展配置',
        displayBlock: true,
        tips: '必须严格保持Json格式'
      },
      compProps: {
        type: 'textarea',
        clearable: true,
        rows: 5,
      }
    }
  },







]

// 更新和添加一致
export const updatePageFormItems = addPageFormItems

