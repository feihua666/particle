export const pageFormItems = [

      {
        field: {
          name: 'executeStatusDictId',
        },
        element: {
          comp: 'PtDictFrontSelect',
          formItemProps: {
            label: '执行状态',

          },
          compProps: {
            // 字典查询
            dictParam: {groupCode: 'open_platform_batch_query_execute_status'}
          }
        }
      },

      {
        field: {
          name: 'traceId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: 'traceId',

          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'spanId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: 'spanId',

          },
          compProps: {
            clearable: true,
          }
        }
      },

]
