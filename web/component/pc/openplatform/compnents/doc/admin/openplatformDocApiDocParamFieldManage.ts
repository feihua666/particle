import {
    useCascaderOpenplatformDocApiDocParamFieldCompItem,
    useSelectOpenplatformDocApiCompItem,
    useSelectOpenplatformDocApiDocCompItem
} from "../../openplatformDocCompItem";
import {treeQueryComps} from "../../../../treeQueryComps";

export const pageFormItems = [
    {
        field: {
            name: 'name',
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '字段名称',

            },
            compProps: {
                clearable: true,
                placeholder: '左前缀匹配',
            }
        }
    },
    {
        field: {
            name: 'typeDictId',
        },
        element: {
            comp: 'PtDictFrontSelect',
            formItemProps: {
                label: '字段类型',

            },
            compProps: {
                // 字典查询
                dictParam: {groupCode: 'field_type'}
            }
        }
    },

    {
        field: {
            name: 'explanation',
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '字段说明',

            },
            compProps: {
                clearable: true,
                placeholder: '左前缀匹配',
            }
        }
    },
    useSelectOpenplatformDocApiCompItem({}),
    useSelectOpenplatformDocApiDocCompItem({}),
    {
        field: {
            name: 'categoryDictId',
        },
        element: {
            comp: 'PtDictFrontSelect',
            formItemProps: {
                label: '分类',
            },
            compProps: {
                // 字典查询
                dictParam: {groupCode: 'open_platform_doc_param_field_type'}
            }
        }
    },
    useCascaderOpenplatformDocApiDocParamFieldCompItem({}),
    ...treeQueryComps
]
export const addPageFormItems = [
    {
        field: {
            name: 'name',
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '字段名称',
                required: true,
                tips: '字段名，如：name'
            },
            compProps: {
                clearable: true,
            }
        }
    },

    {
        field: {
            name: 'typeDictId',
        },
        element: {
            comp: 'PtDictFrontSelect',
            formItemProps: {
                label: '字段类型',
                tips: '字段的数据类型',
                required: true,
            },
            compProps: {
                // 字典查询
                dictParam: {groupCode: 'field_type'}
            }
        }
    },
    {
        field: {
            name: 'nestTypeDictId',
        },
        element: {
            comp: 'PtDictFrontSelect',
            formItemProps: {
                label: '嵌套字段类型',
                tips: '嵌套字段的数据类型，一般用于数组，表示数组的元素类型'
            },
            compProps: {
                // 字典查询
                dictParam: {groupCode: 'field_type'}
            }
        }
    },

    {
        field: {
            name: 'isRequired',
        },
        element: {
            comp: 'el-switch',
            formItemProps: {
                label: '是否一定有值',
                required: true,
                tips: '针对请求参数，是为必填，否为非必填；针对响应参数，是为必有值，否为非必有值'
            },
            compProps: {
                activeText: '是',
                inactiveText: '否',
            }
        }
    },


    {
        field: {
            name: 'explanation',
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '字段说明',
                required: true,
                tips: '对字段的简要说明'
            },
            compProps: {
                clearable: true,
            }
        }
    },
    {
        field: {
            name: 'defaultValue',
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '默认值',
                tips: '如果参数有默认值可以填写'
            },
            compProps: {
                clearable: true,
            }
        }
    },
    {
        field: {
            name: 'max_length'
        },
        element: {
            comp: 'el-input-number',
            formItemProps: {
                label: '最大长度'
            },
            compProps: {
                clearable: true,
            }
        }
    },
    {
        field: {
            name: 'dictGroupDictCode',
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '字典组编码',
                tips: '绑定字典，如果参数为一个字典项'
            },
            compProps: {
                clearable: true,
            }
        }
    },
    {
        field: {
            name: 'dictItemTags',
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '字典标签',
                tips: '绑定字典，如果参数为一个字典项，可以根据标签过滤，多个以逗号分隔'
            },
            compProps: {
                clearable: true,
            }
        }
    },

    useSelectOpenplatformDocApiDocCompItem({required: true,tips: '字段绑定的文档内容'}),


    {
        field: {
            name: 'categoryDictId',
        },
        element: {
            comp: 'PtDictFrontSelect',
            formItemProps: {
                label: '分类',
                required: true,
                tips: '分类，旨在以通用的形式对各请求参数或响应参数、请求头进行分类'
            },
            compProps: {
                // 字典查询
                dictParam: {groupCode: 'open_platform_doc_param_field_type'}
            }
        }
    },

    {
        field: {
            name: 'seq',
            value: 10
        },
        element: {
            comp: 'el-input-number',
            formItemProps: {
                label: '排序'
            },
            compProps: {
                clearable: true,
            }
        }
    },

    useCascaderOpenplatformDocApiDocParamFieldCompItem({})

]

// 更新和添加一致
export const updatePageFormItems = addPageFormItems

