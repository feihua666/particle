export const pageFormItems = [
      {
        field: {
          name: 'companyIprTrademarkLicenseId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '企业知识产权商标许可表id',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'licenseName',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '原始许可人名称',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'licenseNameCn',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '中文许可人名称',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'licenseNameEn',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '英文许可人名称',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'isLicensed',
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否为被许可人',
            
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
          name: 'companyIprTrademarkLicenseId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '企业知识产权商标许可表id',
            required: true,
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'licenseName',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '原始许可人名称',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'licenseNameCn',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '中文许可人名称',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'licenseNameEn',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '英文许可人名称',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'isLicensed',
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否为被许可人',
            
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

