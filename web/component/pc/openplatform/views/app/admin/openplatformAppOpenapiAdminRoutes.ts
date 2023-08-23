const openplatformAppOpenapiAdminRoutes = [
    {
        path: '/admin/openplatformAppOpenapiManagePage',
        component: () => import('./OpenplatformAppOpenapiManagePage.vue'),
        meta: {
            root: true,
            code:'adminOpenplatformAppOpenapiManagePage',
            name: '应用与接口配置管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/openplatformAppOpenapiManageAdd',
                component: () => import('./OpenplatformAppOpenapiManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminOpenplatformAppOpenapiManageAdd',
                    name: '应用与接口配置添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminOpenplatformAppOpenapiManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminOpenplatformAppOpenapiManageAdd'
                    }
                }
            },

            {
                path: '/admin/openplatformAppOpenapiManageUpdate',
                component: () => import('./OpenplatformAppOpenapiManageUpdatePage.vue'),
                props: route => ({ openplatformAppOpenapiId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminOpenplatformAppOpenapiManageUpdate',
                    name: '应用与接口配置修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminOpenplatformAppOpenapiManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminOpenplatformAppOpenapiManageUpdate'
                    }
                }
            },
        ]
    },
]
export default openplatformAppOpenapiAdminRoutes