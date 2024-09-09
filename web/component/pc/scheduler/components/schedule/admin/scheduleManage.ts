export const pageFormItems = [
      {
        field: {
          name: 'schedulerName',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '任务计划名称',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'schedulerInstanceId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '任务计划实例id',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
]
