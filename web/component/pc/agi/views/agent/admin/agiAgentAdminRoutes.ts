const agiAgentAdminRoutes = [
    {
        path: '/admin/agiAgentManagePage',
        component: () => import('./AgiAgentManagePage.vue'),
        meta: {
            root: true,
            code:'adminAgiAgentManagePage',
            name: '智能体管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/agiAgentManageAdd',
                component: () => import('./AgiAgentManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminAgiAgentManageAdd',
                    name: '智能体添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminAgiAgentManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminAgiAgentManageAdd'
                    }
                }
            },

            {
                path: '/admin/agiAgentManageUpdate',
                component: () => import('./AgiAgentManageUpdatePage.vue'),
                props: route => ({ agiAgentId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminAgiAgentManageUpdate',
                    name: '智能体修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminAgiAgentManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminAgiAgentManageUpdate'
                    }
                }
            },
        ]
    },
    {
        path: '/admin/agiAgentChatPage',
        component: () => import('./AgiAgentChatPage.vue'),
        props: route => ({ agiAgentId: route.query.id,chatId: route.query.chatId }),
        meta: {
            root: true,
            code:'adminAgiAgentChatPage',
            name: '智能体对话聊天',
            keepAlive: false
        },
        children: [
            {
                path: '/front/agiAgentChatUpdate',
                component: () => import('../../chat/front/AgiAgentChatUpdatePage.vue'),
                props: route => ({ agiAgentChatId: route.query.id }),
                meta: {
                    showInDialog: true,
                    code:'frontAgiAgentChatUpdate',
                    name: '智能体对话修改',
                    // 将表单按钮显示在 drawer footer中
                    dialogProps: {
                        footerBoxId: 'frontAgiAgentChatUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        defer: true,
                        to: '#frontAgiAgentChatUpdate'
                    }
                }
            },
        ]
    },
]
export default agiAgentAdminRoutes
