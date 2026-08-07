import {
  useSelectCrawlerDefinitionCompItem,
  useSelectCrawlerDefinitionHistoryCompItem, useSelectCrawlerProjectCompItem
} from "../../crawlerCompItem.ts";

export const pageFormItems = [

  useSelectCrawlerProjectCompItem({}),
  useSelectCrawlerDefinitionCompItem({required: true}),
  useSelectCrawlerDefinitionHistoryCompItem({}),
  {
    field: {
      name: 'statusDictId',
    },
    element: {
      comp: 'PtDictFrontSelect',
      formItemProps: {
        label: '执行状态',
      },
      compProps: {
        // 字典查询
        dictParam: {groupCode: 'crawler_execution_status'}
      }
    }
  },
  {
    field: {
      name: 'triggerTypeDictId',
    },
    element: {
      comp: 'PtDictFrontSelect',
      formItemProps: {
        label: '触发方式',

      },
      compProps: {
        // 字典查询
        dictParam: {groupCode: 'crawler_execution_trigger_type'}
      }
    }
  },

]

/**
 * 执行页面表单
 */
export const executePageFormItems = [

  {
    field: {
      name: 'crawlerDefinitionName',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '定义名称',
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
      },
      compProps: {
        disabled: true,
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
        tips: '只读，以json的形式配置爬虫规则定义，如有疑问可参考相关文档再编写'
      },
      compProps: {
        type: 'textarea',
        rows: 10,
        clearable: true,
        disabled: true,
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
        tips: '只读，以json的形式配置参数，以爬虫维度配置爬虫级别的参数，如果和项目参数相同会覆盖项目相同的参数'
      },
      compProps: {
        type: 'textarea',
        rows: 10,
        clearable: true,
        disabled: true,
      }
    }
  },
  {
    field: {
      name: 'crawlRuntimeOptionsJson',
      value: '{\n' +
          '  "deriver": {\n' +
          '    "driverType": "PLAYWRIGHT"\n' +
          '  },\n' +
          '  "browser": {\n' +
          '    "playwright": {\n' +
          '      "browserType": "FIREFOX",\n' +
          '      "isHeadless": false\n' +
          '    }\n' +
          '  }\n' +
          '}',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '运行时选项json',
        displayBlock: true,
        tips: '需要指定运行时选项，除非项目启动时已经配置了默认配置'
      },
      compProps: {
        type: 'textarea',
        rows: 10,
      }
    }
  },
  {
    field: {
      name: 'param',
      value: '{}',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '运行参数json',
        displayBlock: true,
        tips: '如果pipeline中需要运行参数，则需要添加对应的参数'
      },
      compProps: {
        type: 'textarea',
        rows: 10,
        clearable: true,
      }
    }
  },

]
