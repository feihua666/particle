const usageCountDefineAdminRoutes = [
    {
        path: '/admin/usageCountDefineManagePage',
        component: () => import('./UsageCountDefineManagePage.vue'),
        meta: {
            root: true,
            code:'adminUsageCountDefineManagePage',
            name: '使用次数定义管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/usageCountDefineManageAdd',
                component: () => import('./UsageCountDefineManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminUsageCountDefineManageAdd',
                    name: '使用次数定义添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminUsageCountDefineManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminUsageCountDefineManageAdd'
                    }
                }
            },

            {
                path: '/admin/usageCountDefineManageUpdate',
                component: () => import('./UsageCountDefineManageUpdatePage.vue'),
                props: route => ({ usageCountDefineId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminUsageCountDefineManageUpdate',
                    name: '使用次数定义修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminUsageCountDefineManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminUsageCountDefineManageUpdate'
                    }
                }
            },
        ]
    },
]
export default usageCountDefineAdminRoutes