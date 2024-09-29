export const pageFormItems = [
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
          name: 'year',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '年',
            
          },
          compProps: {
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
            
          },
          compProps: {
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
            dictParam: {groupCode: '这里填写字典组编码'}
          }
        }
      },
]
export const addPageFormItems = [




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
          name: 'year',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '年',
            required: true,
          },
          compProps: {
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
          name: 'statusDictId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '账单状态',
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

