import {useCascaderReportSegmentTemplateCompItem} from "../../reportSegmentTemplateCompItem";
import {treeQueryComps} from "../../../../treeQueryComps";
import {useCascaderReportReportapiCompItem} from "../../reportReportapiCompItem";

export const pageFormItems = [
      {
        field: {
          name: 'code',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '编码',

          },
          compProps: {
            clearable: true,
            placeholder: '左前缀匹配',
          }
        }
      },
      {
        field: {
          name: 'name',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '名称',

          },
          compProps: {
            clearable: true,
            placeholder: '左前缀匹配',
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
            label: '接口地址',

          },
          compProps: {
            clearable: true,
            placeholder: '左前缀匹配',
          }
        }
      },
  useCascaderReportSegmentTemplateCompItem({fieldName: 'reportSegmentTemplateId',label: '报告片段模板'}),
  useCascaderReportReportapiCompItem({}),
  ...treeQueryComps
]
export const addPageFormItems = [

      {
        field: {
          name: 'code',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '编码',
            tips: '编码唯一'
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'name',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '名称',
            required: true,
            tips: '表示一个可读的名称，仅展示使用'
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'isGroup',
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '分组/接口',
            required: true,
          },
          compProps: {
            activeText: '分组',
            inactiveText: '接口',
          }
        }
      },


      {
        field: {
          name: 'permissions',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '接口权限码',
            tips: '表示该接口使用什么权限如：user:report'
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
            label: '接口地址',
            required: ({form})=> !form.isGroup,
            tips: '类型为接口时必填,接口地址唯一，请以/开头，前端调用地址需要前面拼接 /api/re。开放接口前面拼接 /openapi/re'
          },
          compProps: {
            clearable: true,
          }
        }
      },
  useCascaderReportSegmentTemplateCompItem({
    fieldName: 'reportSegmentTemplateId',
    label: '报告片段模板',
    tips: '指定该报告接口使用什么模板，一般模板输出为文本类文件，如果其它输出格式，可以配置后置处理脚本进行处理',
    required: ({form})=> !form.isGroup,
  }),


      {
        field: {
          name: 'inParamExampleConfigJson',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '入参示例',
            tips: '仅展示使用，提示应该怎么入参调用接口,一般为一个json字符串，post请求，application/json请求内容类型，模板中 global.body 为请求参数',
            displayBlock: true,
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
      name: 'postScript'
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '后置处理脚本',
        displayBlock: true,
        labelTips: '后置处理脚本',
        tips: '仅支持groovy脚本，可对模板渲染的结果进一步处理,可用句柄 reportSegmentTemplateRenderParam 为请求参数对象、reportSegmentTemplateRenderResult 渲染结果对象，包括templateNameContentResult=名称渲染结果文本、templateContentResult=内容渲染结果文本、templateNameContentResultFile=名称渲染结果文件句柄、renderContext=渲染上下文，默认处理为 templateNameContentResultFile转换为绝对路径作为url，即：如果获取设置不输出文件则返回空'
      },
      compProps: {
        type: 'textarea',
        rows: 10,
        clearable: true,
      }
    }
  },
  useCascaderReportReportapiCompItem({tips: '仅类型为接口可以添加父级'}),

      {
        field: {
          name: 'remark',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '描述',
            tips: '一些备注说明'
          },
          compProps: {
            clearable: true,
          }
        }
      },
]

// 更新和添加一致
export const updatePageFormItems = addPageFormItems

