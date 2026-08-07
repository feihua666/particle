import crawlerProjectAdminRoutes from "./views/definition/admin/crawlerProjectAdminRoutes.ts";
import crawlerDefinitionAdminRoutes from "./views/definition/admin/crawlerDefinitionAdminRoutes.ts";
import crawlerDefinitionHistoryAdminRoutes from "./views/definition/admin/crawlerDefinitionHistoryAdminRoutes.ts";
import crawlerExecutionAdminRoutes from "./views/execution/admin/crawlerExecutionAdminRoutes.ts";
import crawlerRawStoreAdminRoutes from "./views/execution/admin/crawlerRawStoreAdminRoutes.ts";
import crawlerRawStoreContentAdminRoutes from "./views/execution/admin/crawlerRawStoreContentAdminRoutes.ts";
import crawlerDataStoreAdminRoutes from "./views/execution/admin/crawlerDataStoreAdminRoutes.ts";
import crawlerDataStoreContentAdminRoutes from "./views/execution/admin/crawlerDataStoreContentAdminRoutes.ts";

const CrawlerRoutes = []
    .concat(crawlerProjectAdminRoutes)
    .concat(crawlerDefinitionAdminRoutes)
    .concat(crawlerDefinitionHistoryAdminRoutes)
    .concat(crawlerExecutionAdminRoutes)
    .concat(crawlerRawStoreAdminRoutes)
    .concat(crawlerRawStoreContentAdminRoutes)
    .concat(crawlerDataStoreAdminRoutes)
    .concat(crawlerDataStoreContentAdminRoutes)
export default CrawlerRoutes
