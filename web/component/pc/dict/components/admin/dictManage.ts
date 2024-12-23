import {treeQueryComps} from "../../../treeQueryComps";
import {useCascaderDictCompItem} from "../dictCompItem";

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
    useCascaderDictCompItem({}),
    ...treeQueryComps
]

export const addPageFormItems = [
    {
        field: {
            name: 'isGroup',
            value: false
        },
        element: {
            comp: 'el-switch',
            formItemProps: {
                label: '是否字典组'
            },
            compProps: {
                activeText: '字典组',
                inactiveText: '字典项',
            }
        }
    },
    {
        field: {
            name: 'code',
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '编码',
                required: ({form}) => form.isGroup == true,
                title: '编码全局唯一，用来唯一标识一个字典组'
            },
            compProps: ({form})=>{
                let disabled = form.isGroup == false
                if(disabled){
                    form.code = ''
                }
                return {
                    clearable: true,
                    placeholder: '编码唯一如：user_code',
                }
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
            name: 'value'
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '字典值',
                required: ({form})=> form.isGroup == false
            },
            compProps:  ({form})=>{
                let disabled = form.isGroup == true
                if(disabled){
                    form.value = ''
                }
                return {
                    clearable: true,
                }
            }
        }
    },
    {
        field: {
            name: 'valueUnit',
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '字典值单位'
            },
            compProps:  {
                clearable: true,
            }
        }
    },
    {
        field: {
            name: 'isSystem',
            value: true
        },
        element: {
            comp: 'el-switch',
            formItemProps: {
                label: '是否系统'
            },
            compProps: {
                activeText: '系统字典',
                inactiveText: '自定义字典',
            }
        }
    },
    {
        field: {
            name: 'isPublic',
            value: true
        },
        element: {
            comp: 'el-switch',
            formItemProps: {
                label: '是否公共'
            },
            compProps: {
                activeText: '公共字典',
                inactiveText: '私有字典',
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
    {
        field: {
            name: 'privateFlag'
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '私有标识'
            },
            compProps: {
                clearable: true,
            }
        }
    },
    {
        field: {
            name: 'privateFlagMemo'
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '私有标识说明',
                required: ({form}) => !!form.privateFlag
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
    {
        field: {
            name: 'groupFlagMemo'
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '分组标识说明',
                required: ({form}) => !!form.groupFlag
            },
            compProps: {
                clearable: true,
            }
        }
    },
    {
        field: {
            name: 'tags'
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '标签',
                title: '标签用来给字典项分组，多个以逗号分隔'
            },
            compProps: {
                clearable: true,
            }
        }
    },
    useCascaderDictCompItem({required: ({form}) => form.isGroup == false}),
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
