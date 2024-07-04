import {list as providerListApi} from "../api/provider/admin/openplatformProviderAdminApi.ts";
export const useSelectProviderCompItem = (
    {
        fieldName= 'openplatformProviderId',
        required=false,label= '供应商',
        tips='',
        labelTips='',
        valueChange = ()=>{},
        multiple = false,
        openplatformOpenapiIdLimit = false
    }
)=>{
    return         {
        field: {
            name: fieldName,
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
                    dataMethodParam: {openplatformOpenapiId: form.openplatformOpenapiId},
                    // 加载数据,param = dataMethodParam
                    dataMethod: ({param})=>{
                        if (openplatformOpenapiIdLimit) {
                            if(!form.openplatformOpenapiId){
                                form.openplatformProviderId = null
                                return {
                                    data: []
                                }
                            }
                        }

                        // 加参数是为了兼容应用添加接口时，根据选择的接口过滤供应商
                        return providerListApi(param).then(res => {
                            let data = res.data.data
                            if (openplatformOpenapiIdLimit) {
                                if(data.length == 0){
                                    form.openplatformProviderId = null
                                }
                            }
                            return Promise.resolve(res)
                        })
                    }
                }
            }
        }
    }
}