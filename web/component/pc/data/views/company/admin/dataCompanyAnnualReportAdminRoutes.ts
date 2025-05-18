const dataCompanyAnnualReportAdminRoutes = [
    {
        path: '/admin/dataCompanyAnnualReportManagePage',
        component: () => import('./DataCompanyAnnualReportManagePage.vue'),
        meta: {
            root: true,
            code:'adminDataCompanyAnnualReportManagePage',
            name: '企业年报管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dataCompanyAnnualReportManageAdd',
                component: () => import('./DataCompanyAnnualReportManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyAnnualReportManageAdd',
                    name: '企业年报添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyAnnualReportManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyAnnualReportManageAdd'
                    }
                }
            },

            {
                path: '/admin/dataCompanyAnnualReportManageUpdate',
                component: () => import('./DataCompanyAnnualReportManageUpdatePage.vue'),
                props: route => ({ dataCompanyAnnualReportId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyAnnualReportManageUpdate',
                    name: '企业年报修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyAnnualReportManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyAnnualReportManageUpdate'
                    }
                }
            },
        ]
    },
]
export default dataCompanyAnnualReportAdminRoutes