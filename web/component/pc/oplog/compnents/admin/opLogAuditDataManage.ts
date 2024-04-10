import {useRemoteSelectOpLogCompItem} from "../opLogCompItem";

export const usePageFormItems = ({props})=>{
  return [
    {
      field: {
        name: 'name',
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '字段名称',

        },
        compProps: {
          clearable: true,
          placeholder: '左前缀匹配，如：昵称',
        }
      }
    },
    {
      field: {
        name: 'propertyName',
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '数据字段',

        },
        compProps: {
          clearable: true,
          placeholder: '左前缀匹配，如：nickname',
        }
      }
    },
    {
      field: {
        name: 'oldValue',
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '旧值',

        },
        compProps: {
          clearable: true,
        }
      }
    },
    {
      field: {
        name: 'newValue',
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '新值',

        },
        compProps: {
          clearable: true,
        }
      }
    },
    {
      field: {
        name: 'changeTypeDictId',
      },
      element: {
        comp: 'PtDictFrontSelect',
        formItemProps: {
          label: '值改变类型字典',

        },
        compProps: {
          // 字典查询
          dictParam: {groupCode: 'op_log_audit_data_change_type'}
        }
      }
    },
    {
      field: {
        name: 'changeType',
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '值改变类型',

        },
        compProps: {
          clearable: true,
          placeholder: '如：PROPERTY_VALUE_CHANGED'
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
          label: '类型对应的字典',

        },
        compProps: {
          // 字典查询
          dictParam: {groupCode: 'op_log_type'}
        }
      }
    },
    {
      field: {
        name: 'type',
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '类型',

        },
        compProps: {
          clearable: true,
          placeholder: '如：create'
        }
      }
    },

    {
      field: {
        name: 'dataId',
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '数据id',

        },
        compProps: {
        }
      }
    },
    {
      field: {
        name: 'dataTable',
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '数据表名',

        },
        compProps: {
          clearable: true,
        }
      }
    },
    {
      field: {
        name: 'dataEntity',
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '数据载体',

        },
        compProps: {
          clearable: true,
        }
      }
    },
    useRemoteSelectOpLogCompItem({props})

  ]
}

