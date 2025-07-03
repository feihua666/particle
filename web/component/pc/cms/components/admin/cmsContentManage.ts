import {
  useCascaderCmsChannelCompItem,
  useCascaderCmsContentCategoryCompItem,
  useSelectCmsSiteCompItem
} from "../cmsSiteCompItem";

export const pageFormItems = [
  useSelectCmsSiteCompItem({}),
  useCascaderCmsChannelCompItem({fieldName: 'cmsChannelId', label: '栏目'}),
  useCascaderCmsContentCategoryCompItem({fieldName: 'cmsContentCategoryId', label: '内容分类'}),
      {
        field: {
          name: 'title',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '标题',

          },
          compProps: {
            clearable: true,
            placeholder: '左前缀匹配'
          }
        }
      },
      {
        field: {
          name: 'author',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '作者',

          },
          compProps: {
            clearable: true,
            placeholder: '左前缀匹配'
          }
        }
      },
      {
        field: {
          name: 'original',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '来源',

          },
          compProps: {
            clearable: true,
            placeholder: '左前缀匹配'
          }
        }
      },

      {
        field: {
          name: 'auditStatusDictId',
        },
        element: {
          comp: 'PtDictFrontSelect',
          formItemProps: {
            label: '审核状态',

          },
          compProps: {
            // 字典查询
            dictParam: {groupCode: 'cms_content_audit_status'}
          }
        }
      },

      {
        field: {
          name: 'contentTypeDictId',
        },
        element: {
          comp: 'PtDictFrontSelect',
          formItemProps: {
            label: '内容类型',

          },
          compProps: {
            // 字典查询
            dictParam: {groupCode: 'cms_content_type'}
          }
        }
      },

]
export const addPageFormItems = [

  useSelectCmsSiteCompItem({required: true}),
  useCascaderCmsChannelCompItem({fieldName: 'cmsChannelId', label: '栏目'}),
  useCascaderCmsContentCategoryCompItem({fieldName: 'cmsContentCategoryId', label: '内容分类'}),

      {
        field: {
          name: 'title',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '标题',
            required: true,
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'author',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '作者',
            required: true,
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'original',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '来源',

          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'profile',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '简介',
            tips: '填写一些简单概念介绍，主要是在列表中展示',
            displayBlock: true,

          },
          compProps: {
            clearable: true,
            type: 'textarea',
            rows: 3,
          }
        }
      },



  {
    field: {
      name: 'contentTypeDictId',
    },
    element: {
      comp: 'PtDictFrontSelect',
      formItemProps: {
        label: '内容类型',
        required: true,
      },
      compProps: {
        // 字典查询
        dictParam: {groupCode: 'cms_content_type'}
      }
    }
  },


      {
        field: {
          name: 'imageUrl',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '图片地址',
            tips: '图片地址主要是在列表中展示'

          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'imageDescription',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '图片描述',
            tips: '图片描述可能主要是在内容详情中展示，如：说明图片来源'
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'imageUrl1',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '图片地址1',
            tips: '图片地址主要是在列表中展示'
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'imageDescription1',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '图片描述1',
            tips: '图片描述可能主要是在内容详情中展示，如：说明图片来源'
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'imageUrl2',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '图片地址2',
            tips: '图片地址主要是在列表中展示'
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'imageDescription2',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '图片描述2',
            tips: '图片描述可能主要是在内容详情中展示，如：说明图片来源'
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'templatePath',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '内容模板路径',
            required: true,
            tips: '支持绝对路径和相对路径，相对路径是相对于系统的 classpath:/template-cms'

          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'templateIndex',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '内容模板',
            required: true,
            tips: '相对于栏目模板路径，如：index.html'
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'staticPath',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '内容静态页存放路径',
            tips: '仅支持绝对路径,在页面静态化时静态页面存放路径'
          },
          compProps: {
            clearable: true,
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
      name: 'auditStatusDictId',
    },
    element: {
      comp: 'PtDictFrontSelect',
      formItemProps: {
        label: '审核状态',
        required: true,
      },
      compProps: {
        // 字典查询
        dictParam: {groupCode: 'cms_content_audit_status'}
      }
    }
  },
]

// 更新和添加一致
export const updatePageFormItems = addPageFormItems

