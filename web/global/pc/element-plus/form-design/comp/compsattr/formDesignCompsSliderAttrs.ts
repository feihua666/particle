/**
 * 滑块选择
 * 该属性适用于 particle 项目无封装 和 el-slider
 */

export const formDesignCompsSliderAttrs = [

    {
        field: {
            // defaultValue 为表单设计与渲染关键字，特殊处理联动问题
            name: 'defaultValue',
            value: 0
        },
        element: {
            comp: 'el-slider',
            formItemProps: {
                label: '默认值',
                title: '默认值将会在表单渲染时初始显示的值',
            },
            compProps: ({form})=>{
                return {
                    max: form.max,
                    min: form.min,
                    step: form.step,
                    showInput: form.showInput,
                    showInputControls: form.showInputControls,
                    showStops: form.showStops,
                    range: form.range,
                }
            }
        }
    },
    {
        field: {
            name: 'min',
            value: 0
        },
        element: {
            comp: 'el-input-number',
            formItemProps: {
                label: '最小值',
            },
            compProps: {
            }
        }
    },
    {
        field: {
            name: 'max',
            value: 100
        },
        element: {
            comp: 'el-input-number',
            formItemProps: {
                label: '最大值',
            },
            compProps: {
            }
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
            },
            compProps: {
            }
        }
    },
    {
        field: {
            name: 'showInput',
            value: false
        },
        element: {
            comp: 'el-switch',
            formItemProps: {
                label: '显示输入框',
                title: '是否显示输入框，仅在非范围选择时有效'
            },
            compProps: {
                activeText: '显示',
                inactiveText: '不显示',
            }
        }
    },
    {
        field: {
            name: 'showInputControls',
            value: true
        },
        element: {
            comp: 'el-switch',
            formItemProps: {
                label: '显示输入框的控制按钮',
                title: '在显示输入框的情况下，是否显示输入框的控制按钮'
            },
            compProps: {
                activeText: '显示',
                inactiveText: '不显示',
            }
        }
    },
    {
        field: {
            name: 'showStops',
            value: false
        },
        element: {
            comp: 'el-switch',
            formItemProps: {
                label: '显示间断点',
            },
            compProps: {
                activeText: '显示',
                inactiveText: '不显示',
            }
        }
    },
    {
        field: {
            name: 'showTooltip',
            value: true
        },
        element: {
            comp: 'el-switch',
            formItemProps: {
                label: '显示提示信息',
            },
            compProps: {
                activeText: '显示',
                inactiveText: '不显示',
            }
        }
    },
    {
        field: {
            name: 'range',
            value: false
        },
        element: {
            comp: 'el-switch',
            formItemProps: {
                label: '开启选择范围',
            },
            compProps: {
                activeText: '开启',
                inactiveText: '不开启',
            }
        }
    },
    {
        field: {
            name: 'vertical',
            value: false
        },
        element: {
            comp: 'el-switch',
            formItemProps: {
                label: '开启垂直模式',
            },
            compProps: {
                activeText: '开启',
                inactiveText: '不开启',
            }
        }
    },
    {
        field: {
            name: 'height',
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '滑块高度',
                title: '滑块高度，垂直模式必填'
            },
            compProps: {
            }
        }
    },
]

