import {useSelectDynamicTableCompItem} from "../../dataCompItem";

export const pageFormItems = [
    useSelectDynamicTableCompItem({}),
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
            name: 'dynamicTableId',
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '动态数据表格id',

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
            name: 'uploadTableFieldNum',
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '上传字段数量',
                required: true,
            },
            compProps: {
            }
        }
    },
    {
        field: {
            name: 'uploadTableDataNum',
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

