/**
 * 表单项组件通用属性
 */
import {FormDesignItemType} from "../main/formDesignItemType";
import {formChange, initForm} from "../attr/FormDesignAttrs";

/**
 * 字段通用属性
 */
export const formDesignCompsAttrs = [
    {
        field: {
            name: 'fieldName',
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '字段名称',
                required: true,
                title: '字段名称用来在表单提交时的对象key'
            },
            compProps: {
                clearable: true,
                placeholder: '例：userNickname',
            }
        }
    },
    {
        field: {
            name: 'disabled',
            value: false
        },
        element: {
            comp: 'el-switch',
            formItemProps: {
                label: '是否禁用',
                title: '如果禁用不影响数据加载及展示，只代表用户不能操作'
            },
            compProps: {
                activeText: '禁用',
                inactiveText: '启用',
            }
        }
    },
]


/**
 * 组件属性与表单属性对应关系
 * 用于转换其key，因为可能会不一样
 */
const compPropToFormAttrMapping = {

}
/**
 * 初始化表单
 * 用于在新拖拽一个表单时，将默认属性设置到表单中
 * @param formDesignItem
 */
export const formDesignAttrsCompInitForm =(formDesignItem: FormDesignItemType)=>{
    if(!formDesignItem){
        return
    }
    let form = formDesignItem.attrs.compForm
    let formItemProps = formDesignItem.comps.compProps
    initForm(form,formItemProps,formDesignItem.attrs.compAttrs,'compPropsHandler',compPropToFormAttrMapping)
}
/**
 * 在表单变化时，联动组件内容
 * @param formDesignItem
 */
export const formDesignAttrsCompFormChange = (formDesignItem: FormDesignItemType,formDesignForm)=>{
    if(!formDesignItem){
        return
    }
    let form = formDesignItem.attrs.compForm
    let formItemProps = formDesignItem.comps.compProps
    formChange(form,formItemProps,formDesignItem.attrs.compAttrs,'compPropsHandler',compPropToFormAttrMapping,['defaultValue','fieldName'])

    formDesignForm[form['fieldName']] = form['defaultValue']
    // 组件字段名称联动 表单项 prop 属性
    formDesignItem.attrs.formItemForm['prop'] = form['fieldName']
}