export const pageFormItems = [
      {
        field: {
          name: 'groupIdentifier',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '任务分组标识',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'uniqueIdentifier',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '唯一标识',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'executeStatusDictId',
        },
        element: {
          comp: 'PtDictFrontSelect',
          formItemProps: {
            label: '执行状态',
            
          },
          compProps: {
            // 字典查询
            dictParam: {groupCode: '这里填写字典组编码'}
          }
        }
      },
      {
        field: {
          name: 'startAt',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '运行开始时间',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'finishAt',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '运行结束时间',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'localHostIp',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '本地主机ip',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'localHostName',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '本地主机名称',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'traceId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '链路追踪id',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'dataExpireAt',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '数据过期时间',
            
          },
          compProps: {
          }
        }
      },
]
export const addPageFormItems = [




      {
        field: {
          name: 'groupIdentifier',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '任务分组标识',
            required: true,
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'uniqueIdentifier',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '唯一标识',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'params',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '执行参数',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'executeStatusDictId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '执行状态',
            required: true,
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'errorMessage',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '执行错误时提示信息',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'startAt',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '运行开始时间',
            required: true,
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'finishAt',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '运行结束时间',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'localHostIp',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '本地主机ip',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'localHostName',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '本地主机名称',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'traceId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '链路追踪id',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'result',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '运行结果',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'dataExpireAt',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '数据过期时间',
            required: true,
          },
          compProps: {
          }
        }
      },












]

// 更新和添加一致
export const updatePageFormItems = addPageFormItems

