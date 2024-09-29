const openplatformProviderApiAdminRoutes = [
    {
        path: '/admin/openplatformProviderApiManagePage',
        component: () => import('./OpenplatformProviderApiManagePage.vue'),
        meta: {
            root: true,
            code:'adminOpenplatformProviderApiManagePage',
            name: '开放平台供应商接口管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/openplatformProviderApiManageAdd',
                component: () => import('./OpenplatformProviderApiManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminOpenplatformProviderApiManageAdd',
                    name: '开放平台供应商接口添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminOpenplatformProviderApiManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminOpenplatformProviderApiManageAdd'
                    }
                }
            },

            {
                path: '/admin/openplatformProviderApiManageUpdate',
                component: () => import('./OpenplatformProviderApiManageUpdatePage.vue'),
                props: route => ({ openplatformProviderApiId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminOpenplatformProviderApiManageUpdate',
                    name: '开放平台供应商接口修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminOpenplatformProviderApiManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminOpenplatformProviderApiManageUpdate'
                    }
                }
            },
        ]
    },
]
export default openplatformProviderApiAdminRoutes