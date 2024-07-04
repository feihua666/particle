export const pageFormItems = [
      {
        field: {
          name: 'name',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '模板名称',
            
          },
          compProps: {
            clearable: true,
            placeholder: '左前缀匹配',
          }
        }
      },
      {
        field: {
          name: 'requestUrlPrefix',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '请求地址前缀',
            
          },
          compProps: {
            clearable: true,
            placeholder: '左前缀匹配',
          }
        }
      },
      {
        field: {
          name: 'requestTypeDictId',
        },
        element: {
          comp: 'PtDictFrontSelect',
          formItemProps: {
            label: '请求类型',
            
          },
          compProps: {
            // 字典查询
            dictParam: {groupCode: 'http_method'}
          }
        }
      },
      {
        field: {
          name: 'requestBodyTypeDictId',
        },
        element: {
          comp: 'PtDictFrontSelect',
          formItemProps: {
            label: '请求体类型',
            
          },
          compProps: {
            // 字典查询
            dictParam: {groupCode: 'http_content_type'}
          }
        }
      },
      {
        field: {
          name: 'responseBodyTypeDictId',
        },
        element: {
          comp: 'PtDictFrontSelect',
          formItemProps: {
            label: '响应体类型',
            
          },
          compProps: {
            // 字典查询
            dictParam: {groupCode: 'http_content_type'}
          }
        }
      },

]
export const addPageFormItems = [




      {
        field: {
          name: 'name',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '模板名称',
            required: true,
            tips: '标识一个接口文档模板'
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'requestUrlPrefix',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '请求地址前缀',
            tips: '一般是一个地址前缀，如：http://example.com,如果不设置将使用使用全局配置'
          },
          compProps: {
            clearable: true,
          }
        }
      },


  {
    field: {
      name: 'requestTypeDictId',
    },
    element: {
      comp: 'PtDictFrontSelect',
      formItemProps: {
        label: '请求类型',

      },
      compProps: {
        // 字典查询
        dictParam: {groupCode: 'http_method'}
      }
    }
  },


  {
    field: {
      name: 'requestBodyTypeDictId',
    },
    element: {
      comp: 'PtDictFrontSelect',
      formItemProps: {
        label: '请求体类型',

      },
      compProps: {
        // 字典查询
        dictParam: {groupCode: 'http_content_type'}
      }
    }
  },
  {
    field: {
      name: 'responseBodyTypeDictId',
    },
    element: {
      comp: 'PtDictFrontSelect',
      formItemProps: {
        label: '响应体类型',

      },
      compProps: {
        // 字典查询
        dictParam: {groupCode: 'http_content_type'}
      }
    }
  },


      {
        field: {
          name: 'responseMaxDuration',
        },
        element: {
          comp: 'el-input-number',
          formItemProps: {
            label: '最大响应时长',
            tips: '单位ms',
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'responseMaxDurationDesc',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '响应时长文本',
            tips: '最大响应时长字段不支持时，可以使用文本描述替代'
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'authenticationType',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '认证方式',
            tips: '对认证方式进行描述，不设置将使用模板进行覆盖',
            displayBlock: true
          },
          compProps: {
            type: 'textarea',
            clearable: true,
            rows: 5
          }
        }
      },

]

// 更新和添加一致
export const updatePageFormItems = addPageFormItems

