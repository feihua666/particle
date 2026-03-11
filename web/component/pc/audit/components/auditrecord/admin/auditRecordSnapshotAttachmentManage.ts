export const pageFormItems = [
      {
        field: {
          name: 'auditRecordId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '审核记录id',
            
          },
          compProps: {
          }
        }
      },
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
          name: 'snapshotDataId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '快照数据id',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'attachmentName',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '附件名称',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'attachmentUrl',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '附件地址',
            
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
          name: 'auditRecordId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '审核记录id',
            required: true,
          },
          compProps: {
          }
        }
      },


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
          name: 'snapshotDataId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '快照数据id',
            required: true,
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'attachmentName',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '附件名称',
            required: true,
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'attachmentUrl',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '附件地址',
            required: true,
          },
          compProps: {
            clearable: true,
          }
        }
      },












]

// 更新和添加一致
export const updatePageFormItems = addPageFormItems

