export const pageFormItems = [
      {
        field: {
          name: 'name',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '限制名称',
          },
          compProps: {
            clearable: true,
            placeholder: '左前缀匹配'
          }
        }
      },
      {
        field: {
          name: 'limitTypeDictId',
        },
        element: {
          comp: 'PtDictFrontSelect',
          formItemProps: {
            label: '限制方式',
            
          },
          compProps: {
            // 字典查询
            dictParam: {groupCode: 'open_platform_limit_rule_type'}
          }
        }
      },

      {
        field: {
          name: 'limitPeriodDictId',
        },
        element: {
          comp: 'PtDictFrontSelect',
          formItemProps: {
            label: '限制周期',
            
          },
          compProps: {
            // 字典查询
            dictParam: {groupCode: 'open_platform_limit_rule_period'}
          }
        }
      },
]
export const addPageFormItems = [




      {
        field: {
          name: 'name',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '限制名称',
            required: true,
          },
          compProps: {
            clearable: true,
          }
        }
      },


  {
    field: {
      name: 'limitTypeDictId',
    },
    element: {
      comp: 'PtDictFrontSelect',
      formItemProps: {
        label: '限制方式',
        required: true,

      },
      compProps: {
        // 字典查询
        dictParam: {groupCode: 'open_platform_limit_rule_type'}
      }
    }
  },


      {
        field: {
          name: 'limitCount',
          value: 0,
        },
        element: {
          comp: 'el-input-number',
          formItemProps: {
            label: '限制条数',
            required: true,
            tips: '仅限 限制方式 选择 按调用次数限制、按调用计费次数限制 时有效。注意：填 0 表示不限制'
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'limitFee',
          value: 0,
        },
        element: {
          comp: 'el-input-number',
          formItemProps: {
            label: '限制金额费用（分）',
            required: true,
            tips: '仅限 限制方式 选择 按费用限制 时有效。注意：填 0 表示不限制'
          },
          compProps: {
          }
        }
      },


  {
    field: {
      name: 'limitPeriodDictId',
    },
    element: {
      comp: 'PtDictFrontSelect',
      formItemProps: {
        label: '限制周期',
        required: true,
        tips: '在指定的周期下进行限制'
      },
      compProps: {
        // 字典查询
        dictParam: {groupCode: 'open_platform_limit_rule_period'}
      }
    }
  },


      {
        field: {
          name: 'limitRate',
          value: 0,
        },
        element: {
          comp: 'el-input-number',
          formItemProps: {
            label: '限制速率',
            required: true,
            tips: '即qps限制，注意：该限制不受前面设置（限制方式、限制周期）的影响。填 0 表示不限制'
          },
          compProps: {
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

