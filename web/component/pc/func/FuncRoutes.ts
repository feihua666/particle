import funcApplicationAdminRoutes from './views/application/admin/funcApplicationAdminRoutes'
import funcAdminRoutes from './views/admin/funcAdminRoutes'
import funcApplicationFuncRelAdminRoutes from './views/admin/funcApplicationFuncRelAdminRoutes'

const FuncRoutes = []
    .concat(funcApplicationAdminRoutes)
    .concat(funcAdminRoutes)
    .concat(funcApplicationFuncRelAdminRoutes)
export default FuncRoutes
