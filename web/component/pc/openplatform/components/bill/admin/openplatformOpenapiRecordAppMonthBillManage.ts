import {useSelectAppCompItem} from "../../openplatformAppCompItem";
import {
  useOauth2SelectClientCompItem
} from "../../../../oauth2authorization/components/oauth2authorizationRegisteredClientCompItem";
import {useSelectCrmCustomerCompItem} from "../../../../crm/components/crmCompItem";

export const pageFormItems = [
  useSelectAppCompItem({}),
  useOauth2SelectClientCompItem({
    fieldName: 'appId',
    label: 'appId'
  }),
  useSelectCrmCustomerCompItem({fieldName: 'customerId',label: '客户'}),
  {
    field: {
      name: 'year',
    },
    element: {
      comp: 'PtDatePicker',
      formItemProps: {
        label: '年',
      },
      compProps: {
        type: 'year',
        valueFormat: 'YYYY'
      }
    }
  },
  {
    field: {
      name: 'month',
    },
    element: {
      comp: 'PtDatePicker',
      formItemProps: {
        label: '月',

      },
      compProps: {
        type: 'month',
        format: 'MM',
        valueFormat: 'MM'
      }
    }
  },

  {
    field: {
      name: 'statusDictId',
    },
    element: {
      comp: 'PtDictFrontSelect',
      formItemProps: {
        label: '账单状态',

      },
      compProps: {
        // 字典查询
        dictParam: {groupCode: 'open_platform_bill_status'}
      }
    }
  },
]
export const updatePageFormItems = [

  useSelectAppCompItem({disabled: true}),
  useOauth2SelectClientCompItem({
    fieldName: 'appId',
    label: 'appId',
    disabled: true
  }),
  useSelectCrmCustomerCompItem({fieldName: 'customerId',label: '客户',disabled: true}),

      {
        field: {
          name: 'year',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '年',
            required: true,
          },
          compProps: {
            disabled: true
          }
        }
      },


      {
        field: {
          name: 'month',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '月',
            required: true,
          },
          compProps: {
            disabled: true
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
            disabled: true
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
            disabled: true
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
            disabled: true
          }
        }
      },


  {
    field: {
      name: 'statusDictId',
    },
    element: {
      comp: 'PtDictFrontSelect',
      formItemProps: {
        label: '账单状态',
        required: true
      },
      compProps: {
        // 字典查询
        dictParam: {groupCode: 'open_platform_bill_status'}
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
            displayBlock: true,
            tips: '可以添加一些账单跟进信息'
          },
          compProps: {
            type: 'textarea',
            clearable: true,
            rows: 10
          }
        }
      },

]


