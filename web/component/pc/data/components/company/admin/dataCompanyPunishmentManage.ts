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
          name: 'punishNo',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '行政处罚决定书文号',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'illegalType',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '违法行为类型 ',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'punishBasis',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '处罚依据',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'illegalFact',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '违法事实',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'punishType',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '处罚类别',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'punishContent',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '处罚内容',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'fineAmount',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '罚款金额（万元）',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'fineAmountCurrencyDictId',
        },
        element: {
          comp: 'PtDictFrontSelect',
          formItemProps: {
            label: '罚款金额币种',
            
          },
          compProps: {
            // 字典查询
            dictParam: {groupCode: '这里填写字典组编码'}
          }
        }
      },
      {
        field: {
          name: 'confiscateAmount',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '没收金额（万元）',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'confiscateAmountCurrencyDictId',
        },
        element: {
          comp: 'PtDictFrontSelect',
          formItemProps: {
            label: '没收金额币种',
            
          },
          compProps: {
            // 字典查询
            dictParam: {groupCode: '这里填写字典组编码'}
          }
        }
      },
      {
        field: {
          name: 'instituteCompanyId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '作出行政处罚决定机关公司id',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'instituteName',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '作出行政处罚决定机关名称',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'suspendOrRevokeLicenseNameCode',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '暂扣或吊销证照名称及编号',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'punishDecisionStartDate',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '作出行政处罚决定开始日期',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'punishDecisionEndDate',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '作出行政处罚决定结束日期',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'publishStartDate',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '发布开始日期',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'publishEndDate',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '发布结束日期',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'dataFrom',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '数据来源',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'dataFromCompanyId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '数据来源公司id',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'dataFromName',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '数据来源名称',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'isDataFlagGs',
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否数据标识为工商公示',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'isDataFlagXyzg',
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否数据标识为信用中国',
            
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
          name: 'companyName',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '企业名称',
            required: true,
          },
          compProps: {
            clearable: true,
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
          name: 'punishNo',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '行政处罚决定书文号',
            required: true,
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'illegalType',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '违法行为类型 ',
            required: true,
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'punishBasis',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '处罚依据',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'illegalFact',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '违法事实',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'punishType',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '处罚类别',
            required: true,
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'punishContent',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '处罚内容',
            required: true,
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'fineAmount',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '罚款金额（万元）',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'fineAmountCurrencyDictId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '罚款金额币种',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'confiscateAmount',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '没收金额（万元）',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'confiscateAmountCurrencyDictId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '没收金额币种',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'instituteCompanyId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '作出行政处罚决定机关公司id',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'instituteName',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '作出行政处罚决定机关名称',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'suspendOrRevokeLicenseNameCode',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '暂扣或吊销证照名称及编号',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'punishDecisionStartDate',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '作出行政处罚决定开始日期',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'punishDecisionEndDate',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '作出行政处罚决定结束日期',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'publishStartDate',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '发布开始日期',
            required: true,
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'publishEndDate',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '发布结束日期',
            required: true,
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'dataFrom',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '数据来源',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'dataFromCompanyId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '数据来源公司id',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'dataFromName',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '数据来源名称',
            
          },
          compProps: {
            clearable: true,
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
            label: '备注',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'isDataFlagGs',
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否数据标识为工商公示',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'isDataFlagXyzg',
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否数据标识为信用中国',
            
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

