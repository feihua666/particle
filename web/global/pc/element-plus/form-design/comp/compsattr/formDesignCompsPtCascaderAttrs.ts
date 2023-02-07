/**
 * 级联选择选项
 * 该属性适用于 PtCascader
 */
import {isArray} from "../../../../../common/tools/ArrayTools";
import {ref} from 'vue'
import {
    formDesignParticleBuiltInComponentsAttrsData,
    formDesignParticleBuiltInComponentsAttrsTreeData
} from "../formDesignParticleBuiltInComponentsAttrs";
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
export const formDesignCompsPtCascaderAttrs = [

    {
        field: {
            // defaultValue 为表单设计与渲染关键字，特殊处理联动问题
            name: 'defaultValue',
        },
        element: {
            comp: 'PtCascader',
            formItemProps: {
                label: '默认值',
                title: '默认值将会在表单渲染时初始显示的值'
            },
            compProps: ({form}) => {
                return {
                    options: strCodeTocode.value(form['options'],[]),
                    collapseTags: form.collapseTags,
                    collapseTagsTooltip: form.collapseTagsTooltip,
                    showAllLevels: form.showAllLevels,
                    filterable: true
                }
            },
        }
    },
    {
        field: {
            name: 'showAllLevels',
            value: false
        },
        element: {
            comp: 'el-switch',
            formItemProps: {
                label: '显示完整路径',
                title: '输入框中是否显示选中值的完整路径'
            },
            compProps: {
                activeText: '显示',
                inactiveText: '不显示',
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
            name: 'filterable',
            value: true
        },
        element: {
            comp: 'el-switch',
            formItemProps: {
                label: '选项是否可以被搜索',
            },
            compProps: {
                activeText: '允许',
                inactiveText: '不允许',
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
    ...formDesignParticleBuiltInComponentsAttrsTreeData
]

