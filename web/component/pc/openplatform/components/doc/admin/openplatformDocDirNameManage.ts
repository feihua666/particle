export const pageFormItems = [
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
        label: '编码',
        required: true,
        tips: '编码唯一，以区分不同的目录'
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

