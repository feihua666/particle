import {codeToStrCode, strCodeTocode} from "../../tools";

/**
 * 滑块选择
 * 该属性适用于 particle 项目无封装 和 el-color-picker
 */
const predefine = [
    '#ff4500',
    '#ff8c00',
    '#ffd700',
    '#90ee90',
    '#00ced1',
    '#1e90ff',
    '#c71585',
    'rgba(255, 69, 0, 0.68)',
    'rgb(255, 120, 0)',
    'hsv(51, 100, 98)',
    'hsva(120, 40, 94, 0.5)',
    'hsl(181, 100%, 37%)',
    'hsla(209, 100%, 56%, 0.73)',
    '#c7158577',
]
export const formDesignCompsColorPickerAttrs = [

    {
        field: {
            // defaultValue 为表单设计与渲染关键字，特殊处理联动问题
            name: 'defaultValue',
        },
        element: {
            comp: 'el-color-picker',
            formItemProps: {
                label: '默认值',
                title: '默认值将会在表单渲染时初始显示的值',
            },
            compProps: ({form})=>{
                return {
                    showAlpha: form.showAlpha,
                    predefine: strCodeTocode.value(form.predefine,[]),
                }
            }
        }
    },
    {
        field: {
            name: 'showAlpha',
            value: false
        },
        element: {
            comp: 'el-switch',
            formItemProps: {
                label: '支持透明度选择',
            },
            compProps: {
                activeText: '支持',
                inactiveText: '不支持',
            }
        }
    },
    {
        field: {
            name: 'predefine',
            value: JSON.stringify(predefine)
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '预定义颜色',
                labelTips: '预定义颜色为一个数组，数组项为颜色值'
            },
            compProps: ({form}) => {
                return {
                    type: 'textarea',
                }
            },
            compPropsHandler: {
                toForm: (value) => {return codeToStrCode.value(value,JSON.stringify(predefine))},
                toProps: (value) => {return strCodeTocode.value(value,[])}
            }
        }
    },
]

