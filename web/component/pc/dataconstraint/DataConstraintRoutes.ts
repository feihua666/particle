import dataObjectAdminRoutes from "./views/admin/dataObjectAdminRoutes";
import dataScopeAdminRoutes from "./views/admin/dataScopeAdminRoutes";

const DataConstraintRoutes = []
    .concat(dataObjectAdminRoutes)
    .concat(dataScopeAdminRoutes)
export default DataConstraintRoutes