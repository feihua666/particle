import FuncRoutes from './func/FuncRoutes'
import DictRoutes from './dict/DictRoutes'
import AreaRoutes from './area/AreaRoutes'
import UserRoutes from './user/UserRoutes'
import RoleRoutes from './role/RoleRoutes'
import ToolsRoutes from './tools/ToolsRoutes'
import LowCodeRoutes from './lowcode/LowCodeRoutes'

let ComponentRoutes = []
    .concat(FuncRoutes)
    .concat(DictRoutes)
    .concat(AreaRoutes)
    .concat(UserRoutes)
    .concat(RoleRoutes)
    .concat(ToolsRoutes)
    .concat(LowCodeRoutes)
export default ComponentRoutes