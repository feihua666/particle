import opLogAdminRoutes from "./views/admin/opLogAdminRoutes";
import opLogAuditDataAdminRoutes from "./views/admin/opLogAuditDataAdminRoutes";

const OpLogRoutes = []
    .concat(opLogAdminRoutes)
    .concat(opLogAuditDataAdminRoutes)
export default OpLogRoutes