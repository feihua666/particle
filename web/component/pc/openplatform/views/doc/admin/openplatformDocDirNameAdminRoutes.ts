const openplatformDocDirNameAdminRoutes = [
    {
        path: '/admin/openplatformDocDirNameManagePage',
        component: () => import('./OpenplatformDocDirNameManagePage.vue'),
        meta: {
            root: true,
            code:'adminOpenplatformDocDirNameManagePage',
            name: '开放接口目录名称管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/openplatformDocDirNameManageAdd',
                component: () => import('./OpenplatformDocDirNameManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminOpenplatformDocDirNameManageAdd',
                    name: '开放接口目录名称添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminOpenplatformDocDirNameManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminOpenplatformDocDirNameManageAdd'
                    }
                }
            },

            {
                path: '/admin/openplatformDocDirNameManageUpdate',
                component: () => import('./OpenplatformDocDirNameManageUpdatePage.vue'),
                props: route => ({ openplatformDocDirNameId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminOpenplatformDocDirNameManageUpdate',
                    name: '开放接口目录名称修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminOpenplatformDocDirNameManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminOpenplatformDocDirNameManageUpdate'
                    }
                }
            },
        ]
    },
]
export default openplatformDocDirNameAdminRoutes