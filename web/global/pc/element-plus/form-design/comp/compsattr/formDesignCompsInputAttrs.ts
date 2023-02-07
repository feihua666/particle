/**
 * 文本输入
 * 该属性适用于 PtInput 和 el-input
 */
export const formDesignCompsInputAttrs = [

    {
        field: {
            // defaultValue 为表单设计与渲染关键字，特殊处理联动问题
            name: 'defaultValue',
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '默认值',
                title: '默认值将会在表单渲染时初始显示的值'
            },
            compProps: {
                clearable: true
            }
        }
    },
    {
        field: {
            name: 'placeholder',
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '占位提示',
                title: '占位提示即 placeholder'
            },
            compProps: {
                clearable: true,
                placeholder: '我就是一个占位提示内容',
            }
        }
    },
    {
        field: {
            name: 'clearable',
            value: true
        },
        element: {
            comp: 'el-switch',
            formItemProps: {
                label: '一键清空',
                title: '会显示一个清空按钮，可一键清空'
            },
            compProps: {
                activeText: '一键清空',
                inactiveText: '不能一键清空',
            }
        }
    },
    {
        field: {
            name: 'showPassword',
            value: false
        },
        element: {
            comp: 'el-switch',
            formItemProps: {
                label: '密码输入',
                title: '将会隐藏输入文本，且不能复制内容'
            },
            compProps: {
                activeText: '密码输入',
                inactiveText: '正常输入',
            }
        }
    },
    {
        field: {
            name: 'type',
            value: 'text'
        },
        element: {
            comp: 'PtRadioGroup',
            formItemProps: {
                label: '多行文本',
                title: '可以输入多行文本'
            },
            compProps: {
                options: [
                    {
                        id: 'text',
                        name: '单行文本'
                    },
                    {
                        id: 'textarea',
                        name: '多行文本'
                    }
                ]
            }
        }
    },
    {
        field: {
            name: 'showWordLimit',
            value: false
        },
        element: {
            comp: 'el-switch',
            formItemProps: {
                label: '显示字数统计',
                title: '将会在文本框右边显示当前输入的文本个数',
                labelTips: '只在 type 为 \'text\' 或 \'textarea\' 的时候生效'
            },
            compProps: {
                activeText: '显示',
                inactiveText: '不显示',
            }
        }
    },
    {
        field: {
            name: 'minlength',
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '最小输入长度',
            },
            compProps: {
                clearable: true,
            }
        }
    },
    {
        field: {
            name: 'maxlength',
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '最大输入长度',
            },
            compProps: {
                clearable: true,
            }
        }
    },
]

