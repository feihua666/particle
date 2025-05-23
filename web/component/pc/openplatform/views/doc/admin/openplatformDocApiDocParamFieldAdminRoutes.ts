const openplatformDocApiDocParamFieldAdminRoutes = [
    {
        path: '/admin/openplatformDocApiDocParamFieldManagePage',
        component: () => import('./OpenplatformDocApiDocParamFieldManagePage.vue'),
        meta: {
            root: true,
            code:'adminOpenplatformDocApiDocParamFieldManagePage',
            name: '开放接口文档参数字段管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/openplatformDocApiDocParamFieldManageAdd',
                component: () => import('./OpenplatformDocApiDocParamFieldManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminOpenplatformDocApiDocParamFieldManageAdd',
                    name: '开放接口文档参数字段添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminOpenplatformDocApiDocParamFieldManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminOpenplatformDocApiDocParamFieldManageAdd'
                    }
                }
            },
            {
                path: '/admin/openplatformDocApiDocParamFieldManageParseAndAdd',
                component: () => import('./OpenplatformDocApiDocParamFieldManageParseAndAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminOpenplatformDocApiDocParamFieldManageAdd',
                    name: '开放接口文档参数字段解析并添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminOpenplatformDocApiDocParamFieldManageParseAndAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminOpenplatformDocApiDocParamFieldManageParseAndAdd'
                    }
                }
            },
            {
                path: '/admin/openplatformDocApiDocParamFieldManageConditionDelete',
                component: () => import('./OpenplatformDocApiDocParamFieldManageConditionDeletePage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminOpenplatformDocApiDocParamFieldManageConditionDelete',
                    name: '开放接口文档参数字段条件删除',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminOpenplatformDocApiDocParamFieldManageConditionDelete'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminOpenplatformDocApiDocParamFieldManageConditionDelete'
                    }
                }
            },
            {
                path: '/admin/openplatformDocApiDocParamFieldManageUpdate',
                component: () => import('./OpenplatformDocApiDocParamFieldManageUpdatePage.vue'),
                props: route => ({ openplatformDocApiDocParamFieldId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminOpenplatformDocApiDocParamFieldManageUpdate',
                    name: '开放接口文档参数字段修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminOpenplatformDocApiDocParamFieldManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminOpenplatformDocApiDocParamFieldManageUpdate'
                    }
                }
            },
        ]
    },
]
export default openplatformDocApiDocParamFieldAdminRoutes
