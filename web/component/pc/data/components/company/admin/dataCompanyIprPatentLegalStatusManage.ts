export const pageFormItems = [
      {
        field: {
          name: 'companyIprPatentId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '企业知识产权专利表id',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'legalStatusDictId',
        },
        element: {
          comp: 'PtDictFrontSelect',
          formItemProps: {
            label: '法律状态',
            
          },
          compProps: {
            // 字典查询
            dictParam: {groupCode: '这里填写字典组编码'}
          }
        }
      },
      {
        field: {
          name: 'legalStatusDetail',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '原始法律状态详情',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'legalStatusDate',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '法律状态日期',
            
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
export const addPageFormItems = [




      {
        field: {
          name: 'companyIprPatentId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '企业知识产权专利表id',
            required: true,
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'legalStatusDictId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '法律状态',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'legalStatusDetail',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '原始法律状态详情',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'legalStatusDate',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '法律状态日期',
            
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

