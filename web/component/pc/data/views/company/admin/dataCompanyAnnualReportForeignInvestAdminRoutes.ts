const dataCompanyAnnualReportForeignInvestAdminRoutes = [
    {
        path: '/admin/dataCompanyAnnualReportForeignInvestManagePage',
        component: () => import('./DataCompanyAnnualReportForeignInvestManagePage.vue'),
        meta: {
            root: true,
            code:'adminDataCompanyAnnualReportForeignInvestManagePage',
            name: '企业年报对外投资管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dataCompanyAnnualReportForeignInvestManageAdd',
                component: () => import('./DataCompanyAnnualReportForeignInvestManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyAnnualReportForeignInvestManageAdd',
                    name: '企业年报对外投资添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyAnnualReportForeignInvestManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyAnnualReportForeignInvestManageAdd'
                    }
                }
            },

            {
                path: '/admin/dataCompanyAnnualReportForeignInvestManageUpdate',
                component: () => import('./DataCompanyAnnualReportForeignInvestManageUpdatePage.vue'),
                props: route => ({ dataCompanyAnnualReportForeignInvestId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyAnnualReportForeignInvestManageUpdate',
                    name: '企业年报对外投资修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyAnnualReportForeignInvestManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyAnnualReportForeignInvestManageUpdate'
                    }
                }
            },
        ]
    },
]
export default dataCompanyAnnualReportForeignInvestAdminRoutes