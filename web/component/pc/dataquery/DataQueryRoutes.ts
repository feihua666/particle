import dataQueryProviderAdminRoutes from "./views/provider/admin/dataQueryProviderAdminRoutes";
import dataQueryDatasourceAdminRoutes from "./views/datasource/admin/dataQueryDatasourceAdminRoutes";
import dataQueryDatasourceApiAdminRoutes from "./views/datasource/admin/dataQueryDatasourceApiAdminRoutes";
import dataQueryDataApiAdminRoutes from "./views/dataapi/admin/dataQueryDataApiAdminRoutes";

let DataQueryRoutes = []
    .concat(dataQueryProviderAdminRoutes)
    .concat(dataQueryDatasourceAdminRoutes)
    .concat(dataQueryDatasourceApiAdminRoutes)
    .concat(dataQueryDataApiAdminRoutes)
export default DataQueryRoutes