const dataCompanySpotCheckAdminRoutes = [
    {
        path: '/admin/dataCompanySpotCheckManagePage',
        component: () => import('./DataCompanySpotCheckManagePage.vue'),
        meta: {
            root: true,
            code:'adminDataCompanySpotCheckManagePage',
            name: '企业抽查检查管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dataCompanySpotCheckManageAdd',
                component: () => import('./DataCompanySpotCheckManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanySpotCheckManageAdd',
                    name: '企业抽查检查添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanySpotCheckManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanySpotCheckManageAdd'
                    }
                }
            },

            {
                path: '/admin/dataCompanySpotCheckManageUpdate',
                component: () => import('./DataCompanySpotCheckManageUpdatePage.vue'),
                props: route => ({ dataCompanySpotCheckId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanySpotCheckManageUpdate',
                    name: '企业抽查检查修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanySpotCheckManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanySpotCheckManageUpdate'
                    }
                }
            },
        ]
    },
]
export default dataCompanySpotCheckAdminRoutes