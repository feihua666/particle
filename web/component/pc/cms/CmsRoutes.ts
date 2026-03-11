import cmsSiteAdminRoutes from "./views/admin/cmsSiteAdminRoutes";
import cmsChannelAdminRoutes from "./views/admin/cmsChannelAdminRoutes";
import cmsContentAdminRoutes from "./views/admin/cmsContentAdminRoutes";
import cmsContentMultimediaAdminRoutes from "./views/admin/cmsContentMultimediaAdminRoutes";
import cmsContentCategoryAdminRoutes from "./views/admin/cmsContentCategoryAdminRoutes";
import cmsTemplateAdminRoutes from "./views/admin/cmsTemplateAdminRoutes";

const CmsRoutes = []
    .concat(cmsSiteAdminRoutes)
    .concat(cmsChannelAdminRoutes)
    .concat(cmsContentAdminRoutes)
    .concat(cmsContentMultimediaAdminRoutes)
    .concat(cmsContentCategoryAdminRoutes)
    .concat(cmsTemplateAdminRoutes)
export default CmsRoutes
