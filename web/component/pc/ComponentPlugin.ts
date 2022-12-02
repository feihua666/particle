import FuncPlugin from './func/FuncPlugin.ts'
import UserPlugin from './user/UserPlugin.ts'
export default {
    install: function (app, options) {
        FuncPlugin.install(app,options)
        UserPlugin.install(app,options)
    }
}