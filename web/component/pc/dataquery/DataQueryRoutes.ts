import dataQueryProviderAdminRoutes from "./views/provider/admin/dataQueryProviderAdminRoutes";
import dataQueryDatasourceAdminRoutes from "./views/datasource/admin/dataQueryDatasourceAdminRoutes";

let DataQueryRoutes = []
    .concat(dataQueryProviderAdminRoutes)
    .concat(dataQueryDatasourceAdminRoutes)
export default DataQueryRoutes