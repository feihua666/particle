import {useSelectCrmCustomerCompItem} from "../../../../crm/components/crmCompItem";

export const pageFormItems = [
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

export const monthBillStatisticPageFormItems = [
  {
    field: {
      name: 'isIncludeMonthSummary',
    },
    element: {
      comp: 'el-switch',
      formItemProps: {
        label: '是否统计月汇总',
        tips: '如果不统计月汇总，请确保统计月份的月汇总已存在，否则统计不到数据'
      },
      compProps: {
        activeText: '统计月汇总',
        inactiveText: '不统计月汇总',
      }
    }
  },
  {
    field: {
      name: 'isIncludeDaySummary',
    },
    element: {
      comp: 'el-switch',
      formItemProps: {
        label: '是否统计日汇总',
        tips: '如果不统计日汇总，请确保统计月份的日汇总已存在，否则统计不到数据，仅在 统计月汇总 情况下生效'
      },
      compProps: {
        activeText: '统计日汇总',
        inactiveText: '不统计日汇总',
      }
    }
  },

]