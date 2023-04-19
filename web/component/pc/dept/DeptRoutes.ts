import deptAdminRoutes from './views/admin/deptAdminRoutes.ts'
import deptTreeAdminRoutes from './views/admin/deptTreeAdminRoutes.ts'
import deptTreeNameAdminRoutes from './views/admin/deptTreeNameAdminRoutes.ts'
import deptTreeUserRelAdminRoutes from './views/depttreeuserrel/admin/deptTreeUserRelAdminRoutes.ts'
import deptUserRelAdminRoutes from './views/deptuserrel/admin/deptUserRelAdminRoutes.ts'
const DeptRoutes = []
    .concat(deptAdminRoutes)
    .concat(deptTreeAdminRoutes)
    .concat(deptTreeNameAdminRoutes)
    .concat(deptTreeUserRelAdminRoutes)
    .concat(deptUserRelAdminRoutes)
export default DeptRoutes