import {componentSize} from "./formDesignAttrsFormItemCompsAttrs";
import {strCodeTocode} from "../tools";

export const formDesignFormSettingAttrs = [
    componentSize,
    {
        field: {
            name: 'labelPosition',
            value: 'left'
        },
        element: {
            comp: 'PtRadioGroup',
            formItemProps: {
                label: '标签位置',
                labelTips: '表单域标签的位置， 当设置为 left 或 right 时，则也需要设置 label-width 属性'
            },
            compProps: {
                buttonView: true,
                options: [
                    {
                        id: 'left',
                        name: '居左'
                    },
                    // element plus 文档上有，但没有效果，所以先注释
                    /*
                    {
                        id: 'right',
                        name: '居右'
                    },*/
                    {
                        id: 'top',
                        name: '居上'
                    },
                ]
            }
        }
    },
    {
        field: {
            name: 'labelWidth',
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '标签宽度',
                labelTips: '标签的长度，例如 \'50px\'。 作为 Form 直接子元素的 form-item 会继承该值。 可以使用 auto。'
            },
            compProps: {
                clearable: true,
            }
        }
    },
    {
        field: {
            name: 'inline',
            value: true
        },
        element: {
            comp: 'el-switch',
            formItemProps: {
                label: '行内表单模式',
            },
            compProps: {
                activeText: '行内表单',
                inactiveText: '块级表单',
            }
        }
    },
    {
        field: {
            name: 'layout',
            value: '3',
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '布局',
                labelTips: '布局选项仅支持PtForm，支持数字和数组两种设置方式，' +
                    '<br/>数字代表每行几个，默认为3，即：每行三列，' +
                    '<br/>数组按下标，下标0对应第一行,下标1对应第二行，分别代表第1行几个、第二行几个' +
                    '<br/>如果数组项不是数字，还可以支持高级用法数组类型，遵循栅格布局col',
                tips: '例：每行3列，直接设置为3即可；第一行一列，第二行两列则设置为[1,2](后面的默认自动每行3列)'
            },
            compProps: {
                clearable: true
            },
            formSettingPropsHandler:{
                toProps: (value) => {return strCodeTocode.value(value,3)}
            }
        }
    }
]