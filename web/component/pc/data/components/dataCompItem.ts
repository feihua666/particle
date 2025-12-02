import {list as dynamicTableListApi} from "../api/dynamictable/admin/dynamicTableAdminApi";
import {list as dynamicDataCategoryListApi} from "../api/dynamicdata/admin/dynamicDataCategoryAdminApi";
import {list as dynamicIndicatorCategoryListApi} from "../api/dynamicdata/admin/dynamicDataIndicatorCategoryAdminApi";

export const useSelectDynamicTableCompItem = ({required= false,tips = '',disabled=false})=>{
    return     {
        field: {
            name: 'dynamicTableId'
        },
        element: {
            comp: 'PtSelect',
            formItemProps: {
                label: '动态数据表格',
                required: required,
                tips: tips || undefined
            },
            compProps: {
                disabled: disabled,
                dataMethod: ()=>{
                    // 修改一下返回的数据
                    return dynamicTableListApi({}).then(res=>{
                        let data = res.data.data
                        if (data && data.length > 0) {
                            data.forEach(item => {
                                item.nameNew = item.name + "(" + item.comment + ")"
                            })
                        }
                        return Promise.resolve(res);
                    })
                },
                // 下拉显示昵称
                props: {label: 'nameNew'}
            }
        }
    }
}
export const useSelectDynamicDataCategoryCompItem = ({required= false,tips = '',disabled=false})=>{
    return     {
        field: {
            name: 'dynamicDataCategoryId'
        },
        element: {
            comp: 'PtSelect',
            formItemProps: {
                label: '动态数据分类',
                required: required,
                tips: tips || undefined
            },
            compProps: {
                disabled: disabled,
                dataMethod: ()=>{
                    return dynamicDataCategoryListApi({})
                },
            }
        }
    }
}
export const useSelectDynamicDataIndicatorCategoryCompItem = ({required= false,tips = '',disabled=false})=>{
    return     {
        field: {
            name: 'dynamicDataIndicatorCategoryId'
        },
        element: {
            comp: 'PtSelect',
            formItemProps: {
                label: '动态数据指标分类',
                required: required,
                tips: tips || undefined
            },
            compProps: {
                disabled: disabled,
                dataMethod: ()=>{
                    return dynamicIndicatorCategoryListApi({})
                },
            }
        }
    }
}
export const useSelectDynamicDataIndicatorCategoryByDynamicDataCategoryIdCompItem = ({required= false,tips = '',disabled=false})=>{
    return     {
        field: {
            name: 'dynamicDataIndicatorCategoryId'
        },
        element: {
            comp: 'PtSelect',
            formItemProps: {
                label: '动态数据指标分类',
                required: required,
                tips: tips || undefined
            },
            compProps: ({form,formData}) =>{
                return {
                    disabled: disabled,

                    // 用于在接口查询时动态加载
                    dataMethodParam: {dynamicDataCategoryId: form.dynamicDataCategoryId},
                    // 加载数据
                    dataMethod: ({param}) => {
                        if (!param.dynamicDataCategoryId) {
                            return {data:[]}
                        }
                        return dynamicIndicatorCategoryListApi(param).then(res=>{
                            let data = res.data.data
                            data.forEach(item => {
                                if(item.isGroup){
                                    item.isDisabled = true
                                }
                            })
                            return Promise.resolve(res)
                        });

                    },
                }
            }
        }
    }
}

