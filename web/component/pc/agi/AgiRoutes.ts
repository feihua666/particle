import agiVectorStoreRawDocumentAdminRoutes from "./views/rag/admin/agiVectorStoreRawDocumentAdminRoutes";
import agiVectorStoreRawDocumentSegmentAdminRoutes from "./views/rag/admin/agiVectorStoreRawDocumentSegmentAdminRoutes";
import agiAgentAdminRoutes from "./views/agent/admin/agiAgentAdminRoutes";
import agiAiModelAdminRoutes from "./views/model/admin/agiAiModelAdminRoutes";
import agiModelProviderAdminRoutes from "./views/model/admin/agiModelProviderAdminRoutes";

const AgiRoutes = []
    .concat(agiVectorStoreRawDocumentAdminRoutes)
    .concat(agiVectorStoreRawDocumentSegmentAdminRoutes)
    .concat(agiAgentAdminRoutes)
    .concat(agiAiModelAdminRoutes)
    .concat(agiModelProviderAdminRoutes)
export default AgiRoutes
