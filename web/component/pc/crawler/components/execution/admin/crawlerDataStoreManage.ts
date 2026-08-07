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
          name: 'crawlerExecutionId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '爬虫执行实例ID',

          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'crawlerDefinitionId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '爬虫定义ID',

          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'crawlerDefinitionHistoryId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '执行时使用的版本ID',

          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'crawlerDefinitionName',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '爬虫定义名称',

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
          name: 'crawlerExecutionId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '爬虫执行实例ID',

          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'crawlerDefinitionId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '爬虫定义ID',

          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'crawlerDefinitionHistoryId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '执行时使用的版本ID',

          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'crawlerDefinitionName',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '爬虫定义名称',
            required: true,
          },
          compProps: {
            clearable: true,
          }
        }
      },












]

// 更新和添加一致
export const updatePageFormItems = addPageFormItems

