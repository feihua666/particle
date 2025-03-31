import {page as agiVectorStoreRawDocumentPageApi} from "../api/rag/admin/agiVectorStoreRawDocumentAdminApi";

export const remoteSelectAgiVectorStoreRawDocumentProps = {
    // 加载数据初始化参数,路由传参
    agiVectorStoreRawDocumentId: {
        type: String
    },
    // 加载数据初始化参数,路由传参
    agiVectorStoreRawDocumentName: String
}
/**
 * 远程搜索知识存储原始文档表单配置项
 * 属性中必须有 agiVectorStoreRawDocumentId 和 agiVectorStoreRawDocumentName 两个属性
 * @param props
 */
export const useRemoteSelectAgiVectorStoreRawDocumentCompItem = ({props,
                                                required = false,
                                                fieldName='agiVectorStoreRawDocumentId',
                                                propAgiVectorStoreRawDocumentIdFieldName='agiVectorStoreRawDocumentId',
                                                propAgiVectorStoreRawDocumentNameFieldName='agiVectorStoreRawDocumentName',
                                                label='知识存储原始文档',
                                                valueChange = ()=>{},tips = '',disabled = null})=>{

  return   {
        field: {
            name: fieldName,
            value: props[propAgiVectorStoreRawDocumentIdFieldName],
            valueChange: valueChange
        },
        element: {
            comp: 'PtSelect',
            formItemProps: {
                label: label,
                required: required,
                tips: tips
            },
            compProps: ()=> {
                let paramsExist = !!(props[propAgiVectorStoreRawDocumentIdFieldName] && props[propAgiVectorStoreRawDocumentNameFieldName])
                let isDisabled = disabled
                if (isDisabled === null) {
                    isDisabled = paramsExist
                }
                let r = {
                    placeholder: '输入名称搜索',
                    disabled: isDisabled,
                    // 给定默认数据
                    dataMethod: ()=> {
                        if(paramsExist){
                            return {data: [{
                                    id: props[propAgiVectorStoreRawDocumentIdFieldName],
                                    name: props[propAgiVectorStoreRawDocumentNameFieldName],
                                }]}
                        }
                        return {data: []}
                    },
                    // 路由中没有数据，开启远程搜索
                    remote: true,
                    remoteMethod: (query: string) => {
                        if(!query){
                            return {data: []}
                        }
                        return agiVectorStoreRawDocumentPageApi({name: query})
                    },
                    // 下拉显示昵称
                    props: {label: 'name'}
                };// r

                return r
            }
        }
    }
}

