import {lowerFirst, upperFirst} from "../../../../../global/common/tools/StringTools";

export const pageFormItems = [

    {
        field: {
            name: 'name'
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '名称'
            },
            compProps: {
                clearable: true,
                placeholder: '左前缀匹配'
            }
        }
    },
    {
        field: {
            name: 'tableName'
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '表名称'
            },
            compProps: {
                clearable: true,
                placeholder: '左前缀匹配'
            }
        }
    },
]

export const addPageFormItems =  [
    {
        field: {
            name: 'name'
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '名称',
                required: true,
                tips: '将作为模型的原始名称变量输出'
            },
            compProps: {
                clearable: true,
            }
        }
    },
    {
        field: {
            name: 'nameEn',
            valueChange:({form,formData,newValue})=>{
                if(newValue){
                    let newValueTemp = newValue.toLowerCase().replace('-','').replace('_','')
                    form.nameEnEntity = upperFirst(newValueTemp)
                    form.requestPath = newValue.toLowerCase()
                }else {
                    form.nameEnEntity = ''
                    form.requestPath = ''
                }
            }
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '英文名称',
                required: true,
                tips: '将作为模型的原始英文名称变量输出'
            },
            compProps: {
                clearable: true,
            }
        }
    },
    {
        field: {
            name: 'nameEnEntity',
            valueChange:({form,formData,newValue})=>{
                if(newValue){
                    let newValueTemp = newValue.toLowerCase().replace('-','').replace('_','')
                    form.nameEnEntityVar = lowerFirst(newValueTemp)
                }else {
                    form.nameEnEntityVar = ''
                }
            }
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '英文实体名称',
                required: true,
                tips: '将作为模型的实体名称变量输出，首字母必须大写'
            },
            compProps: {
                placeholder: '首字母必须大写',
                clearable: true,
            }
        }
    },
    {
        field: {
            name: 'nameEnEntityVar'
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '英文实体变量名称',
                required: true,
                tips: '将作为模型的实体变量名称变量输出,首字母一般小写'
            },
            compProps: {
                placeholder: '首字母一般小写',
                clearable: true,
            }
        }
    },
    {
        field: {
            name: 'tableName'
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '表名称',
                tips: '可以根据表名加载模型项，比手动录入更方便'
            },
            compProps: {
                clearable: true,
            }
        }
    },
    {
        field: {
            name: 'tableTypeDictId'
        },
        element: {
            comp: 'PtDictFrontSelect',
            formItemProps: {
                label: '模型表类型',
                required: true
            },
            compProps: {
                // 字典查询
                dictParam: {groupCode: 'lowcode_model_table_type'}
            }
        }
    },
    {
        field: {
            name: 'requestPath'
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '请求路径',
                required: true
            },
            compProps: {
                clearable: true,
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
                label: '描述'
            },
            compProps: {
                clearable: true,
            }
        }
    },
]

// 更新和添加一致
export const updatePageFormItems = addPageFormItems