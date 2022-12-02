export interface RouteMeta{
    code: string,// 路由对应的功能菜单编码，可用来判断权限
    name: string,// 路由对应的功能菜单名称，目前仅用来注释
    keepAlive?: boolean,// 路由是否缓存，配置 自定义 router 使用
}