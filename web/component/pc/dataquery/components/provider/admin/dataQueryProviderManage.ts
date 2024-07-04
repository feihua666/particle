export const pageFormItems = [
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
        placeholder: '左前缀匹配',
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
      name: 'isDisabled',
      value: false
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
      name: 'disabledReason'
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '禁用原因',
        required: ({form}) => form.isDisabled == true

      },
      compProps: {
        clearable: true,
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
        label: '姓名',

      },
      compProps: {
        clearable: true,
      }
    }
  },
  {
    field: {
      name: 'email',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '邮箱',

      },
      compProps: {
        clearable: true,
      }
    }
  },


  {
    field: {
      name: 'mobile',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '手机号',

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
]

// 更新和添加一致
export const updatePageFormItems = addPageFormItems

