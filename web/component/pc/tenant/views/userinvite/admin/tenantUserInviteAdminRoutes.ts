const tenantUserInviteAdminRoutes = [
    {
        path: '/admin/tenantUserInviteManagePage',
        component: () => import('./TenantUserInviteManagePage.vue'),
        meta: {
            root: true,
            code:'adminTenantUserInviteManagePage',
            name: '租户用户邀请管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/tenantUserInviteManageAdd',
                component: () => import('./TenantUserInviteManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminTenantUserInviteManageAdd',
                    name: '租户用户邀请添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminTenantUserInviteManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminTenantUserInviteManageAdd'
                    }
                }
            },

            {
                path: '/admin/tenantUserInviteManageUpdate',
                component: () => import('./TenantUserInviteManageUpdatePage.vue'),
                props: route => ({ tenantUserInviteId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminTenantUserInviteManageUpdate',
                    name: '租户用户邀请修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminTenantUserInviteManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminTenantUserInviteManageUpdate'
                    }
                }
            },
        ]
    },
]
export default tenantUserInviteAdminRoutes