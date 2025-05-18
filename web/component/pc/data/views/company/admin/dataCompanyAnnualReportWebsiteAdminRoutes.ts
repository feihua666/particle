const dataCompanyAnnualReportWebsiteAdminRoutes = [
    {
        path: '/admin/dataCompanyAnnualReportWebsiteManagePage',
        component: () => import('./DataCompanyAnnualReportWebsiteManagePage.vue'),
        meta: {
            root: true,
            code:'adminDataCompanyAnnualReportWebsiteManagePage',
            name: '企业年报网站网店管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dataCompanyAnnualReportWebsiteManageAdd',
                component: () => import('./DataCompanyAnnualReportWebsiteManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyAnnualReportWebsiteManageAdd',
                    name: '企业年报网站网店添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyAnnualReportWebsiteManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyAnnualReportWebsiteManageAdd'
                    }
                }
            },

            {
                path: '/admin/dataCompanyAnnualReportWebsiteManageUpdate',
                component: () => import('./DataCompanyAnnualReportWebsiteManageUpdatePage.vue'),
                props: route => ({ dataCompanyAnnualReportWebsiteId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyAnnualReportWebsiteManageUpdate',
                    name: '企业年报网站网店修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyAnnualReportWebsiteManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyAnnualReportWebsiteManageUpdate'
                    }
                }
            },
        ]
    },
]
export default dataCompanyAnnualReportWebsiteAdminRoutes