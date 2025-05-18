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
          name: 'companyName',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '企业名称',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'uscc',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '统一社会信用代码',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'regNo',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '注册号',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'capital',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '资金数额（万元）',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'capitalCurrencyDictId',
        },
        element: {
          comp: 'PtDictFrontSelect',
          formItemProps: {
            label: '资金数额币种',
            
          },
          compProps: {
            // 字典查询
            dictParam: {groupCode: '这里填写字典组编码'}
          }
        }
      },
      {
        field: {
          name: 'operatorCompanyPersonId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '经营者名称',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'operatorName',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '经营者名称',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'postalAddress',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '企业通信地址',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'postCode',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '邮政编码',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'contactPhone',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '企业联系电话',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'email',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '企业电子邮箱',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'employeeNum',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '从业人数',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'femaleEmployeeNum',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '其中女性从业人数',
            
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
            label: '企业经营状态',
            
          },
          compProps: {
            // 字典查询
            dictParam: {groupCode: '这里填写字典组编码'}
          }
        }
      },
      {
        field: {
          name: 'holdingControlInfo',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '企业控股情况',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'isHasInvestOrBugEquity',
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否有投资信息或购买其他公司股权',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'isHasWebsite',
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否有网站或网店',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'isHasForeignGuarantee',
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否有对外提供担保信息',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'isHasEquityTransfer',
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '有限责任公司本年度是否发生股东股权转让',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'normalBusinessScope',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '经营范围（一般项目）',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'approvedBusinessScope',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '经营范围（许可项目）',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'isIsHasForeignGuaranteePublic',
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否对外提供担保信息公示',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'isFemaleEmployeeNumPublic',
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否其中女性从业人数公示',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'isHoldingControlInfoPublic',
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否企业控股情况公示',
            
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
          name: 'companyName',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '企业名称',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'uscc',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '统一社会信用代码',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'regNo',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '注册号',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'capital',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '资金数额（万元）',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'capitalCurrencyDictId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '资金数额币种',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'operatorCompanyPersonId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '经营者名称',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'operatorName',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '经营者名称',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'postalAddress',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '企业通信地址',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'postCode',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '邮政编码',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'contactPhone',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '企业联系电话',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'email',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '企业电子邮箱',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'employeeNum',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '从业人数',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'femaleEmployeeNum',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '其中女性从业人数',
            
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
            label: '企业经营状态',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'holdingControlInfo',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '企业控股情况',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'isHasInvestOrBugEquity',
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否有投资信息或购买其他公司股权',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'isHasWebsite',
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否有网站或网店',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'isHasForeignGuarantee',
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否有对外提供担保信息',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'isHasEquityTransfer',
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '有限责任公司本年度是否发生股东股权转让',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'normalBusinessScope',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '经营范围（一般项目）',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'approvedBusinessScope',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '经营范围（许可项目）',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'isIsHasForeignGuaranteePublic',
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否对外提供担保信息公示',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'isFemaleEmployeeNumPublic',
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否其中女性从业人数公示',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'isHoldingControlInfoPublic',
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否企业控股情况公示',
            
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

