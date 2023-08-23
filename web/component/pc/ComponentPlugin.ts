import FuncPlugin from './func/FuncPlugin.ts'
import UserPlugin from './user/UserPlugin.ts'
import DictPlugin from './dict/DictPlugin'
import AreaPlugin from './area/AreaPlugin'
import RolePlugin from './role/RolePlugin'
import ToolsPlugin from './tools/ToolsPlugin'
import LowcodePlugin from './lowcode/LowcodePlugin'
import DataQueryPlugin from './dataquery/DataQueryPlugin.ts'
import TenantPlugin from './tenant/TenantPlugin.ts'
import DeptPlugin from './dept/DeptPlugin.ts'
import OpLogPlugin from './oplog/OpLogPlugin.ts'
import TrackingPlugin from './tracking/TrackingPlugin.ts'
import MessagePlugin from './message/MessagePlugin.ts'
import Oauth2authorizationPlugin from './oauth2authorization/Oauth2authorizationPlugin.ts'
import OpenPlatformPlugin from './openplatform/OpenPlatformPlugin.ts'
export default {
    install: function (app, options) {
        FuncPlugin.install(app,options)
        UserPlugin.install(app,options)
        DictPlugin.install(app,options)
        AreaPlugin.install(app,options)
        RolePlugin.install(app,options)
        ToolsPlugin.install(app,options)
        LowcodePlugin.install(app,options)
        DataQueryPlugin.install(app,options)
        TenantPlugin.install(app,options)
        DeptPlugin.install(app,options)
        OpLogPlugin.install(app,options)
        TrackingPlugin.install(app,options)
        MessagePlugin.install(app,options)
        Oauth2authorizationPlugin.install(app,options)
        OpenPlatformPlugin.install(app,options)
    }
}