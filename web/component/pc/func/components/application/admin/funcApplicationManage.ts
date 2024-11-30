import {list as funcApplicationListApi} from "../../../api/application/admin/funcApplicationAdminApi";
import {treeQueryComps} from "../../../../treeQueryComps";

export const pageFormItems = [
  {
    field: {
      name: 'code',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '应用编码',

      },
      compProps: {
        clearable: true,
        placeholder: '左前缀匹配',
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
        label: '功能应用名称',

      },
      compProps: {
        clearable: true,
        placeholder: '左前缀匹配',
      }
    }
  },

  {
    field: {
      name: 'parentId'
    },
    element: {
      comp: 'PtCascader',
      formItemProps: {
        label: '父级',
      },
      compProps: {
        // 加载数据
        dataMethod: () => { return funcApplicationListApi({})},
        dataMethodResultHandleConvertToTree: true,
      }
    }
  },
  ...treeQueryComps
]
export const addPageFormItems = [
  {
    field: {
      name: 'code',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '应用编码',
        required: true,
      },
      compProps: {
        clearable: true,
        placeholder: '编码唯一',
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
        label: '应用名称',
        required: true,
      },
      compProps: {
        clearable: true,
      }
    }
  },

  {
    field: {
      name: 'parentId',
    },
    element: {
      comp: 'PtCascader',
      formItemProps: {
        label: '父级',
      },
      compProps: {
        clearable: true,
        // 加载数据
        dataMethod: () => { return funcApplicationListApi({})},
        dataMethodResultHandleConvertToTree: true,
      }
    }
  },

  {
    field: {
      name: 'isDisabled',
    },
    element: {
      comp: 'el-switch',
      formItemProps: {
        label: '是否禁用',
        required: true,
      },
      compProps: {
      }
    }
  },

  {
    field: {
      name: 'disabledReason',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '禁用原因',
        required: ({form})=>{
          return form.isDisabled
        }
      },
      compProps: {
        clearable: true,
      }
    }
  },
  {
    field: {
      name: 'isGroup',
    },
    element: {
      comp: 'el-switch',
      formItemProps: {
        label: '是否分组',
        required: true,
      },
      compProps: {
        activeText: '应用组',
        inactiveText: '应用',
      }
    }
  },

  {
    field: {
      name: 'applicationTheme',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '应用主题',

      },
      compProps: {
        clearable: true,
      }
    }
  },


  {
    field: {
      name: 'applicationDefaultRoute',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '默认路由',

      },
      compProps: {
        clearable: true,
      }
    }
  },

  {
    field: {
      name: 'applicationLogoUrl',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '应用logo',
      },
      compProps: {
        clearable: true,
        placeholder: '以 http 开头'
      }
    }
  },

  {
    field: {
      name: 'applicationIconUrl',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '应用图标',
      },
      compProps: {
        clearable: true,
        placeholder: '以 http 开头'
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

  {
    field: {
      name: 'configJson',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '配置json',
        displayBlock: true
      },
      compProps: {
        type: 'textarea',
        rows: 15
      }
    }
  },
]

// 更新和添加一致
export const updatePageFormItems = addPageFormItems

