export const pageFormItems = [
      {
        field: {
          name: 'code',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '配置编码',

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
            label: '配置名称',

          },
          compProps: {
            clearable: true,
            placeholder: '左前缀匹配',
          }
        }
      },

      {
        field: {
          name: 'tag',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '标签',

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
            label: '配置编码',
            required: true,
            tips: '编码唯一，建议分前缀使用，如：user.admin.xxx'
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
            label: '配置名称',
            required: true,
            tips: '一个可读的有意义的名字，一般不作为程序处理依据'
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'value',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '配置值',
            required: true,
            displayBlock: true,
            tips: '参数配置值，支持json格式，一般由使用程序自行处理,如果是单值注意换行问题'
          },
          compProps: {
            clearable: true,
            type: 'textarea',
            rows: 10
          }
        }
      },


      {
        field: {
          name: 'isBuiltIn',
          value: false,
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否内置',
            required: true,
            tips: '是否系统内置参数，一般内置参数不要删除',
          },
          compProps: {
            activeText: '系统内容',
            inactiveText: '用户自定义',
          }
        }
      },


      {
        field: {
          name: 'isDisabled',
          value: false,
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否禁用',
            required: true,
            tips: '禁用后将获取不到该值，一般由使用程序自行处理',
          },
          compProps: {
            activeText: '禁用',
            inactiveText: '启用',
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
          name: 'tag',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '标签',
            tips: '标签可用于归类等用途，如果程序处理建议使用英文，没有什么限制'
          },
          compProps: {
            clearable: true,
            placeholder: '如：user'
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
            tips: '描述，可填写一些备忘或说明信息'
          },
          compProps: {
            clearable: true,
          }
        }
      },












]

// 更新和添加一致
export const updatePageFormItems = addPageFormItems

