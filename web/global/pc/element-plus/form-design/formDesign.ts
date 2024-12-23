import {ref, watch} from "vue";
import {addProp, cloneObj, getValue} from "../../../common/tools/ObjectTools";
import {v4 as uuidv4} from 'uuid';
import {FormDesignItemType} from "./main/formDesignItemType";
import {getCurrentTimestamps} from "../../../common/tools/DateTools";
import {backMove, frontMove, remove} from "../../../common/tools/ArrayTools";
import {formDesignFormSettingAttrs} from "./attr/formDesignFormSettingAttrs";

// 表单设计配置数据,类型为 FormDesignItemType
const formDesignData = ref({
    formDesignForm: {},
    formDesignItems: [],
    formDesignFormSettingForm: {},
    formDesignFormSettingFormProps: {},
    formDesignFormSettingAttrs,
})
// 历史数据，可以后退或重做
const formDesignHistoryData = ref({
    lastAddNewHistoryStepAt: 0,
    undoOrRedoFlag: false,
    index: -1,
    maxStep: 30,
    /**
     * 数组项为一个对象
     * 记录各数据历史
     * {
     *      formDesignForm: {},
                formDesignItems: [],
                formDesignFormSettingForm: {},
                formDesignFormSettingFormProps: {},
     * }
     */
    steps: []
})
const listeners = {
    onAddNew: []
}
// 新添加一个的选中
const formDesignItemSelectNew =  (selectedIndex) =>  {
    let item = formDesignData.value.formDesignItems[selectedIndex]
    // 复制一个新的，否则该数据是引用的可用组件的数据，导致问题
    let netItem = cloneObj(item)
    // 唯一id
    netItem.uniqueId = uuidv4()

    listeners.onAddNew.forEach(callback => {
        callback(netItem)
    })
    formDesignData.value.formDesignItems.splice(selectedIndex,1,netItem)
    formDesignItemSelectState(selectedIndex,true)
    addNewHistoryStep()
}
// 选中状态设置
const formDesignItemSelectState = (selectedIndex,isSelected) =>{
    addProp(formDesignData.value.formDesignItems[selectedIndex],'designControl.formDesignItem.isSelected',isSelected)
}
// 全部取消选中,excludeIndex有值则排除该索引不处理
const formDesignItemUnSelectAll = (excludeIndex) => {
    formDesignData.value.formDesignItems.forEach((item,index)=>{
        if(excludeIndex == index){
            return
        }
        formDesignItemSelectState(index,false)

    })
}
// 初始化表单
const formDesignItemInitFormKey = (formDesignItem: FormDesignItemType)=>{
    if (!formDesignItem) {
        return
    }
    let field  = formDesignItem.comps.field
    // 没有存在生成一个
    if(!field){
        field = {
            name: formDesignItem.comps.compName || formDesignItem.comps.comp + ''
        }
        formDesignItem.comps.field = field
    }
    // 如果已在 form 中存在，新建一个保证不重复
    field.name = field.name + getCurrentTimestamps()

    formDesignData.value.formDesignForm[field.name] = field.value || null


    // 将字段名初始表单
    let fieldNameItem = formDesignItem.attrs.compAttrs.find(item => item?.field.name == 'fieldName')
    if(fieldNameItem){
        fieldNameItem.field.value = field.name
    }
    // 默认表单项的属性字段和组件的字段名称保持一致
    formDesignItem.attrs.formItemForm['prop'] = field.name

}
// 上移组件
const formDesignItemUpMove = (index)=>{
    frontMove(formDesignData.value.formDesignItems,index)
    addNewHistoryStep()
}
// 下移组件
const formDesignItemDownMove = (index)=>{
    backMove(formDesignData.value.formDesignItems,index)
    addNewHistoryStep()
}
// 移除组件
const formDesignItemDelete = (index)=>{
    remove(formDesignData.value.formDesignItems,index)
    addNewHistoryStep()
}
/*****************历史步骤相关***********************/
// 添加新的历史
const addNewHistoryStep =() => {
    // 在摊销或重做时，由于也会有表单变化，导致重新添加数据错乱
    if (formDesignHistoryData.value.undoOrRedoFlag) {
        return;
    }
    // 一定时间内只添加一次，人操作基本没那么快
    let currentTime = new Date().getTime()
    if(formDesignHistoryData.value.lastAddNewHistoryStepAt != 0){
        if(currentTime - formDesignHistoryData.value.lastAddNewHistoryStepAt < 500){
            return
        }
    }
    formDesignHistoryData.value.lastAddNewHistoryStepAt = currentTime

    if (formDesignHistoryData.value.index === formDesignHistoryData.value.maxStep - 1) {
        formDesignHistoryData.value.steps.shift()
    } else {
        formDesignHistoryData.value.index++
    }
    formDesignHistoryData.value.steps[formDesignHistoryData.value.index] = {
        formDesignForm: cloneObj(formDesignData.value.formDesignForm),
        formDesignItems: cloneObj(formDesignData.value.formDesignItems),
        formDesignFormSettingForm: cloneObj(formDesignData.value.formDesignFormSettingForm),
        formDesignFormSettingFormProps: cloneObj(formDesignData.value.formDesignFormSettingFormProps),
    }
    if (formDesignHistoryData.value.index < formDesignHistoryData.value.steps.length - 1) {
        formDesignHistoryData.value.steps = formDesignHistoryData.value.steps.slice(0, formDesignHistoryData.value.index + 1)
    }
}
const applyHistoryStep = () => {
    if(formDesignHistoryData.value.index < 0){
        return
    }
    let historyStep = formDesignHistoryData.value.steps[formDesignHistoryData.value.index]
    formDesignData.value.formDesignForm = cloneObj(historyStep.formDesignForm)
    formDesignData.value.formDesignItems = cloneObj(historyStep.formDesignItems)
    formDesignData.value.formDesignFormSettingForm = cloneObj(historyStep.formDesignFormSettingForm)
    formDesignData.value.formDesignFormSettingFormProps = cloneObj(historyStep.formDesignFormSettingFormProps)
}
const undoHistoryStep =() => {
    if (formDesignHistoryData.value.undoOrRedoFlag) {
        return;
    }
    formDesignHistoryData.value.undoOrRedoFlag = true

    if(formDesignHistoryData.value.index !== 0){
        formDesignHistoryData.value.index--
    }
    applyHistoryStep()
    setTimeout(()=>{formDesignHistoryData.value.undoOrRedoFlag = false},200)

}
const redoHistoryStep =() => {
    if (formDesignHistoryData.value.undoOrRedoFlag) {
        return;
    }
    formDesignHistoryData.value.undoOrRedoFlag = true
    if (formDesignHistoryData.value.index !== (formDesignHistoryData.value.steps.length - 1)) {
        formDesignHistoryData.value.index++
    }
    applyHistoryStep()
    setTimeout(()=>{formDesignHistoryData.value.undoOrRedoFlag = false},200)
}

const formDesignDataControl = {
    // 新添加一个的选中
    formDesignItemSelectNew,
    // 选中状态设置
    formDesignItemSelectState,
    // 全部取消选中,excludeIndex有值则排除该索引不处理
    formDesignItemUnSelectAll,
    // 初始化设置form表单
    formDesignItemInitFormKey,
    formDesignItemUpMove,
    formDesignItemDownMove,
    formDesignItemDelete,
    addNewHistoryStep,
    undoHistoryStep,
    redoHistoryStep,
    // 绑定监听
    bindListener: (name: string,callback: Function) => {
        listeners[name].push(callback)
    },
    removeListener: (name: string,callback: Function) => {
        let index = listeners[name].indexOf(callback)
        listeners[name].splice(index,1)
    },
}

// 当前选中的编辑表单项
const currentFormDesignItemData = ref(null)

// 设置当前选中的表单
watch(()=> formDesignData.value.formDesignItems,(newValue)=>{
    let currentItem = newValue.find(item => getValue(item,'designControl.formDesignItem.isSelected'))
    currentFormDesignItemData.value = currentItem
},{
    deep: true
})

/**
 * 暴露接口
 */
export const useFormDesign = () =>{

    return {
        formDesignData,
        formDesignDataControl,
        currentFormDesignItemData,
        formDesignHistoryData
    }
}
