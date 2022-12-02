/**
 * 根据id请求接口的类型
 */
export interface IdParam{
    id: string
}
/**
 * 更新时的对象参数
 */
export interface updateParam{
    id: string
    version: number,
    [key: string]: any
}
