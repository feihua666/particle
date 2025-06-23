const dataCompanyIprTrademarkLicenseAdminRoutes = [
    {
        path: '/admin/dataCompanyIprTrademarkLicenseManagePage',
        component: () => import('./DataCompanyIprTrademarkLicenseManagePage.vue'),
        meta: {
            root: true,
            code:'adminDataCompanyIprTrademarkLicenseManagePage',
            name: '企业知识产权商标许可信息管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dataCompanyIprTrademarkLicenseManageAdd',
                component: () => import('./DataCompanyIprTrademarkLicenseManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyIprTrademarkLicenseManageAdd',
                    name: '企业知识产权商标许可信息添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyIprTrademarkLicenseManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyIprTrademarkLicenseManageAdd'
                    }
                }
            },

            {
                path: '/admin/dataCompanyIprTrademarkLicenseManageUpdate',
                component: () => import('./DataCompanyIprTrademarkLicenseManageUpdatePage.vue'),
                props: route => ({ dataCompanyIprTrademarkLicenseId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyIprTrademarkLicenseManageUpdate',
                    name: '企业知识产权商标许可信息修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyIprTrademarkLicenseManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyIprTrademarkLicenseManageUpdate'
                    }
                }
            },
        ]
    },
]
export default dataCompanyIprTrademarkLicenseAdminRoutes