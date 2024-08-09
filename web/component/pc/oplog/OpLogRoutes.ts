import opLogAdminRoutes from "./views/admin/opLogAdminRoutes";
import opLogAuditDataAdminRoutes from "./views/admin/opLogAuditDataAdminRoutes";
import opLogErrorAdminRoutes from "./views/error/admin/opLogErrorAdminRoutes";

const OpLogRoutes = []
    .concat(opLogAdminRoutes)
    .concat(opLogAuditDataAdminRoutes)
    .concat(opLogErrorAdminRoutes)
export default OpLogRoutes