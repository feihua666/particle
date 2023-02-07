/**
 * 多选框
 * 该属性适用于 PtCheckboxGroup
 */
import {formDesignParticleBuiltInComponentsAttrsData} from "../formDesignParticleBuiltInComponentsAttrs";
import { strCodeTocode} from "../../tools";

export const formDesignCompsPtCheckboxGroupAttrs = [

    {
        field: {
            // defaultValue 为表单设计与渲染关键字，特殊处理联动问题
            name: 'defaultValue',
            value: []
        },
        element: {
            comp: 'PtCheckboxGroup',
            formItemProps: {
                label: '默认值',
                title: '默认值将会在表单渲染时初始显示的值'
            },
            compProps: ({form}) => {
                return {
                    checkAllView: form['checkAllView'],
                    buttonView: form['buttonView'],
                    min: (form['min'] == undefined || form['min']) ? undefined : form['min'],
                    max: (form['max'] == undefined || form['max']) ? undefined : form['max'],
                    options: strCodeTocode.value(form['options'],[]),
                }
            },
        }
    },
    {
        field: {
            name: 'buttonView',
            value: false
        },
        element: {
            comp: 'el-switch',
            formItemProps: {
                label: '按钮外观',
            },
            compProps: {
                activeText: '是',
                inactiveText: '否',
            }
        }
    },
    {
        field: {
            name: 'checkAllView',
            value: true
        },
        element: {
            comp: 'el-switch',
            formItemProps: {
                label: '显示全选',
            },
            compProps: {
                activeText: '是',
                inactiveText: '否',
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
                label: '最小勾选数',
            },
            compProps: {
                clearable: true,
                placeholder: '最小勾选数',
                min: 0
            },

            compPropsHandler: {
                toProps: (value)=>{
                    return value == null ? undefined : value
                },
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
                label: '最大勾选数',
            },
            compProps: {
                clearable: true,
                placeholder: '最大勾选数',
            },
            compPropsHandler: {
                toProps: (value)=>{
                    return value == null ? undefined : value
                },
            },
        }
    },
    ...formDesignParticleBuiltInComponentsAttrsData
]

