const openplatformOpenapiFeeAdminRoutes = [
    {
        path: '/admin/openplatformOpenapiFeeManagePage',
        component: () => import('./OpenplatformOpenapiFeeManagePage.vue'),
        meta: {
            root: true,
            code:'adminOpenplatformOpenapiFeeManagePage',
            name: '开放平台开放接口费用管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/openplatformOpenapiFeeManageAdd',
                component: () => import('./OpenplatformOpenapiFeeManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminOpenplatformOpenapiFeeManageAdd',
                    name: '开放平台开放接口费用添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminOpenplatformOpenapiFeeManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminOpenplatformOpenapiFeeManageAdd'
                    }
                }
            },

            {
                path: '/admin/openplatformOpenapiFeeManageUpdate',
                component: () => import('./OpenplatformOpenapiFeeManageUpdatePage.vue'),
                props: route => ({ openplatformOpenapiFeeId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminOpenplatformOpenapiFeeManageUpdate',
                    name: '开放平台开放接口费用修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminOpenplatformOpenapiFeeManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminOpenplatformOpenapiFeeManageUpdate'
                    }
                }
            },
        ]
    },
]
export default openplatformOpenapiFeeAdminRoutes