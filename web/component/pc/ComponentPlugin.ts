import FuncPlugin from './func/FuncPlugin'
import UserPlugin from './user/UserPlugin'
import DictPlugin from './dict/DictPlugin'
import AreaPlugin from './area/AreaPlugin'
import RolePlugin from './role/RolePlugin'
import ToolsPlugin from './tools/ToolsPlugin'
import LowcodePlugin from './lowcode/LowcodePlugin'
import DataQueryPlugin from './dataquery/DataQueryPlugin'
import TenantPlugin from './tenant/TenantPlugin'
import DeptPlugin from './dept/DeptPlugin'
import OpLogPlugin from './oplog/OpLogPlugin'
import TrackingPlugin from './tracking/TrackingPlugin'
import MessagePlugin from './message/MessagePlugin'
import Oauth2authorizationPlugin from './oauth2authorization/Oauth2authorizationPlugin'
import OpenPlatformPlugin from './openplatform/OpenPlatformPlugin'
import ReportPlugin from './report/ReportPlugin'
import UsageCountPlugin from './usagecount/UsageCountPlugin'
import FeedbackPlugin from './feedback/FeedbackPlugin'
import CrmPlugin from './crm/CrmPlugin'
import DreamPlugin from './dream/DreamPlugin'
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
        ReportPlugin.install(app,options)
        UsageCountPlugin.install(app,options)
        FeedbackPlugin.install(app,options)
        CrmPlugin.install(app,options)
        DreamPlugin.install(app,options)
    }
}