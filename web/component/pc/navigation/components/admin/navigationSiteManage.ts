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
          name: 'url',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '网站地址',

          },
          compProps: {
            clearable: true,
            placeholder: '精准查询',
          }
        }
      },
  {
    field: {
      name: 'feeSituationDictId',
    },
    element: {
      comp: 'PtDictFrontSelect',
      formItemProps: {
        label: '收费情况',
      },
      compProps: {
        // 字典查询
        dictParam: {groupCode: 'fee_situation'}
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
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'logoUrl',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '网站logo图标地址',
            tips: '支持http开头的地址、图片base64编码'
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
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'screenshotUrl',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '网站截屏地址',

          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'screenshotThumbnailUrl',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '网站截屏缩略图地址',

          },
          compProps: {
            clearable: true,
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
            label: '简短内容',
            displayBlock: true
          },
          compProps: {
            clearable: true,
            type: 'textarea',
            rows: 7
          }
        }
      },


      {
        field: {
          name: 'contentDetail',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '详细内容',
            displayBlock: true
          },
          compProps: {
            clearable: true,
            type: 'textarea',
            rows: 15
          }
        }
      },

  {
    field: {
      name: 'feeSituationDictId',
    },
    element: {
      comp: 'PtDictFrontSelect',
      formItemProps: {
        label: '收费情况',
      },
      compProps: {
        // 字典查询
        dictParam: {groupCode: 'fee_situation'}
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
            tips: '写一些备注信息'
          },
          compProps: {
            clearable: true,
          }
        }
      },

]

// 更新和添加一致
export const updatePageFormItems = addPageFormItems

