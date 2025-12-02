import {useSelectDynamicDataCategoryCompItem} from "../../dataCompItem";

export const pageFormItems = [
    useSelectDynamicDataCategoryCompItem({}),
    {
        field: {
            name: 'name',
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '动态数据指标分类名称',

            },
            compProps: {
                clearable: true,
                placeholder: '左前缀匹配',
            }
        }
    },

    {
        field: {
            name: 'typeDictId',
        },
        element: {
            comp: 'PtDictFrontSelect',
            formItemProps: {
                label: '动态数据指标分类类型',

            },
            compProps: {
                // 字典查询
                dictParam: {groupCode: 'data_dynamic_data_indicator_category_type'}
            }
        }
    },
]
export const addPageFormItems = [

    useSelectDynamicDataCategoryCompItem({required: true}),


    {
        field: {
            name: 'name',
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '动态数据指标分类名称',
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
            name: 'typeDictId',
        },
        element: {
            comp: 'PtDictFrontSelect',
            formItemProps: {
                label: '动态数据指标分类类型',

            },
            compProps: {
                // 字典查询
                dictParam: {groupCode: 'data_dynamic_data_indicator_category_type'}
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

// 更新和添加一致
export const updatePageFormItems = addPageFormItems

export const importDataFormItems = [

    {
        field: {
            name: 'file',
            value: [],
        },
        element: {
            comp: 'PtUpload',
            formItemProps: {
                label: '上传文件',
                required: true,
                displayBlock: true,
            },
            compProps: {
                drag: true,
                autoUpload: false,
                limit: 1,
                dragTip: '将文件拖到此处或<em>点击上传</em>',
                tipTxt: '只能上传一个文件，请严格按照下载的模板进行填写，并保证第一个sheet为导入数据',
                onExceed: (files,uploadFiles)=>{
                    alert('最多只能选择一个文件,如要重新选择，请先删除之前的文件','error')
                }
            }
        }
    },

]
