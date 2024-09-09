export const pageFormItems = [
      {
        field: {
          name: 'schedulerTempTaskRunRecordId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '临时任务运行记录id',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'level',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '日志级别',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
]
export const addPageFormItems = [




      {
        field: {
          name: 'schedulerTempTaskRunRecordId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '临时任务运行记录id',
            required: true,
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'level',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '日志级别',
            required: true,
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'content',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '日志内容',
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

