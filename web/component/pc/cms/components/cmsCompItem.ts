import {list as cmsSiteListApi} from "../api/admin/cmsSiteAdminApi";
import {list as cmsChannelListApi} from "../api/admin/cmsChannelAdminApi";
import {list as cmsContentCategoryListApi} from "../api/admin/cmsContentCategoryAdminApi";
import {page as cmsContentPageApi} from "../api/admin/cmsContentAdminApi";
import {list as cmsTemplateListApi} from "../api/admin/cmsTemplateAdminApi";

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
export const remoteSelectRelatedCmsContentProps = {
    // 加载数据初始化参数,路由传参
    relatedCmsContentId: {
        type: String
    },
    // 加载数据初始化参数,路由传参
    relatedCmsContentTitle: String
}
export const remoteSelectCmsContentProps = {
    // 加载数据初始化参数,路由传参
    cmsContentId: {
        type: String
    },
    // 加载数据初始化参数,路由传参
    cmsContentTitle: String
}
/**
 * 远程搜索内容表单配置项
 * 属性中必须有 cmsContentId 和 cmsContentTitle 两个属性
 * @param props
 */
export const useRemoteSelectCmsContentCompItem = ({props,
                                                      required = false,
                                                      fieldName='cmsContentId',
                                                      propCmsContentIdFieldName='cmsContentId',
                                                      propCmsContentTitleFieldName='cmsContentTitle',
                                                      label='内容',tips='根据内容标题搜索'})=>{
    return   {
        field: {
            name: fieldName,
            value: props[propCmsContentIdFieldName]
        },
        element: {
            comp: 'PtSelect',
            formItemProps: {
                label: label,
                required: required,
                tips: tips
            },
            compProps: ({form,formData})=> {
                let paramsExist = !!(props[propCmsContentIdFieldName] && props[propCmsContentTitleFieldName])
                let r = {
                    placeholder: '输入内容标题搜索',
                    disabled: paramsExist,
                    // 给定默认数据
                    dataMethod: ()=> {
                        if(paramsExist){
                            return {data: [{
                                    id: props[propCmsContentIdFieldName],
                                    title: props[propCmsContentTitleFieldName],
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
export const useCascaderCmsTemplateCompItem = ({fieldName= 'parentId',required=false,label= '父级',tips= ''})=>{
    return         {
        field: {
            name: fieldName
        },
        element: {
            comp: 'PtCascader',
            formItemProps: {
                label: label,
                required: required,
                tips: tips
            },
            compProps: {
                clearable: true,
                // 加载数据
                dataMethod: () => { return cmsTemplateListApi({isDirectory: true})},
                dataMethodResultHandleConvertToTree: true,
            }
        }
    }
}
