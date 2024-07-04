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
            label: '名称',
            
          },
          compProps: {
            clearable: true,
            placeholder: '左前缀匹配',
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
            label: '编码',
            required: true,
            tips: '编码唯一，用来唯一标识一个数据对象'
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
            label: '名称',
            required: true,
            tips: '一个可读的文本'
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'customDataUrl',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '自定义数据的url',
            tips: '仅数据范围自定义时使用，数据范围自定义时用来绑定自定义数据的url,如果没有绑定自定义数据的数据范围，不用填写'
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'isCustomDataLazy',
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '自定义数据是否懒加载',
            required: true,
            tips: '仅数据范围自定义时使用，数据范围自定义时用来获取自定义数据的url对应的数据是否为部分数据，如分页数据则为懒加载,如果没有绑定自定义数据的数据范围，设置无效'
          },
          compProps: {
            activeText: '懒加载',
            inactiveText: '一次性数据'
          }
        }
      },


      {
        field: {
          name: 'customDataTypeDictId',
        },
        element: {
          comp: 'PtDictFrontSelect',
          formItemProps: {
            label: '自定义数据交互方式',
            tips: '仅数据范围自定义时使用，数据范围自定义时用来获取自定义数据的url对应的数据展示交互方式，如表格、树形等,如果没有绑定自定义数据的数据范围，设置无效'
          },
          compProps: {
            // 字典查询
            dictParam: {groupCode: 'data_scope_custom_data_type'}
          }
        }
      },

      {
        field: {
          name: 'customDataConfigJson',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '自定义数据配置',
            tips: '仅数据范围自定义时使用，应该为一个json串，用来解析自定义数据结构,如果没有绑定自定义数据的数据范围，设置无效'
          },
          compProps: {
            clearable: true,
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
        label: '是否禁用',
        required: true,
      },
      compProps: {
      }
    }
  },
  {
    field: {
      name: 'disabledReason'
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '禁用原因',
        required: ({form}) => form.isDisabled == true

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

// 更新和添加一致
export const updatePageFormItems = addPageFormItems

