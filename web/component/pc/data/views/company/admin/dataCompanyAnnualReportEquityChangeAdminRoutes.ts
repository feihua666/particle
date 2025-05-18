const dataCompanyAnnualReportEquityChangeAdminRoutes = [
    {
        path: '/admin/dataCompanyAnnualReportEquityChangeManagePage',
        component: () => import('./DataCompanyAnnualReportEquityChangeManagePage.vue'),
        meta: {
            root: true,
            code:'adminDataCompanyAnnualReportEquityChangeManagePage',
            name: '企业年报股权变更管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dataCompanyAnnualReportEquityChangeManageAdd',
                component: () => import('./DataCompanyAnnualReportEquityChangeManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyAnnualReportEquityChangeManageAdd',
                    name: '企业年报股权变更添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyAnnualReportEquityChangeManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyAnnualReportEquityChangeManageAdd'
                    }
                }
            },

            {
                path: '/admin/dataCompanyAnnualReportEquityChangeManageUpdate',
                component: () => import('./DataCompanyAnnualReportEquityChangeManageUpdatePage.vue'),
                props: route => ({ dataCompanyAnnualReportEquityChangeId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyAnnualReportEquityChangeManageUpdate',
                    name: '企业年报股权变更修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyAnnualReportEquityChangeManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyAnnualReportEquityChangeManageUpdate'
                    }
                }
            },
        ]
    },
]
export default dataCompanyAnnualReportEquityChangeAdminRoutes