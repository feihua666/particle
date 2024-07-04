
export const pageFormItems = [
    {
        field: {
            name: 'code'
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '编码'
            },
            compProps: {
                clearable: true,
                placeholder: '左前缀匹配'
            }
        }
    },
    {
        field: {
            name: 'name'
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '名称'
            },
            compProps: {
                clearable: true,
                placeholder: '左前缀匹配'
            }
        }
    },
]

export const addPageFormItems = [
    {
        field: {
            name: 'code',
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '编码',
                required: true,
                title: '编码全局唯一，用来唯一标识一个功能项'
            },
            compProps: {
                clearable: true,
                placeholder: '如：user_code'
            }
        }
    },
    {
        field: {
            name: 'name'
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '名称',
                required: true
            },
            compProps: {
                clearable: true,
            }
        }
    },
    {
        field: {
            name: 'remark'
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '描述'
            },
            compProps: {
                clearable: true,
            }
        }
    },
]

// 更新和添加一致
export const updatePageFormItems = addPageFormItems