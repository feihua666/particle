export const pageFormItems = [
      {
        field: {
          name: 'companyId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '企业表ID',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'companyAnnualReportId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '企业年报表ID',
            
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
            label: '年报年度',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'serialNumber',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '序号',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'investCompanyId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '对外投资企业',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'investPercent',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '对外投资比例',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'investAmount',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '对外投资金额（万元）',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'investAmountCurrencyDictId',
        },
        element: {
          comp: 'PtDictFrontSelect',
          formItemProps: {
            label: '对外投资金额币种',
            
          },
          compProps: {
            // 字典查询
            dictParam: {groupCode: '这里填写字典组编码'}
          }
        }
      },
      {
        field: {
          name: 'latestHandleAt',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '最后处理时间',
            
          },
          compProps: {
          }
        }
      },
]
export const addPageFormItems = [




      {
        field: {
          name: 'companyId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '企业表ID',
            required: true,
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'companyAnnualReportId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '企业年报表ID',
            required: true,
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
            label: '年报年度',
            required: true,
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'serialNumber',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '序号',
            required: true,
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'investCompanyId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '对外投资企业',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'investPercent',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '对外投资比例',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'investAmount',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '对外投资金额（万元）',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'investAmountCurrencyDictId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '对外投资金额币种',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'latestHandleAt',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '最后处理时间',
            
          },
          compProps: {
          }
        }
      },












]

// 更新和添加一致
export const updatePageFormItems = addPageFormItems

