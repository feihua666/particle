const openplatformDocApiDocExampleCodeAdminRoutes = [
    {
        path: '/admin/openplatformDocApiDocExampleCodeManagePage',
        component: () => import('./OpenplatformDocApiDocExampleCodeManagePage.vue'),
        meta: {
            root: true,
            code:'adminOpenplatformDocApiDocExampleCodeManagePage',
            name: '开放接口文档示例代码管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/openplatformDocApiDocExampleCodeManageAdd',
                component: () => import('./OpenplatformDocApiDocExampleCodeManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminOpenplatformDocApiDocExampleCodeManageAdd',
                    name: '开放接口文档示例代码添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminOpenplatformDocApiDocExampleCodeManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminOpenplatformDocApiDocExampleCodeManageAdd'
                    }
                }
            },

            {
                path: '/admin/openplatformDocApiDocExampleCodeManageUpdate',
                component: () => import('./OpenplatformDocApiDocExampleCodeManageUpdatePage.vue'),
                props: route => ({ openplatformDocApiDocExampleCodeId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminOpenplatformDocApiDocExampleCodeManageUpdate',
                    name: '开放接口文档示例代码修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminOpenplatformDocApiDocExampleCodeManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminOpenplatformDocApiDocExampleCodeManageUpdate'
                    }
                }
            },
        ]
    },
]
export default openplatformDocApiDocExampleCodeAdminRoutes