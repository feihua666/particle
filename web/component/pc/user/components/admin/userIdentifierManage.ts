import {useRemoteSelectUserCompItem, resetPasswordCompItems} from "../userCompItem";

export const usePageFormItems = ({props}) => {
    return  [
        {
            field: {
                name: 'identifier'
            },
            element: {
                comp: 'el-input',
                formItemProps: {
                    label: '登录标识'
                },
                compProps: {
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
                },
                compProps: {
                    // 字典查询
                    dictParam: {groupCode: 'user_account_type'},
                }
            }
        },
        {
            field: {
                name: 'groupFlag'
            },
            element: {
                comp: 'el-input',
                formItemProps: {
                    label: '分组标识'
                },
                compProps: {
                    clearable: true,
                }
            }
        },
        useRemoteSelectUserCompItem({props,required: false}),
    ]
}
export const useBindPageFormItems = ({props}) => {
    return  [
        useRemoteSelectUserCompItem({props,required: true}),
        {
            field: {
                name: 'identifier'
            },
            element: {
                comp: 'el-input',
                formItemProps: {
                    label: '登录标识',
                    required: true
                },
                compProps: {
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
                    required: true
                },
                compProps: {
                    clearable: true,
                    // 字典查询
                    dictParam: {groupCode: 'user_account_type'},
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
                    label: '是否锁定',
                    required: true
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
                name: 'isExpired',
                value: false
            },
            element: {
                comp: 'el-switch',
                formItemProps: {
                    label: '是否过期',
                    required: true
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
                    clearable: true,
                    type: "datetime"
                }
            }
        },
        {
            field: {
                name: 'groupFlag'
            },
            element: {
                comp: 'el-input',
                formItemProps: {
                    label: '分组标识'
                },
                compProps: {
                    clearable: true,
                }
            }
        },

    ]
}

export const useAddPageFormItems = ({props}) => {
    return  [
        ...useBindPageFormItems({props}),
        ...resetPasswordCompItems
    ]
}

// 更新和添加一致
export const updatePageFormItems = [
    {
        field: {
            name: 'identifier'
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '登录标识',
                required: true
            },
            compProps: {
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
                required: true
            },
            compProps: {
                // 字典查询
                dictParam: {groupCode: 'user_account_type'},
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
                label: '是否锁定',
                required: true
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
            name: 'isExpired',
            value: false
        },
        element: {
            comp: 'el-switch',
            formItemProps: {
                label: '是否过期',
                required: true
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
    {
        field: {
            name: 'groupFlag'
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '分组标识'
            },
            compProps: {
                clearable: true,
            }
        }
    },

]