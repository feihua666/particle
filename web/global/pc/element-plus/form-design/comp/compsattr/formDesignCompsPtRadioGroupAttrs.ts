/**
 * 单选框
 * 该属性适用于 PtRadioGroup
 */
import {formDesignParticleBuiltInComponentsAttrsData} from "../formDesignParticleBuiltInComponentsAttrs";
import {strCodeTocode} from "../../tools";

export const formDesignCompsPtRadioGroupAttrs = [

    {
        field: {
            // defaultValue 为表单设计与渲染关键字，特殊处理联动问题
            name: 'defaultValue',
        },
        element: {
            comp: 'PtRadioGroup',
            formItemProps: {
                label: '默认值',
                title: '默认值将会在表单渲染时初始显示的值'
            },
            compProps: ({form}) => {
                return {
                    buttonView: form['buttonView'],
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
    ...formDesignParticleBuiltInComponentsAttrsData
]

