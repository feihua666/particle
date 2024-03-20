const openplatformDocApiDocTemplateAdminRoutes = [
    {
        path: '/admin/openplatformDocApiDocTemplateManagePage',
        component: () => import('./OpenplatformDocApiDocTemplateManagePage.vue'),
        meta: {
            root: true,
            code:'adminOpenplatformDocApiDocTemplateManagePage',
            name: '接口文档模板管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/openplatformDocApiDocTemplateManageAdd',
                component: () => import('./OpenplatformDocApiDocTemplateManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminOpenplatformDocApiDocTemplateManageAdd',
                    name: '接口文档模板添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminOpenplatformDocApiDocTemplateManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminOpenplatformDocApiDocTemplateManageAdd'
                    }
                }
            },

            {
                path: '/admin/openplatformDocApiDocTemplateManageUpdate',
                component: () => import('./OpenplatformDocApiDocTemplateManageUpdatePage.vue'),
                props: route => ({ openplatformDocApiDocTemplateId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminOpenplatformDocApiDocTemplateManageUpdate',
                    name: '接口文档模板修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminOpenplatformDocApiDocTemplateManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminOpenplatformDocApiDocTemplateManageUpdate'
                    }
                }
            },
        ]
    },
]
export default openplatformDocApiDocTemplateAdminRoutes