const dataCompanyAnnualReportAdministrativeLicenseAdminRoutes = [
    {
        path: '/admin/dataCompanyAnnualReportAdministrativeLicenseManagePage',
        component: () => import('./DataCompanyAnnualReportAdministrativeLicenseManagePage.vue'),
        meta: {
            root: true,
            code:'adminDataCompanyAnnualReportAdministrativeLicenseManagePage',
            name: '企业年报行政许可管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dataCompanyAnnualReportAdministrativeLicenseManageAdd',
                component: () => import('./DataCompanyAnnualReportAdministrativeLicenseManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyAnnualReportAdministrativeLicenseManageAdd',
                    name: '企业年报行政许可添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyAnnualReportAdministrativeLicenseManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyAnnualReportAdministrativeLicenseManageAdd'
                    }
                }
            },

            {
                path: '/admin/dataCompanyAnnualReportAdministrativeLicenseManageUpdate',
                component: () => import('./DataCompanyAnnualReportAdministrativeLicenseManageUpdatePage.vue'),
                props: route => ({ dataCompanyAnnualReportAdministrativeLicenseId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyAnnualReportAdministrativeLicenseManageUpdate',
                    name: '企业年报行政许可修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyAnnualReportAdministrativeLicenseManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyAnnualReportAdministrativeLicenseManageUpdate'
                    }
                }
            },
        ]
    },
]
export default dataCompanyAnnualReportAdministrativeLicenseAdminRoutes