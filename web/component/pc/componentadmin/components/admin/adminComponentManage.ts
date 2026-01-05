export const pageFormItems = [
      {
        field: {
          name: 'code',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '组件英文名称',

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
            label: '组件中文名称',

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
            label: '组件英文名称',
            required: true,
              tips: '请输入英文名称，如：area、user',
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
            label: '组件中文名称',
            required: true,
              tips: '请输入中文名称，如：区域、用户',
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
            label: '组件路径',
            required: true,
              tips: '路径相对于项目而言，如：component/area、component/user',
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
            label: '备注',
              tips: '请输入备注，如：用于区域管理、用户管理',

          },
          compProps: {
            clearable: true,
          }
        }
      },












]

// 更新和添加一致
export const updatePageFormItems = addPageFormItems

