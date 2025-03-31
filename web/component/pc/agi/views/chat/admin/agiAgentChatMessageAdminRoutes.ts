const agiAgentChatMessageAdminRoutes = [
    {
        path: '/admin/agiAgentChatMessageManagePage',
        component: () => import('./AgiAgentChatMessageManagePage.vue'),
        meta: {
            root: true,
            code:'adminAgiAgentChatMessageManagePage',
            name: '智能体对话消息管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/agiAgentChatMessageManageAdd',
                component: () => import('./AgiAgentChatMessageManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminAgiAgentChatMessageManageAdd',
                    name: '智能体对话消息添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminAgiAgentChatMessageManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminAgiAgentChatMessageManageAdd'
                    }
                }
            },

            {
                path: '/admin/agiAgentChatMessageManageUpdate',
                component: () => import('./AgiAgentChatMessageManageUpdatePage.vue'),
                props: route => ({ agiAgentChatMessageId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminAgiAgentChatMessageManageUpdate',
                    name: '智能体对话消息修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminAgiAgentChatMessageManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminAgiAgentChatMessageManageUpdate'
                    }
                }
            },
        ]
    },
]
export default agiAgentChatMessageAdminRoutes