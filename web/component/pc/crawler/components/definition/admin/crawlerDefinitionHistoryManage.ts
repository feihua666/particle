import {useSelectCrawlerDefinitionCompItem, useSelectCrawlerProjectCompItem} from "../../crawlerCompItem";

export const pageFormItems = [

  useSelectCrawlerProjectCompItem({}),
  useSelectCrawlerDefinitionCompItem({required: true}),
  {
    field: {
      name: 'crawlerDefinitionVersion',
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
      name: 'crawlerDefinitionName',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '定义名称',
        required: true,
      },
      compProps: {
        disabled: true,
      }
    }
  },


  {
    field: {
      name: 'crawlerDefinitionVersion',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '定义版本号',
        required: true,
      },
      compProps: {
        disabled: true,
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



  {
    field: {
      name: 'definitionJson',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '定义规则数据',
        displayBlock: true,
        tips: '以json的形式配置爬虫规则定义，如有疑问可参考相关文档再编写'
      },
      compProps: {
        type: 'textarea',
        rows: 10,
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
        label: '爬虫级配置json',
        displayBlock: true,
        tips: '以json的形式配置参数，以爬虫维度配置爬虫级别的参数，如果和项目参数相同会覆盖项目相同的参数'
      },
      compProps: {
        type: 'textarea',
        rows: 10,
        clearable: true,
      }
    }
  },



]

// 更新和添加一致
export const updatePageFormItems = addPageFormItems

