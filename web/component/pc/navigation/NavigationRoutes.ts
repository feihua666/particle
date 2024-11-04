import navigationCategoryAdminRoutes from "./views/admin/navigationCategoryAdminRoutes";
import navigationSiteCategoryRelAdminRoutes from "./views/admin/navigationSiteCategoryRelAdminRoutes";
import navigationSiteAdminRoutes from "./views/admin/navigationSiteAdminRoutes";
import navigationStaticDeployAdminRoutes from "./views/admin/navigationStaticDeployAdminRoutes";
import navigationSubmitAdminRoutes from "./views/admin/navigationSubmitAdminRoutes";
import navigationFriendshipLinkAdminRoutes from "./views/admin/navigationFriendshipLinkAdminRoutes";

const NavigationRoutes = []
    .concat(navigationCategoryAdminRoutes)
    .concat(navigationSiteAdminRoutes)
    .concat(navigationSiteCategoryRelAdminRoutes)
    .concat(navigationStaticDeployAdminRoutes)
    .concat(navigationFriendshipLinkAdminRoutes)
    .concat(navigationSubmitAdminRoutes)

export default NavigationRoutes
