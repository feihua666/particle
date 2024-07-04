export const pageFormItems = [
      {
        field: {
          name: 'tenantUserInviteId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '租户用户邀请id',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'invitedUserId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '被邀请人用户id',
            
          },
          compProps: {
          }
        }
      },
]
export const addPageFormItems = [




      {
        field: {
          name: 'tenantUserInviteId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '租户用户邀请id',
            required: true,
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'invitedUserId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '被邀请人用户id',
            required: true,
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'joinAt',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '用户加入时间',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'remark',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '描述',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },












]

// 更新和添加一致
export const updatePageFormItems = addPageFormItems

