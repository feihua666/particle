const tenantFuncApplicationAdminRoutes = [
    {
        path: '/admin/tenantFuncApplicationManagePage',
        component: () => import('./TenantFuncApplicationManagePage.vue'),
        meta: {
            root: true,
            code:'adminTenantFuncApplicationManagePage',
            name: '租户功能应用管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/tenantFuncApplicationManageAdd',
                component: () => import('./TenantFuncApplicationManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminTenantFuncApplicationManageAdd',
                    name: '租户功能应用添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminTenantFuncApplicationManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminTenantFuncApplicationManageAdd'
                    }
                }
            },

            {
                path: '/admin/tenantFuncApplicationManageUpdate',
                component: () => import('./TenantFuncApplicationManageUpdatePage.vue'),
                props: route => ({ tenantFuncApplicationId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminTenantFuncApplicationManageUpdate',
                    name: '租户功能应用修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminTenantFuncApplicationManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminTenantFuncApplicationManageUpdate'
                    }
                }
            },

/*            {
                path: '/admin/tenantFuncApplicationTenantAssignFuncApplication',
                component: () => import('./TenantFuncApplicationTenantAssignFuncApplicationPage.vue'),
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
            },*/
            {
                path: '/admin/tenantFuncApplication/tenantFuncTenantAssignFunc',
                component: () => import('../../tenantfunc/admin/TenantFuncTenantAssignFuncPage.vue'),
                props: route => ({ tenantId: route.query.tenantId,funcApplicationId: route.query.funcApplicationId}),
                meta: {
                    showInDrawer: true,
                    code:'tenantFuncTenantAssignFunc',
                    name: '租户应用分配功能菜单',
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '.pt-route-view-popover-drawer-footer'
                    }
                }
            },
        ]
    },
]
export default tenantFuncApplicationAdminRoutes