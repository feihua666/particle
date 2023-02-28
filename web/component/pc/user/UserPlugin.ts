import LoginForm from './compnents/login/LoginForm.vue'
import UserinfoDropdown from './compnents/login/UserinfoDropdown.vue'
// 使用一个模块前缀
let prefix = 'PtUser'
let map = {
    LoginForm,
    UserinfoDropdown,
}
export default {
    install: function (app, options) {
        // 添加实例方法
        for (let mapKey in map) {
            app.component(prefix + mapKey,map[mapKey])
        }
    }
}