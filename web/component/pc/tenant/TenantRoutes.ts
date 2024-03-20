import tenantAdminRoutes from './views/admin/tenantAdminRoutes'
import tenantUserAdminRoutes from './views/admin/tenantUserAdminRoutes'
import tenantFuncAdminRoutes from './views/tenantfunc/admin/tenantFuncAdminRoutes'
import tenantFuncApplicationAdminRoutes from './views/tenantfuncapplication/admin/tenantFuncApplicationAdminRoutes'
import tenantCreateApplyAdminRoutes from './views/createapply/admin/tenantCreateApplyAdminRoutes'
import tenantUserInviteAdminRoutes from './views/userinvite/admin/tenantUserInviteAdminRoutes'
import tenantUserInviteUserRecordAdminRoutes from './views/userinvite/admin/tenantUserInviteUserRecordAdminRoutes'
const TenantRoutes = []
    .concat(tenantAdminRoutes)
    .concat(tenantUserAdminRoutes)
    .concat(tenantFuncAdminRoutes)
    .concat(tenantFuncApplicationAdminRoutes)
    .concat(tenantCreateApplyAdminRoutes)
    .concat(tenantUserInviteAdminRoutes)
    .concat(tenantUserInviteUserRecordAdminRoutes)
export default TenantRoutes