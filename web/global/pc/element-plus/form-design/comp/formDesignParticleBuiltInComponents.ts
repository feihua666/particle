import {FormDesignItemType} from "../main/formDesignItemType";
import {formDesignCompsAttrs} from "./formDesignCompsAttrs.ts";
import {formDesignCompsInputAttrs} from "./compsattr/formDesignCompsInputAttrs";
import {formDesignCompsInputNumberAttrs} from "./compsattr/formDesignCompsInputNumberAttrs";
import {formDesignParticleBuiltInComponentsAttrsPermission} from "./formDesignParticleBuiltInComponentsAttrs";
import {formDesignCompsPtSelectAttrs} from "./compsattr/formDesignCompsPtSelectAttrs";
import {formDesignCompsPtRadioGroupAttrs} from "./compsattr/formDesignCompsPtRadioGroupAttrs";
import {formDesignCompsPtCheckboxGroupAttrs} from "./compsattr/formDesignCompsPtCheckboxGroupAttrs";
import {formDesignCompsTimePickerAttrs} from "./compsattr/formDesignCompsTimePickerAttrs";
import {formDesignCompsDatePickerAttrs} from "./compsattr/formDesignCompsDatePickerAttrs";
import {formDesignCompsSwitchAttrs} from "./compsattr/formDesignCompsSwitchAttrs";
import {formDesignCompsPtSecretTextAttrs} from "./compsattr/formDesignCompsPtSecretTextAttrs";
import {formDesignCompsColorPickerAttrs} from "./compsattr/formDesignCompsColorPickerAttrs";
import {formDesignCompsPtDividerAttrs} from "./compsattr/formDesignCompsPtDividerAttrs";
import {formDesignCompsPtCascaderAttrs} from "./compsattr/formDesignCompsPtCascaderAttrs";

/**
 * particle 内置的组件
 */
export const particleBuiltInComps:Array<FormDesignItemType> = [
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
            comp: 'PtInput',
            formItemProps: {
                label: '文本输入'
            },
            compProps: {

            }
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
                ...formDesignCompsInputAttrs,
                ...formDesignParticleBuiltInComponentsAttrsPermission

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
            comp: 'PtInputNumber',
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
                ...formDesignCompsInputNumberAttrs,
                ...formDesignParticleBuiltInComponentsAttrsPermission
            ]
        }
    },
    {
        // 用于展示使用
        view: {
            // 名称，用于显示组件名称
            name: '下拉选项',
            title: '当选项过多时，使用下拉菜单展示并选择内容。'
        },
        // container=容器，formItem=表单
        //
        type: 'formItem',
        // 用于渲染使用
        comps:{
            comp: 'PtSelect',
            formItemProps: {
                label: '下拉选项'
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
                ...formDesignCompsPtSelectAttrs,
                ...formDesignParticleBuiltInComponentsAttrsPermission
            ]
        }
    },
    {
        // 用于展示使用
        view: {
            // 名称，用于显示组件名称
            name: '级联选择',
            title: '当一个数据集合有清晰的层级结构时，可通过级联选择器逐级查看并选择。'
        },
        // container=容器，formItem=表单
        //
        type: 'formItem',
        // 用于渲染使用
        comps:{
            comp: 'PtCascader',
            formItemProps: {
                label: '级联选择'
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
                ...formDesignCompsPtCascaderAttrs,
                ...formDesignParticleBuiltInComponentsAttrsPermission
            ]
        }
    },
    {
        // 用于展示使用
        view: {
            // 名称，用于显示组件名称
            name: '单选框组',
            title: '在一组备选项中进行单选，适用于在多个互斥的选项中选择的场景'
        },
        // container=容器，formItem=表单
        //
        type: 'formItem',
        // 用于渲染使用
        comps:{
            comp: 'PtRadioGroup',
            formItemProps: {
                label: '单选框组'
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
                ...formDesignCompsPtRadioGroupAttrs,
                ...formDesignParticleBuiltInComponentsAttrsPermission
            ]
        }
    },
    {
        // 用于展示使用
        view: {
            // 名称，用于显示组件名称
            name: '多选框组',
            title: '适用于多个勾选框绑定到同一个数组的情景，通过是否勾选来表示这一组选项中选中的项。'
        },
        // container=容器，formItem=表单
        //
        type: 'formItem',
        // 用于渲染使用
        comps:{
            comp: 'PtCheckboxGroup',
            formItemProps: {
                label: '多选框组'
            },
            compProps: {},
        },
        // 用于属性设置使用
        attrs:{
            // 表单项的属性值，这只是一个空对象，可以设置默认值
            formItemForm: {},
            // 组件的属性值，这只是一个空对象，可以设置默认值
            compForm: {defaultValue: []},
            // 组件的属性们，数组项与 Form.vue 的属性 comps 数据项 一致
            compAttrs: [
                ...formDesignCompsAttrs,
                ...formDesignCompsPtCheckboxGroupAttrs,
                ...formDesignParticleBuiltInComponentsAttrsPermission
            ]
        }
    },
    {
        // 用于展示使用
        view: {
            // 名称，用于显示组件名称
            name: '时间选择',
            title: '用于选择或输入日时间，支持范围选择'
        },
        // container=容器，formItem=表单
        //
        type: 'formItem',
        // 用于渲染使用
        comps:{
            comp: 'PtTimePicker',
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
            // key 用来切换组件状态
            compForm: {key: null},
            // 组件的属性们，数组项与 Form.vue 的属性 comps 数据项 一致
            compAttrs: [
                ...formDesignCompsAttrs,
                ...formDesignCompsTimePickerAttrs,
                ...formDesignParticleBuiltInComponentsAttrsPermission
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
            comp: 'PtDatePicker',
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
                ...formDesignParticleBuiltInComponentsAttrsPermission
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
            comp: 'PtSwitch',
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
                ...formDesignParticleBuiltInComponentsAttrsPermission
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
            comp: 'PtColorPicker',
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
                ...formDesignParticleBuiltInComponentsAttrsPermission
            ]
        }
    },
    {
        // 用于展示使用
        view: {
            // 名称，用于显示组件名称
            name: '静态文本',
            title: '用于展示纯文本，支持敏感信息星号展示'
        },
        // container=容器，formItem=表单
        //
        type: 'formItem',
        // 用于渲染使用
        comps:{
            comp: 'PtSecretText',
            formItemProps: {
                label: '静态文本'
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
                ...formDesignCompsPtSecretTextAttrs,
                ...formDesignParticleBuiltInComponentsAttrsPermission
            ]
        }
    },
    {
        // 用于展示使用
        view: {
            // 名称，用于显示组件名称
            name: '分隔线',
            title: '区隔内容的分割线'
        },
        // container=容器，formItem=表单
        //
        type: 'formItem',
        // 用于渲染使用
        comps:{
            comp: 'PtDivider',
            formItemProps: {
                label: '',
                class: 'pt-width-100-pc'
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
                ...formDesignCompsPtDividerAttrs,
            ],
            // 表单属性设置为空数组，没有属性可设置
            formItemAttrs: []
        }
    },
]
