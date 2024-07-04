import {useSelectDataConstraintDataObjectCompItem} from "../dataconstraintCompItem";

export const pageFormItems = [
      {
        field: {
          name: 'code',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '编码',
            
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
            label: '名称',
            
          },
          compProps: {
            clearable: true,
            placeholder: '左前缀匹配',
          }
        }
      },
  useSelectDataConstraintDataObjectCompItem({props: {}}),

]
export const addPageFormItems = [

      {
        field: {
          name: 'code',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '编码',
            required: true,
            tips: '编码唯一，用来唯一标识数据范围',
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
            label: '名称',
            required: true,
            tips: '一个可读的文本',
          },
          compProps: {
            clearable: true,
          }
        }
      },

    useSelectDataConstraintDataObjectCompItem({required: true}),

  {
    field: {
      name: 'isCustom',
    },
    element: {
      comp: 'el-switch',
      formItemProps: {
        label: '自定义数据',
        required: true,
        tips: '自定义数据范围，添加或修改后一般需要在列表中单独分配自定义的数据',
      },
      compProps: {
        activeText: '是',
        inactiveText: '否',
      }
    }
  },
      {
        field: {
          name: 'constraintContent',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '约束条件',
            required: ({form}) => !form.isCustom,
            tips: '一般由开发人员填写，用于数据范围约束'
          },
          compProps: {
            clearable: true,
          }
        }
      },

      {
        field: {
          name: 'isForAdd',
          value: true
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '用于添加',
            required: true,
          },
          compProps: {
            activeText: '是',
            inactiveText: '否',
          }
        }
      },


      {
        field: {
          name: 'isForDelete',
          value: true
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '用于删除',
            required: true,
          },
          compProps: {
            activeText: '是',
            inactiveText: '否',
          }
        }
      },


      {
        field: {
          name: 'isForUpdate',
          value: true
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '用于修改',
            required: true,
          },
          compProps: {
            activeText: '是',
            inactiveText: '否',
          }
        }
      },


      {
        field: {
          name: 'isForQuery',
          value: true
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '用于查询',
            required: true,
          },
          compProps: {
            activeText: '是',
            inactiveText: '否',
          }
        }
      },


      {
        field: {
          name: 'isForOther',
          value: true
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '用于其它',
            required: true,
            tips: '用于除增、删、改、查的其它场景，比如：执行等'
          },
          compProps: {
            activeText: '是',
            inactiveText: '否',
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

