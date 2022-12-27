// 使用一个模块前缀
let prefix = 'PtRole'
let map = {

}
export default {
    install: function (app, options) {
        // 添加实例方法
        for (let mapKey in map) {
            app.component(prefix + mapKey,map[mapKey])
        }
    }
}