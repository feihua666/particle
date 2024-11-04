export const pageFormItems = [
      {
        field: {
          name: 'name',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '网站名称',

          },
          compProps: {
            clearable: true,
            placeholder: '左前缀匹配',
          }
        }
      },
      {
        field: {
          name: 'title',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '网站标题',

          },
          compProps: {
            clearable: true,
            placeholder: '左前缀匹配',
          }
        }
      },

      {
        field: {
          name: 'statusDictId',
        },
        element: {
          comp: 'PtDictFrontSelect',
          formItemProps: {
            label: '状态',
          },
          compProps: {
            // 字典查询
            dictParam: {groupCode: 'navigation_submit_status'}
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
            label: '网站名称',
            required: true,
            tips: '如：百度',
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'title',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '网站标题',
            required: true,
            tips: '如：百度一下，你就知道',
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'url',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '网站地址',
            required: true,
            tips: '如：https://baidu.com',
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
            tips: '备注内容',
          },
          compProps: {
            clearable: true,
          }
        }
      },


]

// 更新和添加一致
export const updatePageFormItems = [

  {
    field: {
      name: 'name',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '网站名称',
        required: true,
        tips: '如：百度',
      },
      compProps: {
        clearable: true,
      }
    }
  },


  {
    field: {
      name: 'title',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '网站标题',
        required: true,
        tips: '如：百度一下，你就知道',
      },
      compProps: {
        clearable: true,
      }
    }
  },


  {
    field: {
      name: 'url',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '网站地址',
        required: true,
        tips: '如：https://baidu.com',
      },
      compProps: {
        clearable: true,
      }
    }
  },
  {
    field: {
      name: 'statusDictId',
    },
    element: {
      comp: 'PtDictFrontSelect',
      formItemProps: {
        label: '状态',
      },
      compProps: {
        // 字典查询
        dictParam: {groupCode: 'navigation_submit_status'}
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
        tips: '备注内容',
      },
      compProps: {
        clearable: true,
      }
    }
  },


]

