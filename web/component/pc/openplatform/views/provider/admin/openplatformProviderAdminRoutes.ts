const openplatformProviderAdminRoutes = [
    {
        path: '/admin/openplatformProviderManagePage',
        component: () => import('./OpenplatformProviderManagePage.vue'),
        meta: {
            root: true,
            code:'adminOpenplatformProviderManagePage',
            name: '开放平台开放接口供应商管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/openplatformProviderManageAdd',
                component: () => import('./OpenplatformProviderManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminOpenplatformProviderManageAdd',
                    name: '开放平台开放接口供应商添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminOpenplatformProviderManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminOpenplatformProviderManageAdd'
                    }
                }
            },

            {
                path: '/admin/openplatformProviderManageUpdate',
                component: () => import('./OpenplatformProviderManageUpdatePage.vue'),
                props: route => ({ openplatformProviderId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminOpenplatformProviderManageUpdate',
                    name: '开放平台开放接口供应商修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminOpenplatformProviderManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminOpenplatformProviderManageUpdate'
                    }
                }
            },
        ]
    },
]
export default openplatformProviderAdminRoutes