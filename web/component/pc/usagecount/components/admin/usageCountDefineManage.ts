import {useCascaderUsageCountDefineCompItem} from "../usageCountCompItem";
import {treeQueryComps} from "../../../treeQueryComps";


const validateCodeAndUrlPattern = (form,callback) =>{
    // 编码和url模式都没填写
    if (!form.isGroup && !form.code && !form.urlPattern) {
        callback(new Error('编码和url模式至少填写一个'))
    }else {
        callback()
    }
}

export const pageFormItems = [
    {
        field: {
            name: 'code',
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '编码',

            },
            compProps: {
                clearable: true,
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

            },
            compProps: {
                clearable: true,
                placeholder: '左前缀匹配',
            }
        }
    },
    {
        field: {
            name: 'urlPattern',
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: 'url模式',
            },
            compProps: {
                clearable: true,
                placeholder: '左前缀匹配'
            }
        }
    },
    useCascaderUsageCountDefineCompItem({}),
    ...treeQueryComps
]
export const useAddPageFormItems = ({form}) => {
    return [

        {
            field: {
                name: 'isGroup',
                value: false
            },
            element: {
                comp: 'el-switch',
                formItemProps: {
                    label: '是否分组',
                    required: true,
                    tips: '分组只是一个分类的概念'
                },
                compProps: {
                    activeText: '分组',
                    inactiveText: '定义',
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
                    rules: [
                        { validator: (rule: any, value: any, callback: any) => {
                                validateCodeAndUrlPattern(form,callback)
                            }, trigger: 'blur'
                        },
                    ],
                    tips: '编码主要用于设计用来计数的场景，一般前端根据编码传参以计数使用，一旦添加请谨慎修改'
                },
                compProps: {
                    clearable: true,
                    placeholder: '编码唯一',
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
                }
            }
        },


        {
            field: {
                name: 'urlPattern',
            },
            element: {
                comp: 'el-input',
                formItemProps: {
                    label: 'url模式',
                    rules: [
                        { validator: (rule: any, value: any, callback: any) => {
                                validateCodeAndUrlPattern(form,callback)
                            }, trigger: 'blur'
                        },
                    ],
                    tips: '一般以 / 开头，用来匹配接口，这适用于单接口场景'
                },
                compProps: {
                    clearable: true,
                    placeholder: '如：/user/add',
                }
            }
        },

        {
            field: {
                name: 'seq',
                value: 10
            },
            element: {
                comp: 'el-input-number',
                formItemProps: {
                    label: '排序',
                    required: true
                },
                compProps: {
                    clearable: true,
                }
            }
        },

        useCascaderUsageCountDefineCompItem({}),

        {
            field: {
                name: 'remark',
            },
            element: {
                comp: 'el-input',
                formItemProps: {
                    label: '备注',

                },
                compProps: {
                    clearable: true,
                }
            }
        },
    ]
}

// 更新和添加一致
export const useUpdatePageFormItems = useAddPageFormItems

