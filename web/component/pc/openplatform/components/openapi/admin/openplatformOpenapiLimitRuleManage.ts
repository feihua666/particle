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
            tips: '一个字符串用于标识限制规则的意义方便区分'
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
        tips: '注意：选择不限制时表示可个限制规则失效，包括限流、ip白名单和黑名单等'
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
            tips: '即qps限制，注意：该限制不受前面（限制周期）的影响。填 0 表示不限制'
          },
          compProps: {
          }
        }
      },

  {
    field: {
      name: 'whiteIps',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: 'ip白名单',
        tips: '多个用英文逗号分隔,注意：该限制不受前面（限制周期）的影响。填空表示不限制'
      },
      compProps: {
        clearable: true,
      }
    }
  },
  {
    field: {
      name: 'blackIps',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: 'ip黑名单',
        tips: '多个用英文逗号分隔,注意：该限制不受前面（限制周期）的影响。填空表示不限制'
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

