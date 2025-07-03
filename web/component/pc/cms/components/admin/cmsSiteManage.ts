export const pageFormItems = [
      {
        field: {
          name: 'code',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '站点编码',
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
            label: '站点名称',

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
            label: '站点编码',
            tips: '编码唯一，用来唯一标识站点，注意需要保持url命令规则'
          },
          compProps: {
            clearable: true,
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
            label: '站点名称',
            required: true,
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'domain',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '站点域名',
            tips: '如：www.example.com、example.com'

          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'path',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '站点访问上下文路径',
            tips: '请以 / 开头'

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
            label: '站点模板路径',
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
            label: '站点首页模板',
            required: true,
            tips: '相对于站点模板路径，如：index.html'
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
            label: '站点静态页存放路径',
            tips: '仅支持绝对路径,在页面静态化时静态页面存放路径'

          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'isPrimeSite',
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否主站点',
            required: true,
            tips: '在相同的域名下可以有多个站点，但只能有一个主站点'
          },
          compProps: {
          }
        }
      },


]

// 更新和添加一致
export const updatePageFormItems = addPageFormItems

