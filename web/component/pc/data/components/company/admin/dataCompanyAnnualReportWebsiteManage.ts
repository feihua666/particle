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
          name: 'typeDictId',
        },
        element: {
          comp: 'PtDictFrontSelect',
          formItemProps: {
            label: '类型',
            
          },
          compProps: {
            // 字典查询
            dictParam: {groupCode: '这里填写字典组编码'}
          }
        }
      },
      {
        field: {
          name: 'name',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '名称',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'url',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '网址',
            
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
          name: 'typeDictId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '类型',
            required: true,
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'name',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '名称',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'url',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '网址',
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

