import dynamicTableAdminRoutes from "./views/dynamictable/admin/dynamicTableAdminRoutes";
import dynamicTableFieldAdminRoutes from "./views/dynamictable/admin/dynamicTableFieldAdminRoutes";
import dynamicDataCategoryAdminRoutes from "./views/dynamicdata/admin/dynamicDataCategoryAdminRoutes";
import dynamicDataIndicatorCategoryAdminRoutes from "./views/dynamicdata/admin/dynamicDataIndicatorCategoryAdminRoutes";
import dynamicDataIndicatorAdminRoutes from "./views/dynamicdata/admin/dynamicDataIndicatorAdminRoutes";
import dynamicTableUploadRecordAdminRoutes from "./views/dynamictable/admin/dynamicTableUploadRecordAdminRoutes";
import dynamicDataIndicatorCategoryUploadRecordAdminRoutes
    from "./views/dynamicdata/admin/dynamicDataIndicatorCategoryUploadRecordAdminRoutes";

const DataRoutes = []
    .concat(dynamicTableAdminRoutes)
    .concat(dynamicTableFieldAdminRoutes)
    .concat(dynamicDataCategoryAdminRoutes)
    .concat(dynamicDataIndicatorCategoryAdminRoutes)
    .concat(dynamicDataIndicatorAdminRoutes)
    .concat(dynamicDataIndicatorCategoryUploadRecordAdminRoutes)
    .concat(dynamicTableUploadRecordAdminRoutes)
export default DataRoutes
