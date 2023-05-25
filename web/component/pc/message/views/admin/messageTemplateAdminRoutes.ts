const messageTemplateAdminRoutes = [
    {
        path: '/admin/messageTemplateManagePage',
        component: () => import('./MessageTemplateManagePage.vue'),
        meta: {
            root: true,
            code:'adminMessageTemplateManagePage',
            name: '消息模板管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/messageTemplateManageAdd',
                component: () => import('./MessageTemplateManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminMessageTemplateManageAdd',
                    name: '消息模板添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminMessageTemplateManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminMessageTemplateManageAdd'
                    }
                }
            },

            {
                path: '/admin/messageTemplateManageUpdate',
                component: () => import('./MessageTemplateManageUpdatePage.vue'),
                props: route => ({ messageTemplateId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminMessageTemplateManageUpdate',
                    name: '消息模板修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminMessageTemplateManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminMessageTemplateManageUpdate'
                    }
                }
            },
        ]
    },
]
export default messageTemplateAdminRoutes