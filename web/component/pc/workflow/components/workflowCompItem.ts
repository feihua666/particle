import {list as workflowProjectListApi} from "../api/definition/admin/workflowProjectAdminApi";
import {list as workflowDefinitionListApi} from "../api/definition/admin/workflowDefinitionAdminApi";
import {list as workflowDefinitionHistoryListApi} from "../api/definition/admin/workflowDefinitionHistoryAdminApi.ts";
export const useSelectWorkflowProjectCompItem = ({fieldName= 'workflowProjectId',
                                                     required=false,
                                                     label= '项目',
                                                 tips = '',
                                                 disabled = false})=>{
    return         {
        field: {
            name: fieldName
        },
        element: {
            comp: 'PtSelect',
            formItemProps: {
                label: label,
                required: required,
                tips: tips || undefined,
            },
            compProps: {
                clearable: true,
                disabled: disabled,
                // 加载数据
                dataMethod: () => { return workflowProjectListApi({})},
            }
        }
    }
}
export const useSelectWorkflowDefinitionCompItem = ({fieldName= 'workflowDefinitionId',
                                                     required=false,
                                                     label= '流程定义',
                                                     tips = '',
                                                     disabled = false,
                                                        workflowProjectIdFieldName = 'workflowProjectId',})=>{
    return         {
        field: {
            name: fieldName
        },
        element: {
            comp: 'PtSelect',
            formItemProps: {
                label: label,
                required: required,
                tips: tips || undefined,
            },
            compProps: ({form,formData})=> {
                let paramsExist = !!(form[workflowProjectIdFieldName])
                let r = {
                    placeholder: '请先选择项目',
                    disabled: disabled || !paramsExist,
                    dataMethodParam: {workflowProjectId: form[workflowProjectIdFieldName]},
                    // 给定默认数据
                    dataMethod: ({param})=> {
                        if(paramsExist){
                            return workflowDefinitionListApi(param)
                        }
                        return {data: []}
                    },
                }// r

                return r
            }
        }
    }
}

export const useSelectWorkflowDefinitionHistoryCompItem = ({fieldName= 'workflowDefinitionHistoryId',
                                                        required=false,
                                                        label= '流程定义历史',
                                                        tips = '',
                                                        disabled = false,
                                                        workflowDefinitionIdFieldName = 'workflowDefinitionId',})=>{
    return         {
        field: {
            name: fieldName
        },
        element: {
            comp: 'PtSelect',
            formItemProps: {
                label: label,
                required: required,
                tips: tips || undefined,
            },
            compProps: ({form,formData})=> {
                let paramsExist = !!(form[workflowDefinitionIdFieldName])
                let r = {
                    placeholder: '请先选择流程定义',
                    disabled: disabled || !paramsExist,
                    dataMethodParam: {workflowDefinitionId: form[workflowDefinitionIdFieldName]},
                    // 给定默认数据
                    dataMethod: ({param})=> {
                        if(paramsExist){
                            return workflowDefinitionHistoryListApi(param)
                                .then((res)=>{
                                    res.data.data.forEach((item)=>{
                                        item.name = `${item.workflowDefinitionName}:${item.workflowDefinitionVersion}:(${item.isPublish ? '已发布': '草稿'})`
                                    })
                                    return res
                                })
                        }
                        return {data: []}
                    },
                }// r

                return r
            }
        }
    }
}
