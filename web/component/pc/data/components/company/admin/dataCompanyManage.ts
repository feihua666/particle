export const pageFormItems = [
      {
        field: {
          name: 'name',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '企业名称',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'uscc',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '统一社会信用代码',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'regNo',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '注册号',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'orgCode',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '组织机构代码',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'enName',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '英文名称',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'parentId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '父级id',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'latestUpdateAt',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '最后更新时间',
            
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
            label: '企业名称',
            required: true,
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'uscc',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '统一社会信用代码',
            required: true,
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'regNo',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '注册号',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'orgCode',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '组织机构代码',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'enName',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '英文名称',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'parentId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '父级id',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'latestUpdateAt',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '最后更新时间',
            
          },
          compProps: {
          }
        }
      },












]

// 更新和添加一致
export const updatePageFormItems = addPageFormItems

