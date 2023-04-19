import funcApplicationAdminRoutes from './views/application/admin/funcApplicationAdminRoutes.ts'
import funcAdminRoutes from './views/admin/funcAdminRoutes.ts'
import funcApplicationFuncRelAdminRoutes from './views/admin/funcApplicationFuncRelAdminRoutes.ts'
const FuncRoutes = []
    .concat(funcApplicationAdminRoutes)
    .concat(funcAdminRoutes)
    .concat(funcApplicationFuncRelAdminRoutes)
export default FuncRoutes