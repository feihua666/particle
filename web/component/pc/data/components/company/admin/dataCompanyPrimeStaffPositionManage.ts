export const pageFormItems = [
      {
        field: {
          name: 'companyPrimeStaffId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '企业主要人员表ID',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'positionName',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '职位名称',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'positionDictId',
        },
        element: {
          comp: 'PtDictFrontSelect',
          formItemProps: {
            label: '职位',
            
          },
          compProps: {
            // 字典查询
            dictParam: {groupCode: '这里填写字典组编码'}
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
          name: 'companyPrimeStaffId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '企业主要人员表ID',
            required: true,
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'positionName',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '职位名称',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'positionDictId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '职位',
            
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

