/**
 * 场景数据处理器
 * 一般有以下数据场景，这种数据可能存在级联数据关联，下一个数据依赖上一个数据变量不同而不同，如：省、市、的选择级联选择场景
 * 本工具支持简单的数据处理情况,需要组件自己集成，更复杂的功能后期完善如：支持动态加载等
 */

import {anyObj, isObject} from "../../common/tools/ObjectTools"

export interface SceneDataHanderProps{
    // 处理数据返回一个新数据
    // 添加类型为 anyObj 是因为 vue props 属性表示是一个对象，
    sceneDataHander?: ((data) => any)  | anyObj,
    // 场景值，依赖的场景值
    sceneValue:{}
}

/**
 * 数据加载相关，封装组件时用
 */
export const sceneDataHanderProps: SceneDataHanderProps = {
    // 加载数据处理方法，一般为http请求获取数据，会自动处理 dataLoading 效果，一般返回 promise，也可以直接返回数据本身
    sceneDataHander: {
        type: Function,
        default: (data)=>data
    },
    // 依赖的场景值
    sceneValue:{}
}