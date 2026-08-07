const agiModelProviderAdminRoutes = [
    {
        path: '/admin/agiModelProviderManagePage',
        component: () => import('./AgiModelProviderManagePage.vue'),
        meta: {
            root: true,
            code:'adminAgiModelProviderManagePage',
            name: 'AI模型提供商管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/agiModelProviderManageAdd',
                component: () => import('./AgiModelProviderManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminAgiModelProviderManageAdd',
                    name: 'AI模型提供商添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminAgiModelProviderManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminAgiModelProviderManageAdd'
                    }
                }
            },

            {
                path: '/admin/agiModelProviderManageUpdate',
                component: () => import('./AgiModelProviderManageUpdatePage.vue'),
                props: route => ({ agiModelProviderId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminAgiModelProviderManageUpdate',
                    name: 'AI模型提供商修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminAgiModelProviderManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminAgiModelProviderManageUpdate'
                    }
                }
            },
        ]
    },
]
export default agiModelProviderAdminRoutes
