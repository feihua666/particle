export const pageFormItems = [
      {
        field: {
          name: 'workflowExecutionId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '工作流执行ID',

          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'nodeId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '节点ID',

          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'statusDictId',
        },
        element: {
          comp: 'PtDictFrontSelect',
          formItemProps: {
            label: '执行状态',
          },
          compProps: {
            // 字典查询
            dictParam: {groupCode: 'workflow_execution_status'}
          }
        }
      },

]
export const addPageFormItems = [




      {
        field: {
          name: 'workflowExecutionId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '工作流执行ID',
            required: true,
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'nodeId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '节点ID（对应graph里的id）',
            required: true,
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'statusDictId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '状态：pending/running/success/failed',
            required: true,
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'inputJson',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '节点输入',

          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'outputJson',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '节点输出',

          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'errorMsg',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '错误信息',

          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'retryCount',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '重试次数',

          },
          compProps: {
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












]

// 更新和添加一致
export const updatePageFormItems = addPageFormItems

