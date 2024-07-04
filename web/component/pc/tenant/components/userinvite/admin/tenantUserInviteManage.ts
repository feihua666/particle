export const pageFormItems = [
      {
        field: {
          name: 'inviteCode',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '邀请码',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'isExpired',
          value: false
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否过期',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'inviterUserId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '邀请人用户id',
            
          },
          compProps: {
          }
        }
      },
]
export const addPageFormItems = [




      {
        field: {
          name: 'inviteCode',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '邀请码',
            required: true,
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'maxInviteCount',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '最大邀请人数',
            required: true,
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'invitedCount',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '已邀请人数',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'isExpired',
          value: false
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否过期',
            required: true,
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'expiredReason',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '过期原因',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'expireAt',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '到期时间',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'inviterUserId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '邀请人用户id',
            required: true,
          },
          compProps: {
          }
        }
      },












]

// 更新和添加一致
export const updatePageFormItems = addPageFormItems

