const openplatformDocApiDirRelAdminRoutes = [
    {
        path: '/admin/openplatformDocApiDirRelManagePage',
        component: () => import('./OpenplatformDocApiDirRelManagePage.vue'),
        meta: {
            root: true,
            code:'adminOpenplatformDocApiDirRelManagePage',
            name: '接口与目录绑定管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/openplatformDocApiDirRelManageAdd',
                component: () => import('./OpenplatformDocApiDirRelManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminOpenplatformDocApiDirRelManageAdd',
                    name: '接口与目录绑定添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminOpenplatformDocApiDirRelManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminOpenplatformDocApiDirRelManageAdd'
                    }
                }
            },

            {
                path: '/admin/openplatformDocApiDirRelManageUpdate',
                component: () => import('./OpenplatformDocApiDirRelManageUpdatePage.vue'),
                props: route => ({ openplatformDocApiDirRelId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminOpenplatformDocApiDirRelManageUpdate',
                    name: '接口与目录绑定修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminOpenplatformDocApiDirRelManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminOpenplatformDocApiDirRelManageUpdate'
                    }
                }
            },
        ]
    },
]
export default openplatformDocApiDirRelAdminRoutes