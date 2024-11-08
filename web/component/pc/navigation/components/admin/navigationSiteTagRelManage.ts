import {useRemoteSelectNavigationSiteCompItem} from "../navigationSiteCompItem";
import {useRemoteSelectNavigationSiteTagCompItem} from "../navigationSiteTagCompItem";

export const pageFormItems = [
  useRemoteSelectNavigationSiteCompItem({props: {}, required: false}),
  useRemoteSelectNavigationSiteTagCompItem({props: {}, required: false}),
]
export const useAddPageFormItems = (props)=>{
  return [

    useRemoteSelectNavigationSiteCompItem({props, required: true}),
    useRemoteSelectNavigationSiteTagCompItem({props, required: true}),
  ]
}

// 更新和添加一致
export const useUpdatePageFormItems = useAddPageFormItems

