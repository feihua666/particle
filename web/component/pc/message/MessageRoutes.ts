import messageAdminRoutes from "./views/admin/messageAdminRoutes";
import messageTemplateAdminRoutes from "./views/admin/messageTemplateAdminRoutes";
import messageUserStateAdminRoutes from "./views/admin/messageUserStateAdminRoutes";

const MessageRoutes = []
    .concat(messageAdminRoutes)
    .concat(messageTemplateAdminRoutes)
    .concat(messageUserStateAdminRoutes)
export default MessageRoutes