import {
    resetPasswordCompItems,
    useRemoteSelectUserCompItem,
    useRemoteSelectUserIdentifierCompItem
} from "../userCompItem";

export const usePageFormItems = ({props}) => {
    return [
        useRemoteSelectUserCompItem({props,required: false}),
        useRemoteSelectUserIdentifierCompItem({props,required: false}),
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
    return [
        useRemoteSelectUserCompItem({props,required: true}),
        useRemoteSelectUserIdentifierCompItem({props,required: true}),
        ...resetPasswordCompItems,
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

// 更新和添加一致
export const updatePageFormItems = [
    {
        field: {
            name: 'password'
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '密码',
            },
            compProps: {
                clearable: true,
                placeholder: '不修改密码请留空'
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
                type: "datetime",
                placeholder: '不填写永不过期'
            }
        }
    },
    {
        field: {
            name: 'isNeedUpdate',
            value: false
        },
        element: {
            comp: 'el-switch',
            formItemProps: {
                label: '提示修改',
                required: true
            },
            compProps: {
                activeText: '提示',
                inactiveText: '不提示',
            }
        }
    },
    {
        field: {
            name: 'needUpdateMessage'
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '提示修改消息内容',
                required: ({form})=> form.isNeedUpdate
            },
            compProps: {
                clearable: true,
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