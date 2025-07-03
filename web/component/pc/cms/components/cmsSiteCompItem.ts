import {list as cmsSiteListApi} from "../api/admin/cmsSiteAdminApi";
import {list as cmsChannelListApi} from "../api/admin/cmsChannelAdminApi";
import {list as cmsContentCategoryListApi} from "../api/admin/cmsContentCategoryAdminApi";
import {page as cmsContentPageApi} from "../api/admin/cmsContentAdminApi";

export const useSelectCmsSiteCompItem = ({fieldName= 'cmsSiteId',required=false,label= '站点'})=>{
    return         {
        field: {
            name: fieldName
        },
        element: {
            comp: 'PtSelect',
            formItemProps: {
                label: label,
                required: required
            },
            compProps: {
                clearable: true,
                // 加载数据
                dataMethod: () => { return cmsSiteListApi({})},
            }
        }
    }
}

export const useCascaderCmsChannelCompItem = ({fieldName= 'parentId',required=false,label= '父级'})=>{
    return         {
        field: {
            name: fieldName
        },
        element: {
            comp: 'PtCascader',
            formItemProps: {
                label: label,
                required: required
            },
            compProps: ({form,formData}) => {
                return {
                    // 用于在接口查询时动态加载
                    dataMethodParam: {cmsSiteId: form.cmsSiteId},
                    clearable: true,
                    // 加载数据
                    dataMethod: ({param}) => { return cmsChannelListApi(param)},
                    dataMethodResultHandleConvertToTree: true,
                }
            }
        }
    }
}
export const useCascaderCmsContentCategoryCompItem = ({fieldName= 'parentId',required=false,label= '父级'})=>{
    return         {
        field: {
            name: fieldName
        },
        element: {
            comp: 'PtCascader',
            formItemProps: {
                label: label,
                required: required
            },
            compProps: ({form,formData})=>{
                return {
                    // 用于在接口查询时动态加载
                    dataMethodParam: {cmsSiteId: form.cmsSiteId,cmsChannelId: form.cmsChannelId},
                    clearable: true,
                    // 加载数据
                    dataMethod: ({param}) => { return cmsContentCategoryListApi(param)},
                    dataMethodResultHandleConvertToTree: true,
                }
            }
        }
    }
}
/**
 * 远程搜索内容表单配置项
 * 属性中必须有 cmsContentId 和 cmsContentTitle 两个属性
 * @param props
 */
export const useRemoteSelectCmsContentCompItem = ({props,required})=>{
    return   {
        field: {
            name: 'cmsContentId',
            value: props.cmsContentId
        },
        element: {
            comp: 'PtSelect',
            formItemProps: {
                label: '内容',
                required: required
            },
            compProps: ({form,formData})=> {
                let paramsExist = !!(props.cmsContentId && props.cmsContentTitle)
                let r = {
                    placeholder: '输入内容名称搜索',
                    disabled: paramsExist,
                    // 给定默认数据
                    dataMethod: ()=> {
                        if(paramsExist){
                            return {data: [{
                                    id: props.cmsContentId,
                                    title: props.cmsContentTitle,
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
                        return cmsContentPageApi({title: query,cmsSiteId: form.cmsSiteId})
                    },
                    // 下拉显示标题
                    props: {label: 'title'}
                }// r

                return r
            }
        }
    }
}
