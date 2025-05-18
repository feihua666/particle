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
          name: 'companyVcProductId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '公司产品id',

          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'roundDictId',
        },
        element: {
          comp: 'PtDictFrontSelect',
          formItemProps: {
            label: '融资轮次',

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
          name: 'amountCurrencyDictId',
        },
        element: {
          comp: 'PtDictFrontSelect',
          formItemProps: {
            label: '融资金额币种',

          },
          compProps: {
            // 字典查询
            dictParam: {groupCode: '这里填写字典组编码'}
          }
        }
      },
      {
        field: {
          name: 'valuation',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '估值',

          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'financingDate',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '融资日期',

          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'publishAt',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '报道时间',

          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'publishTitle',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '报道标题',

          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'publishUrl',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '报道链接地址',

          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'publishSnapshotUrl',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '报道快照链接地址',

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
          name: 'companyVcProductId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '公司产品id',

          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'roundDictId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '融资轮次',

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
          name: 'amountCurrencyDictId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '融资金额币种',

          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'valuation',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '估值',

          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'financingDate',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '融资日期',

          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'publishAt',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '报道时间',

          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'publishTitle',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '报道标题',

          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'publishUrl',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '报道链接地址',

          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'publishSnapshotUrl',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '报道快照链接地址',

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

