import adminComponentAdminRoutes from "./views/admin/adminComponentAdminRoutes";
import adminComponentDependencyAdminRoutes from "./views/admin/adminComponentDependencyAdminRoutes";

const ComponentadminRoutes = []
    .concat(adminComponentAdminRoutes)
    .concat(adminComponentDependencyAdminRoutes)
export default ComponentadminRoutes
