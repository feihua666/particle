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
          name: 'changeItemDictId',
        },
        element: {
          comp: 'PtDictFrontSelect',
          formItemProps: {
            label: '变更事项',
            
          },
          compProps: {
            // 字典查询
            dictParam: {groupCode: '这里填写字典组编码'}
          }
        }
      },
      {
        field: {
          name: 'changeItemName',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '变更事项',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'contentBefore',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '变更前内容',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'contentAfter',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '变更后内容',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'changeDate',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '变更日期',
            
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
          name: 'changeItemDictId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '变更事项',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'changeItemName',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '变更事项',
            required: true,
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'contentBefore',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '变更前内容',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'contentAfter',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '变更后内容',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'changeDate',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '变更日期',
            
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

