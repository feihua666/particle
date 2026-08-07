export const pageFormItems = [
      {
        field: {
          name: 'crawlerExecutionId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '爬虫执行ID',
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
            placeholder: '左前缀匹配',
          }
        }
      },
      {
        field: {
          name: 'pageUrl',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '页面地址',

          },
          compProps: {
            clearable: true,
            placeholder: '左前缀匹配',
          }
        }
      },
      {
        field: {
          name: 'pageTitle',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '页面标题',
          },
          compProps: {
            clearable: true,
            placeholder: '左前缀匹配',
          }
        }
      },
      {
        field: {
          name: 'statusDictId',
        },
        element: {
          comp: 'PtDictFrontSelect',
          formItemProps: {
            label: '状态',

          },
          compProps: {
            // 字典查询
            dictParam: {groupCode: '这里填写字典组编码'}
          }
        }
      },
]
