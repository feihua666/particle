/**
 * 数据加载相关
 */

import {isPromise} from "../../common/tools/PromiseTools"
import {anyObj, isObject} from "../../common/tools/ObjectTools"
import {listToTree,isArray} from "../../common/tools/ArrayTools"

export interface DataMethodPage{
    isPage: boolean,
    pageNo?: number
    pageSize?: number,
    totalCount?: number,
    empty?: boolean,
    totalPages?: number,
    data: any|[]|{}
}
export interface DataMethodPageQuery{
    pageNo: number,
    pageSize: number
}

export interface DataMethodProps{
    // 添加类型为 anyObj 是因为 vue props 属性表示是一个对象，
    dataMethod?: ((param: DataMethodPageQuery) => Promise<any>|any) | anyObj,
    [key: string]: any
}

/**
 * 数据加载相关，封装组件时用
 */
export const dataMethodProps: DataMethodProps = {
    // 加载数据处理方法，一般为http请求获取数据，会自动处理 dataLoading 效果，一般返回 promise，也可以直接返回数据本身
    dataMethod: {
        type: Function,
    },
    // dataMethod 请求参数
    dataMethodParam: {
        type: [Object],
        default: () => ({})
    },
    // 空数据
    dataMethodEmptyData: {
        type: [Object,Array],
        default: () => []
    },
    // 处理加载后的数据，仅限 dataMethod 返回 promise 时有效
    // 主要是给 dataMethod 获取的数据一个处理数据的机会
    dataMethodResultHandle: {
        type: Function,
        // dataMethodEmptyData 参数和 dataMethodEmptyData 属性是一个值
        default: ({success,error,dataMethodEmptyData,convertToTree,listToTreeMethod = listToTree}) => {
            // success 为 res
            if (success) {
                let p = null
                let data = success
                if(!data){
                    return dataMethodEmptyData
                }
                // 自定义数据情形，或直接数据情形
                // 取一层 data
                if(data.data !== undefined){
                    p = data
                    data = data.data

                }
                // response.data.data 情形
                // 再取一层 data
                if(data.data !== undefined){
                    p = data
                    data = data.data
                }
                if(convertToTree && isArray(data)){
                    if(p){
                        p.data = listToTreeMethod(data)
                        return p
                    }else {
                        data = listToTreeMethod(data)
                    }
                }
                return data
            }
            if (error) {
                return dataMethodEmptyData
            }
            return dataMethodEmptyData
        }
    },
    // 将数据转为 tree,仅限数据加载成功时有效，参见： dataMethodResultHandle中的 convertToTree 参数
    dataMethodResultHandleConvertToTree: {
        type: Boolean,
        default: false,
    },
    // dataMethodResultHandleConvertToTree为true时，将list转为树的方法
    dataMethodResultHandleListToTreeMethod: {
        type: Function,
    },
    // 分页处理，对分页进行判断
    dataMethodResultPageHandle: {
        type: Function,
        // 参数 data 为 dataMethodResultHandle 返回的结果
        default: (data): DataMethodPage => {
            let r: DataMethodPage = {
                isPage: false,
                data: null
            }
            if(!data){
                return r
            }
            // 数据分页情形
            if (isObject(data) && data.pageNo !== undefined && data.pageSize !== undefined && data.totalCount !== undefined) {
                r.isPage = true

                r.pageNo = data.pageNo
                r.pageSize = data.pageSize
                r.totalCount = data.totalCount
                r.empty = data.empty
                r.totalPages = data.totalPages
                // 分布数据
                r.data = data.data
            }else {
                if (data.data == undefined) {
                    // 一般为自定义数据，直接数据情形
                    r.data = data
                }else {
                    // // 一般为自定义数据或后台返回，带一层 data 的数据情形
                    r.data = data.data
                }

            }
            return r
        }
    },
}
export interface ReactiveDataMethodData{
    // 输入的数据
    dataMethodData: Array<any>,
    // 加载数据时loading
    dataMethodLocalLoading: boolean
    dataMethodPage: DataMethodPage,
    dataMethodPageQuery: DataMethodPageQuery,
    dataMethodLoaded: boolean
}
// 属性,不能直接导出对象使用，导出意味着单例，会有状态，导致数据共享造成数据不一致
export const reactiveDataMethodData = ():ReactiveDataMethodData => ({
    // 输入的数据
    dataMethodData: [],
    dataMethodLocalLoading: false,
    // 分页时可用，值为 属性方法 dataMethodResultPageHandle 返回的数据
    dataMethodPage: null,
    // 分页查询参数，props.dataMethod()中分布参数参数
    dataMethodPageQuery: {
        pageNo: null, // 0时不回调传参
        pageSize: 10
    },
    dataMethodLoaded: false
})
export const emitDataMethodEvent = {
    // 原生数据结果
    dataMethodResult: 'dataMethodResult',
    // 经过dataMethodResultHandle处理后的结果
    dataMethodData: 'dataMethodData',
    dataMethodDataLoading: 'dataMethodDataLoading'
}
const handleAdapter = (pageAdapter: DataMethodPage,reactiveData):void => {
    reactiveData.dataMethodData = pageAdapter.data
    reactiveData.dataMethodPage = pageAdapter
    if (pageAdapter.isPage) {
        reactiveData.dataMethodPageQuery.pageNo = pageAdapter.pageNo
        reactiveData.dataMethodPageQuery.pageSize = pageAdapter.pageSize
    }
}
const handleLoading = (loading: boolean,{reactiveData,emit}):void => {

    reactiveData.dataMethodLoaded = !loading

    reactiveData.dataMethodLocalLoading = loading
    emit(emitDataMethodEvent.dataMethodDataLoading,reactiveData.dataMethodLocalLoading)
}
// 加载数据
export const doDataMethod = ({props,reactiveData,emit}) =>{
    if (reactiveData.dataMethodLocalLoading) {
        return
    }
    handleLoading(true,{reactiveData,emit})
    if (props.dataMethod) {
        let result =  null
        // 页码为0说明为初始加载，不需要加载分页参数
        if(reactiveData.dataMethodPageQuery.pageNo > 0){
            result = props.dataMethod({param: props.dataMethodParam, pageQuery: reactiveData.dataMethodPageQuery})
        }else {
            result = props.dataMethod({param: props.dataMethodParam})
        }
        if (isPromise(result)) {
            const promiseResult = result.then(res =>{
                let pageAdapter = props.dataMethodResultPageHandle(props.dataMethodResultHandle({success: res,convertToTree: props.dataMethodResultHandleConvertToTree,listToTreeMethod: props.dataMethodResultHandleListToTreeMethod}))
                handleAdapter(pageAdapter,reactiveData)
                emit(emitDataMethodEvent.dataMethodData,reactiveData.dataMethodData,pageAdapter)

                return Promise.resolve(res)
            }).catch(error => {
                let pageAdapter = props.dataMethodResultPageHandle(props.dataMethodResultHandle({error: error,dataMethodEmptyData: props.dataMethodEmptyData}) || props.dataMethodEmptyData)
                handleAdapter(pageAdapter,reactiveData)
                emit(emitDataMethodEvent.dataMethodData,reactiveData.dataMethodData,pageAdapter)
                return Promise.reject(error)
            }).finally(()=>{
                handleLoading(false,{reactiveData,emit})
            })
            emit(emitDataMethodEvent.dataMethodResult,promiseResult)
        }else {
            let pageAdapter = props.dataMethodResultPageHandle(props.dataMethodResultHandle({success: result,convertToTree: props.dataMethodResultHandleConvertToTree,dataMethodEmptyData: props.dataMethodEmptyData,listToTreeMethod: props.dataMethodResultHandleListToTreeMethod}))
            handleAdapter(pageAdapter,reactiveData)
            emit(emitDataMethodEvent.dataMethodResult,result)
            emit(emitDataMethodEvent.dataMethodData,reactiveData.dataMethodData,pageAdapter)
            handleLoading(false,{reactiveData,emit})
        }
    }else {
        handleLoading(false,{reactiveData,emit})
    }
}