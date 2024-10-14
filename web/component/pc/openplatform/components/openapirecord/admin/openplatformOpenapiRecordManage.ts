import {useSelectAppCompItem} from "../../openplatformAppCompItem";
import {useRemoteSelectUserCompItem} from "../../../../user/components/userCompItem";
import {useCascaderOpenapiCompItem} from "../../openplatformOpenapiCompItem";
import {useSelectCrmCustomerCompItem} from "../../../../crm/components/crmCompItem";

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
  useSelectCrmCustomerCompItem({fieldName: 'customerId',label: '客户'}),

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
            displayBlock:true,
            tips: '最原始的参数，所有位置的参数，下面的是将这里的拆分展示,注意：请求参数MD5值是以该值计算的MD5'
          },
          compProps: {
            type: 'textarea',
            readonly: true,
            rows: 5
          }
        }
      },
  {
    field: {
      name: 'requestFormParam',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '表单参数',
        displayBlock:true
      },
      compProps: {
        type: 'textarea',
        readonly: true,
        rows: 5
      }
    }
  },
  {
    field: {
      name: 'requestPathParam',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '路径参数',
        displayBlock:true
      },
      compProps: {
        type: 'textarea',
        readonly: true,
        rows: 5
      }
    }
  },
  {
    field: {
      name: 'requestBodyParam',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '请求体参数',
        displayBlock:true
      },
      compProps: {
        type: 'textarea',
        readonly: true,
        rows: 5
      }
    }
  },
  {
    field: {
      name: 'requestQueryString',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '查询字符串参数',
        displayBlock:true
      },
      compProps: {
        readonly: true,
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

