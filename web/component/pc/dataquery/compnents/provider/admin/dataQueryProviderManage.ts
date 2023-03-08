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
      name: 'contactUserName',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '联系人姓名',

      },
      compProps: {
        clearable: true,
      }
    }
  },
  {
    field: {
      name: 'contactUserEmail',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '联系人邮箱',

      },
      compProps: {
        clearable: true,
      }
    }
  },


  {
    field: {
      name: 'contactUserPhone',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '联系人电话',

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

