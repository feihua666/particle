import {nextTick} from 'vue'
import {list as lowcodeSegmentTemplateListApi} from "../../api/generator/admin/lowcodeSegmentTemplateAdminApi";
import {list as lowcodeModelListApi} from "../../api/generator/admin/lowcodeModelAdminApi";
import {list as lowcodeSegmentGenListApi} from "../../api/generator/admin/lowcodeSegmentGenAdminApi";

const getFormName = (form,formData)=>{
    const separator = '_'
    let namePrefix = ''
    if (formData.lowcodeModelId) {
        namePrefix = formData.lowcodeModelId.name
    }
    let nameSuffix = ''
    if (formData.generateTypeDictId) {
        nameSuffix = separator + formData.generateTypeDictId.name
    }
    return namePrefix + nameSuffix
}
export const pageFormItems = [
    {
        field: {
            name: 'name'
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '生成名称'
            },
            compProps: {
                clearable: true,
                placeholder: '左前缀匹配'
            }
        }
    },
    {
        field: {
            name: 'lowcodeSegmentTemplateId'
        },
        element: {
            comp: 'PtCascader',
            formItemProps: {
                label: '片段模板',
            },
            compProps: {
                dataMethod: lowcodeSegmentTemplateListApi,
                dataMethodResultHandleConvertToTree: true,
            }
        }
    },
    {
        field: {
            name: 'lowcodeModelId'
        },
        element: {
            comp: 'PtSelect',
            formItemProps: {
                label: '低代码模型',
            },
            compProps: {
                dataMethod: lowcodeModelListApi,
            }
        }
    },
    {
        field: {
            name: 'generateTypeDictId'
        },
        element: {
            comp: 'PtDictFrontSelect',
            formItemProps: {
                label: '生成类型',
            },
            compProps: {
                // 字典查询
                dictParam: {groupCode: 'lowcode_segment_gen_type'},
            }
        }
    },
    {
        field: {
            name: 'refrenceSegmentGenId'
        },
        element: {
            comp: 'PtSelect',
            formItemProps: {
                label: '引用生成',
            },
            compProps: {
                dataMethod: lowcodeSegmentGenListApi,
            }
        }
    },
]

export const addPageFormItems = [
    {
        field: {
            name: 'name'
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '生成名称',
                required: true,
                tips: '仅做为标识识别，自动联动 低代码模型和生成类型'
            },
            compProps: {
                clearable: true,
            }
        }
    },

    {
        field: {
            name: 'lowcodeSegmentTemplateId',
        },
        element: {
            comp: 'PtCascader',
            formItemProps: {
                label: '低代码片段模板',
                tips: '将基于该模板进行渲染，注意：和生成类型有对应关系',
                required: true
            },
            compProps: {
                dataMethod: lowcodeSegmentTemplateListApi,
                dataMethodResultHandleConvertToTree: true,
            }
        }
    },
    {
        field: {
            name: 'lowcodeModelId',
            valueChange:({form,formData,newValue})=>{
                nextTick(()=>{
                    form.name = getFormName(form,formData)
                })
            }
        },
        element: {
            comp: 'PtSelect',
            formItemProps: {
                label: '低代码模型',
                tips: '模型数据用于生成具体的服务'
            },
            compProps: {
                dataMethod: lowcodeModelListApi,
            }
        }
    },
    {
        field: {
            name: 'generateTypeDictId',
            valueChange:({form,formData,newValue})=>{
                nextTick(()=>{
                    form.name = getFormName(form,formData)
                })
            }
        },
        element: {
            comp: 'PtDictFrontSelect',
            formItemProps: {
                label: '生成类型',
                required: true,
                tips: '不同的类型会影响渲染时的表单，请选择正确的类型'
            },
            compProps: {
                // 字典查询
                dictParam: {groupCode: 'lowcode_segment_gen_type'},
            }
        }
    },
    {
        field: {
            name: 'refrenceSegmentGenId'
        },
        element: {
            comp: 'PtSelect',
            formItemProps: {
                label: '引用生成',
                tips: '可以使用引用的生成参数，以便快捷输入'
            },
            compProps: {
                dataMethod: lowcodeSegmentGenListApi,
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