export const pageFormItems = [
      {
        field: {
          name: 'userId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '用户id',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'deptTreeId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '部门树id',
            
          },
          compProps: {
          }
        }
      },
]
export const addPageFormItems = [




      {
        field: {
          name: 'userId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '用户id',
            required: true,
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'deptTreeId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '部门树id',
            required: true,
          },
          compProps: {
          }
        }
      },












]

// 更新和添加一致
export const updatePageFormItems = addPageFormItems

