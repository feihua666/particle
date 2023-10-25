import usageCountDefineAdminRoutes from "./views/admin/usageCountDefineAdminRoutes";
import usageCountConfigAdminRoutes from "./views/admin/usageCountConfigAdminRoutes";
import usageCountRecordAdminRoutes from "./views/admin/usageCountRecordAdminRoutes";
import usageCountRecordDetailAdminRoutes from "./views/admin/usageCountRecordDetailAdminRoutes";

const UsageCountRoutes = []
    .concat(usageCountDefineAdminRoutes)
    .concat(usageCountConfigAdminRoutes)
    .concat(usageCountRecordAdminRoutes)
    // .concat(usageCountRecordDetailAdminRoutes)
export default UsageCountRoutes