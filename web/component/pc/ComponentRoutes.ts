import FuncRoutes from './func/FuncRoutes'
import DictRoutes from './dict/DictRoutes'
import AreaRoutes from './area/AreaRoutes'
import UserRoutes from './user/UserRoutes'
import RoleRoutes from './role/RoleRoutes'
import ToolsRoutes from './tools/ToolsRoutes'
import LowcodeRoutes from './lowcode/LowcodeRoutes'
import DataQueryRoutes from './dataquery/DataQueryRoutes.ts'
import TenantRoutes from './tenant/TenantRoutes.ts'
import DeptRoutes from './dept/DeptRoutes.ts'
import OpLogRoutes from "./oplog/OpLogRoutes";
import TrackingRoutes from "./tracking/TrackingRoutes";

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
export default ComponentRoutes