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
          name: 'fileName',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '许可文件名称',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'validToDate',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '许可文件到期日期',
            
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
          name: 'fileName',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '许可文件名称',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'validToDate',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '许可文件到期日期',
            
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

