import openplatformAppAdminRoutes from './views/app/admin/openplatformAppAdminRoutes'
import openplatformAppOpenapiAdminRoutes from './views/app/admin/openplatformAppOpenapiAdminRoutes'
import openplatformOpenapiAdminRoutes from './views/openapi/admin/openplatformOpenapiAdminRoutes'
import openplatformOpenapiFeeAdminRoutes from './views/openapi/admin/openplatformOpenapiFeeAdminRoutes'
import openplatformOpenapiRecordAdminRoutes from './views/openapirecord/admin/openplatformOpenapiRecordAdminRoutes'
import openplatformProviderAdminRoutes from './views/provider/admin/openplatformProviderAdminRoutes'
import openplatformProviderRecordAdminRoutes from './views/providerrecord/admin/openplatformProviderRecordAdminRoutes'
import openplatformDocDirNameAdminRoutes from './views/doc/admin/openplatformDocDirNameAdminRoutes'
import openplatformDocDirAdminRoutes from './views/doc/admin/openplatformDocDirAdminRoutes'
import openplatformDocApiAdminRoutes from './views/doc/admin/openplatformDocApiAdminRoutes'
import openplatformDocApiDocAdminRoutes from './views/doc/admin/openplatformDocApiDocAdminRoutes'
import openplatformDocApiDocParamFieldAdminRoutes from './views/doc/admin/openplatformDocApiDocParamFieldAdminRoutes'
import openplatformDocApiDocResponseCodeAdminRoutes from './views/doc/admin/openplatformDocApiDocResponseCodeAdminRoutes'
import openplatformDocApiDocExampleCodeAdminRoutes from "./views/doc/admin/openplatformDocApiDocExampleCodeAdminRoutes";
import openplatformDocApiDocTemplateAdminRoutes from "./views/doc/admin/openplatformDocApiDocTemplateAdminRoutes";
import openplatformDocApiDocTemplateParamFieldAdminRoutes from "./views/doc/admin/openplatformDocApiDocTemplateParamFieldAdminRoutes";
import openplatformDocApiDocTemplateResponseCodeAdminRoutes from "./views/doc/admin/openplatformDocApiDocTemplateResponseCodeAdminRoutes";
import openplatformDocApiDocTemplateExampleCodeAdminRoutes from "./views/doc/admin/openplatformDocApiDocTemplateExampleCodeAdminRoutes";
import openplatformDocApiDirRelAdminRoutes from "./views/doc/admin/openplatformDocApiDirRelAdminRoutes";
import openplatformOpenapiRecordAppOpenapiDaySummaryAdminRoutes
    from "./views/bill/admin/openplatformOpenapiRecordAppOpenapiDaySummaryAdminRoutes";
import openplatformOpenapiRecordAppOpenapiMonthSummaryAdminRoutes
    from "./views/bill/admin/openplatformOpenapiRecordAppOpenapiMonthSummaryAdminRoutes";
import openplatformOpenapiRecordCustomerMonthBillAdminRoutes
    from "./views/bill/admin/openplatformOpenapiRecordCustomerMonthBillAdminRoutes";
import openplatformProviderRecordPrdApiDaySummaryAdminRoutes
    from "./views/bill/admin/openplatformProviderRecordPrdApiDaySummaryAdminRoutes";
import openplatformProviderRecordPrdApiMonthSummaryAdminRoutes
    from "./views/bill/admin/openplatformProviderRecordPrdApiMonthSummaryAdminRoutes";
import openplatformProviderRecordPrdMonthBillAdminRoutes
    from "./views/bill/admin/openplatformProviderRecordPrdMonthBillAdminRoutes";
import openplatformProviderApiAdminRoutes from "./views/provider/admin/openplatformProviderApiAdminRoutes";
import openplatformOpenapiBatchQueryRecordAdminRoutes
    from "./views/openapi/admin/openplatformOpenapiBatchQueryRecordAdminRoutes";

const OpenPlatformRoutes = []
    .concat(openplatformAppAdminRoutes)
    .concat(openplatformAppOpenapiAdminRoutes)
    .concat(openplatformOpenapiAdminRoutes)
    .concat(openplatformOpenapiFeeAdminRoutes)
    .concat(openplatformOpenapiRecordAdminRoutes)
    .concat(openplatformProviderAdminRoutes)
    .concat(openplatformProviderApiAdminRoutes)
    .concat(openplatformProviderRecordAdminRoutes)
    .concat(openplatformDocDirNameAdminRoutes)
    .concat(openplatformDocDirAdminRoutes)
    .concat(openplatformDocApiAdminRoutes)
    .concat(openplatformDocApiDocAdminRoutes)
    .concat(openplatformDocApiDocParamFieldAdminRoutes)
    .concat(openplatformDocApiDocResponseCodeAdminRoutes)
    .concat(openplatformDocApiDocExampleCodeAdminRoutes)
    .concat(openplatformDocApiDocTemplateAdminRoutes)
    .concat(openplatformDocApiDocTemplateParamFieldAdminRoutes)
    .concat(openplatformDocApiDocTemplateResponseCodeAdminRoutes)
    .concat(openplatformDocApiDocTemplateExampleCodeAdminRoutes)
    .concat(openplatformDocApiDirRelAdminRoutes)
    .concat(openplatformOpenapiRecordAppOpenapiDaySummaryAdminRoutes)
    .concat(openplatformOpenapiRecordAppOpenapiMonthSummaryAdminRoutes)
    .concat(openplatformOpenapiRecordCustomerMonthBillAdminRoutes)
    .concat(openplatformProviderRecordPrdApiDaySummaryAdminRoutes)
    .concat(openplatformProviderRecordPrdApiMonthSummaryAdminRoutes)
    .concat(openplatformProviderRecordPrdMonthBillAdminRoutes)
    .concat(openplatformOpenapiBatchQueryRecordAdminRoutes)
export default OpenPlatformRoutes