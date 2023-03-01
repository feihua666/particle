import {list as lowcodeModelListApi} from "../../api/generator/admin/lowcodeModelAdminApi";

export const pageFormItems = [

    {
        field: {
            name: 'columnName'
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '字段名称'
            },
            compProps: {
                clearable: true,
                placeholder: '左前缀匹配'
            }
        }
    },
    {
        field: {
            name: 'propertyName'
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '实体属性名称'
            },
            compProps: {
                clearable: true,
                placeholder: '左前缀匹配'
            }
        }
    },
    {
        field: {
            name: 'lowcodeModelId',
        },
        element: {
            comp: 'PtSelect',
            formItemProps: {
                label: '模型',
            },
            compProps: {
                dataMethod: lowcodeModelListApi
            }
        }
    },
]

export const addPageFormItems = [
    {
        field: {
            name: 'columnName'
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '字段名称',
                required: true
            },
            compProps: {
                clearable: true,
            }
        }
    },
    {
        field: {
            name: 'propertyName'
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '实体属性名称',
                required: true
            },
            compProps: {
                clearable: true,
            }
        }
    },
    {
        field: {
            name: 'jdbcType'
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '字段类型',
                required: true
            },
            compProps: {
                clearable: true,
            }
        }
    },
    {
        field: {
            name: 'propertyType'
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '实体属性类型',
                required: true
            },
            compProps: {
                clearable: true,
            }
        }
    },
    {
        field: {
            name: 'propertyFullType'
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '实体属性类型全路径',
                required: true
            },
            compProps: {
                clearable: true,
            }
        }
    },
    {
        field: {
            name: 'commentFull'
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '字段全注释',
                required: true
            },
            compProps: {
                clearable: true,
            }
        }
    },
    {
        field: {
            name: 'commentSimple'
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '字段简洁注释',
                required: true
            },
            compProps: {
                clearable: true,
            }
        }
    },
    {
        field: {
            name: 'defaultValue'
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '默认值',
            },
            compProps: {
                clearable: true,
            }
        }
    },
    {
        field: {
            name: 'isUnique'
        },
        element: {
            comp: 'el-switch',
            formItemProps: {
                label: '是否唯一',
                required: true
            },
            compProps: {
                activeText: '唯一',
                inactiveText: '不唯一',
            }
        }
    },
    {
        field: {
            name: 'isRequired'
        },
        element: {
            comp: 'el-switch',
            formItemProps: {
                label: '是否必填',
                required: true
            },
            compProps: {
                activeText: '必填',
                inactiveText: '选填',
            }
        }
    },
    {
        field: {
            name: 'isKey'
        },
        element: {
            comp: 'el-switch',
            formItemProps: {
                label: '是否主键',
                required: true
            },
            compProps: {
                activeText: '主键',
                inactiveText: '非主键',
            }
        }
    },
    {
        field: {
            name: 'isKeyIdentity'
        },
        element: {
            comp: 'el-switch',
            formItemProps: {
                label: '是否主键自增',
                required: true
            },
            compProps: {
                activeText: '自增',
                inactiveText: '非自增',
            }
        }
    },
    {
        field: {
            name: 'isKeyWord'
        },
        element: {
            comp: 'el-switch',
            formItemProps: {
                label: '是否关键字',
                required: true
            },
            compProps: {
                activeText: '关键字',
                inactiveText: '非关键字',
            }
        }
    },
    {
        field: {
            name: 'columnLength',
            value: 0
        },
        element: {
            comp: 'el-input-number',
            formItemProps: {
                label: '字段长度',
                required: true
            },
            compProps: {
                clearable: true,
            }
        }
    },
    {
        field: {
            name: 'fractionLength',
            value: 0
        },
        element: {
            comp: 'el-input-number',
            formItemProps: {
                label: '字段小数位长度',
                required: true
            },
            compProps: {
                clearable: true,
            }
        }
    },
    {
        field: {
            name: 'isForeignKey'
        },
        element: {
            comp: 'el-switch',
            formItemProps: {
                label: '字段是否外键',
                required: true
            },
            compProps: {
                activeText: '外键',
                inactiveText: '非外键',
            }
        }
    },
    {
        field: {
            name: 'lowcodeModelId',
        },
        element: {
            comp: 'PtSelect',
            formItemProps: {
                label: '模型',
                required: true
            },
            compProps: {
                clearable: true,
                dataMethod: lowcodeModelListApi
            }
        }
    },
    {
        field: {
            name: 'remark'
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '描述',
            },
            compProps: {
                clearable: true,
            }
        }
    }]

// 更新和添加一致
export const updatePageFormItems = addPageFormItems