import {FormDesignItemType} from "../main/formDesignItemType";
import {formChange, initForm} from "./FormDesignAttrs";
import {hasOwnProps} from "../../../../common/tools/ObjectTools";
import {codeToStrCode, strCodeTocode} from "../tools";

const rulesToProp = (value) => {return strCodeTocode.value(value,[])}
const rulesToForm = (value) => {return codeToStrCode.value(value,'[]')}


/**
 * 组件大小
 */
export const componentSize = {
    field: {
        name: 'size',
    },
    element: {
        comp: 'PtRadioGroup',
        formItemProps: {
            label: '大小',
            title: '用于控制该组件的默认尺寸'
        },
        compProps: {
            buttonView: true,
            options: [
                {
                    id: 'small',
                    name: '小'
                },
                {
                    id: 'default',
                    name: '默认'
                },
                {
                    id: 'large',
                    name: '大'
                },
                {
                    id: '',
                    name: '不设置'
                },
            ]
        }
    }
}

/**
 * 表单项组件通用属性
 */
export const formDesignAttrsFormItemCompsAttrs = [

    {
        field: {
            name: 'prop',
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '表单属性',
                title: '表单属性主要用来表单校验使用，一般与表单下的组件字段名称保持一致',
                labelTips: 'model 的键名。 它可以是一个路径数组(例如 [\'a\', \'b\', 0])。 <br/>在定义了 validate、resetFields 的方法时，该属性是必填的'
            },
            compProps: {
                clearable: true,
                placeholder: '例：userNickname',
            }
        }
    },
    {
        field: {
            name: 'label',
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '标签文本',
                title: '标签用来展示表单代表的用途'
            },
            compProps: {
                clearable: true,
                placeholder: '例：用户昵称',
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
                title: '标签宽度可以通过表单设置统一设置宽度，也可以在这里单独指定',
                labelTips: '例如 \'50px\'。 可以使用 auto。建议这里不设置使用统一表单设置'
            },
            compProps: {
                clearable: true
            }
        }
    },
    {
        field: {
            name: 'required',
            value: false
        },
        element: {
            comp: 'el-switch',
            formItemProps: {
                label: '是否必填',
                title: '如果必填则会在标签边上出现一个红星以提示',
                labelTips: '如不设置，则会根据校验规则确认'
            },
            compProps: {
                activeText: '必填',
                inactiveText: '非必填',
            }
        }
    },
    {
        field: {
            name: 'useLabelAsRequiredText',
            value: false,
            // 数据变化处理
            valueChange:({form,newValue,oldValue})=>{
                formItemRulesAttrHandle(form)
            }
        },
        element: {
            comp: 'el-switch',
            formItemProps: {
                label: '使用标签文本作为必填提示',
            },
            compProps: {
                activeText: '使用',
                inactiveText: '不使用',
            }
        }
    },
    {
        field: {
            name: 'inlineMessage',
            value: false
        },
        element: {
            comp: 'el-switch',
            formItemProps: {
                label: '行内显示校验信息',
                title: '是否在行内显示校验信息',
            },
            compProps: {
                activeText: '行内显示',
                inactiveText: '块级显示',
            }
        }
    },
    componentSize,
    {
        field: {
            name: 'rules',
            value: '[]'
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '验证规则',
                title: '表单项验证规则是一个数组来定义验证规则',
                labelTips: '例：[<br/>' +
                    '        { required: true, message: \'age is required\' },<br/>' +
                    '        { type: \'number\', message: \'age must be a number\' },<br/>' +
                    '      ]'
            },

            // 用于formItem 属性与可视化表单ui双向数据转换控制
            formItemPropsHandler: {
                toForm: rulesToForm,
                toProps: rulesToProp,
            },
            compProps: {
                clearable: true,
                type: 'textarea',
                placeholder: '验证规则',
            }
        }
    },
]

/**
 * 表单项组件属性与表单属性对应关系
 * 用于转换其key，因为可能会不一样
 */
const formItemPropToFormAttrMapping = {
}
/**
 * 初始化表单项
 * 用于在新拖拽一个表单时，将默认属性设置到表单中
 * @param formDesignItem
 */
export const formDesignAttrsFormItemInitForm =(formDesignItem: FormDesignItemType)=>{
    if(!formDesignItem){
        return
    }
    let form = formDesignItem.attrs.formItemForm
    let formItemProps = formDesignItem.comps.formItemProps
    initForm(form,formItemProps,formDesignAttrsFormItemCompsAttrs,'formItemPropsHandler',formItemPropToFormAttrMapping)
}
/**
 * 在表单项表单变化时，联动组件内容
 * @param formDesignItem
 */
export const formDesignAttrsFormItemFormChange = (formDesignItem: FormDesignItemType)=>{
    if(!formDesignItem){
        return
    }
    let form = formDesignItem.attrs.formItemForm
    let formItemProps = formDesignItem.comps.formItemProps
    // formItemRulesAttrHandle(form)
    formChange(form,formItemProps,formDesignAttrsFormItemCompsAttrs,'formItemPropsHandler',formItemPropToFormAttrMapping,['useLabelAsRequiredText'])


}

const formItemRulesAttrHandle = (form) => {
    let rules = rulesToProp(form['rules'])
    let requiredExist = false
    let index = -1;
    for (let i = 0; i < rules.length; i++) {
        if(hasOwnProps(rules[i],'required')){
            requiredExist = true
            index = i
            break
        }
    }
    if(form['useLabelAsRequiredText']){
        if(requiredExist){
            rules[index].message = form['label'] + ' 不能为空'
        }else {
            rules.push({required: true,message: form['label'] + ' 不能为空',trigger: 'blur'})
        }

    }else {
        if(requiredExist){
            rules.splice(index,1)
        }
    }

    form['rules'] = rulesToForm(rules)
}