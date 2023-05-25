const messageUserStateAdminRoutes = [
    {
        path: '/admin/messageUserStateManagePage',
        component: () => import('./MessageUserStateManagePage.vue'),
        meta: {
            root: true,
            code:'adminMessageUserStateManagePage',
            name: '用户消息读取状态管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/messageUserStateManageAdd',
                component: () => import('./MessageUserStateManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminMessageUserStateManageAdd',
                    name: '用户消息读取状态添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminMessageUserStateManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminMessageUserStateManageAdd'
                    }
                }
            },

            {
                path: '/admin/messageUserStateManageUpdate',
                component: () => import('./MessageUserStateManageUpdatePage.vue'),
                props: route => ({ messageUserStateId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminMessageUserStateManageUpdate',
                    name: '用户消息读取状态修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminMessageUserStateManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminMessageUserStateManageUpdate'
                    }
                }
            },
        ]
    },
]
export default messageUserStateAdminRoutes