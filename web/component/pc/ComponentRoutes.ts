import FuncRoutes from './func/FuncRoutes'
import DictRoutes from './dict/DictRoutes'
import AreaRoutes from './area/AreaRoutes'
import UserRoutes from './user/UserRoutes'

let ComponentRoutes = []
    .concat(FuncRoutes)
    .concat(DictRoutes)
    .concat(AreaRoutes)
    .concat(UserRoutes)
export default ComponentRoutes