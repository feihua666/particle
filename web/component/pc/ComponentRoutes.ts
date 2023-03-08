import FuncRoutes from './func/FuncRoutes'
import DictRoutes from './dict/DictRoutes'
import AreaRoutes from './area/AreaRoutes'
import UserRoutes from './user/UserRoutes'
import RoleRoutes from './role/RoleRoutes'
import ToolsRoutes from './tools/ToolsRoutes'
import LowcodeRoutes from './lowcode/LowcodeRoutes'
import DataQueryRoutes from './dataquery/DataQueryRoutes.ts'

let ComponentRoutes = []
    .concat(FuncRoutes)
    .concat(DictRoutes)
    .concat(AreaRoutes)
    .concat(UserRoutes)
    .concat(RoleRoutes)
    .concat(ToolsRoutes)
    .concat(LowcodeRoutes)
    .concat(DataQueryRoutes)
export default ComponentRoutes