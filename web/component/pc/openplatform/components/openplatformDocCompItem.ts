import {list as openplatformDocDirNameListApi} from "../api/doc/admin/openplatformDocDirNameAdminApi";
import {list as openplatformDocDirListApi} from "../api/doc/admin/openplatformDocDirAdminApi";
import {list as openplatformDocApiListApi} from "../api/doc/admin/openplatformDocApiAdminApi";
import {list as openplatformDocApiDocListApi} from "../api/doc/admin/openplatformDocApiDocAdminApi";
import {list as openplatformDocApiDocParamFieldListApi} from "../api/doc/admin/openplatformDocApiDocParamFieldAdminApi";
import {list as openplatformDocApiDocTemplateListApi} from "../api/doc/admin/openplatformDocApiDocTemplateAdminApi";
import {
    list as openplatformDocApiDocTemplateParamFieldListApi
} from "../api/doc/admin/openplatformDocApiDocTemplateParamFieldAdminApi";

export const useSelectOpenplatformDocDirNameCompItem = ({required = false, tips = ''}) => {
    return {
        field: {
            name: 'openplatformDocDirNameId'
        },
        element: {
            comp: 'PtSelect',
            formItemProps: {
                label: '目录名称',
                required: required,
                tips: tips || undefined
            },
            compProps: {
                dataMethod: openplatformDocDirNameListApi
            }
        }
    }
}


export const useCascaderOpenplatformDocDirCompItem = ({
                                                          fieldName = 'parentId',
                                                          required = false,
                                                          label = '父级',
                                                          valueChange = () => {
                                                          },
                                                          tips = ''
                                                      }) => {
    return {
        field: {
            name: fieldName,
            valueChange: valueChange
        },
        element: {
            comp: 'PtCascader',
            formItemProps: {
                label: label,
                required: required,
                tips: tips || undefined
            },
            compProps: {
                // 加载数据
                dataMethod: openplatformDocDirListApi,
                dataMethodResultHandleConvertToTree: true,
            }
        }
    }
}

export const useSelectOpenplatformDocApiCompItem = ({required = false, tips = ''}) => {
    return {
        field: {
            name: 'openplatformDocApiId'
        },
        element: {
            comp: 'PtSelect',
            formItemProps: {
                label: '绑定接口',
                required: required,
                tips: tips || undefined
            },
            compProps: {
                dataMethod: openplatformDocApiListApi
            }
        }
    }
}
export const useSelectOpenplatformDocApiDocCompItem = ({required = false, tips = ''}) => {
    return {
        field: {
            name: 'openplatformDocApiDocId'
        },
        element: {
            comp: 'PtSelect',
            formItemProps: {
                label: '文档内容',
                required: required,
                tips: tips || undefined
            },
            compProps: {
                dataMethod: openplatformDocApiDocListApi,
                props: {
                    label: 'requestUrl'
                }
            }
        }
    }
}
export const useCascaderOpenplatformDocApiDocParamFieldCompItem = ({
                                                                       fieldName = 'parentId',
                                                                       required = false,
                                                                       label = '父级',
                                                                       valueChange = () => {
                                                                       },
                                                                       tips = '',

                                                                   }) => {
    return {
        field: {
            name: fieldName,
            valueChange: valueChange
        },
        element: {
            comp: 'PtCascader',
            formItemProps: {
                label: label,
                required: required,
                tips: tips || undefined,
                labelTips: "选择文档内容和分类后可用",
            },
            compProps: ({form, formData}) => {

                let disabled = false
                let disabledReason = undefined
                if (!form.openplatformDocApiDocId || !form.categoryDictId) {
                    disabled = true
                    disabledReason = '选择文档内容和分类后可用'
                }
                let dataMethod = ()=>[]
                if (!disabled) {
                    dataMethod = ()=>{return openplatformDocApiDocParamFieldListApi({openplatformDocApiDocId: form.openplatformDocApiDocId, categoryDictId: form.categoryDictId})}
                }
                let result = {
                    disabled: disabled,
                    disabledReason: disabledReason,
                    // 添加参数只为了动态响应
                    dataMethodParam:{openplatformDocApiDocId: form.openplatformDocApiDocId, categoryDictId: form.categoryDictId},
                    // 加载数据
                    dataMethod: dataMethod,
                    dataMethodResultHandleConvertToTree: true,
                };

                return result
            }
        }
    }
}


export const useSelectOpenplatformDocApiDocTemplateCompItem = ({required = false, tips = ''}) => {
    return {
        field: {
            name: 'openplatformDocApiDocTemplateId'
        },
        element: {
            comp: 'PtSelect',
            formItemProps: {
                label: '文档内容模板',
                required: required,
                tips: tips || undefined
            },
            compProps: {
                dataMethod: openplatformDocApiDocTemplateListApi
            }
        }
    }
}

export const useCascaderOpenplatformDocApiDocTemplateParamFieldCompItem = ({
                                                                       fieldName = 'parentId',
                                                                       required = false,
                                                                       label = '父级',
                                                                       valueChange = () => {
                                                                       },
                                                                       tips = '',

                                                                   }) => {
    return {
        field: {
            name: fieldName,
            valueChange: valueChange
        },
        element: {
            comp: 'PtCascader',
            formItemProps: {
                label: label,
                required: required,
                tips: tips || undefined,
                labelTips: "选择文档内容模板和分类后可用",
            },
            compProps: ({form, formData}) => {

                let disabled = false
                let disabledReason = undefined
                if (!form.openplatformDocApiDocTemplateId || !form.categoryDictId) {
                    disabled = true
                    disabledReason = '选择文档内容模板和分类后可用'
                }
                let dataMethod = ()=>[]
                if (!disabled) {
                    dataMethod = ()=>{return openplatformDocApiDocTemplateParamFieldListApi({openplatformDocApiDocTemplateId: form.openplatformDocApiDocTemplateId, categoryDictId: form.categoryDictId})}
                }
                let result = {
                    disabled: disabled,
                    disabledReason: disabledReason,
                    // 添加参数只为了动态响应
                    dataMethodParam:{openplatformDocApiDocTemplateId: form.openplatformDocApiDocTemplateId, categoryDictId: form.categoryDictId},
                    // 加载数据
                    dataMethod: dataMethod,
                    dataMethodResultHandleConvertToTree: true,
                };

                return result
            }
        }
    }
}
