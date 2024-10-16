const openplatformOpenapiLimitRuleAdminRoutes = [
    {
        path: '/admin/openplatformOpenapiLimitRuleManagePage',
        component: () => import('./OpenplatformOpenapiLimitRuleManagePage.vue'),
        meta: {
            root: true,
            code:'adminOpenplatformOpenapiLimitRuleManagePage',
            name: '开放平台开放接口限制规则管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/openplatformOpenapiLimitRuleManageAdd',
                component: () => import('./OpenplatformOpenapiLimitRuleManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminOpenplatformOpenapiLimitRuleManageAdd',
                    name: '开放平台开放接口限制规则添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminOpenplatformOpenapiLimitRuleManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminOpenplatformOpenapiLimitRuleManageAdd'
                    }
                }
            },

            {
                path: '/admin/openplatformOpenapiLimitRuleManageUpdate',
                component: () => import('./OpenplatformOpenapiLimitRuleManageUpdatePage.vue'),
                props: route => ({ openplatformOpenapiLimitRuleId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminOpenplatformOpenapiLimitRuleManageUpdate',
                    name: '开放平台开放接口限制规则修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminOpenplatformOpenapiLimitRuleManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminOpenplatformOpenapiLimitRuleManageUpdate'
                    }
                }
            },
        ]
    },
]
export default openplatformOpenapiLimitRuleAdminRoutes