import {list as dataQueryDatasourceListApi} from "../../../api/datasource/admin/dataQueryDatasourceAdminApi";
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
// 参数类型,与后端字典组编码 dataquery_datasource_api_param_type 下的字典项一致
export const paramType = {
  object: 'object',
  array: 'array',
  string: 'string',
  number: 'number',
  float: 'float',
  boolean: 'boolean',
}
/**
 * 根据不同的参数类型转换对象
 */
export const inParamTypeHandler = {
  [paramType.object]: (rawParam) => JSON.parse(rawParam),
  [paramType.array]: (rawParam) => JSON.parse(rawParam),
  [paramType.number]: (rawParam) => parseInt(rawParam),
  [paramType.float]: (rawParam) => parseFloat(rawParam),
  [paramType.boolean]: (rawParam) => JSON.parse(rawParam),
}
export const inParamExampleConfigJson = (dataQueryDatasourceApiFormItemConfigsRef,{tips=''} = {})=>{
  return     {
    field: {
      name: 'inParamExampleConfigJson',
    },
    element: {
      comp: 'PtButton',
      formItemProps: {
        label: '入参示例',
        tips: tips || '对接口的请求示例，仅做参考，不做为逻辑处理依据，表示接收的参数示例'
      },
      compProps: ({form})=>{
        return {
          text: true,
          type: form.inParamExampleConfigJson ? 'primary' : 'default',
          buttonText: '点击配置',
          method: ()=>{
            if(dataQueryDatasourceApiFormItemConfigsRef.value){
              dataQueryDatasourceApiFormItemConfigsRef.value.reactiveData.inParamExampleConfigJson.dialogVisible = true
            }
          }
        }
      }
    }
  }
}
export const inParamTestCaseDataConfigJson = (dataQueryDatasourceApiFormItemConfigsRef,{tips=''} = {})=>{
  return     {
    field: {
      name: 'inParamTestCaseDataConfigJson',
    },
    element: {
      comp: 'PtButton',
      formItemProps: {
        label: '入参测试用例数据',
        tips: tips || '对接口的请求标识数据，为做接口测试时，提供准确的依据'
      },
      compProps: ({form,formData})=>{
        return {
          text: true,
          type: form.inParamTestCaseDataConfigJson ? 'primary' : 'default',
          buttonText: '点击配置',
          method: ()=>{
            if(dataQueryDatasourceApiFormItemConfigsRef.value){
              dataQueryDatasourceApiFormItemConfigsRef.value.reactiveData.inParamTestCaseDataConfigJson.dialogVisible = true
            }
          }
        }
      }
    }
  }
}
export const inParamDocConfigJson = (dataQueryDatasourceApiFormItemConfigsRef,{tips=''} = {})=>{
  return {
    field: {
      name: 'inParamDocConfigJson',
    },
    element: {
      comp: 'PtButton',
      formItemProps: {
        label: '入参文档配置',
        tips: tips || '对接口的参数字段说明，表示在基本配置中接口或本地方法接收的参数文档说明'
      },
      compProps: ({form,formData})=>{
        return {
          text: true,
          type: form.inParamDocConfigJson ? 'primary' : 'default',
          buttonText: '点击配置',
          beforeMethod: ()=>{
            if(!form.inParamTypeDictId){
              return '请先选择入参类型'
            }
            return true
          },
          method: ()=>{
            if(dataQueryDatasourceApiFormItemConfigsRef.value){
              dataQueryDatasourceApiFormItemConfigsRef.value.reactiveData.inParamDoc.dialogVisible = true
            }
          }
        }
      }
    }
  }
}
export const outParamExampleConfigJson = (dataQueryDatasourceApiFormItemConfigsRef,{tips=''} = {})=>{
  return     {
    field: {
      name: 'outParamExampleConfigJson',
    },
    element: {
      comp: 'PtButton',
      formItemProps: {
        label: '出参示例',
        tips: tips || '表示在出参类型的基础上对接口的响应示例，仅做参考，不做为逻辑处理依据'
      },
      compProps:  ({form})=>{
        return {
          text: true,
          type: form.outParamExampleConfigJson ? 'primary' : 'default',
          buttonText: '点击配置',
          method: ()=>{
            if(dataQueryDatasourceApiFormItemConfigsRef.value){
              dataQueryDatasourceApiFormItemConfigsRef.value.reactiveData.outParamExampleConfigJson.dialogVisible = true
            }
          }
        }
      }
    }
  }
}
export const outParamDocConfigJson = (dataQueryDatasourceApiFormItemConfigsRef,{tips=''} = {})=>{
  return {
    field: {
      name: 'outParamDocConfigJson',
    },
    element: {
      comp: 'PtButton',
      formItemProps: {
        label: '出参文档配置',
        tips: tips || '表示在出参类型的基础上对接口的参数字段说明'
      },
      compProps: ({form,formData})=>{
        return {
          text: true,
          type: form.outParamDocConfigJson ? 'primary' : 'default',
          buttonText: '点击配置',
          beforeMethod: ()=>{
            if(!form.outParamTypeDictId){
              return '请先选择出参类型'
            }
            return true
          },
          method: ()=>{
            if(dataQueryDatasourceApiFormItemConfigsRef.value){
              dataQueryDatasourceApiFormItemConfigsRef.value.reactiveData.outParamDoc.dialogVisible = true
            }
          }
        }
      }
    }
  }
}
export const inParamValidateConfigJson = (dataQueryDatasourceApiFormItemConfigsRef,{tips=''} = {})=>{
  return     {
    field: {
      name: 'inParamValidateConfigJson',
    },
    element: {
      comp: 'PtButton',
      formItemProps: {
        label: '入参校验配置',
        tips: tips || '入参的校验规则，逻辑处理,支持多个配置，有一个校验失败表示失败，不配置代表不校验，表示对参数的校验'
      },
      compProps: ({form,formData})=>{
        return {
          text: true,
          type: form.inParamValidateConfigJson ? 'primary' : 'default',
          buttonText: '点击配置',
          method: ()=>{
            if(dataQueryDatasourceApiFormItemConfigsRef.value){
              dataQueryDatasourceApiFormItemConfigsRef.value.reactiveData.inParamValidate.dialogVisible = true
            }
          }
        }
      }
    }
  }
}
export const inParamExtConfigJson = (dataQueryDatasourceApiFormItemConfigsRef)=>{
  return     {
    field: {
      name: 'inParamExtConfigJson',
    },
    element: {
      comp: 'PtButton',
      formItemProps: {
        label: '入参扩展配置',
        tips: '扩展配置支持，针对不同的接口可能有更个性的配置，主要用来处理请求参数'
      },
      compProps: ({form,formData})=>{
        return {
          text: true,
          type: form.inParamExtConfigJson ? 'primary' : 'default',
          buttonText: '点击配置',
          method: ()=>{
            if(dataQueryDatasourceApiFormItemConfigsRef.value){
              dataQueryDatasourceApiFormItemConfigsRef.value.reactiveData.inParamExtConfigJson.dialogVisible = true
            }
          }
        }
      }
    }
  }
}
export const outParamSuccessConfigJson = (dataQueryDatasourceApiFormItemConfigsRef,{tips=''} = {})=>{
  return {
    field: {
      name: 'outParamSuccessConfigJson',
    },
    element: {
      comp: 'PtButton',
      formItemProps: {
        label: '出参结果成功或失败配置',
        tips: tips || '表示在出参类型的基础上对该出参做一个成功或失败的判断，以处理告警或其它需要的逻辑，不配置代表都成功'
      },
      compProps: ({form})=>{
        return {
          text: true,
          type: form.outParamSuccessConfigJson ? 'primary' : 'default',
          buttonText: '点击配置',
          method: ()=>{
            if(dataQueryDatasourceApiFormItemConfigsRef.value){
              dataQueryDatasourceApiFormItemConfigsRef.value.reactiveData.outParamSuccess.dialogVisible = true
            }
          }
        }
      }
    }
  }
}
export const outParamExtConfigJson = (dataQueryDatasourceApiFormItemConfigsRef)=>{
  return     {
    field: {
      name: 'outParamExtConfigJson',
    },
    element: {
      comp: 'PtButton',
      formItemProps: {
        label: '出参扩展配置',
        tips: '扩展配置支持，针对不同的接口可能有更个性的配置，主要用来处理请求参数'
      },
      compProps: ({form,formData})=>{
        return {
          text: true,
          type: form.outParamExtConfigJson ? 'primary' : 'default',
          buttonText: '点击配置',
          method: ()=>{
            if(dataQueryDatasourceApiFormItemConfigsRef.value){
              dataQueryDatasourceApiFormItemConfigsRef.value.reactiveData.outParamExtConfigJson.dialogVisible = true
            }
          }
        }
      }
    }
  }
}
export const outParamTransConfigJson = (dataQueryDatasourceApiFormItemConfigsRef,{tips=''} = {})=>{
  return {
    field: {
      name: 'outParamTransConfigJson',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '出参翻译配置',
        tips: tips || '针对响应数数据翻译数据字典支持'
      },
      compProps: {
        clearable: true,
      }
    }
  }
}
export const dictConfigJson = (dataQueryDatasourceApiFormItemConfigsRef,{tips=''} = {})=>{
  return     {
    field: {
      name: 'dictConfigJson',
    },
    element: {
      comp: 'PtButton',
      formItemProps: {
        label: '字典配置',
        tips: tips || '接口用到的字典数据配置，可配合文档使用'
      },
      compProps:  ({form})=>{
        return {
          text: true,
          type: form.dictConfigJson ? 'primary' : 'default',
          buttonText: '点击配置',
          method: ()=>{
            if(dataQueryDatasourceApiFormItemConfigsRef.value){
              dataQueryDatasourceApiFormItemConfigsRef.value.reactiveData.dictConfig.dialogVisible = true
            }
          }
        }
      }
    }
  }
}
export const pageableAdapterConfigJson = (dataQueryDatasourceApiFormItemConfigsRef,{tips=''} = {})=>{
  return     {
    field: {
      name: 'pageableAdapterConfigJson',
    },
    element: {
      comp: 'PtButton',
      formItemProps: {
        label: '分页信息配置',
        tips: tips || '用来解析分页请求和响应数据转换，仅适用于分页查询使用，主要用来提取请求的分页请求信息和返回数据的分页响应信息'
      },
      compProps:  ({form})=>{
        return {
          text: true,
          type: form.pageableAdapterConfigJson ? 'primary' : 'default',
          buttonText: '点击配置',
          method: ()=>{
            if(dataQueryDatasourceApiFormItemConfigsRef.value){
              dataQueryDatasourceApiFormItemConfigsRef.value.reactiveData.pageableAdapterConfig.dialogVisible = true
            }
          }
        }
      }
    }
  }
}
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
        placeholder: '左前缀匹配'
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
        placeholder: '左前缀匹配'
      }
    }
  },
  {
    field: {
      name: 'categoryDictId',
    },
    element: {
      comp: 'PtDictFrontSelect',
      formItemProps: {
        label: '分类',
      },
      compProps: {
        // 字典查询
        dictParam: {groupCode: 'dataquery_datasource_api_category'}
      }
    }
  },
  useSelectDataqueryProviderCompItem({}),
  {
    field: {
      name: 'dataQueryDatasourceId',
    },
    element: {
      comp: 'PtSelect',
      formItemProps: {
        label: '数据查询数据源',
      },
      compProps: {
        dataMethod: dataQueryDatasourceListApi
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
      name: 'sameTag',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '等同标签',

      },
      compProps: {
        clearable: true,
      }
    }
  },
]
export const useAddPageFormItems = ({form,formData,dataQueryDatasourceApiFormItemConfigsRef,dataQueryDatasourceApiFormItemBasicConfigsRef,addSingleDirectElements = false})=>{

  let temp = []
  if(addSingleDirectElements){
    temp = [
      {
        field: {
          name: 'isAddSingleDirect',
          value: false
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '添加一对一直连数据查询接口'
          },
          compProps: {
            activeText: '添加',
            inactiveText: '不添加',
          }
        }
      },
      {
        field: {
          name: 'dataQueryDataApiUrl',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '数据查询接口地址',
            required: ({form}) => form.isAddSingleDirect,
            tips: '请以 / 开头,前端调用地址需要前面拼接 /api/dq。开放接口前面拼接 /openapi/dq'
          },
          compProps: {
            clearable: true,
            placeholder: '接口地址，唯一'
          }
        }
      },
    ]
  }

  return [
    {
      field: {
        name: 'code',
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '编码',
          tips: '为接口定义一个编码，建议符合变量命名规划'
        },
        compProps: {
          placeholder: '编码，唯一',
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
          tips: '接口名称，任意输入，代表一个可人眼识别的字符串'
        },
        compProps: {
          clearable: true,
        }
      }
    },
    {
      field: {
        name: 'categoryDictId',
      },
      element: {
        comp: 'PtDictFrontSelect',
        formItemProps: {
          label: '分类',
          required: true,
          tips: '仅做分类使用，方便归类查询'
        },
        compProps: {
          // 字典查询
          dictParam: {groupCode: 'dataquery_datasource_api_category'}
        }
      }
    },
    useSelectDataqueryProviderCompItem({
      required: true,
      tips: '数据查询供应商和数据源没有做强关联，请保持和数据源对应的供应商一致'
    }),

    {
      field: {
        name: 'dataQueryDatasourceId',
      },
      element: {
        comp: 'PtSelect',
        formItemProps: {
          label: '数据查询数据源',
          required: true,
          tips: '数据查询供应商和数据源没有做强关联，请保持和数据查询供应商对应的供应商一致'
        },
        compProps: {
          dataMethod: dataQueryDatasourceListApi
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
          tips: '重要，标识接口数据的输出类型和处理逻辑，表示最终输出的结果数据结构类型，是在出参类型的基础上的二次包装，其中【代理响应数据接口】表示不包装',
          required: true,
        },
        compProps: ({form,formData})=>{
          let disabled = !form.dataQueryDatasourceId
          return {
            disabled: disabled,
            disabledReason: '请先选择数据源',
            title: '请先选择数据源',
            // 字典查询
            dictParam: {groupCode: 'dataquery_datasource_api_type'},
          }
        }
      }
    },
    {
      field: {
        name: 'dataQueryProviderDocLinkUrl',
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '文档链接',
          displayBlock: true,
          tips: '如果对接是第三方，可能会提供参考地址，须以http开头的绝对路径'
        },
        compProps: {
          placeholder: '如：http(s)://xxx.com/xxx/api',
          clearable: true,
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
          tips: '不选择则表示无入参,须和基础配置中的使用保持一致，该类型可以向上追溯到接口测试、数据查询入参保持一致'
        },
        compProps: {
          // 字典查询
          dictParam: {groupCode: 'dataquery_datasource_api_param_type'}
        }
      }
    },
    inParamExampleConfigJson(dataQueryDatasourceApiFormItemConfigsRef),
    inParamTestCaseDataConfigJson(dataQueryDatasourceApiFormItemConfigsRef),
    inParamDocConfigJson(dataQueryDatasourceApiFormItemConfigsRef),
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
          required: true,
          tips: "表示在调用基础配置接口或本地方法后返回的结果的理想类型，该值仅是一个参考值，最终返回类型是结合基础配置接口和本类型综合适配得出"
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
    outParamTransConfigJson(dataQueryDatasourceApiFormItemConfigsRef),
    outParamExtConfigJson(dataQueryDatasourceApiFormItemConfigsRef),
    {
      field: {
        name: 'configJson',
      },
      element: {
        comp: 'PtButton',
        formItemProps: {
          label: '基础配置',
          tips: '具体的执行内容配置',
          required: true
        },
        compProps: ({form})=>{
          return {
            text: true,
            type: form.configJson ? 'primary' : 'default',
            buttonText: '点击配置',
            beforeMethod: ()=>{
              if(!form.dataQueryDatasourceId){
                return '请先选择数据源'
              }
              return true
            },
            method: ()=>{
              let map = {
                datasource_jdbc(){
                  if(dataQueryDatasourceApiFormItemBasicConfigsRef.value){
                    dataQueryDatasourceApiFormItemBasicConfigsRef.value.reactiveData.jdbc.dialogVisible = true
                  }
                },
                datasource_http(){
                  if(dataQueryDatasourceApiFormItemBasicConfigsRef.value){
                    dataQueryDatasourceApiFormItemBasicConfigsRef.value.reactiveData.http.dialogVisible = true
                  }
                },
                datasource_neo4j(){
                  if(dataQueryDatasourceApiFormItemBasicConfigsRef.value){
                    dataQueryDatasourceApiFormItemBasicConfigsRef.value.reactiveData.neo4j.dialogVisible = true
                  }
                },
                datasource_es(){
                  if(dataQueryDatasourceApiFormItemBasicConfigsRef.value){
                    dataQueryDatasourceApiFormItemBasicConfigsRef.value.reactiveData.es.dialogVisible = true
                  }
                },
              }// end map
              let datasourceTypeDictValue = formData.dataQueryDatasourceId.typeDictValue
              let method = map[datasourceTypeDictValue] || (()=>{
                let datasourceName = formData.dataQueryDatasourceId.name
                let datasourceTypeDictName = formData.dataQueryDatasourceId.typeDictName
                alert(`选择的数据源 ${datasourceName} 类型为 ${datasourceTypeDictName} 暂不支持配置`,'error')
              })
              method()
            }
          }
        }
      }
    },
    dictConfigJson(dataQueryDatasourceApiFormItemConfigsRef),
    pageableAdapterConfigJson(dataQueryDatasourceApiFormItemConfigsRef),
    {
      field: {
        name: 'rateLimitControlConfigJson',
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '流量控制配置',
          tips: '对三方接口可能有调用频次限制时进行配置，一般为QPS'
        },
        compProps: {
          clearable: true,
        }
      }
    },
    {
      field: {
        name: 'circuitBreakerControlConfigJson',
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '熔断控制配置',
          tips: '可对调用的接口在不稳定时进行熔断处理规则配置'
        },
        compProps: {
          clearable: true,
        }
      }
    },
    {
      field: {
        name: 'connectTimeout',
        value: 1000
      },
      element: {
        comp: 'el-input-number',
        formItemProps: {
          label: '连接等待时间',
          tips: '单位：ms。0为不限制，在存在有连接参数的调用时可用如：http调用'
        },
        compProps: {
        }
      }
    },
    {
      field: {
        name: 'readTimeout',
        value: 6000
      },
      element: {
        comp: 'el-input-number',
        formItemProps: {
          label: '读取等待时间',
          tips: '单位：ms。0为不限制，在接口调用时超过该值将丢弃'
        },
        compProps: {
        }
      }
    },
    {
      field: {
        name: 'isUseCache',
        value: false
      },
      element: {
        comp: 'el-switch',
        formItemProps: {
          label: '是否使用缓存',
          tips: '缓存时间不支持自定义',
          labelTips: '参考实现：com.particle.global.cache.CacheHelper'
        },
        compProps: {
          activeText: '使用缓存',
          inactiveText: '不使用缓存',
        }
      }
    },
    {
      field: {
        name: 'sameTag',
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '等同标签',
          tips: '一个字符串，用来标识相同入参出参的接口'
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
      ...temp
  ]
}

// 更新和添加一致
export const useUpdatePageFormItems = useAddPageFormItems

