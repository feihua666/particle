import {list as dataQueryProviderListApi} from "../../../api/provider/admin/dataQueryProviderAdminApi";

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
  {
    field: {
      name: 'typeDictId',
    },
    element: {
      comp: 'PtDictFrontSelect',
      formItemProps: {
        label: '类型',

      },
      compProps: {
        // 字典查询
        dictParam: {groupCode: 'dataquery_datasource_type'}
      }
    }
  },
  {
    field: {
      name: 'username',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '用户名',

      },
      compProps: {
        clearable: true,
      }
    }
  },
  {
    field: {
      name: 'dataQueryProviderId',
    },
    element: {
      comp: 'PtSelect',
      formItemProps: {
        label: '数据查询供应商',
      },
      compProps: {
        dataMethod: dataQueryProviderListApi
      }
    }
  },
]
export const useAddPageFormItems = ({reactiveData,configJsonDialogVisible}) => {
  return [
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
          label: '类型',
          required: true,
        },
        compProps: {
          // 字典查询
          dictParam: {groupCode: 'dataquery_datasource_type'}
        }
      }
    },


    {
      field: {
        name: 'configJson',
      },
      element: {
        comp: 'PtButton',
        formItemProps: {
          label: 'json配置',
          required: true,
          displayBlock: true
        },
        compProps: ({form})=>{
          return {
            text: true,
            type: form.configJson ? 'primary' : 'default',
            buttonText: '点击配置',
            beforeMethod: ()=>{
              if(!reactiveData.form.typeDictId){
                return '请先选择数据源类型'
              }
              return true
            },
            method: ()=>{
              configJsonDialogVisible.value = true
            }
          }
        }
      }
    },


    {
      field: {
        name: 'username',
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '用户名',

        },
        compProps: {
          clearable: true,
        }
      }
    },


    {
      field: {
        name: 'password',
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '密码',

        },
        compProps: {
          clearable: true,
        }
      }
    },


    {
      field: {
        name: 'dataQueryProviderId',
      },
      element: {
        comp: 'PtSelect',
        formItemProps: {
          label: '数据查询供应商',
          required: true,
        },
        compProps: {
          dataMethod: dataQueryProviderListApi
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
// 代理相关配置
export const proxyFormItems = [
  {
    field: {
      name: 'proxyAddress',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '代理地址',
      },
      compProps: {
        clearable: true,
      }
    }
  },
  {
    field: {
      name: 'proxyPort',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '代理地址端口',
      },
      compProps: {
        clearable: true,
      }
    }
  },
  {
    field: {
      name: 'proxyUsername',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '代理用户',
      },
      compProps: {
        clearable: true,
      }
    }
  },
  {
    field: {
      name: 'proxyPassword',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '代理用户密码',
      },
      compProps: {
        clearable: true,
      }
    }
  },
]

const datasource_jdbc = [
  {
    field: {
      name: 'driverClassName',
      // 设置mysql默认驱动名
      value: 'com.mysql.cj.jdbc.Driver'
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '驱动类名',
        required: true
      },
      compProps: {
        clearable: true,
      }
    }
  },
  {
    field: {
      name: 'url',
      // 设置mysql默认驱动名
      value: 'jdbc:mysql://localhost/particle_dev?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&serverTimezone=GMT%2B8'
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '地址',
        required: true
      },
      compProps: {
        clearable: true,
      }
    }
  },
]
const datasource_http = [
  {
    field: {
      name: 'domainUrl',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '域名地址',
        required: true
      },
      compProps: {
        clearable: true,
        placeholder: '如：http://example.com'
      }
    }
  },
  {
    field: {
      name: 'authScriptType',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '认证脚本类型',
      },
      compProps: {
        clearable: true,
        placeholder: '如：http://example.com'
      }
    }
  },
  {
    field: {
      name: 'authScriptTemplate',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '认证脚本',
      },
      compProps: {
        type: 'textarea',
        rows: '10'
      }
    }
  },
]
const datasource_not_support = [
  {
    field: {
      name: 'configJson',
      value: '{}'
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '配置Json',
        required: true
      },
      compProps: {
        type: 'textarea',
        rows: 10,
        clearable: true,
        placeholder: '一般为json严格格式'
      }
    }
  },
]

export const datasourceTypeFormItems = {
  datasource_jdbc,
  datasource_http,
  datasource_not_support,
}