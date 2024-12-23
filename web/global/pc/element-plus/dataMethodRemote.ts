/**
 * 远程数据加载相关
 */

import {isPromise} from "../../common/tools/PromiseTools"
import {DataMethodPage, DataMethodPageQuery, dataMethodProps, reactiveDataMethodData} from './dataMethod'


/**
 * 数据加载相关，封装组件时用
 */
export const dataMethodRemoteProps = {

    dataRemoteMethod: dataMethodProps.dataMethod,
    // 处理加载后的数据，仅限 remoteMethod 返回 promise 时有效
    // 主要是给 remoteMethod 获取的数据一个处理数据的机会
    dataMethodRemoteResultHandle: dataMethodProps.dataMethodResultHandle,
    // dataRemoteMethod 请求参数
    dataRemoteMethodParam: {
        type: [Object],
        default: () => ({})
    },
    // 空数据
    dataMethodRemoteEmptyData: {
        type: [Object,Array],
        default: () => []
    },
    // 将数据转为 tree,仅限数据加载成功时有效，参见： dataMethodRemoteResultHandle中的 convertToTree 参数
    dataMethodRemoteResultHandleConvertToTree: {
        type: Boolean,
        default: false,
    },
    // 分页处理，对分页进行判断
    dataMethodRemoteResultPageHandle: dataMethodProps.dataMethodResultPageHandle,
}
export interface ReactiveDataMethodRemoteData{
    // 输入的数据
    dataMethodRemoteData: Array<any>,
    // 加载数据时loading
    dataMethodRemoteLocalLoading: boolean
    dataMethodRemotePage: DataMethodPage,
    dataMethodRemotePageQuery: DataMethodPageQuery,
    dataMethodRemoteLoaded: boolean
}
// 属性,不能直接导出对象使用，导出意味着单例，会有状态，导致数据共享造成数据不一致
export const reactiveDataMethodRemoteData = ():ReactiveDataMethodRemoteData => ({
    // 输入的数据
    dataMethodRemoteData: [],
    dataMethodRemoteLocalLoading: false,
    // 分页时可用，值为 属性方法 dataMethodRemoteResultPageHandle 返回的数据
    dataMethodRemotePage: null,
    // 分页查询参数，remoteMethod 中分页参数参数
    dataMethodRemotePageQuery: reactiveDataMethodData().dataMethodPageQuery,
    dataMethodRemoteLoaded: false
})
export const emitDataMethodRemoteEvent = {
    // 原生数据结果
    dataMethodRemoteResult: 'dataMethodRemoteResult',
    // 经过dataMethodRemoteResultHandle处理后的结果
    dataMethodRemoteData: 'dataMethodRemoteData',
    dataMethodRemoteDataLoading: 'dataMethodRemoteDataLoading'
}
const handleAdapter = (pageAdapter: DataMethodPage,reactiveData):void => {
    reactiveData.dataMethodRemoteData = pageAdapter.data
    reactiveData.dataMethodRemotePage = pageAdapter
    if (pageAdapter.isPage) {
        reactiveData.dataMethodRemotePageQuery.pageNo = pageAdapter.pageNo
        reactiveData.dataMethodRemotePageQuery.pageSize = pageAdapter.pageSize
    }
}
const handleLoading = (loading: boolean,{reactiveData,emit}):void => {

    reactiveData.dataMethodRemoteLoaded = !loading
    reactiveData.dataMethodRemoteLocalLoading = loading
    if(emit){
        emit(emitDataMethodRemoteEvent.dataMethodRemoteDataLoading,reactiveData.dataMethodRemoteLocalLoading)
    }
}
// 加载数据
export const doDataMethodRemote = ({props,reactiveData,emit,remoteMethod}:{}) =>{
    if (reactiveData.dataMethodRemoteLocalLoading) {
        return
    }
    handleLoading(true,{reactiveData,emit})
    let remoteMethodTemp = remoteMethod
    if(!remoteMethodTemp){
        remoteMethodTemp = props.dataRemoteMethod()
    }
    if (remoteMethodTemp) {
        let result =  null
        // 页码为0说明为初始加载，不需要加载分页参数
        if(reactiveData.dataMethodRemotePageQuery.pageNo > 0){
            result = remoteMethodTemp({param: props.dataRemoteMethodParam, pageQuery: reactiveData.dataMethodRemotePageQuery})
        }else {
            result = remoteMethodTemp({param: props.dataRemoteMethodParam})
        }
        if (isPromise(result)) {
            const promiseResult = result.then(res =>{
                let pageAdapter = props.dataMethodRemoteResultPageHandle(props.dataMethodRemoteResultHandle({success: res,convertToTree: props.dataMethodRemoteResultHandleConvertToTree}))
                handleAdapter(pageAdapter,reactiveData)
                if(emit){
                    emit(emitDataMethodRemoteEvent.dataMethodRemoteData,reactiveData.dataMethodRemoteData,pageAdapter)
                }

                return Promise.resolve(res)
            }).catch(error => {
                let pageAdapter = props.dataMethodRemoteResultPageHandle(props.dataMethodRemoteResultHandle({error: error,dataMethodRemoteEmptyData: props.dataMethodRemoteEmptyData}) || props.dataMethodRemoteEmptyData)
                handleAdapter(pageAdapter,reactiveData)
                if(emit){
                    emit(emitDataMethodRemoteEvent.dataMethodRemoteData,reactiveData.dataMethodRemoteData,pageAdapter)
                }
                return Promise.reject(error)
            }).finally(()=>{
                handleLoading(false,{reactiveData,emit})
            })
            if(emit){
                emit(emitDataMethodRemoteEvent.dataMethodRemoteResult,promiseResult)
            }
        }else {
            let pageAdapter = props.dataMethodRemoteResultPageHandle(props.dataMethodRemoteResultHandle({success: result,convertToTree: props.dataMethodRemoteResultHandleConvertToTree,dataMethodRemoteEmptyData: props.dataMethodRemoteEmptyData}))
            handleAdapter(pageAdapter,reactiveData)
            if(emit){
                emit(emitDataMethodRemoteEvent.dataMethodRemoteResult,result)
                emit(emitDataMethodRemoteEvent.dataMethodRemoteData,reactiveData.dataMethodRemoteData,pageAdapter)
            }
            handleLoading(false,{reactiveData,emit})
        }
    }else {
        handleLoading(false,{reactiveData,emit})
    }
}
