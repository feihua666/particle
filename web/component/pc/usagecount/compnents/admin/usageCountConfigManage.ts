import {useCascaderUsageCountDefineCompItem} from "../usageCountCompItem";
import {useSelectTenantCompItem} from "../../../tenant/compnents/tenantCompItem";

export const pageFormItems = [
    {
        field: {
            name: 'name',
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '名称',

            },
            compProps: {
                clearable: true,
                placeholder: '左前缀匹配',
            }
        }
    },
    useCascaderUsageCountDefineCompItem({
        fieldName: 'usageCountDefineId',
        label: '使用次数定义',
        disableGroup: true
    }),
    {
        field: {
            name: 'limitRuleTypeDictId',
        },
        element: {
            comp: 'PtDictFrontSelect',
            formItemProps: {
                label: '限制规则类型',

            },
            compProps: {
                // 字典查询
                dictParam: {groupCode: 'usage_count_limit_rule_type'}
            }
        }
    },
    {
        field: {
            name: 'limitPeriodDictId',
        },
        element: {
            comp: 'PtDictFrontSelect',
            formItemProps: {
                label: '限制周期',

            },
            compProps: {
                // 字典查询
                dictParam: {groupCode: 'usage_count_limit_period'}
            }
        }
    },
  useSelectTenantCompItem({
    props: {},
    fieldName: 'limitTenantId',
    label: '限制租户',
  }),
]
export const addPageFormItems = [

    {
        field: {
            name: 'name',
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '名称',
                required: true,
                tips: '一个只展示用的文本'
            },
            compProps: {
                clearable: true,
            }
        }
    },

    useCascaderUsageCountDefineCompItem({
        fieldName: 'usageCountDefineId',
        label: '使用次数定义',
        required: true,
        disableGroup: true
    }),

    {
        field: {
            name: 'limitCount',
            value: 0
        },
        element: {
            comp: 'el-input-number',
            formItemProps: {
                label: '限制的最大使用次数',
                tips: '0或不填写不限制',
                required: true
            },
            compProps: {
                min: 0
            }
        }
    },

    {
        field: {
            name: 'limitRuleTypeDictId',
        },
        element: {
            comp: 'PtDictFrontSelect',
            formItemProps: {
                label: '限制规则类型',
                required: true
            },
            compProps: {
                // 字典查询
                dictParam: {groupCode: 'usage_count_limit_rule_type'}
            }
        }
    },
    {
        field: {
            name: 'limitPeriodDictId',
        },
        element: {
            comp: 'PtDictFrontSelect',
            formItemProps: {
                label: '限制周期',
                required: true
            },
            compProps: {
                // 字典查询
                dictParam: {groupCode: 'usage_count_limit_period'}
            }
        }
    },
    useSelectTenantCompItem({
        props: {},
        fieldName: 'limitTenantId',
        label: '限制租户',
        tips: '如果设置将按租户配置其它租户不受影响，否则将全局生效'
    }),
    {
        field: {
            name: 'exceedTip',
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '超出提示信息',
            },
            compProps: {
                clearable: true,
            }
        }
    },
    {
        field: {
            name: 'remark',
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '备注',

            },
            compProps: {
                clearable: true,
            }
        }
    },

]

// 更新和添加一致
export const updatePageFormItems = addPageFormItems

