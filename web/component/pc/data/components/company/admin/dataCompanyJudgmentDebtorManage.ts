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
          name: 'executeCourtCompanyId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '执行法院公司id',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'executeCourtName',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '执行法院名称',
            
          },
          compProps: {
            clearable: true,
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
            label: '结案日期',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'isFinished',
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否已结案',
            
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
          name: 'executeCourtCompanyId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '执行法院公司id',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'executeCourtName',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '执行法院名称',
            
          },
          compProps: {
            clearable: true,
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
            label: '结案日期',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'isFinished',
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否已结案',
            
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

