import dataQueryProviderAdminRoutes from "./views/provider/admin/dataQueryProviderAdminRoutes";
import dataQueryDatasourceAdminRoutes from "./views/datasource/admin/dataQueryDatasourceAdminRoutes";
import dataQueryDatasourceApiAdminRoutes from "./views/datasource/admin/dataQueryDatasourceApiAdminRoutes";

let DataQueryRoutes = []
    .concat(dataQueryProviderAdminRoutes)
    .concat(dataQueryDatasourceAdminRoutes)
    .concat(dataQueryDatasourceApiAdminRoutes)
export default DataQueryRoutes