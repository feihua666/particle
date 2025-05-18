const dataCompanyCaseFilingAdminRoutes = [
    {
        path: '/admin/dataCompanyCaseFilingManagePage',
        component: () => import('./DataCompanyCaseFilingManagePage.vue'),
        meta: {
            root: true,
            code:'adminDataCompanyCaseFilingManagePage',
            name: '企业立案信息管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dataCompanyCaseFilingManageAdd',
                component: () => import('./DataCompanyCaseFilingManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyCaseFilingManageAdd',
                    name: '企业立案信息添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyCaseFilingManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyCaseFilingManageAdd'
                    }
                }
            },

            {
                path: '/admin/dataCompanyCaseFilingManageUpdate',
                component: () => import('./DataCompanyCaseFilingManageUpdatePage.vue'),
                props: route => ({ dataCompanyCaseFilingId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyCaseFilingManageUpdate',
                    name: '企业立案信息修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyCaseFilingManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyCaseFilingManageUpdate'
                    }
                }
            },
        ]
    },
]
export default dataCompanyCaseFilingAdminRoutes