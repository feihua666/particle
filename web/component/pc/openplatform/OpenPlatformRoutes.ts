import openplatformAppAdminRoutes from './views/app/admin/openplatformAppAdminRoutes.ts'
import openplatformAppOpenapiAdminRoutes from './views/app/admin/openplatformAppOpenapiAdminRoutes.ts'
import openplatformOpenapiAdminRoutes from './views/openapi/admin/openplatformOpenapiAdminRoutes.ts'
import openplatformOpenapiFeeAdminRoutes from './views/openapi/admin/openplatformOpenapiFeeAdminRoutes.ts'
import openplatformOpenapiRecordAdminRoutes from './views/openapirecord/admin/openplatformOpenapiRecordAdminRoutes.ts'
import openplatformProviderAdminRoutes from './views/provider/admin/openplatformProviderAdminRoutes.ts'
import openplatformProviderRecordAdminRoutes from './views/providerrecord/admin/openplatformProviderRecordAdminRoutes.ts'
const OpenPlatformRoutes = []
    .concat(openplatformAppAdminRoutes)
    .concat(openplatformAppOpenapiAdminRoutes)
    .concat(openplatformOpenapiAdminRoutes)
    .concat(openplatformOpenapiFeeAdminRoutes)
    .concat(openplatformOpenapiRecordAdminRoutes)
    .concat(openplatformProviderAdminRoutes)
    .concat(openplatformProviderRecordAdminRoutes)
export default OpenPlatformRoutes