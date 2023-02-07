/**
 * 静态文本
 * 该属性适用于 PtSecretText
 */
export const formDesignCompsPtSecretTextAttrs = [
    {
        field: {
            // defaultValue 为表单设计与渲染关键字，特殊处理联动问题
            name: 'defaultValue',
            value: '静态文本示例'
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '默认值',
                title: '默认值将会在表单渲染时初始显示的值'
            },
            compProps: ({form}) => {
                return {
                    clearable: true
                }
            },
        }
    },
    {
        field: {
            name: 'defaultShowSecretText',
            value: false
        },
        element: {
            comp: 'el-switch',
            formItemProps: {
                label: '展示隐藏文本',
            },
            compProps: {
                activeText: '默认展示',
                inactiveText: '默认不展示',
            }
        }
    },
    {
        field: {
            name: 'showSwitchButton',
            value: false
        },
        element: {
            comp: 'el-switch',
            formItemProps: {
                label: '显示切换按钮',
            },
            compProps: {
                activeText: '展示',
                inactiveText: '不展示',
            }
        }
    },
]

