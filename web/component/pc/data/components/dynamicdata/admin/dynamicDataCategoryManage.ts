export const pageFormItems = [
    {
        field: {
            name: 'name',
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '数据类型名称',
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
                label: '动态数据分类类型',

            },
            compProps: {
                // 字典查询
                dictParam: {groupCode: 'data_dynamic_data_category_type'}
            }
        }
    },
]
export const addPageFormItems = [

    {
        field: {
            name: 'name',
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '数据类型名称',
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
                label: '动态数据分类类型',

            },
            compProps: {
                // 字典查询
                dictParam: {groupCode: 'data_dynamic_data_category_type'}
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

