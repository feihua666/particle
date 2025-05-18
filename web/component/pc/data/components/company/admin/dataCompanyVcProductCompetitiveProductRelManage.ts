export const pageFormItems = [
      {
        field: {
          name: 'companyVcProductId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '企业融资产品表ID',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'companyVcCompetitiveProductId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '企业竞品id',
            
          },
          compProps: {
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
          name: 'companyVcProductId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '企业融资产品表ID',
            required: true,
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'companyVcCompetitiveProductId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '企业竞品id',
            required: true,
          },
          compProps: {
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

