import {
    useSelectDynamicDataCategoryCompItem,
    useSelectDynamicDataIndicatorCategoryByDynamicDataCategoryIdCompItem
} from "../../dataCompItem";

export const pageFormItems = [
    useSelectDynamicDataCategoryCompItem({tips: '仅用来过滤指标分类，只选该项无查询过滤效果'}),
    useSelectDynamicDataIndicatorCategoryByDynamicDataCategoryIdCompItem({}),
    {
        field: {
            name: 'uploadFileName',
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '上传文件名称',

            },
            compProps: {
                clearable: true,
                placeholder: '模糊匹配',
            }
        }
    },

]
export const addPageFormItems = [

    {
        field: {
            name: 'dynamicDataIndicatorCategoryId',
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '动态数据指标分类id',
                required: true,
            },
            compProps: {
            }
        }
    },

    {
        field: {
            name: 'uploadFileName',
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '上传文件名称',
                required: true,
            },
            compProps: {
                clearable: true,
            }
        }
    },


    {
        field: {
            name: 'uploadFileUrl',
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '上传文件地址',
                required: true,
            },
            compProps: {
                clearable: true,
            }
        }
    },


    {
        field: {
            name: 'uploadIndicatorNum',
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '上传指标数量',
                required: true,
            },
            compProps: {
            }
        }
    },


    {
        field: {
            name: 'uploadIndicatorDataNum',
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '上传数据数量',
                required: true,
            },
            compProps: {
            }
        }
    },


]

// 更新和添加一致
export const updatePageFormItems = addPageFormItems

