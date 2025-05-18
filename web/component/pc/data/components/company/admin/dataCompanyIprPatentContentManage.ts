export const pageFormItems = [
      {
        field: {
          name: 'companyIprPatentId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '企业知识产权专利表id',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'abstractContent',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '原始摘要内容',
            
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
          name: 'companyIprPatentId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '企业知识产权专利表id',
            required: true,
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'abstractContent',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '原始摘要内容',
            
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

