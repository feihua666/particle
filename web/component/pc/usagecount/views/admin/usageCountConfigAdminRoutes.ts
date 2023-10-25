const usageCountConfigAdminRoutes = [
    {
        path: '/admin/usageCountConfigManagePage',
        component: () => import('./UsageCountConfigManagePage.vue'),
        meta: {
            root: true,
            code:'adminUsageCountConfigManagePage',
            name: '使用次数配置管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/usageCountConfigManageAdd',
                component: () => import('./UsageCountConfigManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminUsageCountConfigManageAdd',
                    name: '使用次数配置添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminUsageCountConfigManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminUsageCountConfigManageAdd'
                    }
                }
            },

            {
                path: '/admin/usageCountConfigManageUpdate',
                component: () => import('./UsageCountConfigManageUpdatePage.vue'),
                props: route => ({ usageCountConfigId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminUsageCountConfigManageUpdate',
                    name: '使用次数配置修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminUsageCountConfigManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminUsageCountConfigManageUpdate'
                    }
                }
            },
        ]
    },
]
export default usageCountConfigAdminRoutes