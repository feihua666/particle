const dataCompanyAnnualReportShareholderAdminRoutes = [
    {
        path: '/admin/dataCompanyAnnualReportShareholderManagePage',
        component: () => import('./DataCompanyAnnualReportShareholderManagePage.vue'),
        meta: {
            root: true,
            code:'adminDataCompanyAnnualReportShareholderManagePage',
            name: '企业年报股东管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dataCompanyAnnualReportShareholderManageAdd',
                component: () => import('./DataCompanyAnnualReportShareholderManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyAnnualReportShareholderManageAdd',
                    name: '企业年报股东添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyAnnualReportShareholderManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyAnnualReportShareholderManageAdd'
                    }
                }
            },

            {
                path: '/admin/dataCompanyAnnualReportShareholderManageUpdate',
                component: () => import('./DataCompanyAnnualReportShareholderManageUpdatePage.vue'),
                props: route => ({ dataCompanyAnnualReportShareholderId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyAnnualReportShareholderManageUpdate',
                    name: '企业年报股东修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyAnnualReportShareholderManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyAnnualReportShareholderManageUpdate'
                    }
                }
            },
        ]
    },
]
export default dataCompanyAnnualReportShareholderAdminRoutes