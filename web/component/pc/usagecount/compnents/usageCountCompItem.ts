import {list as usageCountDefineListApi} from "../api/admin/usageCountDefineAdminApi";
import {list as usageCountConfigListApi} from "../api/admin/usageCountConfigAdminApi";

export const useCascaderUsageCountDefineCompItem = ({fieldName= 'parentId',required=false,label= '父级',disableGroup = false})=>{
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
            compProps: {
                clearable: true,
                // 加载数据
                dataMethod: () => {
                    if (disableGroup) {
                        return usageCountDefineListApi({}).then(res=>{
                            let data = res.data.data
                            data.forEach(item => {
                                if(item.isGroup){
                                    item.isDisabled = true
                                }
                            })
                            return Promise.resolve(res)
                        })
                    }else {
                        return usageCountDefineListApi({})
                    }

                },
                dataMethodResultHandleConvertToTree: true,
            }
        }
    }
}
export const useSelectUsageCountConfigCompItem = ({fieldName= 'usageCountConfigId',required=false,label= '使用次数配置'})=>{
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
                dataMethod: () => { return usageCountConfigListApi({})},
            }
        }
    }
}