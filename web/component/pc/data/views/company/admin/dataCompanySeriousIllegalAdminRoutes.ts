const dataCompanySeriousIllegalAdminRoutes = [
    {
        path: '/admin/dataCompanySeriousIllegalManagePage',
        component: () => import('./DataCompanySeriousIllegalManagePage.vue'),
        meta: {
            root: true,
            code:'adminDataCompanySeriousIllegalManagePage',
            name: '企业严重违法管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dataCompanySeriousIllegalManageAdd',
                component: () => import('./DataCompanySeriousIllegalManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanySeriousIllegalManageAdd',
                    name: '企业严重违法添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanySeriousIllegalManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanySeriousIllegalManageAdd'
                    }
                }
            },

            {
                path: '/admin/dataCompanySeriousIllegalManageUpdate',
                component: () => import('./DataCompanySeriousIllegalManageUpdatePage.vue'),
                props: route => ({ dataCompanySeriousIllegalId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanySeriousIllegalManageUpdate',
                    name: '企业严重违法修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanySeriousIllegalManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanySeriousIllegalManageUpdate'
                    }
                }
            },
        ]
    },
]
export default dataCompanySeriousIllegalAdminRoutes