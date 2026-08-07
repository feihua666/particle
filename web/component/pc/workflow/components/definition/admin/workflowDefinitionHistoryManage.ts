import {useSelectWorkflowDefinitionCompItem, useSelectWorkflowProjectCompItem} from "../../workflowCompItem";

export const pageFormItems = [

  useSelectWorkflowProjectCompItem({}),
  useSelectWorkflowDefinitionCompItem({required: true}),
      {
        field: {
          name: 'workflowDefinitionVersion',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '版本号',

          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'isPublish',
        },
        element: {
          comp: 'PtSelect',
          formItemProps: {
            label: '是否发布',
          },
          compProps: {
            dataMethod: () => {
              return {
                data: [
                  {id: 'true',name: '已发布'},
                  {id: 'false',name: '未发布'},
                ]
              }
            }
          }
        }
      },
]
export const addPageFormItems = [




      {
        field: {
          name: 'workflowDefinitionId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '工作流定义id',
            required: true,
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'workflowDefinitionVersion',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '定义版本号',
            required: true,
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'graphDataJson',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '流程图数据',

          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'configJson',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '工作流级配置json',

          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'isPublish',
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否发布',
            required: true,
          },
          compProps: {
          }
        }
      },












]

// 更新和添加一致
export const updatePageFormItems = addPageFormItems

