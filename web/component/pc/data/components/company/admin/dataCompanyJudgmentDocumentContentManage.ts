export const pageFormItems = [
      {
        field: {
          name: 'companyJudgmentDocumentId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '裁判文书表id',
            
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
            label: '裁判内容',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'latestHandleAt',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '最后处理时间',
            
          },
          compProps: {
          }
        }
      },
]
export const addPageFormItems = [




      {
        field: {
          name: 'companyJudgmentDocumentId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '裁判文书表id',
            
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
            label: '裁判内容',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'latestHandleAt',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '最后处理时间',
            
          },
          compProps: {
          }
        }
      },












]

// 更新和添加一致
export const updatePageFormItems = addPageFormItems

