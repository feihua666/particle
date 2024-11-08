import navigationCategoryAdminRoutes from "./views/admin/navigationCategoryAdminRoutes";
import navigationSiteCategoryRelAdminRoutes from "./views/admin/navigationSiteCategoryRelAdminRoutes";
import navigationSiteAdminRoutes from "./views/admin/navigationSiteAdminRoutes";
import navigationStaticDeployAdminRoutes from "./views/admin/navigationStaticDeployAdminRoutes";
import navigationSubmitAdminRoutes from "./views/admin/navigationSubmitAdminRoutes";
import navigationFriendshipLinkAdminRoutes from "./views/admin/navigationFriendshipLinkAdminRoutes";
import navigationSiteTagAdminRoutes from "./views/admin/navigationSiteTagAdminRoutes";
import navigationSiteTagRelAdminRoutes from "./views/admin/navigationSiteTagRelAdminRoutes";

const NavigationRoutes = []
    .concat(navigationCategoryAdminRoutes)
    .concat(navigationSiteAdminRoutes)
    .concat(navigationSiteCategoryRelAdminRoutes)
    .concat(navigationStaticDeployAdminRoutes)
    .concat(navigationFriendshipLinkAdminRoutes)
    .concat(navigationSubmitAdminRoutes)
    .concat(navigationSiteTagAdminRoutes)
    .concat(navigationSiteTagRelAdminRoutes)

export default NavigationRoutes
