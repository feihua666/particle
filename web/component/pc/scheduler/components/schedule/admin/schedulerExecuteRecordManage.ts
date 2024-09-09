export const pageFormItems = [
      {
        field: {
          name: 'schedulerName',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: 'schedulerName',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'schedulerInstanceId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: 'schedulerInstanceId',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'name',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '任务名称',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'groupName',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '任务组',
            
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
            
          },
          compProps: {
            clearable: true,
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
]
export const addPageFormItems = [




      {
        field: {
          name: 'schedulerName',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: 'schedulerName',
            required: true,
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'schedulerInstanceId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: 'schedulerInstanceId',
            required: true,
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'name',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '任务名称',
            required: true,
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'groupName',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '任务组',
            required: true,
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












]

// 更新和添加一致
export const updatePageFormItems = addPageFormItems

