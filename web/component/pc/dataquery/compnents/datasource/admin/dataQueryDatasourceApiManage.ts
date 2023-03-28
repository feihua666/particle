import {list as dataQueryDatasourceListApi} from "../../../api/datasource/admin/dataQueryDatasourceAdminApi";
import {list as dataQueryProviderListApi} from "../../../api/provider/admin/dataQueryProviderAdminApi";
import {clone} from "../../../../../../global/common/tools/ObjectTools";
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
export const inParamExampleConfigJson = (dataQueryDatasourceApiFormItemConfigsRef)=>{
  return     {
    field: {
      name: 'inParamExampleConfigJson',
    },
    element: {
      comp: 'PtButton',
      formItemProps: {
        label: '入参示例',
        tips: '对接口的请求示例，仅做参考，不做为逻辑处理依据'
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
export const inParamTestCaseDataConfigJson = (dataQueryDatasourceApiFormItemConfigsRef)=>{
  return     {
    field: {
      name: 'inParamTestCaseDataConfigJson',
    },
    element: {
      comp: 'PtButton',
      formItemProps: {
        label: '入参测试用例数据',
        tips: '对接口的请求标识数据，为做接口测试时，提供准确的依据'
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
export const inParamDocConfigJson = (dataQueryDatasourceApiFormItemConfigsRef)=>{
  return {
    field: {
      name: 'inParamDocConfigJson',
    },
    element: {
      comp: 'PtButton',
      formItemProps: {
        label: '入参文档配置',
        tips: '对接口的参数字段说明'
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
export const outParamExampleConfigJson = (dataQueryDatasourceApiFormItemConfigsRef)=>{
  return     {
    field: {
      name: 'outParamExampleConfigJson',
    },
    element: {
      comp: 'PtButton',
      formItemProps: {
        label: '出参示例',
        tips: '对接口的响应示例，仅做参考，不做为逻辑处理依据'
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
export const outParamDocConfigJson = (dataQueryDatasourceApiFormItemConfigsRef)=>{
  return {
    field: {
      name: 'outParamDocConfigJson',
    },
    element: {
      comp: 'PtButton',
      formItemProps: {
        label: '出参文档配置',
        tips: '对接口的参数字段说明'
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
export const inParamValidateConfigJson = (dataQueryDatasourceApiFormItemConfigsRef)=>{
  return     {
    field: {
      name: 'inParamValidateConfigJson',
    },
    element: {
      comp: 'PtButton',
      formItemProps: {
        label: '入参校验配置',
        tips: '入参的校验规则，逻辑处理,支持多个配置，有一个校验失败表示失败，不配置代表不校验'
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
export const outParamSuccessConfigJson = (dataQueryDatasourceApiFormItemConfigsRef)=>{
  return {
    field: {
      name: 'outParamSuccessConfigJson',
    },
    element: {
      comp: 'PtButton',
      formItemProps: {
        label: '出参结果配置',
        tips: '可以用来标识出参成功或失败，以处理告警等逻辑，不配置代表都成功'
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
export const dictConfigJson = (dataQueryDatasourceApiFormItemConfigsRef)=>{
  return     {
    field: {
      name: 'dictConfigJson',
    },
    element: {
      comp: 'PtButton',
      formItemProps: {
        label: '字典配置',
        tips: '接口用到的字典数据配置'
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
export const pageableAdapterConfigJson = (dataQueryDatasourceApiFormItemConfigsRef)=>{
  return     {
    field: {
      name: 'pageableAdapterConfigJson',
    },
    element: {
      comp: 'PtButton',
      formItemProps: {
        label: '分页信息配置',
        tips: '用来解析分页请求和响应数据转换'
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
export const useAddPageFormItems = ({form,formData,dataQueryDatasourceApiFormItemConfigsRef,dataQueryDatasourceApiFormItemBasicConfigsRef})=>{
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
          tips: '接口名称，任意输入'
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
        name: 'dataQueryDatasourceId',
      },
      element: {
        comp: 'PtSelect',
        formItemProps: {
          label: '数据查询数据源',
          required: true,
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
          tips: '重要，标识接口数据的输出类型和处理逻辑',
          required: true,
          rules: [
            { validator: (rule: any, value: any, callback: any) => {
                if (value) {
                  // 数据源选择的是jdbc类型数据源，不支持代理验证
                  if(formData.dataQueryDatasourceId.typeDictValue == 'datasource_jdbc'){
                    if(formData.typeDictId?.value == 'proxy'){
                      callback(new Error(`${formData.dataQueryDatasourceId.name}为jdbc类型数据源不支持代理`))
                    } else {
                      callback()
                    }
                  } else {
                    callback()
                  }
                } else {
                  callback(new Error('类型不能为空'))
                }
              }, trigger: 'blur' }
          ]
        },
        compProps: ({form,formData})=>{
          let disabled = !form.dataQueryDatasourceId
          return {
            disabled: disabled,
            disabledReason: '请先选择数据源',
            title: '请先选择数据源',
            // 字典查询
            dictParam: {groupCode: 'dataquery_datasource_api_type'},
            sceneDataHander(data){
              if(formData.dataQueryDatasourceId?.typeDictValue == 'datasource_jdbc'){
                if(data){
                  let r = []
                  data.forEach(item =>{

                    let itemTemp = clone(item)
                    if(item.value == 'proxy'){
                      itemTemp.isDisabled = true
                      itemTemp.disabledReason = `${formData.dataQueryDatasourceId.name}为jdbc类型数据源不支持代理`
                      r.push(itemTemp)
                    }else {
                      r.push(itemTemp)
                    }
                  })
                  return r
                }
              }
              return data
            }
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
          tips: '不选择则表示无入参'
        },
        compProps: {
          // 字典查询
          dictParam: {groupCode: 'dataquery_datasource_api_param_type'}
        }
      }
    },
    inParamExampleConfigJson(dataQueryDatasourceApiFormItemConfigsRef),


    {
      field: {
        name: 'inParamTestCaseDataConfigJson',
      },
      element: {
        comp: 'PtButton',
        formItemProps: {
          label: '入参测试用例数据',
          tips: '对接口的请求标识数据，为做接口测试时，提供准确的依据'
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
    },
    inParamDocConfigJson(dataQueryDatasourceApiFormItemConfigsRef),
    inParamValidateConfigJson(dataQueryDatasourceApiFormItemConfigsRef),
    {
      field: {
        name: 'inParamExtConfigJson',
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '入参扩展配置',
          tips: '扩展配置支持，针对不同的数据源可能有更个性的配置'
        },
        compProps: {
          clearable: true,
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
          required: true,
          tips: "表示最原始的返回数据类型"
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
    {
      field: {
        name: 'outParamTransConfigJson',
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '出参翻译配置',
          tips: '针对响应数数据翻译数据字典支持'
        },
        compProps: {
          clearable: true,
        }
      }
    },
    {
      field: {
        name: 'outParamExtConfigJson',
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '出参扩展配置',
          tips: '扩展配置支持，针对不同的数据源可能有更个性的配置'
        },
        compProps: {
          clearable: true,
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
        value: 10000
      },
      element: {
        comp: 'el-input-number',
        formItemProps: {
          label: '连接等待时间',
          tips: '单位：ms。0为不限制，在存在有连接参数的调用时可用如：http调用，默认10s'
        },
        compProps: {
        }
      }
    },
    {
      field: {
        name: 'readTimeout',
        value: 60000
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '读取等待时间',
          tips: '单位：ms。0为不限制，在接口调用时超过该值将丢弃，默认60s'
        },
        compProps: {
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
  ]
}

// 更新和添加一致
export const useUpdatePageFormItems = useAddPageFormItems

