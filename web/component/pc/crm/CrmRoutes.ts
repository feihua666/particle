import crmCompanyAdminRoutes from "./views/company/admin/crmCompanyAdminRoutes";
import crmDeptAdminRoutes from "./views/company/admin/crmDeptAdminRoutes";
import crmCustomerAdminRoutes from "./views/customer/admin/crmCustomerAdminRoutes";
import crmCustomerContactAdminRoutes from "./views/customer/admin/crmCustomerContactAdminRoutes";
import crmCustomerRelationDefineAdminRoutes from "./views/ralation/admin/crmCustomerRelationDefineAdminRoutes";
import crmCustomerRelationAdminRoutes from "./views/ralation/admin/crmCustomerRelationAdminRoutes";
import crmCustomerTagAdminRoutes from "./views/tag/admin/crmCustomerTagAdminRoutes";
import crmCustomerTagRelAdminRoutes from "./views/tag/admin/crmCustomerTagRelAdminRoutes";

const CrmRoutes = []
    .concat(crmCompanyAdminRoutes)
    .concat(crmDeptAdminRoutes)
    .concat(crmCustomerAdminRoutes)
    .concat(crmCustomerContactAdminRoutes)
    .concat(crmCustomerRelationDefineAdminRoutes)
    .concat(crmCustomerRelationAdminRoutes)
    .concat(crmCustomerTagAdminRoutes)
    .concat(crmCustomerTagRelAdminRoutes)
export default CrmRoutes