import {useSelectAppCompItem} from "../../openplatformAppCompItem";
import {
  useOauth2SelectClientCompItem
} from "../../../../oauth2authorization/components/oauth2authorizationRegisteredClientCompItem";
import {useCascaderOpenapiCompItem} from "../../openplatformOpenapiCompItem";
import {useSelectCrmCustomerCompItem} from "../../../../crm/components/crmCompItem";

export const pageFormItems = [
  useSelectAppCompItem({}),
  useOauth2SelectClientCompItem({
    fieldName: 'appId',
    label: 'appId'
  }),
  useCascaderOpenapiCompItem({
    fieldName: 'openplatformOpenapiId',
    label: '开放接口',
    disableGroup: true
  }),
  {
    field: {
      name: 'dayAt',
    },
    element: {
      comp: 'PtDatePicker',
      formItemProps: {
        label: '日期'
      },
      compProps:  {
        clearable: true,
        type: "date",
      }
    }
  },
  useSelectCrmCustomerCompItem({fieldName: 'customerId',label: '客户'}),
]
export const addPageFormItems = [




      {
        field: {
          name: 'openplatformAppId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '开放平台应用id',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'appId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: 'appId',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'openplatformOpenapiId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '开放平台接口id',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'dayAt',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '日期',
            required: true,
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'customerId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '客户id',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'totalCall',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '调用总量',
            required: true,
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'totalFeeCall',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '调用计费总量',
            required: true,
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'averageUnitPriceAmount',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '平均单价金额',
            required: true,
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'totalFeeAmount',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '总消费金额',
            required: true,
          },
          compProps: {
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

