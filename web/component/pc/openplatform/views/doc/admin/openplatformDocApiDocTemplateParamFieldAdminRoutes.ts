const openplatformDocApiDocTemplateParamFieldAdminRoutes = [
    {
        path: '/admin/openplatformDocApiDocTemplateParamFieldManagePage',
        component: () => import('./OpenplatformDocApiDocTemplateParamFieldManagePage.vue'),
        meta: {
            root: true,
            code:'adminOpenplatformDocApiDocTemplateParamFieldManagePage',
            name: '接口文档模板参数字段管理',
            keepAlive: true
        },
        children: [

            {
                path: '/admin/openplatformDocApiDocTemplateParamFieldManageAdd',
                component: () => import('./OpenplatformDocApiDocTemplateParamFieldManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminOpenplatformDocApiDocTemplateParamFieldManageAdd',
                    name: '开放接口文档参数字段添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminOpenplatformDocApiDocTemplateParamFieldManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminOpenplatformDocApiDocTemplateParamFieldManageAdd'
                    }
                }
            },
            {
                path: '/admin/openplatformDocApiDocTemplateParamFieldManageParseAndAdd',
                component: () => import('./OpenplatformDocApiDocTemplateParamFieldManageParseAndAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminOpenplatformDocApiDocTemplateParamFieldManageAdd',
                    name: '接口文档模板参数字段解析并添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminOpenplatformDocApiDocTemplateParamFieldManageParseAndAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminOpenplatformDocApiDocTemplateParamFieldManageParseAndAdd'
                    }
                }
            },
            {
                path: '/admin/openplatformDocApiDocTemplateParamFieldManageUpdate',
                component: () => import('./OpenplatformDocApiDocTemplateParamFieldManageUpdatePage.vue'),
                props: route => ({ openplatformDocApiDocTemplateParamFieldId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminOpenplatformDocApiDocTemplateParamFieldManageUpdate',
                    name: '接口文档模板参数字段修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminOpenplatformDocApiDocTemplateParamFieldManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminOpenplatformDocApiDocTemplateParamFieldManageUpdate'
                    }
                }
            },
        ]
    },
]
export default openplatformDocApiDocTemplateParamFieldAdminRoutes