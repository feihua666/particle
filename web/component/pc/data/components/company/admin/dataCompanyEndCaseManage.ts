export const pageFormItems = [
      {
        field: {
          name: 'caseNo',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '案号',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'executedPersonName',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '被执行人名称',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'isExecutedPersonNaturalPerson',
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否被执行人为自然人',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'executedPersonCompanyId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '法人公司id',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'executedPersonCompanyPersonId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '法人个人id',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'executedPersonLicenseNo',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '被执行人证照/证件号码',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'courtName',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '法院名称',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'courtCompanyId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '法院名称公司id',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'fileCaseDate',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '立案日期',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'finishedCaseDate',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '结束日期',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'executeAmount',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '执行标的金额（万元）',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'executeAmountCurrencyDictId',
        },
        element: {
          comp: 'PtDictFrontSelect',
          formItemProps: {
            label: '执行标的金额币种',
            
          },
          compProps: {
            // 字典查询
            dictParam: {groupCode: '这里填写字典组编码'}
          }
        }
      },
      {
        field: {
          name: 'unPerformAmount',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '未履行金额（万元）',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'unPerformAmountCurrencyDictId',
        },
        element: {
          comp: 'PtDictFrontSelect',
          formItemProps: {
            label: '未履行金额币种',
            
          },
          compProps: {
            // 字典查询
            dictParam: {groupCode: '这里填写字典组编码'}
          }
        }
      },
      {
        field: {
          name: 'dataMd5',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '数据md5',
            
          },
          compProps: {
            clearable: true,
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
          name: 'caseNo',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '案号',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'executedPersonName',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '被执行人名称',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'isExecutedPersonNaturalPerson',
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否被执行人为自然人',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'executedPersonCompanyId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '法人公司id',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'executedPersonCompanyPersonId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '法人个人id',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'executedPersonLicenseNo',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '被执行人证照/证件号码',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'courtName',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '法院名称',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'courtCompanyId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '法院名称公司id',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'fileCaseDate',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '立案日期',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'finishedCaseDate',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '结束日期',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'executeAmount',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '执行标的金额（万元）',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'executeAmountCurrencyDictId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '执行标的金额币种',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'unPerformAmount',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '未履行金额（万元）',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'unPerformAmountCurrencyDictId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '未履行金额币种',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'dataMd5',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '数据md5',
            required: true,
          },
          compProps: {
            clearable: true,
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

