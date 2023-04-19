const tenantUserInviteUserRecordAdminRoutes = [
    {
        path: '/admin/tenantUserInviteUserRecordManagePage',
        component: () => import('./TenantUserInviteUserRecordManagePage.vue'),
        meta: {
            root: true,
            code:'adminTenantUserInviteUserRecordManagePage',
            name: '租户用户邀请记录管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/tenantUserInviteUserRecordManageAdd',
                component: () => import('./TenantUserInviteUserRecordManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminTenantUserInviteUserRecordManageAdd',
                    name: '租户用户邀请记录添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminTenantUserInviteUserRecordManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminTenantUserInviteUserRecordManageAdd'
                    }
                }
            },

            {
                path: '/admin/tenantUserInviteUserRecordManageUpdate',
                component: () => import('./TenantUserInviteUserRecordManageUpdatePage.vue'),
                props: route => ({ tenantUserInviteUserRecordId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminTenantUserInviteUserRecordManageUpdate',
                    name: '租户用户邀请记录修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminTenantUserInviteUserRecordManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminTenantUserInviteUserRecordManageUpdate'
                    }
                }
            },
        ]
    },
]
export default tenantUserInviteUserRecordAdminRoutes