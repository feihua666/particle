import {useSelectAdminComponentCompItem} from "../componentAdminCompItem";

export const pageFormItems = [
    useSelectAdminComponentCompItem({fieldName: 'componentId',label: '源组件'}),
    useSelectAdminComponentCompItem({fieldName: 'dependComponentId',label: '依赖组件'}),
]
export const addPageFormItems = [



    useSelectAdminComponentCompItem({fieldName: 'componentId',label: '源组件',required: true}),
    useSelectAdminComponentCompItem({fieldName: 'dependComponentId',label: '依赖组件',required: true}),


    {
        field: {
            name: 'isRequired',
            value: true,
        },
        element: {
            comp: 'el-switch',
            formItemProps: {
                label: '是否必需',
                required: true,
                tips: '是否一定需要依赖的组件，如果不是必须的，那就是可选的',
            },
            compProps: {
                activeText: '必需',
                inactiveText: '可选',
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
                label: '备注',

            },
            compProps: {
                clearable: true,
            }
        }
    },












]

// 更新和添加一致
export const updatePageFormItems = addPageFormItems

