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
                props: route => ({ tenantCreateApplyId: route.query.id,applyUserId: route.query.applyUserId,applyUserNickname: route.query.applyUserNickname }),
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
            {
                path: '/admin/tenantCreateApplyManageAudit',
                component: () => import('./TenantCreateApplyManageAuditPage.vue'),
                props: route => ({ tenantCreateApplyId: route.query.id,applyUserId: route.query.applyUserId,applyUserNickname: route.query.applyUserNickname }),
                meta: {
                    showInDrawer: true,
                    code:'adminTenantCreateApplyManageAudit',
                    name: '租户创建申请审核',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminTenantCreateApplyManageAudit'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminTenantCreateApplyManageAudit'
                    }
                }
            },
        ]
    },
]
export default tenantCreateApplyAdminRoutes