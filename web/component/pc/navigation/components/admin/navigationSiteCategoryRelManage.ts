import {useCascaderNavigationCategoryCompItem} from "../navigationCategoryCompItem";
import {useRemoteSelectNavigationSiteCompItem} from "../navigationSiteCompItem";

export const pageFormItems = [
  useRemoteSelectNavigationSiteCompItem({props: {}, required: false}),
  useCascaderNavigationCategoryCompItem({fieldName: 'navigationCategoryId', label: '导航分类'}),

];
export const useAddPageFormItems = (props) =>{
  return [


    useRemoteSelectNavigationSiteCompItem({props: props, required: true}),
    useCascaderNavigationCategoryCompItem({fieldName: 'navigationCategoryId', label: '导航分类', required: true}),

    {
      field: {
        name: 'seq',
        value: 10
      },
      element: {
        comp: 'el-input-number',
        formItemProps: {
          label: '排序',
          required: true
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

