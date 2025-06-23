export const pageFormItems = [
      {
        field: {
          name: 'companyIprTrademarkTransferId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '企业知识产权商标转让表id',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'transferName',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '原始转让人名称',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'transferNameCn',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '中文转让人名称',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'transferNameEn',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '英文转让人名称',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'isTransferred',
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否被转让人',
            
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
          name: 'companyIprTrademarkTransferId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '企业知识产权商标转让表id',
            required: true,
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'transferName',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '原始转让人名称',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'transferNameCn',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '中文转让人名称',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'transferNameEn',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '英文转让人名称',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'isTransferred',
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否被转让人',
            
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

