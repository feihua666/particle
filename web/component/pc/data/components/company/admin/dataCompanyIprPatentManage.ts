export const pageFormItems = [
      {
        field: {
          name: 'title',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '标题',

          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'applyNo',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '原始申请号',

          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'applyDate',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '申请日期',

          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'publicNo',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '原始公布号',

          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'publicDate',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '公布日',

          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'authorizePublicNo',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '授权公告号',

          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'authorizePublicDate',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '授权公告日',

          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'priorityNo',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '优先权号',

          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'priorityDate',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '优先权日',

          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'ipcCategoryNos',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: 'IPC分类号',

          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'ipcMainCategoryNo',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '主IPC分类号',

          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'cpcCategoryNos',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: 'CPC分类号',

          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'cpcMainCategoryNo',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '主CPC分类号',

          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'patentTypeDictId',
        },
        element: {
          comp: 'PtDictFrontSelect',
          formItemProps: {
            label: '专利类型',

          },
          compProps: {
            // 字典查询
            dictParam: {groupCode: '这里填写字典组编码'}
          }
        }
      },
      {
        field: {
          name: 'receivingOfficeName',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '受理局名称',

          },
          compProps: {
            clearable: true,
          }
        }
      },

      {
        field: {
          name: 'patentImageUrl',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '专利图片地址',

          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'isCurrentValid',
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否当前有效',

          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'instructionManualPageSize',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '说明书页数',

          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'locarnoCategoryNos',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '洛迦诺分类号',

          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'patentStrength',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '专利强度',

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
          name: 'title',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '标题',

          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'applyNo',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '原始申请号',
            required: true,
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'applyDate',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '申请日期',

          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'publicNo',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '原始公布号',

          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'publicDate',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '公布日',

          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'authorizePublicNo',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '授权公告号',

          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'authorizePublicDate',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '授权公告日',

          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'priorityNo',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '优先权号',

          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'priorityDate',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '优先权日',

          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'ipcCategoryNos',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: 'IPC分类号',

          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'ipcMainCategoryNo',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '主IPC分类号',

          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'cpcCategoryNos',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: 'CPC分类号',

          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'cpcMainCategoryNo',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '主CPC分类号',

          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'patentTypeDictId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '专利类型',
            required: true,
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'receivingOfficeName',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '受理局名称',

          },
          compProps: {
            clearable: true,
          }
        }
      },

      {
        field: {
          name: 'patentImageUrl',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '专利图片地址',

          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'isCurrentValid',
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否当前有效',

          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'instructionManualPageSize',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '说明书页数',

          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'locarnoCategoryNos',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '洛迦诺分类号',

          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'patentStrength',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '专利强度',

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

