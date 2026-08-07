import {useSelectCrawlerProjectCompItem} from "../../../../crawler/components/crawlerCompItem.ts";

export const pageFormItems = [
  useSelectCrawlerProjectCompItem({}),
  {
    field: {
      name: 'name',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '爬虫名称',

      },
      compProps: {
        clearable: true,
        placeholder: '左前缀匹配'
      }
    }
  },
]
export const useAddPageFormItems = ({isUpdate}:{isUpdate: boolean})=>{
  return [
    {
      field: {
        name: 'name',
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '爬虫名称',
          required: true,
          tips: '一个字符串标识，能看懂就行'
        },
        compProps: {
          clearable: true,
        }
      }
    },

    ...(isUpdate ? [useSelectCrawlerProjectCompItem({ required: true ,disabled: true,tips: '不支持修改归属项目，接口也不支持'})] : [useSelectCrawlerProjectCompItem({ required: true })]),

    {
      field: {
        name: 'remark',
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '描述',
          tips: '写点什么'
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

