export const pageFormItems = [
      {
        field: {
          name: 'dataScopeId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '数据范围id',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'dataId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '自定义数据id',
            
          },
          compProps: {
          }
        }
      },
]
export const addPageFormItems = [




      {
        field: {
          name: 'dataScopeId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '数据范围id',
            required: true,
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'dataId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '自定义数据id',
            required: true,
          },
          compProps: {
          }
        }
      },












]

// 更新和添加一致
export const updatePageFormItems = addPageFormItems

