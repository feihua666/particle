/**
 * 根据id请求接口的类型
 */
import {anyObj} from "../../global/common/tools/ObjectTools";

export interface IdParam{
    id: string
}
/**
 * 更新时的对象参数
 */
export interface updateParam extends anyObj{
    id: string
    version: number,
    [key: string]: any
}
