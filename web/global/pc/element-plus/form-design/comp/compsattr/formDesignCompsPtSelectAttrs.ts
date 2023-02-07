/**
 * 下拉选项
 * 该属性适用于 PtSelect
 */
import {isArray} from "../../../../../common/tools/ArrayTools";
import {ref} from 'vue'
import {formDesignParticleBuiltInComponentsAttrsData} from "../formDesignParticleBuiltInComponentsAttrs";
import { strCodeTocode} from "../../tools";
import {componentSize} from "../../attr/formDesignAttrsFormItemCompsAttrs";
const isArrayRef = ref(isArray)
const multipeToProps = (value,form)=>{

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
export const formDesignCompsPtSelectAttrs = [

    {
        field: {
            // defaultValue 为表单设计与渲染关键字，特殊处理联动问题
            name: 'defaultValue',
        },
        element: {
            comp: 'PtSelect',
            formItemProps: {
                label: '默认值',
                title: '默认值将会在表单渲染时初始显示的值'
            },
            compProps: ({form}) => {
                return {
                    options: strCodeTocode.value(form['options'],[]),
                    multiple: form.multiple,
                    collapseTags: form.collapseTags,
                    collapseTagsTooltip: form.collapseTagsTooltip,
                    multipleLimit: form.multipleLimit,
                    clearable: true
                }
            },
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
    componentSize,
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
            name: 'multiple',
            value: false,
        },
        element: {
            comp: 'el-switch',
            formItemProps: {
                label: '是否多选',
            },
            compProps: {
                activeText: '是',
                inactiveText: '否',
            },
            compPropsHandler:{
                toProps: multipeToProps
            }
        }
    },
    {
        field: {
            name: 'collapseTags',
            value: false
        },
        element: {
            comp: 'el-switch',
            formItemProps: {
                label: '多选折叠展示',
                labelTips: '多选时是否将选中值按文字的形式展示'
            },
            compProps: {
                activeText: '是',
                inactiveText: '否',
            }
        }
    },
    {
        field: {
            name: 'collapseTagsTooltip',
            value: false
        },
        element: {
            comp: 'el-switch',
            formItemProps: {
                label: '多选折叠展示提示',
                labelTips: '当鼠标悬停于折叠标签的文本时，是否显示所有选中的标签。<br/> 要使用此属性，collapse-tags属性必须设定为 true'
            },
            compProps: {
                activeText: '是',
                inactiveText: '否',
            }
        }
    },
    {
        field: {
            name: 'multipleLimit',
            value: 0
        },
        element: {
            comp: 'el-input-number',
            formItemProps: {
                label: '最多选择数',
                labelTips: 'multiple 属性设置为 true 时，代表多选场景下用户最多可以选择的项目数， 为 0 则不限制'
            },
            compProps: {
                min: 0,
            }
        }
    },
    ...formDesignParticleBuiltInComponentsAttrsData
]

