export const pageFormItems = [
      {
        field: {
          name: 'companyVcFinancingId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '企业融资表ID',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'companyVcInvestInstitutionId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '企业投资机构表id',
            
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
          name: 'companyVcFinancingId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '企业融资表ID',
            required: true,
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'companyVcInvestInstitutionId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '企业投资机构表id',
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

