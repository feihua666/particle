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
      name: 'constraintContentTypeDictId',
    },
    element: {
      comp: 'PtDictFrontSelect',
      formItemProps: {
        label: '约束条件内容类型',
        required: ({form}) => !form.isCustom,
        tips: '在自定义数据、内置片段时，填写无效，内置片段时建议填写原生sql'
      },
      compProps: {
        // 字典查询
        dictParam: {groupCode: 'data_scope_constraint_content_type'}
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
            label: '约束条件内容',
            required: ({form}) => !form.isCustom,
            tips: '一般由开发人员填写，用于数据范围约束,在自定义数据时可以用来指定一个字段，否则默认为id，如果保持默认请清空',
            labelTips: '内置参数：</br>本人创建 = selfCreatedBy</br>\n' +
                '本部门下的人创建 = selfDeptCreateBy</br>\n' +
                '本部门及以下部门下的人创建 = selfDeptAndSubDeptCreateBy</br>\n' +
                '已分配的部门数据范围下的人创建 = selfAssignedDeptCreateBy</br>\n' +
                '\n' +
                '本人 = selfPrefix + {column_id}</br>\n' +
                '本部门下的人 = selfDeptPrefix + {column_id}</br>\n' +
                '本部门及以下部门下的人 = selfDeptAndSubDeptPrefix + {column_id}</br>\n' +
                '已分配的部门数据范围下的人 = selfAssignedDeptPrefix + {column_id}</br>'
          },
          compProps: {
            type: 'textarea',
            rows: 5,
            clearable: true,
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

