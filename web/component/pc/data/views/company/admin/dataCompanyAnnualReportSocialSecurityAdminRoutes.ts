const dataCompanyAnnualReportSocialSecurityAdminRoutes = [
    {
        path: '/admin/dataCompanyAnnualReportSocialSecurityManagePage',
        component: () => import('./DataCompanyAnnualReportSocialSecurityManagePage.vue'),
        meta: {
            root: true,
            code:'adminDataCompanyAnnualReportSocialSecurityManagePage',
            name: '企业年报社保管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dataCompanyAnnualReportSocialSecurityManageAdd',
                component: () => import('./DataCompanyAnnualReportSocialSecurityManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyAnnualReportSocialSecurityManageAdd',
                    name: '企业年报社保添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyAnnualReportSocialSecurityManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyAnnualReportSocialSecurityManageAdd'
                    }
                }
            },

            {
                path: '/admin/dataCompanyAnnualReportSocialSecurityManageUpdate',
                component: () => import('./DataCompanyAnnualReportSocialSecurityManageUpdatePage.vue'),
                props: route => ({ dataCompanyAnnualReportSocialSecurityId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyAnnualReportSocialSecurityManageUpdate',
                    name: '企业年报社保修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyAnnualReportSocialSecurityManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyAnnualReportSocialSecurityManageUpdate'
                    }
                }
            },
        ]
    },
]
export default dataCompanyAnnualReportSocialSecurityAdminRoutes