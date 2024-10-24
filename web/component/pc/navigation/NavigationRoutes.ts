import navigationCategoryAdminRoutes from "./views/admin/navigationCategoryAdminRoutes";
import navigationSiteCategoryRelAdminRoutes from "./views/admin/navigationSiteCategoryRelAdminRoutes";
import navigationSiteAdminRoutes from "./views/admin/navigationSiteAdminRoutes";

const NavigationRoutes = []
    .concat(navigationCategoryAdminRoutes)
    .concat(navigationSiteAdminRoutes)
    .concat(navigationSiteCategoryRelAdminRoutes)
export default NavigationRoutes
