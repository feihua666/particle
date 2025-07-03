export const pageFormItems = [
      {
        field: {
          name: 'cmsSiteId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '站点id',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'cmsContentId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '内容id',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'deviceId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '访问终端设备id',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'ip',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '访问终端设备ip',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'viewAt',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '访问时间',
            
          },
          compProps: {
          }
        }
      },
]
export const addPageFormItems = [




      {
        field: {
          name: 'cmsSiteId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '站点id',
            required: true,
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'cmsContentId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '内容id',
            required: true,
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'deviceId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '访问终端设备id',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'ip',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '访问终端设备ip',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'viewAt',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '访问时间',
            required: true,
          },
          compProps: {
          }
        }
      },












]

// 更新和添加一致
export const updatePageFormItems = addPageFormItems

