import {list as datasourceApiListApi} from "../../../api/datasource/admin/dataQueryDatasourceApiAdminApi";
import {
  dictConfigJson,
  inParamDocConfigJson,
  inParamExampleConfigJson,
  inParamTestCaseDataConfigJson,
  outParamDocConfigJson,
  outParamExampleConfigJson,
  outParamSuccessConfigJson,
  pageableAdapterConfigJson
} from "../../datasource/admin/dataQueryDatasourceApiManage";


export const pageFormItems = [
  {
    field: {
      name: 'url',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '接口地址'
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
        label: '接口名称',

      },
      compProps: {
        clearable: true,
        placeholder: '左前缀匹配',
      }
    }
  },
  {
    field: {
      name: 'dataQueryDatasourceApiId',
    },
    element: {
      comp: 'PtSelect',
      formItemProps: {
        label: '数据查询数据源接口',
      },
      compProps: {
        dataMethod: datasourceApiListApi
      }
    }
  },
  {
    field: {
      name: 'adaptTypeDictId',
    },
    element: {
      comp: 'PtDictFrontSelect',
      formItemProps: {
        label: '适配类型',

      },
      compProps: {
        // 字典查询
        dictParam: {groupCode: 'dataquery_data_api_adapt_type'}
      }
    }
  },
  {
    field: {
      name: 'inParamTypeDictId',
    },
    element: {
      comp: 'PtDictFrontSelect',
      formItemProps: {
        label: '入参类型',

      },
      compProps: {
        // 字典查询
        dictParam: {groupCode: 'dataquery_datasource_api_param_type'}
      }
    }
  },
  {
    field: {
      name: 'outParamTypeDictId',
    },
    element: {
      comp: 'PtDictFrontSelect',
      formItemProps: {
        label: '出参类型',

      },
      compProps: {
        // 字典查询
        dictParam: {groupCode: 'dataquery_datasource_api_param_type'}
      }
    }
  },
  {
    field: {
      name: 'responseTypeDictId',
    },
    element: {
      comp: 'PtDictFrontSelect',
      formItemProps: {
        label: '输出类型',

      },
      compProps: {
        // 字典查询
        dictParam: {groupCode: 'dataquery_datasource_api_type'}
      }
    }
  },
]
export const useAddPageFormItems = ({form,formData,dataQueryDataApiFormItemConfigsRef}) => {
  return [
    {
      field: {
        name: 'url',
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '接口地址',
          required: true,
          tips: '请以 / 开头'
        },
        compProps: {
          clearable: true,
          placeholder: '接口地址，唯一'
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
          label: '接口名称',
          required: true,
        },
        compProps: {
          clearable: true,
        }
      }
    },


    {
      field: {
        name: 'adaptTypeDictId',
      },
      element: {
        comp: 'PtDictFrontSelect',
        formItemProps: {
          label: '适配类型',
          required: true
        },
        compProps: {
          // 字典查询
          dictParam: {groupCode: 'dataquery_data_api_adapt_type'}
        }
      }
    },
    {
      field: {
        name: 'dataQueryDatasourceApiId',
      },
      element: {
        comp: 'PtSelect',
        formItemProps: {
          label: '数据查询数据源接口',
          tips: '适配类型为一对一直连时必填',
          required: ({formData})=>{
            if(!formData.adaptTypeDictId){
              return false
            }
            return formData.adaptTypeDictId?.value == 'single_direct'
          }
        },
        compProps: {
          dataMethod: datasourceApiListApi
        }
      }
    },



    {
      field: {
        name: 'adaptConfigJson',
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '接口适配',
          required: ({formData})=>{
            if(!formData.adaptTypeDictId){
              return false
            }
            return formData.adaptTypeDictId?.value !== 'single_direct'
          }
        },
        compProps: {
          clearable: true,
        }
      }
    },

    {
      field: {
        name: 'responseTypeDictId',
      },
      element: {
        comp: 'PtDictFrontSelect',
        formItemProps: {
          label: '输出类型',
          required: ({formData})=>{
            if(!formData.adaptTypeDictId){
              return false
            }
            return formData.adaptTypeDictId?.value !== 'single_direct'
          }
        },
        compProps: {
          // 字典查询
          dictParam: {groupCode: 'dataquery_datasource_api_type'}
        }
      }
    },
    {
      field: {
        name: 'inParamTypeDictId',
      },
      element: {
        comp: 'PtDictFrontSelect',
        formItemProps: {
          label: '入参类型',
          required: ({formData})=>{
            if(!formData.adaptTypeDictId){
              return false
            }
            return formData.adaptTypeDictId?.value !== 'single_direct'
          }
        },
        compProps: {
          // 字典查询
          dictParam: {groupCode: 'dataquery_datasource_api_param_type'}
        }
      }
    },

    inParamExampleConfigJson(dataQueryDataApiFormItemConfigsRef),

    inParamDocConfigJson(dataQueryDataApiFormItemConfigsRef),

    inParamTestCaseDataConfigJson(dataQueryDataApiFormItemConfigsRef),


    {
      field: {
        name: 'outParamTypeDictId',
      },
      element: {
        comp: 'PtDictFrontSelect',
        formItemProps: {
          label: '出参类型',
          required: ({formData})=>{
            if(!formData.adaptTypeDictId){
              return false
            }
            return formData.adaptTypeDictId?.value !== 'single_direct'
          }
        },
        compProps: {
          // 字典查询
          dictParam: {groupCode: 'dataquery_datasource_api_param_type'}
        }
      }
    },

    outParamExampleConfigJson(dataQueryDataApiFormItemConfigsRef),

    outParamDocConfigJson(dataQueryDataApiFormItemConfigsRef),

    outParamSuccessConfigJson(dataQueryDataApiFormItemConfigsRef),
    pageableAdapterConfigJson(dataQueryDataApiFormItemConfigsRef),
    dictConfigJson(dataQueryDataApiFormItemConfigsRef),

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

