import {resetPasswordCompItems} from "../userCompItem";
import {componentEnabled} from "../../../../../common/config/componentsConfig";
import {useCascaderDeptCompItem} from "../../../dept/compnents/deptCompItem";

export const pageFormItems = [
    {
        field: {
            name: 'name'
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '姓名'
            },
            compProps: {
                clearable: true,
                placeholder: '左前缀匹配'
            }
        }
    },
    {
        field: {
            name: 'nickname'
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '昵称'
            },
            compProps: {
                clearable: true,
                placeholder: '左前缀匹配'
            }
        }
    },
    {
        field: {
            name: 'serialNo'
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '编号'
            },
            compProps: {
                clearable: true,
            }
        }
    },
    {
        field: {
            name: 'genderDictId'
        },
        element: {
            comp: 'PtDictFrontSelect',
            formItemProps: {
                label: '性别',
            },
            compProps: {
                clearable: true,
                // 字典查询
                dictParam: {groupCode: 'gender'}
            }
        }
    },
    {
        field: {
            name: 'categoryDictId'
        },
        element: {
            comp: 'PtDictFrontSelect',
            formItemProps: {
                label: '分类',
            },
            compProps: {
                clearable: true,
                // 字典查询
                dictParam: {groupCode: 'user_category'}
            }
        }
    },
    {
        field: {
            name: 'sourceFromDictId'
        },
        element: {
            comp: 'PtDictFrontSelect',
            formItemProps: {
                label: '来源',
            },
            compProps: {
                clearable: true,
                // 字典查询
                dictParam: {groupCode: 'user_source_from'}
            }
        }
    },
    componentEnabled('dept') ? useCascaderDeptCompItem(
        {
            fieldName: 'deptId',
            required: false,
            label: '部门'
        }
    ) : null,
]

export const addPageFormItems = [
    {
        field: {
            name: 'avatar'
        },
        element: {
            comp: 'PtUploadAvatar',
            formItemProps: {
                label: '头像'
            },
            compProps: {
            }
        }
    },
    {
        field: {
            name: 'nickname'
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '昵称',
                required: true,
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
                label: '姓名'
            },
            compProps: {
                clearable: true,
            }
        }
    },

    {
        field: {
            name: 'serialNo'
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '编号'
            },
            compProps: {
                clearable: true,
                placeholder: '编号全局唯一'
            }
        }
    },

    {
        field: {
            name: 'isVirtual',
            value: false
        },
        element: {
            comp: 'el-switch',
            formItemProps: {
                label: '虚拟用户'
            },
            compProps: {
                activeText: '虚拟用户',
                inactiveText: '真实用户',
            }
        }
    },
    {
        field: {
            name: 'genderDictId'
        },
        element: {
            comp: 'PtDictFrontSelect',
            formItemProps: {
                label: '性别',
                required: ({form})=> !form.isVirtual,
            },
            compProps: {
                // 字典查询
                dictParam: {groupCode: 'gender'}
            }
        }
    },
    {
        field: {
            name: 'isLock',
            value: false
        },
        element: {
            comp: 'el-switch',
            formItemProps: {
                label: '是否锁定'
            },
            compProps: {
                activeText: '锁定',
                inactiveText: '正常',
            }
        }
    },
    {
        field: {
            name: 'lockReason'
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '锁定原因',
                required: ({form}) => form.isLock == true
            },
            compProps: {
                clearable: true,
            }
        }
    },
    {
        field: {
            name: 'groupFlag',
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '分组标识'
            },
            compProps:  {
                clearable: true,
                placeholder: '就是一个字符串'
            }
        }
    },
    {
        field: {
            name: 'categoryDictId'
        },
        element: {
            comp: 'PtDictFrontSelect',
            formItemProps: {
                label: '分类',
                required:true,
            },
            compProps: {
                // 字典查询
                dictParam: {groupCode: 'user_category'}
            }
        }
    },
    {
        field: {
            name: 'sourceFromDictId'
        },
        element: {
            comp: 'PtDictFrontSelect',
            formItemProps: {
                label: '来源',
                required:true,
            },
            compProps: {
                disabled: true,
                // 字典查询
                dictParam: {groupCode: 'user_source_from'},
                // 默认选中后台添加
                defaultValueItem: (item) => item.value == 'backend_add'
            }
        }
    },

    {
        field: {
            name: 'isExpired',
            value: false
        },
        element: {
            comp: 'el-switch',
            formItemProps: {
                label: '是否过期'
            },
            compProps: {
                activeText: '已过期',
                inactiveText: '正常',
            }
        }
    },
    {
        field: {
            name: 'expiredReason'
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '过期原因',
                required: ({form}) => form.isExpired == true
            },
            compProps: {
                clearable: true,
                placeholder: '如：手动过期|系统自动达到过期时间'
            }
        }
    },
    {
        field: {
            name: 'expireAt',
        },
        element: {
            comp: 'PtDatePicker',
            formItemProps: {
                label: '过期时间'
            },
            compProps:  {
                type: "datetime"
            }
        }
    },
    componentEnabled('dept') ? useCascaderDeptCompItem(
        {
            fieldName: 'deptId',
            required: false,
            label: '部门'
        }
        ) : null,
    {
        field: {
            name: 'remark'
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '备注'
            },
            compProps: {
                clearable: true,
            }
        }
    },
    //  以下为账号信息
    {
        field: {
            name: 'identifier',
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '账号',
                required:true,
            },
            compProps:  {
                clearable: true,
            }
        }
    },
    {
        field: {
            name: 'identityTypeDictId'
        },
        element: {
            comp: 'PtDictFrontSelect',
            formItemProps: {
                label: '账号类型',
                required:true,
            },
            compProps: {
                clearable: true,
                disabled: true,
                // 字典查询
                dictParam: {groupCode: 'user_account_type'},
                // 默认选中账号
                defaultValueItem: (item) => item.value == 'front_account'
            }
        }
    },
    {
        field: {
            name: 'identifierGroupFlag',
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '账号分组标识',
            },
            compProps:  {
                clearable: true,
            }
        }
    },
    ...resetPasswordCompItems
]

// 更新和添加一致
export const updatePageFormItems = [
    {
        field: {
            name: 'avatar'
        },
        element: {
            comp: 'PtUploadAvatar',
            formItemProps: {
                label: '头像'
            },
            compProps: {
            }
        }
    },
    {
        field: {
            name: 'nickname'
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '昵称',
                required: true,
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
                label: '姓名'
            },
            compProps: {
                clearable: true,
            }
        }
    },

    {
        field: {
            name: 'serialNo'
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '编号'
            },
            compProps: {
                clearable: true,
                placeholder: '编号全局唯一'
            }
        }
    },

    {
        field: {
            name: 'isVirtual',
            value: false
        },
        element: {
            comp: 'el-switch',
            formItemProps: {
                label: '虚拟用户'
            },
            compProps: {
                activeText: '虚拟用户',
                inactiveText: '真实用户',
            }
        }
    },
    {
        field: {
            name: 'genderDictId'
        },
        element: {
            comp: 'PtDictFrontSelect',
            formItemProps: {
                label: '性别',
                required: ({form})=> !form.isVirtual,
            },
            compProps: {
                // 字典查询
                dictParam: {groupCode: 'gender'}
            }
        }
    },
    {
        field: {
            name: 'isLock',
            value: false
        },
        element: {
            comp: 'el-switch',
            formItemProps: {
                label: '是否锁定'
            },
            compProps: {
                activeText: '锁定',
                inactiveText: '正常',
            }
        }
    },
    {
        field: {
            name: 'lockReason'
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '锁定原因',
                required: ({form}) => form.isLock == true
            },
            compProps: {
                clearable: true,
            }
        }
    },
    {
        field: {
            name: 'groupFlag',
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '分组标识'
            },
            compProps:  {
                clearable: true,
                placeholder: '就是一个字符串'
            }
        }
    },
    {
        field: {
            name: 'categoryDictId'
        },
        element: {
            comp: 'PtDictFrontSelect',
            formItemProps: {
                label: '分类',
                required:true,
            },
            compProps: {
                // 字典查询
                dictParam: {groupCode: 'user_category'}
            }
        }
    },
    {
        field: {
            name: 'sourceFromDictId'
        },
        element: {
            comp: 'PtDictFrontSelect',
            formItemProps: {
                label: '来源',
                required:true,
            },
            compProps: {
                // 字典查询
                dictParam: {groupCode: 'user_source_from'}
            }
        }
    },

    {
        field: {
            name: 'isExpired',
            value: false
        },
        element: {
            comp: 'el-switch',
            formItemProps: {
                label: '是否过期'
            },
            compProps: {
                activeText: '已过期',
                inactiveText: '正常',
            }
        }
    },
    {
        field: {
            name: 'expiredReason'
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '过期原因',
                required: ({form}) => form.isExpired == true
            },
            compProps: {
                clearable: true,
                placeholder: '如：手动过期|系统自动达到过期时间'
            }
        }
    },
    {
        field: {
            name: 'expireAt',
        },
        element: {
            comp: 'PtDatePicker',
            formItemProps: {
                label: '过期时间'
            },
            compProps:  {
                type: "datetime"
            }
        }
    },
    componentEnabled('dept') ? useCascaderDeptCompItem(
        {
            fieldName: 'deptId',
            required: false,
            label: '部门'
        }
    ) : null,
    {
        field: {
            name: 'remark'
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '备注'
            },
            compProps: {
                clearable: true,
            }
        }
    },
]