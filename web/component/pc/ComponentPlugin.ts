import FuncPlugin from './func/FuncPlugin.ts'
import UserPlugin from './user/UserPlugin.ts'
import DictPlugin from './dict/DictPlugin'
import AreaPlugin from './area/AreaPlugin'
import RolePlugin from './role/RolePlugin'
import ToolsPlugin from './tools/ToolsPlugin'
import LowCodePlugin from './lowcode/LowCodePlugin'
export default {
    install: function (app, options) {
        FuncPlugin.install(app,options)
        UserPlugin.install(app,options)
        DictPlugin.install(app,options)
        AreaPlugin.install(app,options)
        RolePlugin.install(app,options)
        ToolsPlugin.install(app,options)
        LowCodePlugin.install(app,options)
    }
}