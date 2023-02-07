/**
 * 开关选择
 * 该属性适用于 PtSwitch 和 el-switch
 */
const valueToProps = (value,form)=>{

    let tempValue = value

    if (value === 'true') {
        tempValue = true
    }
    if (value === 'false') {
        tempValue = false
    }
    return tempValue
}
export const formDesignCompsSwitchAttrs = [

    {
        field: {
            // defaultValue 为表单设计与渲染关键字，特殊处理联动问题
            name: 'defaultValue',
        },
        element: {
            comp: 'el-switch',
            formItemProps: {
                label: '默认值',
                title: '默认值将会在表单渲染时初始显示的值'
            },
            compProps: ({form})=>{
                return {
                    activeValue: valueToProps(form.activeValue,form),
                    inactiveValue: valueToProps(form.inactiveValue,form),
                    activeText: form.activeText,
                    inactiveText: form.inactiveText,
                }
            }
        }
    },

    {
        field: {
            name: 'activeText',
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '激活文本',
            },
            compProps: {
                clearable: true,
            }
        }
    },
    {
        field: {
            name: 'inactiveText',
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '未激活文本',
            },
            compProps: {
                clearable: true,
            }
        }
    },
    {
        field: {
            name: 'inlinePrompt',
            value: false
        },
        element: {
            comp: 'el-switch',
            formItemProps: {
                label: '文本内联',
            },
            compProps: {
            }
        }
    },
    {
        field: {
            name: 'activeValue',
            value: 'true',
            valueChange: ({form,newValue})=>{
                if(newValue == null || newValue === ''){
                    form.activeValue = 'true'
                }
            }
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '激活值',
            },
            compProps: {
                clearable: true,
            },
            compPropsHandler:{
                toProps: valueToProps
            }
        }
    },
    {
        field: {
            name: 'inactiveValue',
            value: 'false',
            valueChange: ({form,newValue})=>{
                if(newValue == null || newValue === ''){
                    form.activeValue = 'false'
                }
            }
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '未激活值',
            },
            compProps: {
                clearable: true,
            },
            compPropsHandler:{
                toProps: valueToProps
            }
        }
    },
]

