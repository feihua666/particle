import {list as datasourceApiListApi} from "../../../api/datasource/admin/dataQueryDatasourceApiAdminApi";
import {
  dictConfigJson,
  inParamDocConfigJson,
  inParamExampleConfigJson,
  inParamTestCaseDataConfigJson, inParamValidateConfigJson,
  outParamDocConfigJson,
  outParamExampleConfigJson,
  outParamSuccessConfigJson,
  pageableAdapterConfigJson
} from "../../datasource/admin/dataQueryDatasourceApiManage";
import {ElMessage} from 'element-plus'

const alert = (message,type='success')=>{
  ElMessage({
    showClose: true,
    message: message,
    type: type,
    showIcon: true,
    grouping: true
  })
}

export const pageFormItems = [
  {
    field: {
      name: 'url',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '接口地址',
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
export const useAddPageFormItems = ({form,formData,dataQueryDatasourceApiFormItemConfigsRef,dataQueryDataApiFormItemConfigsRef}) => {

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
          tips: '请以 / 开头,前端调用地址需要前面拼接 /api/dq。开放接口前面拼接 /openapi/dq'
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
          tips: '标识接口的名称'
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
          required: true,
          tips: '注：选择一对一直连，接口适配及其后面字段将忽略（将使用数据查询数据源接口直连接口配置），请勿填写'
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
        comp: 'PtButton',
        formItemProps: {
          label: '接口适配',
          tips: '在一对一直连时无需配置',
          required: ({formData})=>{
            if(!formData.adaptTypeDictId){
              return false
            }
            return formData.adaptTypeDictId?.value !== 'single_direct'
          }
        },
        compProps: ({form,formData})=>{
          return {
            text: true,
            type: form.adaptConfigJson ? 'primary' : 'default',
            buttonText: '点击配置',
            beforeMethod: ()=>{
              if(!form.adaptTypeDictId){
                return '请先选择适配类型'
              }
              if (formData.adaptTypeDictId.value == 'single_direct') {
                return '一对一直连不支持该配置'
              }
              return true
            },
            method: ()=>{
              if(dataQueryDataApiFormItemConfigsRef.value){
                let dialogShow = false
                let map = {
                  multiple_aggregation: ()=>{
                    dialogShow = true
                    dataQueryDataApiFormItemConfigsRef.value.reactiveData.adaptMultipleAggregationConfigJson.dialogVisible = true
                  },
                  custom_script: ()=>{
                    dialogShow = true
                    dataQueryDataApiFormItemConfigsRef.value.reactiveData.adaptCustomScriptConfig.dialogVisible = true
                  }
                }
                map[formData.adaptTypeDictId.value]()
                if (!dialogShow) {
                  alert('暂不支持配置的适配类型 ' + formData.adaptTypeDictId.name)
                }
              }
            }
          }
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
          },
          tips: '对输出数据的包装类型，【代理响应数据接口】表示不包装'
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
          },
          tips: '不填写表示无入参，该类型直接关系到接口调用的传参类型'
        },
        compProps: {
          // 字典查询
          dictParam: {groupCode: 'dataquery_datasource_api_param_type'}
        }
      }
    },

    inParamExampleConfigJson(dataQueryDatasourceApiFormItemConfigsRef),

    inParamDocConfigJson(dataQueryDatasourceApiFormItemConfigsRef),
    inParamValidateConfigJson(dataQueryDatasourceApiFormItemConfigsRef),
    inParamTestCaseDataConfigJson(dataQueryDatasourceApiFormItemConfigsRef),

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

    outParamExampleConfigJson(dataQueryDatasourceApiFormItemConfigsRef),

    outParamDocConfigJson(dataQueryDatasourceApiFormItemConfigsRef),

    outParamSuccessConfigJson(dataQueryDatasourceApiFormItemConfigsRef),
    pageableAdapterConfigJson(dataQueryDatasourceApiFormItemConfigsRef),
    dictConfigJson(dataQueryDatasourceApiFormItemConfigsRef),
    {
      field: {
        name: 'isUseRemote',
        value: false
      },
      element: {
        comp: 'el-switch',
        formItemProps: {
          label: '是否使用远程服务',
          tips: '远程服务意味着该数据查询使用配置的远程接口返回数据',
          labelTips: '该配置项仅限默认的配置实现，com.particle.dataquery.infrastructure.dataapi.gateway.impl.DefaultDataApiForOpenapiRemoteQueryGatewayImpl'
        },
        compProps: {
          activeText: '使用',
          inactiveText: '不使用',
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

