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
          name: ' serialNumber',
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
          name: 'shareholderName',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '股东名称',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'isShareholderNaturalPerson',
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否股东名称为自然人',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'shareholderCompanyId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '股东名称公司id',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'shareholderCompanyPersonId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '股东名称个人id',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'shareholdingPercent',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '持股比例',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'subCapital',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '认缴出资金额（万元）',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'subCapitalCurrencyDictId',
        },
        element: {
          comp: 'PtDictFrontSelect',
          formItemProps: {
            label: '认缴出资金额币种',
            
          },
          compProps: {
            // 字典查询
            dictParam: {groupCode: '这里填写字典组编码'}
          }
        }
      },
      {
        field: {
          name: 'subCapitalTypeDictId',
        },
        element: {
          comp: 'PtDictFrontSelect',
          formItemProps: {
            label: '认缴出资方式',
            
          },
          compProps: {
            // 字典查询
            dictParam: {groupCode: '这里填写字典组编码'}
          }
        }
      },
      {
        field: {
          name: 'subCapitalDate',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '认缴出资日期',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'actualCapital',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '实缴出资金额（万元）',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'actualCapitalCurrencyDictId',
        },
        element: {
          comp: 'PtDictFrontSelect',
          formItemProps: {
            label: '实缴出资金额币种',
            
          },
          compProps: {
            // 字典查询
            dictParam: {groupCode: '这里填写字典组编码'}
          }
        }
      },
      {
        field: {
          name: 'actualCapitalDate',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '实缴出资日期',
            
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
          name: ' serialNumber',
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
          name: 'shareholderName',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '股东名称',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'isShareholderNaturalPerson',
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否股东名称为自然人',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'shareholderCompanyId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '股东名称公司id',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'shareholderCompanyPersonId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '股东名称个人id',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'shareholdingPercent',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '持股比例',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'subCapital',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '认缴出资金额（万元）',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'subCapitalCurrencyDictId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '认缴出资金额币种',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'subCapitalTypeDictId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '认缴出资方式',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'subCapitalDate',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '认缴出资日期',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'actualCapital',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '实缴出资金额（万元）',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'actualCapitalCurrencyDictId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '实缴出资金额币种',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'actualCapitalDate',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '实缴出资日期',
            
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

