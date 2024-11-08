import {treeQueryComps} from "../../../treeQueryComps";
import {useCascaderRoleCompItem} from "../roleCompItem";

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

    useCascaderRoleCompItem({}),
    ...treeQueryComps
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
                title: '编码全局唯一，用来唯一标识一个角色'
            },
            compProps: {
                clearable: true,
                placeholder: '编码唯一如：110',
            }
        }
    },
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
                placeholder: '如：管理员',
            }
        }
    },
    {
        field: {
            name: 'isDisabled',
            value: false
        },
        element: {
            comp: 'el-switch',
            formItemProps: {
                label: '是否禁用'
            },
            compProps: {
                activeText: '禁用',
                inactiveText: '启用',
            }
        }
    },
    {
        field: {
            name: 'disabledReason'
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '禁用原因',
                required: ({form}) => form.isDisabled == true

            },
            compProps: {
                clearable: true,
            }
        }
    },
    useCascaderRoleCompItem({}),
    {
        field: {
            name: 'seq',
            value: 1000
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
