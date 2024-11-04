export const pageFormItems = [
      {
        field: {
          name: 'code',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '部署编码',

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
            label: '部署名称',

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
          name: 'code',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '部署编码',
            tips: '编码唯一，指定一个编码，保留字段'
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
            label: '部署名称',
            required: true,
            tips: '一个有意义的名称'
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'frontDomain',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '域名地址',
            tips: '建议不填写，这样可以适用所有域名地址的部署，如：http://example.com'
          },
          compProps: {
            clearable: true,
          }
        }
      },

      {
        field: {
          name: 'frontContextPath',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '子一级路径',
            tips: '一般部署到域名根路径，不要填写，否则需要填写域名下的子一级路径,如：/nav'
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'frontSubContextPath',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '子二级路径',
            tips: '一般部署到域名根路径，不要填写，否则需要填写域名下的子二级路径,如：/nav'
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'isPureStaticDeploy',
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '纯静态部署',
            required: true,
          },
          compProps: {
            activeText: '纯静态部署',
            inactiveText: '半静态部署',
          }
        }
      },
  {
    field: {
      name: 'deployPath',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '部署路径',
        tips: '部署到本地的哪个路径，绝对目录路径，如：/data/nav'
      },
      compProps: {
        clearable: true,
      }
    }
  },
  {
    field: {
      name: 'deployPostGroovyScript',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '部署后脚本',
        tips: '在部署完成后执行脚本，如：可以提交到git等',
        displayBlock: true,
      },
      compProps: {
        type: 'textarea',
        rows: 15,
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

export const deployPageFormItems = [
  {
    field: {
      name: 'isIncrementDeploy',
      value: true,
    },
    element: {
      comp: 'el-switch',
      formItemProps: {
        label: '增量部署',
      },
      compProps: {
        clearable: true,
        activeText: '增量部署',
        inactiveText: '全量部署',
      }
    }
  }
  ]
