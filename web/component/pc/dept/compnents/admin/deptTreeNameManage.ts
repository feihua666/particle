export const pageFormItems = [
      {
        field: {
          name: 'code',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '部门树名称编码',
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
            label: '部门树名称',
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
            label: '部门树名称编码',
            tips: '编码唯一'
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
            label: '部门树名称',
            required: true,
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

