import FuncRoutes from './func/FuncRoutes'
import DictRoutes from './dict/DictRoutes'
import AreaRoutes from './area/AreaRoutes'
import UserRoutes from './user/UserRoutes'
import RoleRoutes from './role/RoleRoutes'

let ComponentRoutes = []
    .concat(FuncRoutes)
    .concat(DictRoutes)
    .concat(AreaRoutes)
    .concat(UserRoutes)
    .concat(RoleRoutes)
export default ComponentRoutes