import {useSelectDataqueryProviderCompItem} from "../../dataqueryProviderCompItem";

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
        placeholder: '模糊匹配',
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
        placeholder: '模糊匹配',
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
      name: 'configJson',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: 'json配置',

      },
      compProps: {
        clearable: true,
        placeholder: '模糊匹配',
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
        placeholder: '模糊匹配',
      }
    }
  },
  useSelectDataqueryProviderCompItem({}),
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


    useSelectDataqueryProviderCompItem({required: true}),


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
      comp: 'PtDictFrontSelect',
      formItemProps: {
        label: '认证脚本类型',
      },
      compProps: {
        // 字典查询
        dictParam: {groupCode: 'dataquery_datasource_http_auth_script_type'},
        // 选取value为选重值
        props: {value: 'value'},
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
        tips: "可用变量句柄 data(请求数据，一般为一个map根据接口定义类型不同而不同)、dataJsonStr（data对应的jsonStr，一般用来做签名等）、headers（是一个map可以设置值为请求头）、username（数据源配置的用户）、password（数据源配置的密码）<br/>" +
            "enjoy示例：#(headers.put('sign','xxxx')) 可以直接设置请求头。<br/>" +
            "groovy设置请求头示例：headers.put('sign','xxxx') 可以直接设置请求头。<br/>" +
            "groovy返回请求头示例：def map = new HashMap();map.put('sign','xxxx');map; 可以直接返回Map<String,String>对象作为请求头。"
      },
      compProps: {
        type: 'textarea',
        rows: '10'
      }
    }
  },
]
const datasource_neo4j = [
  {
    field: {
      name: 'uri'
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '连接地址',
        required: true
      },
      compProps: {
        clearable: true,
        placeholder: '如：bolt://localhost:7687'
      }
    }
  },
]
const datasource_es = [
  {
    field: {
      name: 'uris'
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '连接地址',
        required: true,
        tips: '支持集群设置，多个以逗号分隔'
      },
      compProps: {
        clearable: true,
        placeholder: '如：http://localhost:9200'
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
  datasource_neo4j,
  datasource_es,
  datasource_not_support,
}
