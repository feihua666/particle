/**
 * 初始化数据加载相关，一般用户选中节点
 * 如：在树形情况下，可自动加载选中的数据
 */

import {isPromise} from "../../common/tools/PromiseTools"
import {DataMethodPage, DataMethodPageQuery, dataMethodProps, reactiveDataMethodData} from './dataMethod'


/**
 * 数据加载相关，封装组件时用
 */
export const dataMethodInitProps = {
    dataInitMethod: dataMethodProps.dataMethod,
    // 处理加载后的数据，仅限 initMethod 返回 promise 时有效
    // 主要是给 initMethod 获取的数据一个处理数据的机会
    dataMethodInitResultHandle: dataMethodProps.dataMethodResultHandle,
    // dataInitMethod 请求参数
    dataInitMethodParam: {
        type: [Object],
        default: () => ({})
    },
    // 空数据
    dataMethodInitEmptyData: {
        type: [Object,Array],
        default: () => []
    },
    // 将数据转为 tree,仅限数据加载成功时有效，参见： dataMethodInitResultHandle中的 convertToTree 参数
    dataMethodInitResultHandleConvertToTree: {
        type: Boolean,
        default: false,
    },
    // 分页处理，对分页进行判断
    dataMethodInitResultPageHandle: dataMethodProps.dataMethodResultPageHandle,
}
export interface ReactiveDataMethodInitData{
    // 输入的数据
    dataMethodInitData: Array<any>,
    // 加载数据时loading
    dataMethodInitLocalLoading: boolean
    dataMethodInitPage: DataMethodPage,
    dataMethodInitPageQuery: DataMethodPageQuery,
    dataMethodInitLoaded: boolean
}
// 属性,不能直接导出对象使用，导出意味着单例，会有状态，导致数据共享造成数据不一致
export const reactiveDataMethodInitData = ():ReactiveDataMethodInitData => ({
    // 输入的数据
    dataMethodInitData: [],
    dataMethodInitLocalLoading: false,
    // 分页时可用，值为 属性方法 dataMethodInitResultPageHandle 返回的数据
    dataMethodInitPage: null,
    // 分页查询参数，initMethod 中分页参数参数
    dataMethodInitPageQuery: reactiveDataMethodData().dataMethodPageQuery,
    dataMethodInitLoaded: false
})
export const emitDataMethodInitEvent = {
    // 原生数据结果
    dataMethodInitResult: 'dataMethodInitResult',
    // 经过dataMethodInitResultHandle处理后的结果
    dataMethodInitData: 'dataMethodInitData',
    dataMethodInitDataLoading: 'dataMethodInitDataLoading'
}
const handleAdapter = (pageAdapter: DataMethodPage,reactiveData):void => {
    reactiveData.dataMethodInitData = pageAdapter.data
    reactiveData.dataMethodInitPage = pageAdapter
    if (pageAdapter.isPage) {
        reactiveData.dataMethodInitPageQuery.pageNo = pageAdapter.pageNo
        reactiveData.dataMethodInitPageQuery.pageSize = pageAdapter.pageSize
    }
}
const handleLoading = (loading: boolean,{reactiveData,emit}):void => {

    reactiveData.dataMethodInitLoaded = !loading

    reactiveData.dataMethodInitLocalLoading = loading
    if(emit){
        emit(emitDataMethodInitEvent.dataMethodInitDataLoading,reactiveData.dataMethodInitLocalLoading)
    }
}
// 加载数据
export const doDataMethodInit = ({props,reactiveData,emit,initMethod}:{}) =>{
    if (reactiveData.dataMethodInitLocalLoading) {
        return
    }
    handleLoading(true,{reactiveData,emit})
    let initMethodTemp = initMethod
    if(!initMethodTemp){
        initMethodTemp = props.dataInitMethod
    }
    if (initMethodTemp) {
        let result =  null
        // 页码为0说明为初始加载，不需要加载分页参数
        if(reactiveData.dataMethodInitPageQuery.pageNo > 0){
            result = initMethodTemp({param: props.dataInitMethodParam, pageQuery: reactiveData.dataMethodInitPageQuery})
        }else {
            result = initMethodTemp({param: props.dataInitMethodParam})
        }
        if (isPromise(result)) {
            const promiseResult = result.then(res =>{
                let pageAdapter = props.dataMethodInitResultPageHandle(props.dataMethodInitResultHandle({success: res,convertToTree: props.dataMethodInitResultHandleConvertToTree}))
                handleAdapter(pageAdapter,reactiveData)
                if(emit){
                    emit(emitDataMethodInitEvent.dataMethodInitData,reactiveData.dataMethodInitData,pageAdapter)
                }

                return Promise.resolve(res)
            }).catch(error => {
                let pageAdapter = props.dataMethodInitResultPageHandle(props.dataMethodInitResultHandle({error: error,dataMethodInitEmptyData: props.dataMethodInitEmptyData}) || props.dataMethodInitEmptyData)
                handleAdapter(pageAdapter,reactiveData)
                if(emit){
                    emit(emitDataMethodInitEvent.dataMethodInitData,reactiveData.dataMethodInitData,pageAdapter)
                }
                return Promise.reject(error)
            }).finally(()=>{
                handleLoading(false,{reactiveData,emit})
            })
            if(emit){
                emit(emitDataMethodInitEvent.dataMethodInitResult,promiseResult)
            }
        }else {
            let pageAdapter = props.dataMethodInitResultPageHandle(props.dataMethodInitResultHandle({success: result,convertToTree: props.dataMethodInitResultHandleConvertToTree,dataMethodInitEmptyData: props.dataMethodInitEmptyData}))
            handleAdapter(pageAdapter,reactiveData)
            if(emit){
                emit(emitDataMethodInitEvent.dataMethodInitResult,result)
                emit(emitDataMethodInitEvent.dataMethodInitData,reactiveData.dataMethodInitData,pageAdapter)
            }
            handleLoading(false,{reactiveData,emit})
        }
    }else {
        handleLoading(false,{reactiveData,emit})
    }
}
