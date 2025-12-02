import FuncRoutes from './func/FuncRoutes'
import DictRoutes from './dict/DictRoutes'
import AreaRoutes from './area/AreaRoutes'
import UserRoutes from './user/UserRoutes'
import RoleRoutes from './role/RoleRoutes'
import ToolsRoutes from './tools/ToolsRoutes'
import LowcodeRoutes from './lowcode/LowcodeRoutes'
import DataQueryRoutes from './dataquery/DataQueryRoutes'
import TenantRoutes from './tenant/TenantRoutes'
import DeptRoutes from './dept/DeptRoutes'
import OpLogRoutes from "./oplog/OpLogRoutes";
import TrackingRoutes from "./tracking/TrackingRoutes";
import MessageRoutes from "./message/MessageRoutes";
import Oauth2authorizationRoutes from "./oauth2authorization/Oauth2authorizationRoutes";
import OpenPlatformRoutes from "./openplatform/OpenPlatformRoutes";
import ReportRoutes from "./report/ReportRoutes";
import UsageCountRoutes from "./usagecount/UsageCountRoutes";
import FeedbackRoutes from "./feedback/FeedbackRoutes";
import CrmRoutes from "./crm/CrmRoutes";
import DreamRoutes from "./dream/DreamRoutes";
import ConfigRoutes from "./config/ConfigRoutes";
import DataConstraintRoutes from "./dataconstraint/DataConstraintRoutes";
import SchedulerRoutes from "./scheduler/SchedulerRoutes";
import NavigationRoutes from "./navigation/NavigationRoutes";
import AgiRoutes from "./agi/AgiRoutes";
import CmsRoutes from "./cms/CmsRoutes";
import DataRoutes from "./data/DataRoutes";

let ComponentRoutes = []
    .concat(FuncRoutes)
    .concat(DictRoutes)
    .concat(AreaRoutes)
    .concat(UserRoutes)
    .concat(RoleRoutes)
    .concat(ToolsRoutes)
    .concat(LowcodeRoutes)
    .concat(DataQueryRoutes)
    .concat(TenantRoutes)
    .concat(DeptRoutes)
    .concat(OpLogRoutes)
    .concat(TrackingRoutes)
    .concat(MessageRoutes)
    .concat(Oauth2authorizationRoutes)
    .concat(OpenPlatformRoutes)
    .concat(ReportRoutes)
    .concat(UsageCountRoutes)
    .concat(FeedbackRoutes)
    .concat(CrmRoutes)
    .concat(DreamRoutes)
    .concat(ConfigRoutes)
    .concat(DataConstraintRoutes)
    .concat(SchedulerRoutes)
    .concat(NavigationRoutes)
    .concat(AgiRoutes)
    .concat(CmsRoutes)
    .concat(DataRoutes)
export default ComponentRoutes
