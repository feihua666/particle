import {isArray} from "../../../../../common/tools/ArrayTools";
import {ref} from "vue";

/**
 * 时间选择
 * 该属性适用于 PtTimePicker 和 el-time-picker
 */
const isArrayRef = ref(isArray)
// 类型值转为js属性值
const typeToProps = (value,form)=>{

    form['key'] = value
    let defaultValueArray = isArrayRef.value(form['defaultValue'])
    let rangeType = ['dates','datetimerange','daterange','monthrange']
    if(rangeType.indexOf(value) >= 0){
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
export const formDesignCompsDatePickerAttrs = [

    {
        field: {
            // defaultValue 为表单设计与渲染关键字，特殊处理联动问题
            name: 'defaultValue',
        },
        element: {
            comp: 'el-date-picker',
            formItemProps: {
                label: '默认值',
                title: '默认值将会在表单渲染时初始显示的值'
            },
            compProps: ({form}) => {
                return {
                    type: form['type'],
                    valueFormat: form['valueFormat'],
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
            name: 'type',
            value: 'date'
        },
        element: {
            comp: 'PtRadioGroup',
            formItemProps: {
                label: '类型',
            },
            compProps: {
                options: ['year','month','date','dates','datetime','week','datetimerange','daterange','monthrange'].map(item => ({id: item,name: item}))
            },
            compPropsHandler:{
                toProps: typeToProps
            }
        }
    },
    {
        field: {
            name: 'valueFormat',
            value: 'YYYY-MM-DD HH:mm:ss'
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '值格式化',
            },
            compProps: {
            },
        }
    },
]

