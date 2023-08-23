import {useSelectAppCompItem} from "../../openplatformAppCompItem";
import {useCascaderOpenapiCompItem} from "../../openplatformOpenapiCompItem";
import {useSelectOpenapiFeeCompItem} from "../../openplatformOpenapiFeeCompItem";
import {useSelectProviderCompItem} from "../../openplatformProviderCompItem";

export const pageFormItems = [
  useSelectAppCompItem({}),
  useCascaderOpenapiCompItem({
    fieldName: 'openplatformOpenapiId',
    label: '开放接口',
    disableGroup: true
  }),
  useSelectOpenapiFeeCompItem({})
]
export const useAddPageFormItems = ({props = {}}) => {
  return [
    useSelectAppCompItem({
      required: true,
    }),
    useCascaderOpenapiCompItem({
      fieldName: 'openplatformOpenapiId',
      label: '开放接口',
      required: true,
      disableGroup: true,
      valueChange({form,formData,newValue,oldValue}){

      }
    }),
    useSelectOpenapiFeeCompItem({
      tips: '如果配置将覆盖app管理中的默认计费规则配置'
    }),
    useSelectProviderCompItem({
      tips: '如果供应商有多个，可以选择指定一个进行调用',
      openplatformOpenapiIdLimit: true,
    }),
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
}

// 更新和添加一致
export const useUpdatePageFormItems = useAddPageFormItems

