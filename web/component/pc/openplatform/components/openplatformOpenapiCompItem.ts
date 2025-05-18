import {
    detail,
    detailForUpdate,
    list as openapiListApi,
    listByOpenplatformAppId
} from "../api/openapi/admin/openplatformOpenapiAdminApi.ts";
import {list as providerListApi} from "../api/provider/admin/openplatformProviderAdminApi";

export const useCascaderOpenapiCompItem = ({
                                               fieldName= 'parentId',
                                               required=false,label= '父级',
                                               disableGroup = false,
                                               valueChange = () => {},
    tips='',
                                               labelTips=''
})=>{
    return         {
        field: {
            name: fieldName,
            valueChange
        },
        element: {
            comp: 'PtCascader',
            formItemProps: {
                label: label,
                required: required,
                tips: tips,
                labelTips: labelTips
            },
            compProps: {
                clearable: true,
                // 加载数据
                dataMethod: () => {
                    if (disableGroup) {
                        return openapiListApi({}).then(res=>{
                            let data = res.data.data
                            data.forEach(item => {
                                if(item.isGroup){
                                    item.isDisabled = true
                                }
                            })
                            return Promise.resolve(res)
                        })
                    }else {
                        return openapiListApi({})
                    }

                },
                dataMethodResultHandleConvertToTree: true,
            }
        }
    }
}
export const useCascaderOpenapiByOpenplatformAppIdCompItem = ({
                                               fieldName= 'parentId',
                                               required=false,label= '父级',
                                               disableGroup = false,
                                               valueChange = () => {},
                                               tips='',
                                               labelTips=''
                                           })=>{
    return         {
        field: {
            name: fieldName,
            valueChange
        },
        element: {
            comp: 'PtCascader',
            formItemProps: {
                label: label,
                required: required,
                tips: tips,
                labelTips: labelTips
            },
            compProps: ({form,formData})=>{
                return {
                    clearable: true,
                    // 用于在接口查询时动态加载
                    dataMethodParam: {id: form.openplatformAppId},
                    // 加载数据
                    dataMethod: ({param}) => {
                        if (!param.id) {
                            return {data:[]}
                        }
                        if (disableGroup) {
                            return listByOpenplatformAppId(param).then(res=>{
                                let data = res.data.data
                                data.forEach(item => {
                                    if(item.isGroup){
                                        item.isDisabled = true
                                    }
                                })
                                return Promise.resolve(res)
                            });
                        }else {
                            return listByOpenplatformAppId(param);
                        }

                    },
                    dataMethodResultHandleConvertToTree: true,
                }
            }
        }
    }
}

export const useSelectOpenapiProviderCompItem = (
    {
        required=true,label= '可用供应商配置',
        tips='',
        labelTips='',
        valueChange = ()=>{},
        multiple = false,
        openplatformOpenapiId,
    }
)=>{
    return         {
        field: {
            name: 'providerConfigJsonId',
            valueChange,
            value: multiple ? []: null
        },
        element: {
            comp: 'PtSelect',
            formItemProps: {
                label: label,
                required: required,
                tips: tips,
                labelTips: labelTips
            },
            compProps:({form})=> {
                return {
                    clearable: true,
                    multiple,
                    // 添加参数，以实现联动
                    dataMethodParam: {id: openplatformOpenapiId},
                    // 加载数据,param = dataMethodParam
                    dataMethod: ({param})=>{
                        if(!param.id){
                            return {
                                data: []
                            }
                        }
                        return detail(param).then(res => {
                            let data = res.data.data
                            let providerConfigJsonStr = data.providerConfigJson
                            if (!providerConfigJsonStr) {
                                return Promise.resolve({
                                    data: []
                                })
                            }
                            let providerConfigJsonArray = JSON.parse(providerConfigJsonStr)
                            providerConfigJsonArray.forEach(item => {
                                if (item.providerApiVersion) {
                                    item.name = item.openplatformProviderName + '(' + item.openplatformProviderCode + ')(' + item.providerApiVersion + ')';
                                }else{
                                    item.name = item.openplatformProviderName + '(' + item.openplatformProviderCode + ')';
                                }
                            })
                            return Promise.resolve({
                                data: providerConfigJsonArray
                            })
                        })
                    }
                }
            }
        }
    }
}
