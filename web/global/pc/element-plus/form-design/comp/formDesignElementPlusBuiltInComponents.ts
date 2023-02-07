import {FormDesignItemType} from "../main/formDesignItemType";
import {formDesignCompsAttrs} from "./formDesignCompsAttrs";
import {formDesignCompsInputAttrs} from "./compsattr/formDesignCompsInputAttrs";
import {formDesignCompsInputNumberAttrs} from "./compsattr/formDesignCompsInputNumberAttrs";
import {formDesignCompsTimePickerAttrs} from "./compsattr/formDesignCompsTimePickerAttrs";
import {formDesignCompsDatePickerAttrs} from "./compsattr/formDesignCompsDatePickerAttrs";
import {formDesignCompsSwitchAttrs} from "./compsattr/formDesignCompsSwitchAttrs";
import {formDesignParticleBuiltInComponentsAttrsPermission} from "./formDesignParticleBuiltInComponentsAttrs";
import {formDesignCompsRateAttrs} from "./compsattr/formDesignCompsRateAttrs";
import {formDesignCompsSliderAttrs} from "./compsattr/formDesignCompsSliderAttrs";
import {formDesignCompsColorPickerAttrs} from "./compsattr/formDesignCompsColorPickerAttrs";
/**
 * element plus 内置组件
 */
export const elementPlusBuiltInComps:Array<FormDesignItemType> = [
    {
        // 用于展示使用
        view: {
            // 名称，用于显示组件名称
            name: '文本输入',
            title: '通过鼠标或键盘输入字符'

        },
        // container=容器，formItem=表单
        //
        type: 'formItem',
        // 用于渲染使用
        comps:{
            comp: 'el-input',
            formItemProps: {
                label: '文本输入'
            },
            compProps: {}
        },
        // 用于属性设置使用
        attrs:{
            // 表单项的属性值，这只是一个空对象，可以设置默认值
            formItemForm: {},
            // 组件的属性值，这只是一个空对象，可以设置默认值
            compForm: {},
            // 组件的属性们，数组项与 Form.vue 的属性 comps 数据项 一致
            compAttrs: [
                ...formDesignCompsAttrs,
                ...formDesignCompsInputAttrs
            ]
        }
    },
    {
        // 用于展示使用
        view: {
            // 名称，用于显示组件名称
            name: '数字输入',
            title: '仅允许输入标准的数字值，可定义范围'
        },
        // container=容器，formItem=表单
        //
        type: 'formItem',
        // 用于渲染使用
        comps:{
            comp: 'el-input-number',
            formItemProps: {
                label: '数字输入'
            },
            compProps: {},
        },
        // 用于属性设置使用
        attrs:{
            // 表单项的属性值，这只是一个空对象，可以设置默认值
            formItemForm: {},
            // 组件的属性值，这只是一个空对象，可以设置默认值
            compForm: {},
            // 组件的属性们，数组项与 Form.vue 的属性 comps 数据项 一致
            compAttrs: [
                ...formDesignCompsAttrs,
                ...formDesignCompsInputNumberAttrs
            ]
        }
    },
    {
        // 用于展示使用
        view: {
            // 名称，用于显示组件名称
            name: '时间选择',
            title: '用于选择或输入日时间'
        },
        // container=容器，formItem=表单
        //
        type: 'formItem',
        // 用于渲染使用
        comps:{
            comp: 'el-time-picker',
            formItemProps: {
                label: '时间选择'
            },
            compProps: {},
        },
        // 用于属性设置使用
        attrs:{
            // 表单项的属性值，这只是一个空对象，可以设置默认值
            formItemForm: {},
            // 组件的属性值，这只是一个空对象，可以设置默认值
            compForm: {},
            // 组件的属性们，数组项与 Form.vue 的属性 comps 数据项 一致
            compAttrs: [
                ...formDesignCompsAttrs,
                ...formDesignCompsTimePickerAttrs,
            ]
        }
    },
    {
        // 用于展示使用
        view: {
            // 名称，用于显示组件名称
            name: '日期选择',
            title: '用于选择或输入日期，支持范围选择，支持选择到时间'
        },
        // container=容器，formItem=表单
        //
        type: 'formItem',
        // 用于渲染使用
        comps:{
            comp: 'el-date-picker',
            formItemProps: {
                label: '日期选择'
            },
            compProps: {},
        },
        // 用于属性设置使用
        attrs:{
            // 表单项的属性值，这只是一个空对象，可以设置默认值
            formItemForm: {},
            // 组件的属性值，这只是一个空对象，可以设置默认值
            // key 用来切换组件状态
            compForm: {key: null},
            // 组件的属性们，数组项与 Form.vue 的属性 comps 数据项 一致
            compAttrs: [
                ...formDesignCompsAttrs,
                ...formDesignCompsDatePickerAttrs,
            ]
        }
    },
    {
        // 用于展示使用
        view: {
            // 名称，用于显示组件名称
            name: '开关',
            title: '表示两种相互对立的状态间的切换，多用于触发「开/关」'
        },
        // container=容器，formItem=表单
        //
        type: 'formItem',
        // 用于渲染使用
        comps:{
            comp: 'el-switch',
            formItemProps: {
                label: '开关'
            },
            compProps: {},
        },
        // 用于属性设置使用
        attrs:{
            // 表单项的属性值，这只是一个空对象，可以设置默认值
            formItemForm: {},
            // 组件的属性值，这只是一个空对象，可以设置默认值
            compForm: {},
            // 组件的属性们，数组项与 Form.vue 的属性 comps 数据项 一致
            compAttrs: [
                ...formDesignCompsAttrs,
                ...formDesignCompsSwitchAttrs,
            ]
        }
    },
    {
        // 用于展示使用
        view: {
            // 名称，用于显示组件名称
            name: '评分',
            title: '用于评分'
        },
        // container=容器，formItem=表单
        //
        type: 'formItem',
        // 用于渲染使用
        comps:{
            comp: 'el-rate',
            formItemProps: {
                label: '评分'
            },
            compProps: {},
        },
        // 用于属性设置使用
        attrs:{
            // 表单项的属性值，这只是一个空对象，可以设置默认值
            formItemForm: {},
            // 组件的属性值，这只是一个空对象，可以设置默认值
            compForm: {},
            // 组件的属性们，数组项与 Form.vue 的属性 comps 数据项 一致
            compAttrs: [
                ...formDesignCompsAttrs,
                ...formDesignCompsRateAttrs,
            ]
        }
    },
    {
        // 用于展示使用
        view: {
            // 名称，用于显示组件名称
            name: '滑块',
            title: '通过拖动滑块在一个固定区间内进行选择'
        },
        // container=容器，formItem=表单
        //
        type: 'formItem',
        // 用于渲染使用
        comps:{
            comp: 'el-slider',
            formItemProps: {
                label: '滑块'
            },
            compProps: {},
        },
        // 用于属性设置使用
        attrs:{
            // 表单项的属性值，这只是一个空对象，可以设置默认值
            formItemForm: {},
            // 组件的属性值，这只是一个空对象，可以设置默认值
            compForm: {},
            // 组件的属性们，数组项与 Form.vue 的属性 comps 数据项 一致
            compAttrs: [
                ...formDesignCompsAttrs,
                ...formDesignCompsSliderAttrs,
            ]
        }
    },
    {
        // 用于展示使用
        view: {
            // 名称，用于显示组件名称
            name: '颜色选择',
            title: '用于颜色选择，支持多种格式。'
        },
        // container=容器，formItem=表单
        //
        type: 'formItem',
        // 用于渲染使用
        comps:{
            comp: 'el-color-picker',
            formItemProps: {
                label: '颜色选择器'
            },
            compProps: {},
        },
        // 用于属性设置使用
        attrs:{
            // 表单项的属性值，这只是一个空对象，可以设置默认值
            formItemForm: {},
            // 组件的属性值，这只是一个空对象，可以设置默认值
            compForm: {},
            // 组件的属性们，数组项与 Form.vue 的属性 comps 数据项 一致
            compAttrs: [
                ...formDesignCompsAttrs,
                ...formDesignCompsColorPickerAttrs,
            ]
        }
    },
]