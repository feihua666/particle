export const pageFormItems = [
      {
        field: {
          name: 'name',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '操作名称',
            
          },
          compProps: {
            clearable: true,
            placeholder: '左前缀匹配，如：添加用户',
          }
        }
      },
      {
        field: {
          name: 'moduleDictId',
        },
        element: {
          comp: 'PtDictFrontSelect',
          formItemProps: {
            label: '模块字典',
          },
          compProps: {
            // 字典查询
            dictParam: {groupCode: 'op_log_module'}
          }
        }
      },
      {
        field: {
          name: 'module',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '模块',
            
          },
          compProps: {
            clearable: true,
            placeholder: '左前缀匹配，如：user',
          }
        }
      },
      {
        field: {
          name: 'typeDictId',
        },
        element: {
          comp: 'PtDictFrontSelect',
          formItemProps: {
            label: '类型字典',
          },
          compProps: {
            // 字典查询
            dictParam: {groupCode: 'op_log_type'}
          }
        }
      },
      {
        field: {
          name: 'type',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '类型',
            
          },
          compProps: {
            clearable: true,
            placeholder: '左前缀匹配，如：create',
          }
        }
      },
      {
        field: {
          name: 'userName',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '操作用户姓名',
            
          },
          compProps: {
            clearable: true,
            placeholder: '左前缀匹配',
          }
        }
      },
      {
        field: {
          name: 'userNickname',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '操作用户昵称',
            
          },
          compProps: {
            clearable: true,
            placeholder: '左前缀匹配',
          }
        }
      },
      {
        field: {
          name: 'url',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '请求地址',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'mainDataId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '主数据id',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'mainDataTable',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '主数据表名',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'mainDataEntity',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '主数据载体',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'operateAtStart',
        },
        element: {
          comp: 'PtDatePicker',
          formItemProps: {
            label: '操作时间开始',
          },
          compProps: {
            type: 'datetime'
          }
        }
      },
  {
    field: {
      name: 'operateAtEnd',
    },
    element: {
      comp: 'PtDatePicker',
      formItemProps: {
        label: '操作时间结束',
      },
      compProps: {
        type: 'datetime'
      }
    }
  },
]
