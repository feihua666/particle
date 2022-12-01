/**
 * 数据加载相关
 */

import {isPromise} from "../../common/tools/PromiseTools"
import {isObject} from "../../common/tools/ObjectTools"

export interface DataMethodPage{
    isPage: boolean,
    pageNo?: number
    pageSize?: number,
    totalCount?: number,
    empty?: boolean,
    totalPages?: number,
    data: any|[]|{}
}
/**
 * 数据加载相关，封装组件时用
 */
export const dataMethodProps = {
    // 加载数据处理方法，一般为http请求获取数据，会自动处理 dataLoading 效果，一般返回 promise，也可以直接返回数据本身
    dataMethod: {
        type: Function,
    },
    // 空数据
    emptyData: {
        type: [Object,Array],
        default: () => []
    },
    // 处理加载后的数据，仅限 dataMethod 返回 promise 时有效
    // 主要是给 dataMethod 获取的数据一个处理数据的机会
    dataMethodResultHandle: {
        type: Function,
        // emptyData 参数和 emptyData 属性是一个值
        default: ({success,error,emptyData}) => {
            // success 为 res
            if (success) {
                return success.data
            }
            if (error) {
                return emptyData
            }
        }
    },
    // 分页处理，对分页进行判断
    dataMethodResultPageHandle: {
        type: Function,
        default: (data): DataMethodPage => {
            let r: DataMethodPage = {
                isPage: false,
                data: null
            }
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
                r.data = data
            }
            return r
        }
    },
}
// 属性
export const reactiveDataMethodData = {
    // 输入的数据
    dataMethodData: [],
    dataMethodLocalLoading: false,
    // 分页时可用，值为 属性方法 dataMethodResultPageHandle 返回的数据
    dataMethodPage: null,
    // 分页查询参数，props.dataMethod()中参数
    dataMethodPageQuery: {
        pageNo: 1,
        pageSize: 10
    }
}
export const emitDataMethodEvent = {
    // 原生数据结果
    dataMethodResult: 'dataMethodResult',
    // 经过dataMethodResultHandle处理后的结果
    dataMethodData: 'dataMethodData'
}
// 加载数据
export const doDataMethod = ({props,reactiveData,emit}) =>{
    if (reactiveData.dataMethodLocalLoading) {
        return
    }
    reactiveData.dataMethodLocalLoading = true
    if (props.dataMethod) {
        const result =  props.dataMethod(reactiveData.dataMethodPageQuery)
        if (isPromise(result)) {
            const promiseResult = result.then(res =>{
                let pageAdapter = props.dataMethodResultPageHandle(props.dataMethodResultHandle({success: res}))
                reactiveData.dataMethodData = pageAdapter.data
                reactiveData.dataMethodPage = pageAdapter
                emit(emitDataMethodEvent.dataMethodData,reactiveData.dataMethodData,pageAdapter)

                return Promise.resolve(res)
            }).catch(error => {
                let pageAdapter = props.dataMethodResultPageHandle(props.dataMethodResultHandle({error: error,emptyData: props.emptyData}) || props.emptyData)
                reactiveData.dataMethodData = pageAdapter.data
                reactiveData.dataMethodPage = pageAdapter
                emit(emitDataMethodEvent.dataMethodData,reactiveData.dataMethodData,pageAdapter)
                return Promise.reject(error)
            }).finally(()=>{
                reactiveData.dataMethodLocalLoading = false
            })
            emit(emitDataMethodEvent.dataMethodResult,promiseResult)
        }else {
            let pageAdapter = props.dataMethodResultPageHandle(props.dataMethodResultHandle({success: result}))
            reactiveData.dataMethodData = pageAdapter.data
            reactiveData.dataMethodPage = pageAdapter
            emit(emitDataMethodEvent.dataMethodResult,result)
            emit(emitDataMethodEvent.dataMethodData,reactiveData.dataMethodData,pageAdapter)
            reactiveData.dataMethodLocalLoading = false
        }
    }else {
        reactiveData.dataMethodLocalLoading = false
    }
}