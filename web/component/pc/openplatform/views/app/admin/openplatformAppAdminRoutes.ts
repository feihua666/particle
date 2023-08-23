const openplatformAppAdminRoutes = [
    {
        path: '/admin/openplatformAppManagePage',
        component: () => import('./OpenplatformAppManagePage.vue'),
        meta: {
            root: true,
            code:'adminOpenplatformAppManagePage',
            name: '开放平台应用管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/openplatformAppManageAdd',
                component: () => import('./OpenplatformAppManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminOpenplatformAppManageAdd',
                    name: '开放平台应用添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminOpenplatformAppManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminOpenplatformAppManageAdd'
                    }
                }
            },

            {
                path: '/admin/openplatformAppManageUpdate',
                component: () => import('./OpenplatformAppManageUpdatePage.vue'),
                props: route => ({ openplatformAppId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminOpenplatformAppManageUpdate',
                    name: '开放平台应用修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminOpenplatformAppManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminOpenplatformAppManageUpdate'
                    }
                }
            },
        ]
    },
]
export default openplatformAppAdminRoutes