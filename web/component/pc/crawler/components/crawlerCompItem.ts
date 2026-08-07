import {list as crawlerProjectListApi} from "../api/definition/admin/crawlerProjectAdminApi";
import {list as crawlerDefinitionListApi} from "../api/definition/admin/crawlerDefinitionAdminApi";
import {list as crawlerDefinitionHistoryListApi} from "../api/definition/admin/crawlerDefinitionHistoryAdminApi.ts";

export const useSelectCrawlerProjectCompItem = ({fieldName= 'crawlerProjectId',
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
                dataMethod: () => { return crawlerProjectListApi({})},
            }
        }
    }
}
export const useSelectCrawlerDefinitionCompItem = ({fieldName= 'crawlerDefinitionId',
                                                     required=false,
                                                     label= '爬虫定义',
                                                     tips = '',
                                                     disabled = false,
                                                        crawlerProjectIdFieldName = 'crawlerProjectId',})=>{
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

                if (crawlerProjectIdFieldName) {
                    let paramsExist = !!(form[crawlerProjectIdFieldName]);
                    let r = {
                        placeholder: '请先选择项目',
                        disabled: disabled || !paramsExist,
                        dataMethodParam: {crawlerProjectId: form[crawlerProjectIdFieldName]},
                        // 给定默认数据
                        dataMethod: ({param})=> {
                            if(paramsExist){
                                return crawlerDefinitionListApi(param)
                            }
                            return {data: []}
                        },
                    }// r

                    return r
                }else{
                    return {
                        disabled: disabled,
                        // 给定默认数据
                        dataMethod: () => { return crawlerDefinitionListApi({}) },
                    }
                }

            }
        }
    }
}

export const useSelectCrawlerDefinitionHistoryCompItem = ({fieldName= 'crawlerDefinitionHistoryId',
                                                        required=false,
                                                        label= '爬虫定义历史',
                                                        tips = '',
                                                        disabled = false,
                                                        crawlerDefinitionIdFieldName = 'crawlerDefinitionId',})=>{
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
                let paramsExist = !!(form[crawlerDefinitionIdFieldName])
                let r = {
                    placeholder: '请先选择爬虫定义',
                    disabled: disabled || !paramsExist,
                    dataMethodParam: {crawlerDefinitionId: form[crawlerDefinitionIdFieldName]},
                    // 给定默认数据
                    dataMethod: ({param})=> {
                        if(paramsExist){
                            return crawlerDefinitionHistoryListApi(param)
                                .then((res)=>{
                                    res.data.data.forEach((item)=>{
                                        item.name = `${item.crawlerDefinitionName}:${item.crawlerDefinitionVersion}:(${item.isPublish ? '已发布': '草稿'})`
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
