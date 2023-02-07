/**
 * 评分选择
 * 该属性适用于 particle 项目无封装 和 el-rate
 */

export const formDesignCompsRateAttrs = [

    {
        field: {
            // defaultValue 为表单设计与渲染关键字，特殊处理联动问题
            name: 'defaultValue',
            value: 0
        },
        element: {
            comp: 'el-rate',
            formItemProps: {
                label: '默认值',
                title: '默认值将会在表单渲染时初始显示的值',
                labelTips: '可以将值清空，当只选择一个星时，再次点击会清空默认为0'
            },
            compProps: ({form})=>{
                return {
                    max: form.max,
                    allowHalf: form.allowHalf,
                    lowThreshold: form.lowThreshold,
                    highThreshold: form.highThreshold,
                    showScore: form.showScore,
                    clearable: true,
                }
            }
        }
    },

    {
        field: {
            name: 'max',
            value: 5
        },
        element: {
            comp: 'el-input-number',
            formItemProps: {
                label: '最大分值',
            },
            compProps: {
            }
        }
    },
    {
        field: {
            name: 'allowHalf',
        },
        element: {
            comp: 'el-switch',
            formItemProps: {
                label: '允许半选',
            },
            compProps: {
                activeText: '允许',
                inactiveText: '不允许',
            }
        }
    },
/*    {
        field: {
            name: 'lowThreshold',
            value: 2
        },
        element: {
            comp: 'el-input-number',
            formItemProps: {
                label: '中低等分数界限',
            },
            compProps: {
            }
        }
    },
    {
        field: {
            name: 'highThreshold',
            value: 4
        },
        element: {
            comp: 'el-input-number',
            formItemProps: {
                label: '中高等分数界限',
            },
            compProps: {
            }
        }
    },*/
    {
        field: {
            name: 'showScore',
            value: false
        },
        element: {
            comp: 'el-switch',
            formItemProps: {
                label: '显示分数',
            },
            compProps: {
                activeText: '显示',
                inactiveText: '不显示',
            },
        }
    },
    {
        field: {
            name: 'clearable',
            value: false
        },
        element: {
            comp: 'el-switch',
            formItemProps: {
                label: '是否允许重置值为 0',
            },
            compProps: {
                activeText: '允许',
                inactiveText: '不允许',
            },
        }
    },
]

