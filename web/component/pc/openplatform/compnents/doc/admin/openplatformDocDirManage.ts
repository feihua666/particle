import {
    useCascaderOpenplatformDocDirCompItem,
    useSelectOpenplatformDocDirNameCompItem
} from "../../openplatformDocCompItem";
import {treeQueryComps} from "../../../../treeQueryComps";

export const pageFormItems = [
    {
        field: {
            name: 'name',
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '名称',

            },
            compProps: {
                clearable: true,
                placeholder: '左前缀匹配'
            }
        }
    },
    {
        field: {
            name: 'nameSimple',
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '简称',

            },
            compProps: {
                clearable: true,
                placeholder: '左前缀匹配'
            }
        }
    },
    useSelectOpenplatformDocDirNameCompItem({}),
    useCascaderOpenplatformDocDirCompItem({}),

    ...treeQueryComps
]
export const addPageFormItems = [


    {
        field: {
            name: 'name',
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '名称',
                required: true,
            },
            compProps: {
                clearable: true,
            }
        }
    },


    {
        field: {
            name: 'nameSimple',
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '简称',

            },
            compProps: {
                clearable: true,
            }
        }
    },

    useSelectOpenplatformDocDirNameCompItem({required: true}),

    {
        field: {
            name: 'seq',
            value: 10
        },
        element: {
            comp: 'el-input-number',
            formItemProps: {
                label: '排序'
            },
            compProps: {
                clearable: true,
            }
        }
    },

    useCascaderOpenplatformDocDirCompItem({}),

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

