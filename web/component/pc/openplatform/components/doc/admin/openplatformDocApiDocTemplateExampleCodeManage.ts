import {useSelectOpenplatformDocApiDocTemplateCompItem} from "../../openplatformDocCompItem";

export const pageFormItems = [
      {
        field: {
          name: 'langDictId',
        },
        element: {
          comp: 'PtDictFrontSelect',
          formItemProps: {
            label: '开发语言',
            
          },
          compProps: {
            // 字典查询
            dictParam: {groupCode: 'develop_language'}
          }
        }
      },
  useSelectOpenplatformDocApiDocTemplateCompItem({}),

]
export const addPageFormItems = [

  {
    field: {
      name: 'langDictId',
    },
    element: {
      comp: 'PtDictFrontSelect',
      formItemProps: {
        label: '开发语言',

      },
      compProps: {
        // 字典查询
        dictParam: {groupCode: 'develop_language'}
      }
    }
  },
  useSelectOpenplatformDocApiDocTemplateCompItem({required: true,tips: '绑定的文档内容模板'}),
  {
    field: {
      name: 'seq',
      value: 10
    },
    element: {
      comp: 'el-input-number',
      formItemProps: {
        label: '排序'
      },
      compProps: {
        clearable: true,
      }
    }
  },





  {
    field: {
      name: 'exampleCode',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '示例代码片段',
        required: true,
        displayBlock: true,
      },
      compProps: {
        type: 'textarea',
        rows: 20,
        clearable: true,
      }
    }
  },





  {
    field: {
      name: 'exampleDownloadUrl',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '示例代码下载地址',
        tips: '暂只支持填写绝对路径的地址，上传后续支持',
        displayBlock: true,
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

