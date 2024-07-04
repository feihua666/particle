import {
  useSelectOpenplatformDocApiCompItem,
  useSelectOpenplatformDocApiDocTemplateCompItem
} from "../../openplatformDocCompItem";

export const pageFormItems = [
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
            placeholder: '左前缀匹配'
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
            placeholder: '左前缀匹配'
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
  useSelectOpenplatformDocApiCompItem({})
]
export const addPageFormItems = [


    useSelectOpenplatformDocApiCompItem({required: true}),
      {
        field: {
          name: 'requestUrlPrefix',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '请求地址前缀',
            tips: '一般是一个地址前缀，如：http://example.com,如果不设置将使用模板进行覆盖否则默认为使用全局配置'
          },
          compProps: {
            clearable: true,
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
            required: true,
            tips: '一般是一个接口路径，如：/api/user，最终的地址应该是 请求前缀+请求地址 的拼接结果'
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
        tips: '不设置将使用模板进行覆盖'
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
        tips: '不设置将使用模板进行覆盖'
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
        tips: '不设置将使用模板进行覆盖'
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
            tips: '单位ms，不设置将使用模板进行覆盖',
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
            displayBlock: true,
            tips: '最大响应时长字段不支持时，可以使用文本描述替代，不设置将使用模板进行覆盖'
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
            displayBlock: true,
            tips: '对认证方式进行描述，不设置将使用模板进行覆盖'
          },
          compProps: {
            type: 'textarea',
            clearable: true,
            rows: 5
          }
        }
      },

      {
        field: {
          name: 'descriptionDetail',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '详细描述',
            displayBlock: true,
            tips: '对接口进行详细的描述，旨在更加详细的介绍接口的用途，如：入参、出参说明等'
          },
          compProps: {
            type: 'textarea',
            clearable: true,
            rows: 5
          }
        }
      },


      {
        field: {
          name: 'requestParamExample',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '请求参数示例',
            required: true,
            displayBlock: true,
            tips: '对请求参数样例举例，如：一个json字符串'
          },
          compProps: {
            type: 'textarea',
            clearable: true,
            rows: 5
          }
        }
      },


      {
        field: {
          name: 'responseParamExample',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '响应参数示例',
            required: true,
            displayBlock: true,
            tips: '对响应参数样例举例，如：一个json字符串'
          },
          compProps: {
            type: 'textarea',
            clearable: true,
            rows: 5
          }
        }
      },


  useSelectOpenplatformDocApiDocTemplateCompItem({tips: '可以选择一个模板，以进行通用设置'}),


]

// 更新和添加一致
export const updatePageFormItems = addPageFormItems

