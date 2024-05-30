const systemConfigAdminRoutes = [
    {
        path: '/admin/systemConfigManagePage',
        component: () => import('./SystemConfigManagePage.vue'),
        meta: {
            root: true,
            code:'adminSystemConfigManagePage',
            name: '系统参数配置管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/systemConfigManageAdd',
                component: () => import('./SystemConfigManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminSystemConfigManageAdd',
                    name: '系统参数配置添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminSystemConfigManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminSystemConfigManageAdd'
                    }
                }
            },

            {
                path: '/admin/systemConfigManageUpdate',
                component: () => import('./SystemConfigManageUpdatePage.vue'),
                props: route => ({ systemConfigId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminSystemConfigManageUpdate',
                    name: '系统参数配置修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminSystemConfigManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminSystemConfigManageUpdate'
                    }
                }
            },
        ]
    },
]
export default systemConfigAdminRoutes