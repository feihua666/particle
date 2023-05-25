const messageAdminRoutes = [
    {
        path: '/admin/messageManagePage',
        component: () => import('./MessageManagePage.vue'),
        meta: {
            root: true,
            code:'adminMessageManagePage',
            name: '消息管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/messageManageAdd',
                component: () => import('./MessageManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminMessageManageAdd',
                    name: '消息添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminMessageManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminMessageManageAdd'
                    }
                }
            },

            {
                path: '/admin/messageManageUpdate',
                component: () => import('./MessageManageUpdatePage.vue'),
                props: route => ({ messageId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminMessageManageUpdate',
                    name: '消息修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminMessageManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminMessageManageUpdate'
                    }
                }
            },
        ]
    },
]
export default messageAdminRoutes