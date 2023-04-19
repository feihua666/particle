import tenantAdminRoutes from './views/admin/tenantAdminRoutes.ts'
import tenantUserAdminRoutes from './views/admin/tenantUserAdminRoutes.ts'
import tenantFuncAdminRoutes from './views/tenantfunc/admin/tenantFuncAdminRoutes.ts'
import tenantFuncApplicationAdminRoutes from './views/tenantfuncapplication/admin/tenantFuncApplicationAdminRoutes.ts'
import tenantCreateApplyAdminRoutes from './views/createapply/admin/tenantCreateApplyAdminRoutes.ts'
import tenantUserInviteAdminRoutes from './views/userinvite/admin/tenantUserInviteAdminRoutes.ts'
import tenantUserInviteUserRecordAdminRoutes from './views/userinvite/admin/tenantUserInviteUserRecordAdminRoutes.ts'
const TenantRoutes = []
    .concat(tenantAdminRoutes)
    .concat(tenantUserAdminRoutes)
    .concat(tenantFuncAdminRoutes)
    .concat(tenantFuncApplicationAdminRoutes)
    .concat(tenantCreateApplyAdminRoutes)
    .concat(tenantUserInviteAdminRoutes)
    .concat(tenantUserInviteUserRecordAdminRoutes)
export default TenantRoutes