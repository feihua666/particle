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
          name: 'dishonestExecutedPersonName',
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
          name: 'isDishonestExecutedPersonNaturalPerson',
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
          name: 'dishonestExecutedPersonCompanyId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '被执行人公司id',

          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'dishonestExecutedPersonCompanyPersonId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '被执行人个人id',

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
          name: 'dishonestExecutedPersonLicenseNo',
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
          name: 'publishDate',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '发布日期',

          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'documentNo',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '执行依据文号',

          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'decisionBasisDeptCompanyId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '做出执行的依据单位公司id',

          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'decisionBasisDeptName',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '做出执行的依据单位',

          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'obligation',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '生效法律文书确定的义务',

          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'performance',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '履行情况',

          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'performPart',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '已履行',

          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'unPerformPart',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '未履行',

          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'dishonestExecutedPersonBehavior',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '失信被执行人行为具体情形',

          },
          compProps: {
            clearable: true,
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
            label: '执行标的金额(万元)',

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
          name: 'dishonestExecutedPersonName',
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
          name: 'isDishonestExecutedPersonNaturalPerson',
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
          name: 'dishonestExecutedPersonCompanyId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '被执行人公司id',

          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'dishonestExecutedPersonCompanyPersonId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '被执行人个人id',

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
          name: 'dishonestExecutedPersonLicenseNo',
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
          name: 'publishDate',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '发布日期',

          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'documentNo',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '执行依据文号',

          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'decisionBasisDeptCompanyId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '做出执行的依据单位公司id',

          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'decisionBasisDeptName',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '做出执行的依据单位',

          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'obligation',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '生效法律文书确定的义务',

          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'performance',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '履行情况',

          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'performPart',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '已履行',

          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'unPerformPart',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '未履行',

          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'dishonestExecutedPersonBehavior',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '失信被执行人行为具体情形',

          },
          compProps: {
            clearable: true,
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
            label: '执行标的金额(万元)',

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

