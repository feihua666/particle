const openplatformDocApiAdminRoutes = [
    {
        path: '/admin/openplatformDocApiManagePage',
        component: () => import('./OpenplatformDocApiManagePage.vue'),
        meta: {
            root: true,
            code:'adminOpenplatformDocApiManagePage',
            name: '开放接口文档接口管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/openplatformDocApiManageAdd',
                component: () => import('./OpenplatformDocApiManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminOpenplatformDocApiManageAdd',
                    name: '开放接口文档接口添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminOpenplatformDocApiManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminOpenplatformDocApiManageAdd'
                    }
                }
            },

            {
                path: '/admin/openplatformDocApiManageUpdate',
                component: () => import('./OpenplatformDocApiManageUpdatePage.vue'),
                props: route => ({ openplatformDocApiId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminOpenplatformDocApiManageUpdate',
                    name: '开放接口文档接口修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminOpenplatformDocApiManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminOpenplatformDocApiManageUpdate'
                    }
                }
            },
        ]
    },
]
export default openplatformDocApiAdminRoutes