import {page as navigationSiteTagPageApi} from "../api/admin/navigationSiteTagAdminApi";

export const remoteSelectNavigationSiteTagProps = {
    // 加载数据初始化参数,路由传参
    navigationSiteTagId: {
        type: String
    },
    // 加载数据初始化参数,路由传参
    navigationSiteTagName: String
}

/**
 * 远程搜索网站标签表单配置项
 * 属性中必须有 navigationSiteTagId 和 navigationSiteTagName 两个属性
 * @param props
 */
export const useRemoteSelectNavigationSiteTagCompItem = ({props,required})=>{
  return   {
        field: {
            name: 'navigationSiteTagId',
            value: props.navigationSiteTagId
        },
        element: {
            comp: 'PtSelect',
                formItemProps: {
                label: '网站标签',
                required: required
            },
            compProps: ()=> {
                let paramsExist = !!(props.navigationSiteTagId && props.navigationSiteTagName)
                let r = {
                    placeholder: '输入网站标签名称搜索',
                    disabled: paramsExist,
                    // 给定默认数据
                    dataMethod: ()=> {
                        if(paramsExist){
                            return {data: [{
                                    id: props.navigationSiteTagId,
                                    name: props.navigationSiteTagName,
                                }]}
                        }
                        return {data: []}
                    },
                    // 路由中没有数据，开启远程搜索
                    remote: !paramsExist,
                    remoteMethod: (query: string) => {
                        if(!query){
                            return {data: []}
                        }
                        return navigationSiteTagPageApi({name: query})
                    },
                    // 下拉显示名称
                    props: {label: 'name'}
                }// r

                return r
            }
        }
    }
}
