const dataCompanyAnnualReportForeignGuaranteeAdminRoutes = [
    {
        path: '/admin/dataCompanyAnnualReportForeignGuaranteeManagePage',
        component: () => import('./DataCompanyAnnualReportForeignGuaranteeManagePage.vue'),
        meta: {
            root: true,
            code:'adminDataCompanyAnnualReportForeignGuaranteeManagePage',
            name: '企业年报对外担保管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dataCompanyAnnualReportForeignGuaranteeManageAdd',
                component: () => import('./DataCompanyAnnualReportForeignGuaranteeManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyAnnualReportForeignGuaranteeManageAdd',
                    name: '企业年报对外担保添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyAnnualReportForeignGuaranteeManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyAnnualReportForeignGuaranteeManageAdd'
                    }
                }
            },

            {
                path: '/admin/dataCompanyAnnualReportForeignGuaranteeManageUpdate',
                component: () => import('./DataCompanyAnnualReportForeignGuaranteeManageUpdatePage.vue'),
                props: route => ({ dataCompanyAnnualReportForeignGuaranteeId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyAnnualReportForeignGuaranteeManageUpdate',
                    name: '企业年报对外担保修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyAnnualReportForeignGuaranteeManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyAnnualReportForeignGuaranteeManageUpdate'
                    }
                }
            },
        ]
    },
]
export default dataCompanyAnnualReportForeignGuaranteeAdminRoutes