const dataCompanyAnnualReportAssetsAdminRoutes = [
    {
        path: '/admin/dataCompanyAnnualReportAssetsManagePage',
        component: () => import('./DataCompanyAnnualReportAssetsManagePage.vue'),
        meta: {
            root: true,
            code:'adminDataCompanyAnnualReportAssetsManagePage',
            name: '企业资产状况信息管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dataCompanyAnnualReportAssetsManageAdd',
                component: () => import('./DataCompanyAnnualReportAssetsManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyAnnualReportAssetsManageAdd',
                    name: '企业资产状况信息添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyAnnualReportAssetsManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyAnnualReportAssetsManageAdd'
                    }
                }
            },

            {
                path: '/admin/dataCompanyAnnualReportAssetsManageUpdate',
                component: () => import('./DataCompanyAnnualReportAssetsManageUpdatePage.vue'),
                props: route => ({ dataCompanyAnnualReportAssetsId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyAnnualReportAssetsManageUpdate',
                    name: '企业资产状况信息修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyAnnualReportAssetsManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyAnnualReportAssetsManageUpdate'
                    }
                }
            },
        ]
    },
]
export default dataCompanyAnnualReportAssetsAdminRoutes