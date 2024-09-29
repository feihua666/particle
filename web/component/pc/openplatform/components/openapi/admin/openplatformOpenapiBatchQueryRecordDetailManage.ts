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
            dictParam: {groupCode: '这里填写字典组编码'}
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
