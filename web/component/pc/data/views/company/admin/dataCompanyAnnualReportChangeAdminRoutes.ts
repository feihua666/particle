const dataCompanyAnnualReportChangeAdminRoutes = [
    {
        path: '/admin/dataCompanyAnnualReportChangeManagePage',
        component: () => import('./DataCompanyAnnualReportChangeManagePage.vue'),
        meta: {
            root: true,
            code:'adminDataCompanyAnnualReportChangeManagePage',
            name: '企业年报变更管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dataCompanyAnnualReportChangeManageAdd',
                component: () => import('./DataCompanyAnnualReportChangeManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyAnnualReportChangeManageAdd',
                    name: '企业年报变更添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyAnnualReportChangeManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyAnnualReportChangeManageAdd'
                    }
                }
            },

            {
                path: '/admin/dataCompanyAnnualReportChangeManageUpdate',
                component: () => import('./DataCompanyAnnualReportChangeManageUpdatePage.vue'),
                props: route => ({ dataCompanyAnnualReportChangeId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyAnnualReportChangeManageUpdate',
                    name: '企业年报变更修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyAnnualReportChangeManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyAnnualReportChangeManageUpdate'
                    }
                }
            },
        ]
    },
]
export default dataCompanyAnnualReportChangeAdminRoutes