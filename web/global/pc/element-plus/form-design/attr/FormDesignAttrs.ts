import {getPropByValue} from "../../../../common/tools/ObjectTools";

/**
 * 根据 根据值获取key
 * @param value
 */
const getMappingKey = (formToAttrsMapping,value: string) => {

    return getPropByValue(formToAttrsMapping,value)
}
/**
 * 初始化表单
 * 用于在新拖拽一个表单时，将默认属性设置到表单中
 * @param form 表单对象，指组件属性表单或表单项属性表单 如：FormDesignItemType.attrs.formItemForm 或 FormDesignItemType.attrs.compForm
 * @param props 组件定义属性对象 如：FormDesignItemType.comps.formItemProps 或 FormDesignItemType.comps.compProps
 * @param attrs 组件属性表单或表单项属性表单定义数组 如：formDesignAttrsFormItemCompsAttrs
 * @param formPropToformAttrMapping 从form到props字典映射，可能不一样需要加映射
 */
export const initForm =(form,props,attrs,valueHanderKey,formPropToformAttrMapping)=>{
    for (let formItemPropsKey in props) {
        let formKey = formPropToformAttrMapping[formItemPropsKey] || formItemPropsKey
        let existItem = attrs.find(item => item.field.name == formKey)
        if (existItem) {
            // existItem 可能是一个响应试对象，所以尝试使用 .value获取一次
            let valueHandler = existItem?.element[valueHanderKey] || existItem?.value?.element[valueHanderKey]
            if (valueHandler && valueHandler.toForm) {
                form[formKey] = valueHandler.toForm(props[formItemPropsKey],form,props)
            }else {
                form[formKey] = props[formItemPropsKey]
            }

        }
    }
}
/**
 * 在表单项表单变化时，联动组件内容
 * @param form 表单对象，指组件属性表单或表单项属性表单
 * @param props 组件定义属性对象
 * @param attrs 组件属性表单或表单项属性表单定义数组
 * @param formPropToformAttrMapping 从form到props字典映射，可能不一样需要加映射
 */
export const formChange = (form,props,attrs,valueHanderKey,formPropToformAttrMapping, ignoreFormKeys=[])=>{

    for (let formKey in form) {
        let formItemPropsKey = getMappingKey(formPropToformAttrMapping,formKey) || formKey

        // undefined 不设置值
        if(form[formKey] === undefined){
            continue
        }
        if (ignoreFormKeys.some(ignoreFormKey => ignoreFormKey == formItemPropsKey)) {
            continue
        }
        // existItem 有可能会不存在，有可能只在form中定义了属性
        let existItem = attrs.find(item => item.field.name == formKey)
        // existItem 可能是一个响应试对象，所以尝试使用 .value获取一次
        let valueHandler = existItem?.element[valueHanderKey] || existItem?.value?.element[valueHanderKey]

        if (valueHandler && valueHandler.toProps) {
            props[formItemPropsKey] = valueHandler.toProps(form[formKey],form,props)
        }else {
            props[formItemPropsKey] = form[formKey]
        }

    }

}