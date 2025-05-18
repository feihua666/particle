const dataCompanyCaseFilingPartyAdminRoutes = [
    {
        path: '/admin/dataCompanyCaseFilingPartyManagePage',
        component: () => import('./DataCompanyCaseFilingPartyManagePage.vue'),
        meta: {
            root: true,
            code:'adminDataCompanyCaseFilingPartyManagePage',
            name: '企业立案信息当事人管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dataCompanyCaseFilingPartyManageAdd',
                component: () => import('./DataCompanyCaseFilingPartyManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyCaseFilingPartyManageAdd',
                    name: '企业立案信息当事人添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyCaseFilingPartyManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyCaseFilingPartyManageAdd'
                    }
                }
            },

            {
                path: '/admin/dataCompanyCaseFilingPartyManageUpdate',
                component: () => import('./DataCompanyCaseFilingPartyManageUpdatePage.vue'),
                props: route => ({ dataCompanyCaseFilingPartyId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyCaseFilingPartyManageUpdate',
                    name: '企业立案信息当事人修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyCaseFilingPartyManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyCaseFilingPartyManageUpdate'
                    }
                }
            },
        ]
    },
]
export default dataCompanyCaseFilingPartyAdminRoutes