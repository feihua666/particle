import {useSelectDynamicTableCompItem} from "../../dataCompItem";

export const pageFormItems = [
    useSelectDynamicTableCompItem({}),
    {
        field: {
            name: 'name',
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '字段名称',

            },
            compProps: {
                clearable: true,
                placeholder: '模糊匹配',
            }
        }
    },
    {
        field: {
            name: 'comment',
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '字段注释',

            },
            compProps: {
                clearable: true,
                placeholder: '模糊匹配',
            }
        }
    },

]
export const useAddPageFormItems = ({isForAdd = true})=>{
    return [
        useSelectDynamicTableCompItem({required: true,disabled: !isForAdd}),


        {
            field: {
                name: 'name',
            },
            element: {
                comp: 'el-input',
                formItemProps: {
                    label: '字段名称',
                    required: true,
                    tips: '请遵循数据库的字段名命令规则如：user_id',
                },
                compProps: {
                    clearable: true,
                    disabled: !isForAdd,
                }
            }
        },


        {
            field: {
                name: 'comment',
            },
            element: {
                comp: 'el-input',
                formItemProps: {
                    label: '字段注释',
                    required: true,

                },
                compProps: {
                    clearable: true,
                }
            }
        },


        {
            field: {
                name: 'type',
            },
            element: {
                comp: 'el-input',
                formItemProps: {
                    label: '字段类型',
                    required: true,
                    tips: '请如实填写数据库的字段类型，如：mysql中 varchar(100)',
                },
                compProps: {
                    clearable: true,
                    disabled: !isForAdd,
                }
            }
        },


        {
            field: {
                name: 'isRequired',
                value: false
            },
            element: {
                comp: 'el-switch',
                formItemProps: {
                    label: '是否必填',
                    required: true,
                },
                compProps: {
                    activeText: '必填',
                    inactiveText: '选填',
                    disabled: !isForAdd,
                }
            }
        },

        {
            field: {
                name: 'defaultValue',
            },
            element: {
                comp: 'el-input',
                formItemProps: {
                    label: '默认值',
                },
                compProps: {
                    clearable: true,
                    disabled: !isForAdd,
                }
            }
        },

    ]
}

// 更新和添加一致
export const useUpdatePageFormItems = useAddPageFormItems

