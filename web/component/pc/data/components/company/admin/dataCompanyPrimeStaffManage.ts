export const pageFormItems = [
      {
        field: {
          name: 'companyId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '企业表ID',

          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'staffName',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '姓名',

          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'staffCompanyPersonId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '个人表ID',

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
          name: 'companyId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '企业表ID',
            required: true,
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'staffName',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '姓名',

          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'staffCompanyPersonId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '个人表ID',

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

