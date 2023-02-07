/**
 * 表单设计数据基础类型
 */
import {anyObj} from "../../../../common/tools/ObjectTools";
export interface PropsHandler{
    toForm?: (propsValue) => any
    toProps?: (formValue) => any
}
export interface FormDesignItemType{
    // 唯一id，全局唯一
    uniqueId?: string
    // 用于展示使用
    view: {
        // 名称，用于显示组件名称
        name: string,
        // 图标支持的图标字符串
        icon?: string,
        // 鼠标移到可拖拽元素上面的提示
        title?:string
    },
    // container=容器，formItem=表单
    type: 'container' | 'formItem',
    // 用于渲染使用,这基本与 Form.vue 的属性 comps 中项的 element 属性 一致
    comps: {
        // 字段表单数据信息
        field?: {
            // 表单key
            name: string,
            // 默认值
            value?: any
        },
        // 组件 string=组件注册名，object=组件实例
        comp: string|object,
        // comp 为组件对象时，这里填写组件注册名
        compName?: string
        // formItem 属性
        formItemProps: anyObj,
        // 组件的属性
        compProps: anyObj,
    },
    // 用于属性设置使用
    attrs: {
        // 表单项的属性值，这只是一个空对象，可以设置默认值
        formItemForm: anyObj,
        // 表单项的属性们，数组项与 Form.vue 的属性 comps 数据项 一致,默认为 formDesignAttrsFormItemCompsAttrs.ts 中的 formDesignAttrsFormItemCompsAttrs
        formItemAttrs?: any[]
        // 组件的属性值，这只是一个空对象，可以设置默认值
        compForm: anyObj,
        // 组件的属性们，数组项与 Form.vue 的属性 comps 数据项 一致,其中 element.formItemPropsHandler 和 element.compPropsHandler是多出来的，类型为 PropsHandler 属性处理器用来自定义处理数据值
        compAttrs: any[]
    },
    // 表单设置控制变量
    designControl?: {
        // 表单项控制相关
        formDesignItem: {
            // 是否被选中
            isSelected: boolean
        },
    }
}