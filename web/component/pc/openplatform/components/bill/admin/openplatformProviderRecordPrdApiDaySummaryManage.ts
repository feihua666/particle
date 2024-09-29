export const pageFormItems = [
      {
        field: {
          name: 'openplatformProviderId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '供应商id',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'openplatformProviderApiId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '供应商接口id',
            
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
          name: 'averageUnitPriceAmount',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '平均单价金额',
            
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
]
export const addPageFormItems = [




      {
        field: {
          name: 'openplatformProviderId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '供应商id',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'openplatformProviderApiId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '供应商接口id',
            
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

