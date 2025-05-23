import {treeQueryComps} from "../../../treeQueryComps";
import {useCascaderDictCompItem} from "../dictCompItem";
import {useSelectOpenapiProviderCompItem} from "../../../openplatform/components/openplatformOpenapiCompItem";

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
                label: '是否字典组',
                tips: '字典组下可以添加字典项和字典组'
            },
            compProps: {
                activeText: '字典组',
                inactiveText: '非字典组',
            }
        }
    },
    {
        field: {
            name: 'isItem',
            value: false
        },
        element: {
            comp: 'el-switch',
            formItemProps: {
                label: '是否字典项',
                tips: '字典组下不可以添加字典项和字典组'
            },
            compProps: {
                activeText: '字典项',
                inactiveText: '非字典项',
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
                tips: '编码全局唯一，字典组编码，用来唯一标识一个字典组,字典项无需填写'
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
                required: true,
                tips: '字典项或字典组的名称'
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
                required: ({form})=> form.isItem == true,
                tips: '字典项值，字典项使用字典值表示，字典组使用编码表示，字典组无需填写'
            },
            compProps:  ({form})=>{
                let disabled = form.isItem == false
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
                label: '字典值单位',
                tips: '用于字典项值有单位的场景，方便字典值以单独可量化形式表示'
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
                label: '是否系统',
                tips: '表示是否属于系统字典，如果为系统字典表示字典值或所属的字典组被硬编码了，不应该删除或随意修改，否则是用户自定义字典'
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
                label: '是否公共',
                tips: '表示是否属于公共字典，如果为公共字典表示字典为公开项，否则为私有字典即为某一主体所特有，可以开发中结合私有标识字段灵活使用'
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
                label: '私有标识',
                tips: '当字典为私有字典时，可根据该私有标识查询字典，以获取公共字典和主体所属私有字典的并集'
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
                required: ({form}) => !!form.privateFlag,
                tips: '对私有标识字段的一个备注说明'
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
                label: '分组标识',
                tips: '可以给定一个标识用于指定在同一个字典组下不同分组的字典项，以分组的方式获取'
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
                required: ({form}) => !!form.groupFlag,
                tips: '对分组标识字段的一个备注说明'
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
                tips: '标签用来给字典项分组，多个以逗号分隔'
            },
            compProps: {
                clearable: true,
            }
        }
    },
    {
        field: {
            name: 'relatedGroupCode'
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '关联字典组编码',
                tips: '关联字典组编码，用于在字典项下还有字典项的扩展场景'
            },
            compProps: {
                clearable: true,
            }
        }
    },
    useCascaderDictCompItem({required: ({form}) => form.isItem == true,tips: '一般选择一个字典组来做为父级'}),
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
            name: 'mappingArrayJson',
        },
        element: {
            comp: 'PtTableFormButton',
            formItemProps: {
                label: '映射配置',
                tips: '可以添加一些映射配置，以匹配该字典项'

            },
            compProps: ({form})=>{
                return {
                    formProps:{
                        labelWidth: '120',
                        formSubmitDataHandler({isAdd,formRef,form,formData}){
                            return form
                        },
                        comps: [
                            {
                                field: {
                                    name: 'mappingValue',
                                },
                                element: {
                                    comp: 'el-input',
                                    formItemProps: {
                                        label: '映射值',
                                        required: true,
                                        tips: '使用该映射值匹配字典项'
                                    },
                                    compProps: {
                                        clearable: true,
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
                                        label: '备注',
                                        tips: '备注信息'
                                    },
                                    compProps: {
                                        clearable: true,
                                    }
                                }
                            },
                        ]
                    },
                    tableProps:{
                        propForDeleteView: 'mappingValue',
                        columns: [
                            {
                                prop: 'mappingValue',
                                label: '映射值',
                                showOverflowTooltip: true
                            },
                            {
                                prop: 'remark',
                                label: '备注',
                                showOverflowTooltip: true
                            },

                        ]
                    }
                }
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
// 批量添加
export const batchAddPageFormItems = addPageFormItems.concat([
    {
        field: {
            name: 'batchContent'
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '批量添加内容',
                required: true,
                tips: '仅支持 编码、名称、字典值三项(在表单中请输入任意值来占位否则表单验证不通过)，其它项不支持，分三种情况<br/>' +
                    '1. 只选择字典组，批量添加内容为两列中间空格分隔，左列为编码，右列为名称<br/>' +
                    '2. 只选择字典项，批量添加内容为两列中间空格分隔，左列为名称，右列为字典值<br/>' +
                    '3. 字典组字典项都选择，批量添加内容为三列中间空格分隔，左列为编码，中列为名称，右列为字典值<br/>'
            },
            compProps: {
                clearable: true,
                type: 'textarea',
                rows: 10
            }
        }
    },
])
