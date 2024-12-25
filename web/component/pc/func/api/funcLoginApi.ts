import axios, {AxiosPromise} from 'axios'
import {anyObj} from "../../../../global/common/tools/ObjectTools";

/**
 * 参见后台方法 com.particle.func.adapter.login.FuncLoginController.getList
 */
export interface loginUserFuncListParam{
    // 如果为 all 将不过滤，值为功能类型字典值，多个以逗号分隔,默认 menu,page,group
    includeTypeDictValues?: string,
    // 过虑是否展示的数据，不传忽略过虑条件
    isShow?:boolean
}
/**
 * 当前登录用户的功能
 * 平板或pc后台管理使用
 */
export const loginUserFuncList = (data: loginUserFuncListParam): AxiosPromise => {
    if (!data) {
        data = {}
    }
    return axios.get('/func/login/getList',{params: data})
}
/**
 * 当前登录用户的应用
 */
export const loginUserFuncApplicationList = (data: anyObj): AxiosPromise => {
    return axios.get('/func/login/loginUserFuncApplicationList',{params: data})
}
