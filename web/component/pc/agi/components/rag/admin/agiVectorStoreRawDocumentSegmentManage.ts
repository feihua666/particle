import {useRemoteSelectAgiVectorStoreRawDocumentCompItem} from "../../agiCompItem";

export const pageFormItems = [

  useRemoteSelectAgiVectorStoreRawDocumentCompItem({props: {}}),
      {
        field: {
          name: 'content',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '名称',

          },
          compProps: {
            clearable: true,
          }
        }
      }
]
export const useAddPageFormItems = (props) => {
  return [
    useRemoteSelectAgiVectorStoreRawDocumentCompItem({props,required: true}),
    {
      field: {
        name: 'content',
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '片段内容',
          required: true,
          displayBlock: true
        },
        compProps: {
          clearable: true,
          type: 'textarea',
          rows: 25
        }
      }
    },
    {
      field: {
        name: 'metadataJson',
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '元数据信息',
          required: true,
          displayBlock: true,
          tips: '元数据信息，这是一个json对象注意不是数组，要求key和value均不能为null，至少有一条元数据key和value信息'
        },
        compProps: {
          clearable: true,
          type: 'textarea',
          rows: 10
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
          tip: '一些备注信息'

        },
        compProps: {
          clearable: true,
        }
      }
    },

  ]
}

// 更新和添加一致
export const useUpdatePageFormItems = useAddPageFormItems

