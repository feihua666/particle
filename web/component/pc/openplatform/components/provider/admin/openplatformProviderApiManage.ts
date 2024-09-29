export const pageFormItems = [
      {
        field: {
          name: 'code',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '编码',
            
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
            label: '供应商名称',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'dataQueryDatasourceApiId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '数据查询数据源接口id',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'openplatformOpenapiFeeId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '计费id',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'requestUrl',
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
          name: 'scriptTypeDictValue',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '脚本类型',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'scriptContent',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '脚本内容',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'readTimeout',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '读取等待时间',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'connectTimeout',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '连接等待时间',
            
          },
          compProps: {
          }
        }
      },
]
export const addPageFormItems = [




      {
        field: {
          name: 'code',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '编码',
            
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
            label: '供应商名称',
            required: true,
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'dataQueryDatasourceApiId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '数据查询数据源接口id',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'openplatformOpenapiFeeId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '计费id',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'requestUrl',
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
          name: 'scriptTypeDictValue',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '脚本类型',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'scriptContent',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '脚本内容',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'readTimeout',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '读取等待时间',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'connectTimeout',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '连接等待时间',
            
          },
          compProps: {
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

