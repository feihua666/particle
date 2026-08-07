export const pageFormItems = [
      {
        field: {
          name: 'code',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '提供商编码',

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
            label: '提供商显示名称',

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
            label: '提供商类型',

          },
          compProps: {
            // 字典查询
            dictParam: {groupCode: 'agi_model_provider_type'}
          }
        }
      },
]
export const addPageFormItems = [

      {
        field: {
          name: 'code',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '提供商编码',
            required: true,
            tips: '编码唯一，请勿重复',
          },
          compProps: {
            clearable: true,
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
            label: '提供商名称',
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
        label: '提供商类型',
        required: true,
      },
      compProps: {
        // 字典查询
        dictParam: {groupCode: 'agi_model_provider_type'}
      }
    }
  },

      {
        field: {
          name: 'baseUrl',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '基础地址',
            tips: '如：https://api.openai.com/v1/',

          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'apiKeyEncrypted',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: 'API Key',
          },
          compProps: {
            clearable: true,
          }
        }
      },

  {
    field: {
      name: 'isDisabled',
      value: false,
    },
    element: {
      comp: 'el-switch',
      formItemProps: {
        label: '是否禁用',
        required: true,
      },
      compProps: {
        activeText: '禁用',
        inactiveText: '启用',
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

