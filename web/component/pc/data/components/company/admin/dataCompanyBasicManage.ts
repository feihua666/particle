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
          name: 'tin',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '纳税人识别号',
            
          },
          compProps: {
            clearable: true,
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
            label: '登记状态',
            
          },
          compProps: {
            // 字典查询
            dictParam: {groupCode: '这里填写字典组编码'}
          }
        }
      },
      {
        field: {
          name: 'natureDictId',
        },
        element: {
          comp: 'PtDictFrontSelect',
          formItemProps: {
            label: '性质',
            
          },
          compProps: {
            // 字典查询
            dictParam: {groupCode: '这里填写字典组编码'}
          }
        }
      },
      {
        field: {
          name: 'legalPersonName',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '法人名称',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'isLegalPersonNaturalPerson',
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否法人为自然人',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'legalPersonCompanyId',
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
          name: 'legalPersonCompanyPersonId',
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
          name: 'typeDictId',
        },
        element: {
          comp: 'PtDictFrontSelect',
          formItemProps: {
            label: '企业类型',
            
          },
          compProps: {
            // 字典查询
            dictParam: {groupCode: '这里填写字典组编码'}
          }
        }
      },
      {
        field: {
          name: 'regAddress',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '注册地址',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'regAddressPostalCode',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '注册地址',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'businessAddress',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '经营地址',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'businessAddressPostalCode',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '经营地址',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'establishDate',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '成立日期',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'businessFromDate',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '营业期限开始日期',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'businessToDate',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '营业期限终止日期',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'approveDate',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '核准日期',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'cancelDate',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '注销日期',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'cancelReason',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '注销原因',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'revokeDate',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '吊销日期',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'revokeReason',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '吊销原因',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'businessScope',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '经营范围',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'regInstituteCompanyId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '注册机关公司id',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'regInstituteName',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '注册机关名称',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'regCapital',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '注册资本（万元）',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'regCapitalCurrencyDictId',
        },
        element: {
          comp: 'PtDictFrontSelect',
          formItemProps: {
            label: '注册资金币种',
            
          },
          compProps: {
            // 字典查询
            dictParam: {groupCode: '这里填写字典组编码'}
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
          name: 'capitalTypeDictId',
        },
        element: {
          comp: 'PtDictFrontSelect',
          formItemProps: {
            label: '实缴出资方式',
            
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
          name: 'mobile',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '手机号码',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'telephone',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '电话号码',
            
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
            label: '邮箱',
            
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
          name: 'socialSecurityNum',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '社保人数',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'latestYearReportYear',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '最新年报年份',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'scaleTypeDictId',
        },
        element: {
          comp: 'PtDictFrontSelect',
          formItemProps: {
            label: '规模类型',
            
          },
          compProps: {
            // 字典查询
            dictParam: {groupCode: '这里填写字典组编码'}
          }
        }
      },
      {
        field: {
          name: 'longitude',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '经度',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'latitude',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '纬度',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'industryMainDictId',
        },
        element: {
          comp: 'PtDictFrontSelect',
          formItemProps: {
            label: '所属行业',
            
          },
          compProps: {
            // 字典查询
            dictParam: {groupCode: '这里填写字典组编码'}
          }
        }
      },
      {
        field: {
          name: 'industryBigDictId',
        },
        element: {
          comp: 'PtDictFrontSelect',
          formItemProps: {
            label: '所属行业',
            
          },
          compProps: {
            // 字典查询
            dictParam: {groupCode: '这里填写字典组编码'}
          }
        }
      },
      {
        field: {
          name: 'industryMiddleDictId',
        },
        element: {
          comp: 'PtDictFrontSelect',
          formItemProps: {
            label: '所属行业',
            
          },
          compProps: {
            // 字典查询
            dictParam: {groupCode: '这里填写字典组编码'}
          }
        }
      },
      {
        field: {
          name: 'industrySmallDictId',
        },
        element: {
          comp: 'PtDictFrontSelect',
          formItemProps: {
            label: '所属行业',
            
          },
          compProps: {
            // 字典查询
            dictParam: {groupCode: '这里填写字典组编码'}
          }
        }
      },
      {
        field: {
          name: 'provinceAreaId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '所在省份',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'cityAreaId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '所在城市',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'countyAreaId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '所在区县',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'isListed',
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否上市',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'stockCode',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '股票代码',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'listedTypeDictId',
        },
        element: {
          comp: 'PtDictFrontSelect',
          formItemProps: {
            label: '上市类型',
            
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
          name: 'tin',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '纳税人识别号',
            
          },
          compProps: {
            clearable: true,
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
            label: '登记状态',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'natureDictId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '性质',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'legalPersonName',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '法人名称',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'isLegalPersonNaturalPerson',
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否法人为自然人',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'legalPersonCompanyId',
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
          name: 'legalPersonCompanyPersonId',
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
          name: 'typeDictId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '企业类型',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'regAddress',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '注册地址',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'regAddressPostalCode',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '注册地址',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'businessAddress',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '经营地址',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'businessAddressPostalCode',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '经营地址',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'establishDate',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '成立日期',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'businessFromDate',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '营业期限开始日期',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'businessToDate',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '营业期限终止日期',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'approveDate',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '核准日期',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'cancelDate',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '注销日期',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'cancelReason',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '注销原因',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'revokeDate',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '吊销日期',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'revokeReason',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '吊销原因',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'businessScope',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '经营范围',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'regInstituteCompanyId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '注册机关公司id',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'regInstituteName',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '注册机关名称',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'regCapital',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '注册资本（万元）',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'regCapitalCurrencyDictId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '注册资金币种',
            
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
          name: 'capitalTypeDictId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '实缴出资方式',
            
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
          name: 'mobile',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '手机号码',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'telephone',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '电话号码',
            
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
            label: '邮箱',
            
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
          name: 'socialSecurityNum',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '社保人数',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'latestYearReportYear',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '最新年报年份',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'scaleTypeDictId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '规模类型',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'longitude',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '经度',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'latitude',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '纬度',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'industryMainDictId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '所属行业',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'industryBigDictId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '所属行业',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'industryMiddleDictId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '所属行业',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'industrySmallDictId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '所属行业',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'provinceAreaId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '所在省份',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'cityAreaId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '所在城市',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'countyAreaId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '所在区县',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'isListed',
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否上市',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'stockCode',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '股票代码',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'listedTypeDictId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '上市类型',
            
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

