const tenantCreateApplyAdminRoutes = [
    {
        path: '/admin/tenantCreateApplyManagePage',
        component: () => import('./TenantCreateApplyManagePage.vue'),
        meta: {
            root: true,
            code:'adminTenantCreateApplyManagePage',
            name: '租户创建申请管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/tenantCreateApplyManageAdd',
                component: () => import('./TenantCreateApplyManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminTenantCreateApplyManageAdd',
                    name: '租户创建申请添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminTenantCreateApplyManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminTenantCreateApplyManageAdd'
                    }
                }
            },

            {
                path: '/admin/tenantCreateApplyManageUpdate',
                component: () => import('./TenantCreateApplyManageUpdatePage.vue'),
                props: route => ({ tenantCreateApplyId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminTenantCreateApplyManageUpdate',
                    name: '租户创建申请修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminTenantCreateApplyManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminTenantCreateApplyManageUpdate'
                    }
                }
            },
        ]
    },
]
export default tenantCreateApplyAdminRoutes