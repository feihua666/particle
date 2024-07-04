import {useSelectDataqueryProviderCompItem} from "../../../../dataquery/components/dataqueryProviderCompItem";
import {useSelectProviderCompItem} from "../../openplatformProviderCompItem";

export const pageFormItems = [
      {
        field: {
          name: 'openplatformOpenapiRecordId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '调用记录id',
            
          },
          compProps: {
          }
        }
      },
  {
    field: {
      name: 'customerId',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '客户',
      },
      compProps: {
        disabled: true,
        disabledReason: '暂不支持，预留'
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
            label: '接口地址',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'requestParameterMd5',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '请求参数md5',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'responseResultMd5',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '响应结果md5',
            
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
            label: 'traceId',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },

      {
        field: {
          name: 'responseHttpStatus',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '响应http状态码',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'responseBusinessStatus',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '响应业务编码或业务状态码',
          },
          compProps: {
            clearable: true,
          }
        }
      },

  useSelectProviderCompItem({}),
  useSelectDataqueryProviderCompItem({}),
]
export const paramViewPageFormItems = [

  {
    field: {
      name: 'requestParam',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '请求参数',
        displayBlock:true
      },
      compProps: {
        type: 'textarea',
        readonly: true,
        rows: 15
      }
    }
  },


  {
    field: {
      name: 'responseResult',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '响应结果',
        displayBlock:true
      },
      compProps: {
        type: 'textarea',
        readonly: true,
        rows: 25
      }
    }
  },

]

