const openplatformDocApiDocTemplateExampleCodeAdminRoutes = [
    {
        path: '/admin/openplatformDocApiDocTemplateExampleCodeManagePage',
        component: () => import('./OpenplatformDocApiDocTemplateExampleCodeManagePage.vue'),
        meta: {
            root: true,
            code:'adminOpenplatformDocApiDocTemplateExampleCodeManagePage',
            name: '接口文档模板示例代码管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/openplatformDocApiDocTemplateExampleCodeManageAdd',
                component: () => import('./OpenplatformDocApiDocTemplateExampleCodeManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminOpenplatformDocApiDocTemplateExampleCodeManageAdd',
                    name: '接口文档模板示例代码添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminOpenplatformDocApiDocTemplateExampleCodeManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminOpenplatformDocApiDocTemplateExampleCodeManageAdd'
                    }
                }
            },

            {
                path: '/admin/openplatformDocApiDocTemplateExampleCodeManageUpdate',
                component: () => import('./OpenplatformDocApiDocTemplateExampleCodeManageUpdatePage.vue'),
                props: route => ({ openplatformDocApiDocTemplateExampleCodeId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminOpenplatformDocApiDocTemplateExampleCodeManageUpdate',
                    name: '接口文档模板示例代码修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminOpenplatformDocApiDocTemplateExampleCodeManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminOpenplatformDocApiDocTemplateExampleCodeManageUpdate'
                    }
                }
            },
        ]
    },
]
export default openplatformDocApiDocTemplateExampleCodeAdminRoutes