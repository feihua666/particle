const tenantFuncAdminRoutes = [
    {
        path: '/admin/tenantFuncManagePage',
        component: () => import('./TenantFuncManagePage.vue'),
        meta: {
            root: true,
            code:'adminTenantFuncManagePage',
            name: '租户功能菜单管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/tenantFuncManageAdd',
                component: () => import('./TenantFuncManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminTenantFuncManageAdd',
                    name: '租户功能菜单添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminTenantFuncManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminTenantFuncManageAdd'
                    }
                }
            },

            {
                path: '/admin/tenantFuncManageUpdate',
                component: () => import('./TenantFuncManageUpdatePage.vue'),
                props: route => ({ tenantFuncId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminTenantFuncManageUpdate',
                    name: '租户功能菜单修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminTenantFuncManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminTenantFuncManageUpdate'
                    }
                }
            },
/*            {
                path: '/admin/tenantFuncTenantAssignFunc',
                component: () => import('./TenantFuncTenantAssignFuncPage.vue'),
                props: route => ({ tenantId: route.query.tenantId}),
                meta: {
                    showInDrawer: true,
                    code:'tenantFuncTenantAssignFunc',
                    name: '租户分配功能菜单',
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '.pt-route-view-popover-drawer-footer'
                    }
                }
            },*/
        ]
    },
]
export default tenantFuncAdminRoutes