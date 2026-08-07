const agiAiModelAdminRoutes = [
    {
        path: '/admin/agiAiModelManagePage',
        component: () => import('./AgiAiModelManagePage.vue'),
        meta: {
            root: true,
            code:'adminAgiAiModelManagePage',
            name: 'AI模型管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/agiAiModelManageAdd',
                component: () => import('./AgiAiModelManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminAgiAiModelManageAdd',
                    name: 'AI模型添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminAgiAiModelManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminAgiAiModelManageAdd'
                    }
                }
            },

            {
                path: '/admin/agiAiModelManageUpdate',
                component: () => import('./AgiAiModelManageUpdatePage.vue'),
                props: route => ({ agiAiModelId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminAgiAiModelManageUpdate',
                    name: 'AI模型修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminAgiAiModelManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminAgiAiModelManageUpdate'
                    }
                }
            },
        ]
    },
]
export default agiAiModelAdminRoutes
