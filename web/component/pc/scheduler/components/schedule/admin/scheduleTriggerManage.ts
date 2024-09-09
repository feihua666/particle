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
  {
    field: {
      name: 'name',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '触发器名称',
      },
      compProps: {
        clearable: true,
      }
    }
  },
  {
    field: {
      name: 'group',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '触发器组',
      },
      compProps: {
        clearable: true,
      }
    }
  },
]
