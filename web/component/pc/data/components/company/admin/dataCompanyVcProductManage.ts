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
          name: 'productName',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '产品名称',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'productLogoUrl',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '产品logo地址',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'productDescription',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '产品介绍',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'isPrimary',
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否是该公司代表性的产品',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'roundNum',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '融资次数',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'competitiveProductNum',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '竞品数量',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'currentRoundDictId',
        },
        element: {
          comp: 'PtDictFrontSelect',
          formItemProps: {
            label: '当前融资轮次',
            
          },
          compProps: {
            // 字典查询
            dictParam: {groupCode: '这里填写字典组编码'}
          }
        }
      },
      {
        field: {
          name: 'amount',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '融资金额（万元）',
            
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
          name: 'productName',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '产品名称',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'productLogoUrl',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '产品logo地址',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'productDescription',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '产品介绍',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'isPrimary',
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否是该公司代表性的产品',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'roundNum',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '融资次数',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'competitiveProductNum',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '竞品数量',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'currentRoundDictId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '当前融资轮次',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'amount',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '融资金额（万元）',
            
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

