const tenantUserAdminRoutes = [
    {
        path: '/admin/tenantUserManagePage',
        component: () => import('./TenantUserManagePage.vue'),
        meta: {
            root: true,
            code:'adminTenantUserManagePage',
            name: '租户用户管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/tenantUserManageAdd',
                component: () => import('./TenantUserManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminTenantUserManageAdd',
                    name: '租户用户添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminTenantUserManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminTenantUserManageAdd'
                    }
                }
            },

            {
                path: '/admin/tenantUserManageUpdate',
                component: () => import('./TenantUserManageUpdatePage.vue'),
                props: route => ({ tenantUserId: route.query.id, userId: route.query.userId, userNickname: route.query.userNickname }),
                meta: {
                    showInDrawer: true,
                    code:'adminTenantUserManageUpdate',
                    name: '租户用户修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminTenantUserManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminTenantUserManageUpdate'
                    }
                }
            },
        ]
    },
]
export default tenantUserAdminRoutes