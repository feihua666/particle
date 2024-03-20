const openplatformDocApiDocAdminRoutes = [
    {
        path: '/admin/openplatformDocApiDocManagePage',
        component: () => import('./OpenplatformDocApiDocManagePage.vue'),
        meta: {
            root: true,
            code:'adminOpenplatformDocApiDocManagePage',
            name: '开放接口文档管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/openplatformDocApiDocManageAdd',
                component: () => import('./OpenplatformDocApiDocManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminOpenplatformDocApiDocManageAdd',
                    name: '开放接口文档添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminOpenplatformDocApiDocManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminOpenplatformDocApiDocManageAdd'
                    }
                }
            },

            {
                path: '/admin/openplatformDocApiDocManageUpdate',
                component: () => import('./OpenplatformDocApiDocManageUpdatePage.vue'),
                props: route => ({ openplatformDocApiDocId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminOpenplatformDocApiDocManageUpdate',
                    name: '开放接口文档修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminOpenplatformDocApiDocManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminOpenplatformDocApiDocManageUpdate'
                    }
                }
            },
        ]
    },
]
export default openplatformDocApiDocAdminRoutes