export const pageFormItems = [
      {
        field: {
          name: 'dataId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '数据id',

          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'auditResultDictId',
        },
        element: {
          comp: 'PtDictFrontSelect',
          formItemProps: {
            label: '审核结果类型',

          },
          compProps: {
            // 字典查询
            dictParam: {groupCode: 'audit_result'}
          }
        }
      },

      {
        field: {
          name: 'groupFlag',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '分组标识',

          },
          compProps: {
            clearable: true,
          }
        }
      },

]
export const addPageFormItems = [




      {
        field: {
          name: 'dataId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '数据id',
            required: true,
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'auditResultDictId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '审核结果类型',
            required: true,
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'auditComment',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '审核意见',

          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'auditAt',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '审核时间',
            required: true,
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'auditBy',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '审核人',
            required: true,
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'dataPreStatusDictId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '数据审核之前状态',
            required: true,
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'dataPostStatusDictId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '数据审核之后状态',
            required: true,
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'groupFlag',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '分组标识',
            required: true,
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'groupFlagMemo',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '分组标识备忘',
            required: true,
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'remark',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '描述',

          },
          compProps: {
            clearable: true,
          }
        }
      },












]

// 更新和添加一致
export const updatePageFormItems = addPageFormItems

