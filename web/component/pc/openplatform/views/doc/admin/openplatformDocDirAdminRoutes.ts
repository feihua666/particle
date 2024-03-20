const openplatformDocDirAdminRoutes = [
    {
        path: '/admin/openplatformDocDirManagePage',
        component: () => import('./OpenplatformDocDirManagePage.vue'),
        meta: {
            root: true,
            code:'adminOpenplatformDocDirManagePage',
            name: '开放接口目录管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/openplatformDocDirManageAdd',
                component: () => import('./OpenplatformDocDirManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminOpenplatformDocDirManageAdd',
                    name: '开放接口目录添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminOpenplatformDocDirManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminOpenplatformDocDirManageAdd'
                    }
                }
            },

            {
                path: '/admin/openplatformDocDirManageUpdate',
                component: () => import('./OpenplatformDocDirManageUpdatePage.vue'),
                props: route => ({ openplatformDocDirId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminOpenplatformDocDirManageUpdate',
                    name: '开放接口目录修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminOpenplatformDocDirManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminOpenplatformDocDirManageUpdate'
                    }
                }
            },
        ]
    },
]
export default openplatformDocDirAdminRoutes