import {useSelectWorkflowProjectCompItem} from "../../workflowCompItem";

export const pageFormItems = [

  useSelectWorkflowProjectCompItem({}),
      {
        field: {
          name: 'name',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '工作流名称',

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
          label: '工作流名称',
          required: true,
          tips: '一个字符串标识，能看懂就行'
        },
        compProps: {
          clearable: true,
        }
      }
    },

    {
      field: {
        name: 'coverImageUrl',
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '封面图地址',
          tips: '封面图地址主要在列表中展示'
        },
        compProps: {
          clearable: true,
        }
      }
    },

    ...(isUpdate ? [useSelectWorkflowProjectCompItem({ required: true ,disabled: true,tips: '不支持修改归属项目，接口也不支持'})] : [useSelectWorkflowProjectCompItem({ required: true })]),

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

