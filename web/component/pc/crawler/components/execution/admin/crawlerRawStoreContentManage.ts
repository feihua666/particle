export const pageFormItems = [
      {
        field: {
          name: 'crawlerRawStoreId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '爬虫原始数据存储ID',

          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'content',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '存储内容文本',

          },
          compProps: {
            clearable: true,
          }
        }
      },
]
export const addPageFormItems = [




      {
        field: {
          name: 'crawlerRawStoreId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '爬虫原始数据存储ID',
            required: true,
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'content',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '存储内容文本',

          },
          compProps: {
            clearable: true,
          }
        }
      },

]

// 更新和添加一致
export const updatePageFormItems = addPageFormItems

