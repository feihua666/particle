import trackingPageAdminRoutes from "./views/admin/trackingPageAdminRoutes";
import trackingPageRecordAdminRoutes from "./views/admin/trackingPageRecordAdminRoutes";

const TrackingRoutes = []
    .concat(trackingPageAdminRoutes)
    .concat(trackingPageRecordAdminRoutes)
export default TrackingRoutes