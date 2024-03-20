const openplatformDocApiDocTemplateResponseCodeAdminRoutes = [
    {
        path: '/admin/openplatformDocApiDocTemplateResponseCodeManagePage',
        component: () => import('./OpenplatformDocApiDocTemplateResponseCodeManagePage.vue'),
        meta: {
            root: true,
            code:'adminOpenplatformDocApiDocTemplateResponseCodeManagePage',
            name: '接口文档模板响应码管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/openplatformDocApiDocTemplateResponseCodeManageAdd',
                component: () => import('./OpenplatformDocApiDocTemplateResponseCodeManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminOpenplatformDocApiDocTemplateResponseCodeManageAdd',
                    name: '接口文档模板响应码添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminOpenplatformDocApiDocTemplateResponseCodeManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminOpenplatformDocApiDocTemplateResponseCodeManageAdd'
                    }
                }
            },

            {
                path: '/admin/openplatformDocApiDocTemplateResponseCodeManageUpdate',
                component: () => import('./OpenplatformDocApiDocTemplateResponseCodeManageUpdatePage.vue'),
                props: route => ({ openplatformDocApiDocTemplateResponseCodeId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminOpenplatformDocApiDocTemplateResponseCodeManageUpdate',
                    name: '接口文档模板响应码修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminOpenplatformDocApiDocTemplateResponseCodeManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminOpenplatformDocApiDocTemplateResponseCodeManageUpdate'
                    }
                }
            },
        ]
    },
]
export default openplatformDocApiDocTemplateResponseCodeAdminRoutes