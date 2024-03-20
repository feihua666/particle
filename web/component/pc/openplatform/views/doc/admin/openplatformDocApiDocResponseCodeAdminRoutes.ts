const openplatformDocApiDocResponseCodeAdminRoutes = [
    {
        path: '/admin/openplatformDocApiDocResponseCodeManagePage',
        component: () => import('./OpenplatformDocApiDocResponseCodeManagePage.vue'),
        meta: {
            root: true,
            code:'adminOpenplatformDocApiDocResponseCodeManagePage',
            name: '开放接口文档响应码管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/openplatformDocApiDocResponseCodeManageAdd',
                component: () => import('./OpenplatformDocApiDocResponseCodeManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminOpenplatformDocApiDocResponseCodeManageAdd',
                    name: '开放接口文档响应码添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminOpenplatformDocApiDocResponseCodeManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminOpenplatformDocApiDocResponseCodeManageAdd'
                    }
                }
            },

            {
                path: '/admin/openplatformDocApiDocResponseCodeManageUpdate',
                component: () => import('./OpenplatformDocApiDocResponseCodeManageUpdatePage.vue'),
                props: route => ({ openplatformDocApiDocResponseCodeId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminOpenplatformDocApiDocResponseCodeManageUpdate',
                    name: '开放接口文档响应码修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminOpenplatformDocApiDocResponseCodeManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminOpenplatformDocApiDocResponseCodeManageUpdate'
                    }
                }
            },
        ]
    },
]
export default openplatformDocApiDocResponseCodeAdminRoutes