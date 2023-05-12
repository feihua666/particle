const funcApplicationAdminRoutes = [
    {
        path: '/admin/funcApplicationManagePage',
        component: () => import('./FuncApplicationManagePage.vue'),
        meta: {
            root: true,
            code:'adminFuncApplicationManagePage',
            name: '功能应用管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/funcApplicationManageAdd',
                component: () => import('./FuncApplicationManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminFuncApplicationManageAdd',
                    name: '功能应用添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminFuncApplicationManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminFuncApplicationManageAdd'
                    }
                }
            },

            {
                path: '/admin/funcApplicationManageUpdate',
                component: () => import('./FuncApplicationManageUpdatePage.vue'),
                props: route => ({ funcApplicationId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminFuncApplicationManageUpdate',
                    name: '功能应用修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminFuncApplicationManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminFuncApplicationManageUpdate'
                    }
                }
            },
            {
                path: '/admin/funcApplicationAssignFunc',
                component: () => import('../../admin/FuncApplicationFuncRelManageFuncApplicationAssignFuncPage.vue'),
                props: route => ({ funcApplicationId: route.query.funcApplicationId, funcApplicationName: route.query.funcApplicationName }),
                meta: {
                    showInDrawer: true,
                    code:'funcApplicationFuncRelManageFuncApplicationAssignFunc',
                    name: '功能应用分配功能',
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '.pt-route-view-popover-drawer-footer'
                    }
                }
            },
        ]
    },
]
export default funcApplicationAdminRoutes