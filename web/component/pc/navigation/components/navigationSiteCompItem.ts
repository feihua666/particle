import {page as navigationSitePageApi} from "../api/admin/navigationSiteAdminApi";

export const remoteSelectNavigationSiteProps = {
    // 加载数据初始化参数,路由传参
    navigationSiteId: {
        type: String
    },
    // 加载数据初始化参数,路由传参
    navigationSiteName: String
}
/**
 * 远程搜索导航网站表单配置项
 * 属性中必须有 navigationSiteId 和 navigationSiteName 两个属性
 * @param props
 */
export const useRemoteSelectNavigationSiteCompItem = ({props,required})=>{
    return   {
        field: {
            name: 'navigationSiteId',
            value: props.navigationSiteId
        },
        element: {
            comp: 'PtSelect',
            formItemProps: {
                label: '导航网站',
                required: required
            },
            compProps: ()=> {
                let paramsExist = !!(props.navigationSiteId && props.navigationSiteName)
                let r = {
                    placeholder: '输入导航网站名称搜索',
                    disabled: paramsExist,
                    // 给定默认数据
                    dataMethod: ()=> {
                        if(paramsExist){
                            return {data: [{
                                    id: props.navigationSiteId,
                                    name: props.navigationSiteName,
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
                        return navigationSitePageApi({name: query})
                    },
                    // 下拉显示名称
                    props: {label: 'name'}
                }// r

                return r
            }
        }
    }
}
