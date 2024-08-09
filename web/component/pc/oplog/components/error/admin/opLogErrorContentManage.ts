export const pageFormItems = [
      {
        field: {
          name: 'opLogErrorId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '异常日志id',
            
          },
          compProps: {
          }
        }
      },
]
export const viewPageFormItems = [
      {
        field: {
          name: 'content',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '异常内容',
            required: true,
          },
          compProps: {
            type: 'textarea',
            rows: 40,
            clearable: true,
          }
        }
      },

]


