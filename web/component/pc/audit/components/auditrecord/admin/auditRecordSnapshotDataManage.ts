export const pageFormItems = [
      {
        field: {
          name: 'auditRecordId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '审核记录id',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'dataId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '数据id',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'snapshotJson',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '快照内容json',
            
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
          name: 'auditRecordId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '审核记录id',
            required: true,
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'dataId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '数据id',
            required: true,
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'snapshotJson',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '快照内容json',
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

