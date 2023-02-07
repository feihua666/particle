import {componentSize} from "../../attr/formDesignAttrsFormItemCompsAttrs";

/**
 * 数字输入
 * 该属性适用于 PtInputNumber 和 el-input-number
 */
const minToProps = (value)=>{
    return value == null ? undefined : value

}
export const formDesignCompsInputNumberAttrs = [

    {
        field: {
            // defaultValue 为表单设计与渲染关键字，特殊处理联动问题
            name: 'defaultValue',
        },
        element: {
            comp: 'el-input-number',
            formItemProps: {
                label: '默认值',
                title: '默认值将会在表单渲染时初始显示的值'
            },
            compProps: ({form})=>{
                return {
                    precision: form.precision || undefined,
                    step: form.step || undefined,
                    min: form.min== null ? undefined: form.min,
                    max: form.max== null ? undefined: form.max,
                    stepStrictly: form.stepStrictly,
                }
            }
        }
    },
    {
        field: {
            name: 'min',
        },
        element: {
            comp: 'el-input-number',
            formItemProps: {
                label: '允许最小值',
            },
            compProps: {
                clearable: true,
                placeholder: '允许最小值',
            },

            compPropsHandler: {
                toProps: minToProps,
            },
        }
    },
    {
        field: {
            name: 'max',
        },
        element: {
            comp: 'el-input-number',
            formItemProps: {
                label: '允许最大值',
            },
            compProps: {
                clearable: true,
                placeholder: '允许最大值',
            },
            compPropsHandler: {
                toProps: minToProps,
            },
        }
    },
    {
        field: {
            name: 'step',
            value: 1
        },
        element: {
            comp: 'el-input-number',
            formItemProps: {
                label: '步长',
                labelTips: '步长为小数时，请先设置对应的精度'
            },
            compProps: {
                clearable: true,
            }
        }
    },
    {
        field: {
            name: 'stepStrictly',
        },
        element: {
            comp: 'el-switch',
            formItemProps: {
                label: '步长严格限制',
                title: '是否只能输入 step 的倍数'
            },
            compProps: {
                activeText: '是',
                inactiveText: '否',
            }
        }
    },
    {
        field: {
            name: 'precision'
        },
        element: {
            comp: 'el-input-number',
            formItemProps: {
                label: '数值精度',
                title: '数值精度可保留小数点',
                labelTips: '请输入数字。例：输入2则保留两位小数'
            },
            compProps: {
                clearable: true
            }
        }
    },
    componentSize,
    {
        field: {
            name: 'controls',
            value: true
        },
        element: {
            comp: 'el-switch',
            formItemProps: {
                label: '使用控制按钮',
            },
            compProps: {
                activeText: '是',
                inactiveText: '否',
            }
        }
    },
    {
        field: {
            name: 'controlsPosition',
            value: ''
        },
        element: {
            comp: 'PtRadioGroup',
            formItemProps: {
                label: '控制按钮位置',
            },
            compProps: {
                options: [
                    {
                        id: '',
                        name: '默认'
                    },
                    {
                        id: 'right',
                        name: '靠右'
                    },
                ]
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

]

