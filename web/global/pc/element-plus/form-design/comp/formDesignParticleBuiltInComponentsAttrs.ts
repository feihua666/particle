/**
 * particle 内置的组件通用属性
 */
import {codeToStrCode, strCodeTocode} from "../tools";

/**
 * 权限属性
 */
export const formDesignParticleBuiltInComponentsAttrsPermission = [
    {
        field: {
            name: 'permission',
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '权限码',
                title: '支持权限设置,用来控制表单项的权限',
                labelTips: '需注入权限才能生效，参见表单设置。<br/>权限功能即使不使用particle封装组件，直接使用PtForm也同样支持。<br/>例：updateUserNickname'
            },
            compProps: {
                clearable: true,
                placeholder: '权限码'
            }
        }
    },
    {
        field: {
            name: 'noPermissionView',
            value: 'disabled'
        },
        element: {
            comp: 'PtRadioGroup',
            formItemProps: {
                label: '无权限时',
                title: '设置无权限时的提示功能',
                labelTips: '表单设计只支持两种。<br/>另外还支持 hide|notRender=不展示，customFn=自定义函数触发'
            },
            compProps: {
                options: [
                    {
                        id: 'disabled',
                        name: '禁用'
                    },
                    {
                        id: 'alert',
                        name: '弹窗提示'
                    },
                ]
            }
        }
    },
    {
        field: {
            name: 'noPermissionText',
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '无权限时提示信息',
                title: '设置无权限时的提示文本，这在禁用或弹窗提示时都有使用',
                labelTips: '不填写默认提示无权限'
            },
            compProps: {
                clearable: true,
                placeholder: '如：您没有此 按钮 权限'
            }
        }
    },
]

/**
 * 数据属性
 */
const attrsData = [{id: 'id1',name: 'name1'},{id: 'id2',name: 'name2'}]
export const formDesignParticleBuiltInComponentsAttrsData = [
    {
        field: {
            name: 'options',
            value: JSON.stringify(attrsData),

        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '数据',
                labelTips: '是一个数组，数组元素包括两个属性，id、name<br/>'
            },
            compProps: ({form}) => {
                return {
                    type: 'textarea',
                }
            },
            compPropsHandler: {
                toForm: (value) => {return codeToStrCode.value(value,JSON.stringify(attrsData))},
                toProps: (value) => {return strCodeTocode.value(value,[])}
            }
        }
    },
]
const attrsTreeData = [{id: 'id1',name: 'name1',children: [{id: 'id11',name: 'name11'}]},{id: 'id2',name: 'name2'}]

export const formDesignParticleBuiltInComponentsAttrsTreeData = [
    {
        field: {
            name: 'options',
            value: JSON.stringify(attrsTreeData),

        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '数据',
                labelTips: '是一个数组，数组元素包括两个属性，id、name<br/>'
            },
            compProps: ({form}) => {
                return {
                    type: 'textarea',
                }
            },
            compPropsHandler: {
                toForm: (value) => {return codeToStrCode.value(value,JSON.stringify(attrsTreeData))},
                toProps: (value) => {return strCodeTocode.value(value,[])}
            }
        }
    },
]
