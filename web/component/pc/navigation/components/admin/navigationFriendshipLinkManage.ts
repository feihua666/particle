export const pageFormItems = [
      {
        field: {
          name: 'name',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '网站名称',

          },
          compProps: {
            clearable: true,
            placeholder: '左前缀匹配',
          }
        }
      },
      {
        field: {
          name: 'title',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '网站标题',

          },
          compProps: {
            clearable: true,
            placeholder: '左前缀匹配',
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
            label: '网站名称',
            required: true,
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'title',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '网站标题',
            required: true,
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'logoUrl',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: 'logo地址',
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'url',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '网站地址',
            required: true,
          },
          compProps: {
            clearable: true,
          }
        }
      },

      {
        field: {
          name: 'isPublished',
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否已发布',
            required: true,
          },
          compProps: {
            activeText: '已发布',
            inactiveText: '未发布',
          }
        }
      },


      {
        field: {
          name: 'unpublishedReason',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '下架原因',

          },
          compProps: {
            clearable: true,
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

