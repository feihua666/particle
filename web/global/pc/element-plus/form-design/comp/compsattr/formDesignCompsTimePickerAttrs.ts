import {isArray} from "../../../../../common/tools/ArrayTools";
import {ref} from "vue";

/**
 * 时间选择
 * 该属性适用于 PtTimePicker 和 el-time-picker
 */
const isArrayRef = ref(isArray)
const isRangeToProps = (value,form)=>{

    form['key'] = value
    let defaultValueArray = isArrayRef.value(form['defaultValue'])
    if(value === true){
        if (!defaultValueArray) {
            form['defaultValue'] = []
        }
    }else {
        if (defaultValueArray) {
            form['defaultValue'] = null
        }
    }
    return value
}
export const formDesignCompsTimePickerAttrs = [

    {
        field: {
            // defaultValue 为表单设计与渲染关键字，特殊处理联动问题
            name: 'defaultValue',
        },
        element: {
            comp: 'el-time-picker',
            formItemProps: {
                label: '默认值',
                title: '默认值将会在表单渲染时初始显示的值'
            },
            compProps: ({form}) => {
                return {
                    isRange: form['isRange'],
                    clearable: true,
                    key: form['key']
                }
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
    {
        field: {
            name: 'startPlaceholder',
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '开始占位提示',
                title: '占位提示即 placeholder',
                labelTips: '范围选择时开始日期的占位内容'
            },
            compProps: {
                clearable: true,
                placeholder: '我就是一个占位提示内容',
            }
        }
    },
    {
        field: {
            name: 'endPlaceholder',
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '结束占位提示',
                title: '占位提示即 placeholder',
                labelTips: '范围选择时结束日期的占位内容'
            },
            compProps: {
                clearable: true,
                placeholder: '我就是一个占位提示内容',
            }
        }
    },
    {
        field: {
            name: 'clearable',
            value: true
        },
        element: {
            comp: 'el-switch',
            formItemProps: {
                label: '一键清空',
                title: '会显示一个清空按钮，可一键清空'
            },
            compProps: {
                activeText: '一键清空',
                inactiveText: '不能一键清空',
            }
        }
    },
    {
        field: {
            name: 'isRange',
            value: false
        },
        element: {
            comp: 'el-switch',
            formItemProps: {
                label: '范围选择',
                title: '是否为时间范围选择，范围选择，需要选择一个开始时间和一个结束时间'
            },
            compProps: {
                activeText: '是',
                inactiveText: '否',
            },
            compPropsHandler:{
                toProps: isRangeToProps
            }
        }
    },
]

