export const pageFormItems = [
      {
        field: {
          name: 'companyCaseFilingId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '立案信息表id',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'partyName',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '当事人名称',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'isPartyNaturalPerson',
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否法人为自然人',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'partyCompanyId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '当事人公司id',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'partyCompanyPersonId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '当事人个人id',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'partyRoleDictId',
        },
        element: {
          comp: 'PtDictFrontSelect',
          formItemProps: {
            label: '当事人角色',
            
          },
          compProps: {
            // 字典查询
            dictParam: {groupCode: '这里填写字典组编码'}
          }
        }
      },
      {
        field: {
          name: 'partyDescription',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '当事人描述信息',
            
          },
          compProps: {
            clearable: true,
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
          name: 'companyCaseFilingId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '立案信息表id',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'partyName',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '当事人名称',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'isPartyNaturalPerson',
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否法人为自然人',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'partyCompanyId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '当事人公司id',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'partyCompanyPersonId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '当事人个人id',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'partyRoleDictId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '当事人角色',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'partyDescription',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '当事人描述信息',
            
          },
          compProps: {
            clearable: true,
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

