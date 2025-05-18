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
          name: 'caseReason',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '案由',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'caseCourtCompanyId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '案件审理法院公司id',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'caseCourtName',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '案件审理法院名称',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'caseJudgeDate',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '案件裁判日期',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'caseTrialProcedure',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '案件审理程序',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'caseLegalBasis',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '法律依据',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'caseTypeDictId',
        },
        element: {
          comp: 'PtDictFrontSelect',
          formItemProps: {
            label: '案件类型',
            
          },
          compProps: {
            // 字典查询
            dictParam: {groupCode: '这里填写字典组编码'}
          }
        }
      },
      {
        field: {
          name: 'caseAmount',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '案件涉及金额（万元）',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'caseAmountCurrencyDictId',
        },
        element: {
          comp: 'PtDictFrontSelect',
          formItemProps: {
            label: '案件涉及金额币种',
            
          },
          compProps: {
            // 字典查询
            dictParam: {groupCode: '这里填写字典组编码'}
          }
        }
      },
      {
        field: {
          name: 'documentTypeDictId',
        },
        element: {
          comp: 'PtDictFrontSelect',
          formItemProps: {
            label: '文书类型',
            
          },
          compProps: {
            // 字典查询
            dictParam: {groupCode: '这里填写字典组编码'}
          }
        }
      },
      {
        field: {
          name: 'documentPublishDate',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '文书发布日期',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'documentPublishTitle',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '文书发布标题',
            
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
          name: 'caseReason',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '案由',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'caseCourtCompanyId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '案件审理法院公司id',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'caseCourtName',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '案件审理法院名称',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'caseJudgeDate',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '案件裁判日期',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'caseTrialProcedure',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '案件审理程序',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'caseLegalBasis',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '法律依据',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'caseTypeDictId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '案件类型',
            required: true,
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'caseAmount',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '案件涉及金额（万元）',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'caseAmountCurrencyDictId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '案件涉及金额币种',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'documentTypeDictId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '文书类型',
            required: true,
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'documentPublishDate',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '文书发布日期',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'documentPublishTitle',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '文书发布标题',
            
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

