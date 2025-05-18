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
          name: 'debtorName',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '债务人名称',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'isDebtorNaturalPerson',
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否债务人为自然人',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'debtorCompanyId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '债务人公司id',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'debtorCompanyPersonId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '债务人个人id',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'creditorName',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '债权人名称',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'isCreditorNaturalPerson',
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否债权人为自然人',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'creditorCompanyId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '债权人公司id',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'creditorCompanyPersonId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '债权人个人id',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'creditoTypeDictId',
        },
        element: {
          comp: 'PtDictFrontSelect',
          formItemProps: {
            label: '主债权种类',
            
          },
          compProps: {
            // 字典查询
            dictParam: {groupCode: '这里填写字典组编码'}
          }
        }
      },
      {
        field: {
          name: 'creditoAmount',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '主债权金额(万元)',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'creditoAmountCurrencyDictId',
        },
        element: {
          comp: 'PtDictFrontSelect',
          formItemProps: {
            label: '主债权金额币种',
            
          },
          compProps: {
            // 字典查询
            dictParam: {groupCode: '这里填写字典组编码'}
          }
        }
      },
      {
        field: {
          name: 'debtFromDate',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '履行债务的期限自',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'debtToDate',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '履行债务的期限至',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'guaranteeScope',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '保证担保的范围',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'guaranteeTermDictId',
        },
        element: {
          comp: 'PtDictFrontSelect',
          formItemProps: {
            label: '保证的期间',
            
          },
          compProps: {
            // 字典查询
            dictParam: {groupCode: '这里填写字典组编码'}
          }
        }
      },
      {
        field: {
          name: 'guaranteeTypeDictId',
        },
        element: {
          comp: 'PtDictFrontSelect',
          formItemProps: {
            label: '保证的方式',
            
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
          name: 'debtorName',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '债务人名称',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'isDebtorNaturalPerson',
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否债务人为自然人',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'debtorCompanyId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '债务人公司id',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'debtorCompanyPersonId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '债务人个人id',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'creditorName',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '债权人名称',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'isCreditorNaturalPerson',
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否债权人为自然人',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'creditorCompanyId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '债权人公司id',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'creditorCompanyPersonId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '债权人个人id',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'creditoTypeDictId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '主债权种类',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'creditoAmount',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '主债权金额(万元)',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'creditoAmountCurrencyDictId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '主债权金额币种',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'debtFromDate',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '履行债务的期限自',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'debtToDate',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '履行债务的期限至',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'guaranteeScope',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '保证担保的范围',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'guaranteeTermDictId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '保证的期间',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'guaranteeTypeDictId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '保证的方式',
            
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

