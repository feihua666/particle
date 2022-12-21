import FuncRoutes from './func/FuncRoutes'
import DictRoutes from './dict/DictRoutes'
import AreaRoutes from './area/AreaRoutes'

let ComponentRoutes = []
    .concat(FuncRoutes)
    .concat(DictRoutes)
    .concat(AreaRoutes)
export default ComponentRoutes