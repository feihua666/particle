const agiAgentChatAdminRoutes = [
    {
        path: '/admin/agiAgentChatManagePage',
        component: () => import('./AgiAgentChatManagePage.vue'),
        meta: {
            root: true,
            code:'adminAgiAgentChatManagePage',
            name: '智能体对话管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/agiAgentChatManageAdd',
                component: () => import('./AgiAgentChatManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminAgiAgentChatManageAdd',
                    name: '智能体对话添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminAgiAgentChatManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminAgiAgentChatManageAdd'
                    }
                }
            },

            {
                path: '/admin/agiAgentChatManageUpdate',
                component: () => import('./AgiAgentChatManageUpdatePage.vue'),
                props: route => ({ agiAgentChatId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminAgiAgentChatManageUpdate',
                    name: '智能体对话修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminAgiAgentChatManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminAgiAgentChatManageUpdate'
                    }
                }
            },
        ]
    },
]
export default agiAgentChatAdminRoutes