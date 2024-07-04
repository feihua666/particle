import {list as openapiListApi} from "../api/openapi/admin/openplatformOpenapiAdminApi.ts";
export const useCascaderOpenapiCompItem = ({
                                               fieldName= 'parentId',
                                               required=false,label= '父级',
                                               disableGroup = false,
                                               valueChange = () => {}
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
                required: required
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