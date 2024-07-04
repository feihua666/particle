// 该文件内存放整个模块有组件需要单独抽出的内容
import {list as dataObjectListApi} from "../api/admin/dataObjectAdminApi";
import {page as dataScopePageApi,list as dataScopeListApi} from "../api/admin/dataScopeAdminApi";
export const remoteSelectDataScopeProps = {
    // 加载数据初始化参数,路由传参
    dataScopeId: {
        type: String
    },
    // 加载数据初始化参数,路由传参
    dataScopeName: String
}
/**
 * 主要根据数据对象过滤
 * @param props
 * @param required
 */
export const useSelectDataScopeCompItem = ({props,required = false})=>{
    return   {
        field: {
            name: 'dataScopeId',
            value: props.dataScopeId
        },
        element: {
            comp: 'PtSelect',
            formItemProps: {
                label: '数据范围',
                required: required
            },
            compProps: ({form,formData})=> {
                let paramsExist = !!(form.dataObjectId || props.dataObjectId)

                let r = {
                    placeholder: '请先选择数据对象',
                    disabled: !paramsExist || !!props.dataObjectId,
                    dataMethodParam: {dataObjectId: form.dataObjectId || props.dataObjectId},
                    // 给定默认数据
                    dataMethod: ({param})=> {
                        if(paramsExist){
                            return dataScopePageApi(param)
                        }
                        return {data: []}
                    },

                }// r

                return r
            }
        }
    }
}
/**
 * 远程搜索数据范围表单配置项
 * 属性中必须有 dataScopeId 和 dataScopeName 两个属性
 * @param props
 */
export const useRemoteSelectDataScopeCompItem = ({props,required})=>{
    return   {
        field: {
            name: 'dataScopeId',
            value: props.dataScopeId
        },
        element: {
            comp: 'PtSelect',
            formItemProps: {
                label: '数据范围',
                required: required
            },
            compProps: ({form,formData})=> {
                let paramsExist = !!(props.dataScopeId && props.dataScopeName)
                let r = {
                    placeholder: '输入数据范围名称搜索',
                    disabled: paramsExist,
                    // 给定默认数据
                    dataMethod: ()=> {
                        if(paramsExist){
                            return {data: [{
                                    id: props.dataScopeId,
                                    name: props.dataScopeName,
                                }]}
                        }
                        return {data: []}
                    },
                    // 路由中没有数据，开启远程搜索
                    remote: !paramsExist,
                    remoteMethod: (query: string) => {
                        if(!query){
                            return {data: []}
                        }
                        return dataScopePageApi({name: query,dataObjectId: form.dataObjectId})
                    },
                    // 下拉显示昵称
                    props: {label: 'name'}
                }// r

                return r
            }
        }
    }
}
export const useSelectDataConstraintDataObjectCompItem = ({props={},fieldName= 'dataObjectId',required=false,label= '数据对象'})=>{
    return         {
        field: {
            name: fieldName,
            value: props.dataObjectId
        },
        element: {
            comp: 'PtSelect',
            formItemProps: {
                label: label,
                required: required
            },
            compProps: {
                clearable: true,
                disabled: !!props.dataObjectId,
                // 加载数据
                dataMethod: dataObjectListApi,
            }
        }
    }
}
