import {list as datasourceApiListApi} from "../../../api/datasource/admin/dataQueryDatasourceApiAdminApi";
import {
    dictConfigJson,
    inParamDocConfigJson,
    inParamExampleConfigJson,
    inParamExtConfigJson,
    inParamTestCaseDataConfigJson,
    inParamValidateConfigJson,
    outParamDocConfigJson,
    outParamExampleConfigJson,
    outParamExtConfigJson,
    outParamSuccessConfigJson,
    outParamTransConfigJson,
    pageableAdapterConfigJson
} from "../../datasource/admin/dataQueryDatasourceApiManage";
import {ElMessage} from 'element-plus'
import {useSelectDataqueryProviderCompItem} from "../../dataqueryProviderCompItem";

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
        label: '接口名称',

      },
      compProps: {
        clearable: true,
        placeholder: '模糊匹配',
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
      name: 'adaptConfigJson',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '适配逻辑配置',
      },
      compProps: {
        clearable: true,
        placeholder: '模糊匹配',
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
        label: '输出包装类型',
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
  useSelectDataqueryProviderCompItem({}),
  {
    field: {
      name: 'apiIdentifier',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '接口标识',

      },
      compProps: {
        clearable: true,
        placeholder: '精准匹配',
      }
    }
  },
]
export const useAddPageFormItems = ({form,formData,
                                      dataQueryDatasourceApiFormItemConfigsRef,
                                      dataQueryDataApiFormItemConfigsRef,
                                      addPublished = false}) => {
  let publishedItmes = []
  if (addPublished) {
    publishedItmes = [
      {
        field: {
          name: 'isPublished',
          value: false
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否发布',
            tips: '发布后将不能修改和删除'
          },
          compProps: ({form}) => {

            return {
              disabled: !form.isMaster && !form.isPublished,
              disabledReason: !form.isMaster && !form.isPublished ? 'dev不允许发布': undefined,
              activeText: '发布',
              inactiveText: '不发布',
            }
          }
        }
      },
    ]
  }
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
          tips: '请以 / 开头,前端调用地址需要前面拼接 /api/dq。开放接口前面拼接 /openapi/dq,如要修改并提交至master请以 __dev 结尾'
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
          tips: '标识接口的名称,如要修改并提交至master请以 __dev 结尾'
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
          tips: '针对数据源接口的适配，在一对一直连时，部分字段如果为空，将会引用数据源接口，具体以字段说明为准'
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
          label: '接口复杂适配逻辑配置',
          tips: '在一对一直连时该项及后面的字段项无需配置，如果配置会将数据源接口返回结果和入参为基准',
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
          label: '输出包装类型',
          required: ({formData})=>{
            if(!formData.adaptTypeDictId){
              return false
            }
            return formData.adaptTypeDictId?.value !== 'single_direct'
          },
          tips: '对 数据源接口 输出数据的包装类型，【代理响应数据接口】或不填写，表示不包装'
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
          tips: '数据查询入参类型，如果适配类型为 一对一直连，应该和数据源接口配置保持一致或不填写，除非有特殊处理除外'
        },
        compProps: {
          // 字典查询
          dictParam: {groupCode: 'dataquery_datasource_api_param_type'}
        }
      }
    },

    inParamExampleConfigJson(dataQueryDatasourceApiFormItemConfigsRef,{
      tips: '对接口的请求示例，仅做参考，不做为逻辑处理依据，表示接收的参数示例,如果不填写且适配类型为 一对一直连，将使用数据源接口对应配置'
    }),
    inParamTestCaseDataConfigJson(dataQueryDatasourceApiFormItemConfigsRef,{
      tips: '对接口的请求标识数据，为做接口测试时，提供准确的依据,如果不填写且适配类型为 一对一直连，将使用数据源接口对应配置'
    }),
    inParamDocConfigJson(dataQueryDatasourceApiFormItemConfigsRef,{
      tips: '对接口的参数字段说明，表示在基本配置中接口或本地方法接收的参数文档说明,如果不填写且适配类型为 一对一直连，将使用数据源接口对应配置'
    }),
    inParamValidateConfigJson(dataQueryDatasourceApiFormItemConfigsRef),
    inParamExtConfigJson(dataQueryDatasourceApiFormItemConfigsRef),
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
          },
          tips: '对数据源接口返回结果的类型的声明'
        },
        compProps: {
          // 字典查询
          dictParam: {groupCode: 'dataquery_datasource_api_param_type'}
        }
      }
    },

    outParamExampleConfigJson(dataQueryDatasourceApiFormItemConfigsRef,{
      tips: '表示在出参类型的基础上对接口的响应示例，仅做参考，不做为逻辑处理依据,如果不填写且适配类型为 一对一直连，将使用数据源接口对应配置'
    }),

    outParamDocConfigJson(dataQueryDatasourceApiFormItemConfigsRef,{
      tips: '表示在出参类型的基础上对接口的参数字段说明,如果不填写且适配类型为 一对一直连，将使用数据源接口对应配置'
    }),
    outParamSuccessConfigJson(dataQueryDatasourceApiFormItemConfigsRef,{
      tips: '注意数据查询接口配置是在数据源接口返回结果上进行的配置,如果适配类型为 一对一直连，注意不要重复配置，以导致错误'
    }),
    outParamTransConfigJson(dataQueryDatasourceApiFormItemConfigsRef,{
      tips: '针对响应数数据翻译数据字典支持,翻译执行在出参扩展配置之后执行,如果适配类型为 一对一直连，注意不要重复配置，以导致错误'
    }),
    outParamExtConfigJson(dataQueryDatasourceApiFormItemConfigsRef),
    dictConfigJson(dataQueryDatasourceApiFormItemConfigsRef,{
      tips: '接口用到的字典数据配置，可配合文档和翻译等使用,如果不填写且适配类型为 一对一直连，将使用数据源接口对应配置'
    }),
    pageableAdapterConfigJson(dataQueryDatasourceApiFormItemConfigsRef,{
      tips: '用来解析分页请求和响应数据转换，仅适用于分页查询使用，主要用来提取请求的分页请求信息和返回数据的分页响应信息,如果适配类型为 一对一直连，注意不要重复配置，以导致错误'
    }),
    // 发布字段
      ...publishedItmes,
    {
      field: {
        name: 'isUseRemote',
        value: false
      },
      element: {
        comp: 'el-switch',
        formItemProps: {
          label: '是否使用远程服务',
          tips: '远程服务意味着该数据查询使用配置的远程接口返回数据，远程服务数据查询接口必须和本配置保持一致',
          labelTips: '该配置项仅限默认的配置实现，com.particle.dataquery.infrastructure.dataapi.gateway.impl.DefaultDataApiForOpenapiRemoteQueryGatewayImpl'
        },
        compProps: {
          activeText: '使用',
          inactiveText: '不使用',
        }
      }
    },
    useSelectDataqueryProviderCompItem({
      required: false,
      tips: '数据查询供应商,主要用于支持开放平台'
    }),
    {
      field: {
        name: 'apiIdentifier',
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '接口标识',
          tips: '主要用于支持开放平台，需和开放平台接口编码保持一致'

        },
        compProps: {
          clearable: true,
        }
      }
    },
    {
      field: {
        name: 'apiVersion',
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '接口版本标识',
          tips: '主要用于支持开放平台'

        },
        compProps: {
          clearable: true,
        }
      }
    },
    {
      field: {
        name: 'isSupportWarehouse',
      },
      element: {
        comp: 'el-switch',
        formItemProps: {
          label: '是否支持入库',
          tips: '主要用于支持开放平台,用于入库逻辑处理',
        },
        compProps: {
          activeText: '支持',
          inactiveText: '不支持',
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

