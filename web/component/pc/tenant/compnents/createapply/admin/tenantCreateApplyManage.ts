export const pageFormItems = [
      {
        field: {
          name: 'name',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '租户名称',
            
          },
          compProps: {
            clearable: true,
            placeholder: '左前缀匹配',
          }
        }
      },
      {
        field: {
          name: 'tenantTypeDictId',
        },
        element: {
          comp: 'PtDictFrontSelect',
          formItemProps: {
            label: '租户类型字典id',
            
          },
          compProps: {
            // 字典查询
            dictParam: {groupCode: '这里填写字典组编码'}
          }
        }
      },
      {
        field: {
          name: 'applyUserId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '申请用户',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'auditStatusDictId',
        },
        element: {
          comp: 'PtDictFrontSelect',
          formItemProps: {
            label: '审核状态',
            
          },
          compProps: {
            // 字典查询
            dictParam: {groupCode: '这里填写字典组编码'}
          }
        }
      },
      {
        field: {
          name: 'auditUserId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '审核用户id',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'appliedTenantId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '审核通过后创建的租户id',
            
          },
          compProps: {
          }
        }
      },
]
export const addPageFormItems = [




      {
        field: {
          name: 'name',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '租户名称',
            required: true,
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'contactUserName',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '联系人姓名',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'contactUserEmail',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '联系人邮箱',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'contactUserPhone',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '联系人电话',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'tenantTypeDictId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '租户类型字典id',
            required: true,
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'applyUserId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '申请用户',
            required: true,
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'auditStatusDictId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '审核状态',
            required: true,
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'auditStatusComment',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '审核意见',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'auditUserId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '审核用户id',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'appliedTenantId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '审核通过后创建的租户id',
            
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

