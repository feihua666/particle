import {useSelectAppCompItem} from "../../openplatformAppCompItem";
import {useCascaderOpenapiCompItem, useSelectOpenapiProviderCompItem} from "../../openplatformOpenapiCompItem";
import {useSelectOpenapiFeeCompItem} from "../../openplatformOpenapiFeeCompItem";
import {useSelectProviderCompItem} from "../../openplatformProviderCompItem";
import {useSelectOpenapiLimitRuleCompItem} from "../../openplatformOpenapiLimitRuleCompItem";

export const pageFormItems = [
  useSelectAppCompItem({}),
  useCascaderOpenapiCompItem({
    fieldName: 'openplatformOpenapiId',
    label: '开放接口',
    disableGroup: true
  }),
  useSelectOpenapiFeeCompItem({}),
  useSelectOpenapiLimitRuleCompItem({
  }),
]
export const useAddPageFormItems = ({props = {},form}) => {
  return [
    useSelectAppCompItem({
      required: true,
    }),
    useCascaderOpenapiCompItem({
      fieldName: 'openplatformOpenapiId',
      label: '开放接口',
      required: true,
      disableGroup: true,
      valueChange({form,formData,newValue,oldValue}){

      }
    }),
    useSelectOpenapiFeeCompItem({
      tips: '如果配置将覆盖app管理中的默认计费规则配置'
    }),
    useSelectOpenapiLimitRuleCompItem({
      tips: '针对该应用分配的接口进行限制配置，注意区分于应用配置的限制规则，该配置是应用接口级的，粒度更细'
    }),

    {
      field: {
        name: 'specifyProviderConfigJson',
      },
      element: {
        comp: 'PtTableFormButton',
        formItemProps: {
          label: '指定供应商配置',
          tips: '该配置依赖开放接口，请先选择开放接口后再配置'

        },
        compProps: ({form})=>{
          return {
            disabled: !form.openplatformOpenapiId,
            disabledReason: !form.openplatformOpenapiId ? '请先选择开放接口后再配置' : undefined,
            formProps:{
              labelWidth: '120',
              formSubmitDataHandler({isAdd,formRef,form,formData}){
                form.openplatformProviderId = formData.providerConfigJsonId?.openplatformProviderId
                form.openplatformProviderCode = formData.providerConfigJsonId?.openplatformProviderCode
                form.openplatformProviderName = formData.providerConfigJsonId?.openplatformProviderName
                form.providerApiVersion = formData.providerConfigJsonId?.providerApiVersion
                form.dataLagGroovyScript = formData.providerConfigJsonId?.dataLagGroovyScript
                return form
              },
              comps: [
                useSelectOpenapiProviderCompItem({
                  tips: '配置接口指定由哪个供应商提供',
                  openplatformOpenapiId: form.openplatformOpenapiId
                }),

                {
                  field: {
                    name: 'isWhenDataLagNext',
                    value: true,
                  },
                  element: {
                    comp: 'el-switch',
                    formItemProps: {
                      label: '是否数据滞后时使用下一个',
                      tips: '当数据滞后时，是否尝试下一个供应商'

                    },
                    compProps: {
                      activeText: '是',
                      inactiveText: '否',
                    }
                  }
                },
                {
                  field: {
                    name: 'isWhenDataNotFoundNext',
                    value: true,
                  },
                  element: {
                    comp: 'el-switch',
                    formItemProps: {
                      label: '是否数据不存在时使用下一个',
                      tips: '当数据不存在时，是否尝试下一个供应商'

                    },
                    compProps: {
                      activeText: '是',
                      inactiveText: '否',
                    }
                  }
                },
                {
                  field: {
                    name: 'isWhenDataExistNext',
                    value: true,
                  },
                  element: {
                    comp: 'el-switch',
                    formItemProps: {
                      label: '是否数据存在时使用下一个',
                      tips: '当数据存在时，是否尝试下一个供应商，这对数据入库非常有用'

                    },
                    compProps: {
                      activeText: '是',
                      inactiveText: '否',
                    }
                  }
                },
                {
                  field: {
                    name: 'isWhenErrorNext',
                    value: true
                  },
                  element: {
                    comp: 'el-switch',
                    formItemProps: {
                      label: '是否请求异常时使用下一个',
                      tips: '当请求异常时，是否尝试下一个供应商'

                    },
                    compProps: {
                      activeText: '是',
                      inactiveText: '否',
                    }
                  }
                },

                {
                  field: {
                    name: 'isWarehouseResult',
                    value: true
                  },
                  element: {
                    comp: 'el-switch',
                    formItemProps: {
                      label: '是否将结果入库',
                      tips: '当有正常返回数据时，将该结果数据入库'

                    },
                    compProps: {
                      activeText: '是',
                      inactiveText: '否',
                    }
                  }
                },
                {
                  field: {
                    name: 'isWarehouseAsync',
                    value: true
                  },
                  element: {
                    comp: 'el-switch',
                    formItemProps: {
                      label: '是否异步入库',
                      tips: '是否选择入库时，使用异步入库方式'

                    },
                    compProps: {
                      activeText: '是',
                      inactiveText: '否',
                    }
                  }
                },
              ]
            },
            tableProps:{
              showMoveUpAndDownButton: true,
              propForDeleteView: 'openplatformProviderName',
              columns: [
                {
                  prop: 'openplatformProviderId',
                  label: '供应商id',
                  showOverflowTooltip: true
                },
                {
                  prop: 'openplatformProviderCode',
                  label: '供应商编码',
                  showOverflowTooltip: true
                },
                {
                  prop: 'openplatformProviderName',
                  label: '供应商名称',
                  showOverflowTooltip: true
                },
                {
                  prop: 'providerApiVersion',
                  label: '供应商接口版本',
                  showOverflowTooltip: true
                },
                {
                  prop: 'dataLagGroovyScript',
                  label: '数据滞后脚本',
                  showOverflowTooltip: true
                },
                {
                  prop: 'isWhenDataLagNext',
                  label: '数据滞后时',
                  formatter: (row, column, cellValue, index) => {
                    return cellValue ? '使用下一个' : '否'
                  }
                },
                {
                  prop: 'isWhenDataNotFoundNext',
                  label: '数据不存在时',
                  formatter: (row, column, cellValue, index) => {
                    return cellValue ? '使用下一个' : '否';
                  }
                },
                {
                  prop: 'isWhenDataExistNext',
                  label: '数据存在时',
                  formatter: (row, column, cellValue, index) => {
                    return cellValue ? '使用下一个' : '否';
                  }
                },
                {
                  prop: 'isWhenErrorNext',
                  label: '请求异常时',
                  formatter: (row, column, cellValue, index) => {
                    return cellValue ? '使用下一个' : '否';
                  }
                },
                {
                  prop: 'isWarehouseResult',
                  label: '将结果入库',
                  formatter: (row, column, cellValue, index) => {
                    return cellValue ? '结果入库' : '否';
                  }
                },
                {
                  prop: 'isWarehouseAsync',
                  label: '异步入库',
                  formatter: (row, column, cellValue, index) => {
                    return cellValue ? '异步入库' : '否';
                  }
                }

              ]
            }
          }
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

