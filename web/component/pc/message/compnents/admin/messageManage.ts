export const pageFormItems = [
  {
    field: {
      name: 'title',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '消息标题',

      },
      compProps: {
        clearable: true,
        placeholder: '左前缀匹配',
      }
    }
  },
  {
    field: {
      name: 'typeDictId',
    },
    element: {
      comp: 'PtDictFrontSelect',
      formItemProps: {
        label: '消息分类',

      },
      compProps: {
        // 字典查询
        dictParam: {groupCode: 'message_type'}
      }
    }
  },
  {
    field: {
      name: 'sendStatusDictId',
    },
    element: {
      comp: 'PtDictFrontSelect',
      formItemProps: {
        label: '发送状态',

      },
      compProps: {
        // 字典查询
        dictParam: {groupCode: 'message_send_status'}
      }
    }
  },
  {
    field: {
      name: 'isSystem',
      value: false
    },
    element: {
      comp: 'el-switch',
      formItemProps: {
        label: '',

      },
      compProps: {
        // 字典查询
        activeText: '系统消息',
        inactiveText: '业务消息',
      }
    }
  },

]
export const addPageFormItems = [

  {
    field: {
      name: 'title',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '消息标题',
        required: true,
      },
      compProps: {
        clearable: true,
      }
    }
  },

  {
    field: {
      name: 'typeDictId',
    },
    element: {
      comp: 'PtDictFrontSelect',
      formItemProps: {
        label: '消息分类',
      },
      compProps: {
        // 字典查询
        dictParam: {groupCode: 'message_type'}
      }
    }
  },

  {
    field: {
      name: 'content',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '消息内容',
        required: true,
        displayBlock: true
      },
      compProps: {
        clearable: true,
        type: 'textarea',
        rows: 15,
      }
    }
  },
]

// 更新和添加一致
export const updatePageFormItems = addPageFormItems

