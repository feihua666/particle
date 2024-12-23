import {list as openapiListApi, listByOpenplatformAppId} from "../api/openapi/admin/openplatformOpenapiAdminApi.ts";

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
