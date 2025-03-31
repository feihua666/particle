import agiVectorStoreRawDocumentAdminRoutes from "./views/rag/admin/agiVectorStoreRawDocumentAdminRoutes";
import agiVectorStoreRawDocumentSegmentAdminRoutes from "./views/rag/admin/agiVectorStoreRawDocumentSegmentAdminRoutes";
import agiAgentAdminRoutes from "./views/agent/admin/agiAgentAdminRoutes";

const AgiRoutes = []
    .concat(agiVectorStoreRawDocumentAdminRoutes)
    .concat(agiVectorStoreRawDocumentSegmentAdminRoutes)
    .concat(agiAgentAdminRoutes)
export default AgiRoutes
