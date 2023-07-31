const oauth2RegisteredClientAdminRoutes = [
    {
        path: '/admin/oauth2RegisteredClientManagePage',
        component: () => import('./Oauth2RegisteredClientManagePage.vue'),
        meta: {
            root: true,
            code:'adminOauth2RegisteredClientManagePage',
            name: 'oauth2客户端管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/oauth2RegisteredClientManageAdd',
                component: () => import('./Oauth2RegisteredClientManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminOauth2RegisteredClientManageAdd',
                    name: 'oauth2客户端添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminOauth2RegisteredClientManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminOauth2RegisteredClientManageAdd'
                    }
                }
            },

            {
                path: '/admin/oauth2RegisteredClientManageUpdate',
                component: () => import('./Oauth2RegisteredClientManageUpdatePage.vue'),
                props: route => ({ oauth2RegisteredClientId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminOauth2RegisteredClientManageUpdate',
                    name: 'oauth2客户端修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminOauth2RegisteredClientManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminOauth2RegisteredClientManageUpdate'
                    }
                }
            },
        ]
    },
]
export default oauth2RegisteredClientAdminRoutes