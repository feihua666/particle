import {useCascaderTrackingPageCompItem} from "../trackingCompItem";

export const pageFormItems = [
  {
    field: {
      name: 'code',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '页面编码',

      },
      compProps: {
        clearable: true,
        placeholder: '左前缀匹配'
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
        label: '页面名称',

      },
      compProps: {
        clearable: true,
        placeholder: '左前缀匹配'
      }
    }
  },
  {
    field: {
      name: 'pageVersion',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '页面版本',

      },
      compProps: {
        clearable: true,
      }
    }
  },
  {
    field: {
      name: 'groupFlag',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '分组标识',
      },
      compProps: {
        clearable: true,
        placeholder: '左前缀匹配'
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
        label: '页面编码',
        required: true,
      },
      compProps: {
        clearable: true,
        placeholder: '编码唯一'
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
        label: '页面名称',
        required: true,
      },
      compProps: {
        clearable: true,
      }
    }
  },


  {
    field: {
      name: 'groupFlag',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '分组标识',
        required: true,
      },
      compProps: {
        clearable: true,
        placeholder: '如：miniPro表示小程序'
      }
    }
  },

  {
    field: {
      name: 'imageUrl',
    },
    element: {
      comp: 'PtUploadSingleImage',
      formItemProps: {
        label: '页面图片',
        required: true,
        labelTips: '页面截图上传上来，可以生成热力图使用'
      },
      compProps: {
        clearable: true,
      }
    }
  },


  {
    field: {
      name: 'absoluteUrl',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '页面访问地址',
        required: true,
        displayBlock: true
      },
      compProps: {
        clearable: true,
        placeholder: '页面绝对地址或者路由如：/admin/userAddPage'
      }
    }
  },


  {
    field: {
      name: 'pathMemo',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '页面访问说明',
        required: true,
        displayBlock: true
      },
      compProps: {
        clearable: true,
        placeholder: '需填写如何能够进入到该页面，如：1.先点击菜单2.点击xxxx'
      }
    }
  },


  {
    field: {
      name: 'pageVersion',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '页面版本',
        required: true,
      },
      compProps: {
        clearable: true,
        placeholder: '一般指客户端版本，如：0.0.2'
      }
    }
  },



  {
    field: {
      name: 'seq',
      value: 1000
    },
    element: {
      comp: 'el-input-number',
      formItemProps: {
        label: '排序'
      },
      compProps: {
        clearable: true,
      }
    }
  },






  useCascaderTrackingPageCompItem({}),

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

