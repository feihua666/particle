import workflowProjectAdminRoutes from "./views/definition/admin/workflowProjectAdminRoutes";
import workflowDefinitionAdminRoutes from "./views/definition/admin/workflowDefinitionAdminRoutes";
import workflowDefinitionHistoryAdminRoutes from "./views/definition/admin/workflowDefinitionHistoryAdminRoutes";
import workflowExecutionAdminRoutes from "./views/execution/admin/workflowExecutionAdminRoutes";
import workflowExecutionNodeAdminRoutes from "./views/execution/admin/workflowExecutionNodeAdminRoutes";

const WorkflowRoutes = []
    .concat(workflowProjectAdminRoutes)
    .concat(workflowDefinitionAdminRoutes)
    .concat(workflowDefinitionHistoryAdminRoutes)
    .concat(workflowExecutionAdminRoutes)
    .concat(workflowExecutionNodeAdminRoutes)
export default WorkflowRoutes
