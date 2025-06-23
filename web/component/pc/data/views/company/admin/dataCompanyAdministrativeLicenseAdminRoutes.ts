const dataCompanyAdministrativeLicenseAdminRoutes = [
    {
        path: '/admin/dataCompanyAdministrativeLicenseManagePage',
        component: () => import('./DataCompanyAdministrativeLicenseManagePage.vue'),
        meta: {
            root: true,
            code:'adminDataCompanyAdministrativeLicenseManagePage',
            name: '企业行政许可管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dataCompanyAdministrativeLicenseManageAdd',
                component: () => import('./DataCompanyAdministrativeLicenseManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyAdministrativeLicenseManageAdd',
                    name: '企业行政许可添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyAdministrativeLicenseManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyAdministrativeLicenseManageAdd'
                    }
                }
            },

            {
                path: '/admin/dataCompanyAdministrativeLicenseManageUpdate',
                component: () => import('./DataCompanyAdministrativeLicenseManageUpdatePage.vue'),
                props: route => ({ dataCompanyAdministrativeLicenseId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyAdministrativeLicenseManageUpdate',
                    name: '企业行政许可修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyAdministrativeLicenseManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyAdministrativeLicenseManageUpdate'
                    }
                }
            },
        ]
    },
]
export default dataCompanyAdministrativeLicenseAdminRoutes