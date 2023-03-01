
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
                title: '编码全局唯一，用来唯一标识一个数据源'
            },
            compProps: {
                clearable: true,
                placeholder: '编码唯一如：110',
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
            name: 'driverClassName'
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '驱动类名',
                required: true,
                displayBlock: true,
                tips: 'com.mysql.cj.jdbc.Driver'
            },
            compProps: {
                clearable: true,
                placeholder: '如：com.mysql.cj.jdbc.Driver'
            }
        }
    },
    {
        field: {
            name: 'url'
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '地址',
                required: true,
                tips: '例：jdbc:mysql://localhost/particle_test?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&serverTimezone=GMT%2B8',
                displayBlock: true
            },
            compProps: {
                clearable: true,
                placeholder: '如：jdbc:mysql://localhost/particle_test',
            }
        }
    },
    {
        field: {
            name: 'username'
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '用户',
                required: true
            },
            compProps: {
                clearable: true,
                placeholder: '如：root'
            }
        }
    },
    {
        field: {
            name: 'password'
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '密码',
                required: true
            },
            compProps: {
                clearable: true,
                placeholder: '如：xxxx'
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