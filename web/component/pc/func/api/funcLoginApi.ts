import axios, { AxiosPromise} from 'axios'
import {anyObj} from "../../../../global/common/tools/ObjectTools";

/**
 * 参见后台方法 com.particle.func.adapter.login.FuncLoginController.getList
 */
export interface LoginGetListParam{
    // 如果为 all 将不过滤，值为功能类型字典值，多个以逗号分隔,默认 menu,page,group
    includeTypeDictValues?: string,
    // 过虑是否展示的数据，不传忽略过虑条件
    isShow?:boolean
}
/**
 * 当前登录用户的功能
 * 平板或pc后台管理使用
 */
export const loginGetList = (data: LoginGetListParam): AxiosPromise => {
    if (!data) {
        data = {}
    }
    data.funcGroupCode = 'backend_pc'

    return axios.get('/func/login/getList',{params: data})
}
/**
 * 当前登录用户的应用
 */
export const getFuncApplicationList = (data: anyObj): AxiosPromise => {
    return axios.get('/func/login/getFuncApplicationList',{params: data})
}