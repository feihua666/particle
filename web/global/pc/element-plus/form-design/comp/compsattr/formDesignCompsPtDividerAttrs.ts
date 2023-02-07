/**
 * 静态文本
 * 该属性适用于 PtSecretText
 */
export const formDesignCompsPtDividerAttrs = [
    {
        field: {
            // defaultValue 为表单设计与渲染关键字，特殊处理联动问题
            name: 'content',
            value: '分隔线文本'
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '分隔线文本',
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
            name: 'direction',
            value: 'horizontal'
        },
        element: {
            comp: 'PtRadioGroup',
            formItemProps: {
                label: '分割线方向',
            },
            compProps: {
                options: [
                    {
                        id: 'horizontal',
                        name: '水平',
                    },
                    {
                        id: 'vertical',
                        name: '垂直',
                    },
                ]
            }
        }
    },
    {
        field: {
            name: 'borderStyle',
            value: 'solid'
        },
        element: {
            comp: 'PtRadioGroup',
            formItemProps: {
                label: '分隔线样式',
            },
            compProps: {
                options: ['dotted','dashed','solid','double','groove','ridge ','inset ','outset'].map(item => ({id: item,name: item}))
            }
        }
    },
    {
        field: {
            name: 'contentPosition',
            value: 'center'
        },
        element: {
            comp: 'PtRadioGroup',
            formItemProps: {
                label: '分隔线样式',
            },
            compProps: {
                options: ['left','center','right'].map(item => ({id: item,name: item}))
            }
        }
    },
]

