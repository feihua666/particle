import deptAdminRoutes from './views/admin/deptAdminRoutes'
import deptTreeAdminRoutes from './views/admin/deptTreeAdminRoutes'
import deptTreeNameAdminRoutes from './views/admin/deptTreeNameAdminRoutes'
import deptTreeUserRelAdminRoutes from './views/depttreeuserrel/admin/deptTreeUserRelAdminRoutes'
import deptUserRelAdminRoutes from './views/deptuserrel/admin/deptUserRelAdminRoutes'
const DeptRoutes = []
    .concat(deptAdminRoutes)
    .concat(deptTreeAdminRoutes)
    .concat(deptTreeNameAdminRoutes)
    .concat(deptTreeUserRelAdminRoutes)
    .concat(deptUserRelAdminRoutes)
export default DeptRoutes