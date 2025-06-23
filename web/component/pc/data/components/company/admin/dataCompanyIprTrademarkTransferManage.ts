export const pageFormItems = [
      {
        field: {
          name: 'companyIprTrademarkId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '企业知识产权商标表id',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'transferIssueNo',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '转让期号',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'transferPageNo',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '转让页码',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'transferPublicDate',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '转让公告日期',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'dataMd5',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '数据md5',
            
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
          name: 'companyIprTrademarkId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '企业知识产权商标表id',
            required: true,
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'transferIssueNo',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '转让期号',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'transferPageNo',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '转让页码',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'transferPublicDate',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '转让公告日期',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'dataMd5',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '数据md5',
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

