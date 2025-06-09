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
          name: 'putNo',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '列入决定书文号',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'putDate',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '列入异常名录日期',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'putInstituteCompanyId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '作出列入决定机关公司id',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'putInstituteName',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '作出列入决定机关名称',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'removeNo',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '移出决定书文号',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'removeDate',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '移出异常名录日期',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'removeInstituteCompanyId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '作出移出决定机关公司id',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'removeInstituteName',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '作出移出决定机关名称',
            
          },
          compProps: {
            clearable: true,
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
          name: 'putNo',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '列入决定书文号',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'putReason',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '列入异常名录原因',
            required: true,
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'putDate',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '列入异常名录日期',
            required: true,
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'putInstituteCompanyId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '作出列入决定机关公司id',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'putInstituteName',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '作出列入决定机关名称',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'removeNo',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '移出决定书文号',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'removeReason',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '移出异常名录原因',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'removeDate',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '移出异常名录日期',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'removeInstituteCompanyId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '作出移出决定机关公司id',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'removeInstituteName',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '作出移出决定机关名称',
            
          },
          compProps: {
            clearable: true,
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

