export const pageFormItems = [
      {
        field: {
          name: 'name',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '姓名',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'idNo',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '证号',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'mobile',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '手机号',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'mobileOperatorDictId',
        },
        element: {
          comp: 'PtDictFrontSelect',
          formItemProps: {
            label: '手机号运营商',
            
          },
          compProps: {
            // 字典查询
            dictParam: {groupCode: '这里填写字典组编码'}
          }
        }
      },
      {
        field: {
          name: 'mobile1',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '手机号1',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'mobile1OperatorDictId',
        },
        element: {
          comp: 'PtDictFrontSelect',
          formItemProps: {
            label: '手机号1运营商',
            
          },
          compProps: {
            // 字典查询
            dictParam: {groupCode: '这里填写字典组编码'}
          }
        }
      },
      {
        field: {
          name: 'mobile2',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '手机号2',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'mobile2OperatorDictId',
        },
        element: {
          comp: 'PtDictFrontSelect',
          formItemProps: {
            label: '手机号2运营商',
            
          },
          compProps: {
            // 字典查询
            dictParam: {groupCode: '这里填写字典组编码'}
          }
        }
      },
      {
        field: {
          name: 'latestHandleAt',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '最后处理时间',
            
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
            label: '姓名',
            required: true,
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'idNo',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '证号',
            required: true,
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'mobile',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '手机号',
            required: true,
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'mobileOperatorDictId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '手机号运营商',
            required: true,
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'mobile1',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '手机号1',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'mobile1OperatorDictId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '手机号1运营商',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'mobile2',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '手机号2',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'mobile2OperatorDictId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '手机号2运营商',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'latestHandleAt',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '最后处理时间',
            
          },
          compProps: {
          }
        }
      },












]

// 更新和添加一致
export const updatePageFormItems = addPageFormItems

