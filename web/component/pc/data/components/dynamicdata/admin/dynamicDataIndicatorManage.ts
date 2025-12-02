import {
    useSelectDynamicDataCategoryCompItem,
    useSelectDynamicDataIndicatorCategoryByDynamicDataCategoryIdCompItem, useSelectDynamicDataIndicatorCategoryCompItem
} from "../../dataCompItem";

export const pageFormItems = [
    useSelectDynamicDataCategoryCompItem({}),
    useSelectDynamicDataIndicatorCategoryByDynamicDataCategoryIdCompItem({}),
    {
        field: {
            name: 'name',
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '指标名称',

            },
            compProps: {
                clearable: true,
                placeholder: '左前缀匹配',

            }
        }
    },
]
export const useAddPageFormItems = ({isForAdd =  true})=>{
    return [

        useSelectDynamicDataCategoryCompItem({required: true,disabled: !isForAdd}),
        useSelectDynamicDataIndicatorCategoryByDynamicDataCategoryIdCompItem({required: true,disabled: !isForAdd}),


        {
            field: {
                name: 'name',
            },
            element: {
                comp: 'el-input',
                formItemProps: {
                    label: '指标名称',
                    required: true,
                },
                compProps: {
                    clearable: true,
                }
            }
        },


        {
            field: {
                name: 'isDisabled',
                value: false,
            },
            element: {
                comp: 'el-switch',
                formItemProps: {
                    label: '是否禁用',
                    required: true,
                },
                compProps: {
                    activeText: '禁用',
                    inactiveText: '启用',
                }
            }
        },


        {
            field: {
                name: 'remark',
            },
            element: {
                comp: 'el-input',
                formItemProps: {
                    label: '备注信息',
                    required: true,
                },
                compProps: {
                    clearable: true,
                }
            }
        },
    ]
}

// 更新和添加一致
export const useUpdatePageFormItems = useAddPageFormItems

