import {useSelectAppCompItem} from "../../openplatformAppCompItem";
import {useRemoteSelectUserCompItem} from "../../../../user/compnents/userCompItem";
import {useCascaderOpenapiCompItem} from "../../openplatformOpenapiCompItem";

export const pageFormItems = [
  useSelectAppCompItem({
    label: '应用'
  }),
      {
        field: {
          name: 'appId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: 'appId',

          },
          compProps: {
            clearable: true,
          }
        }
      },
  useRemoteSelectUserCompItem({
    props: {},
    fieldName: 'userId',
    propUserIdFieldName: 'userId',
    propUserNicknameFieldName: 'userNickname',
    label: '用户'
  }),

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

  useCascaderOpenapiCompItem({
    fieldName: 'openplatformOpenapiId',
    label: '开放接口',
    disableGroup: true
  }),
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
          name: 'requestNonce',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '请求流水号',

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
            label: '响应业务状态码',

          },
          compProps: {
            clearable: true,
          }
        }
      },
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

