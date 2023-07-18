const tenantAdminRoutes = [
    {
        path: '/admin/tenantManagePage',
        component: () => import('./TenantManagePage.vue'),
        meta: {
            root: true,
            code:'adminTenantManagePage',
            name: '租户管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/tenantManageAdd',
                component: () => import('./TenantManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminTenantManageAdd',
                    name: '租户添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminTenantManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminTenantManageAdd'
                    }
                }
            },

            {
                path: '/admin/tenantManageUpdate',
                component: () => import('./TenantManageUpdatePage.vue'),
                props: route => ({ tenantId: route.query.tenantId,masterUserId: route.query.masterUserId,masterUserNickname: route.query.masterUserNickname }),
                meta: {
                    showInDrawer: true,
                    code:'adminTenantManageUpdate',
                    name: '租户修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminTenantManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminTenantManageUpdate'
                    }
                }
            },
            {
                path: '/admin/tenant/tenantAssignFuncApplication',
                component: () => import('../tenantfuncapplication/admin/TenantFuncApplicationTenantAssignFuncApplicationPage.vue'),
                props: route => ({ tenantId: route.query.tenantId}),
                meta: {
                    showInDrawer: true,
                    code:'tenantFuncApplicationTenantAssignFuncApplication',
                    name: '租户分配功能应用',
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '.pt-route-view-popover-drawer-footer'
                    }
                }
            },
            {
                path: '/admin/tenantManageOneClickAdd',
                component: () => import('./TenantManageOneClickAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminTenantManageOneClickAdd',
                    name: '一键添加租户',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminTenantManageOneClickAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminTenantManageOneClickAdd'
                    }
                }
            },
        ]
    },
]
export default tenantAdminRoutes