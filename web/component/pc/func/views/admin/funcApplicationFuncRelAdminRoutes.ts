const funcApplicationFuncRelAdminRoutes = [
    {
        path: '/admin/funcApplicationFuncRelManagePage',
        component: () => import('./FuncApplicationFuncRelManagePage.vue'),
        meta: {
            root: true,
            code:'adminFuncApplicationFuncRelManagePage',
            name: '功能应用功能关系管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/funcApplicationFuncRelManageAdd',
                component: () => import('./FuncApplicationFuncRelManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminFuncApplicationFuncRelManageAdd',
                    name: '功能应用功能关系添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminFuncApplicationFuncRelManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminFuncApplicationFuncRelManageAdd'
                    }
                }
            },

            {
                path: '/admin/funcApplicationFuncRelManageFuncAssignFuncApplication',
                component: () => import('./FuncApplicationFuncRelManageFuncAssignFuncApplicationPage.vue'),
                props: route => ({ funcId: route.query.funcId, funcName: route.query.funcName }),
                meta: {
                    showInDrawer: true,
                    code:'funcApplicationFuncRelManageFuncAssignFuncApplication',
                    name: '功能分配功能应用',
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '.pt-route-view-popover-drawer-footer'
                    }
                }
            },
            {
                path: '/admin/funcApplicationFuncRelManageFuncApplicationAssignFunc',
                component: () => import('./FuncApplicationFuncRelManageFuncApplicationAssignFuncPage.vue'),
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
            {
                path: '/admin/funcApplicationFuncRelManageDeleteByFuncId',
                component: () => import('./FuncApplicationFuncRelManageDeleteByFuncIdPage.vue'),
                props: route => ({ funcId: route.query.funcId, funcName: route.query.funcName }),
                meta: {
                    showInDrawer: true,
                    code:'funcApplicationFuncRelManageDeleteByFuncId',
                    name: '清空功能功能应用',
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '.pt-route-view-popover-drawer-footer'
                    }
                }
            },
            {
                path: '/admin/funcApplicationFuncRelManageDeleteByFuncApplicationId',
                component: () => import('./FuncApplicationFuncRelManageDeleteByFuncApplicationIdPage.vue'),
                props: route => ({ funcApplicationId: route.query.funcApplicationId, funcApplicationName: route.query.funcApplicationName }),
                meta: {
                    showInDrawer: true,
                    code:'funcApplicationFuncRelManageDeleteByFuncApplicationId',
                    name: '清空功能应用功能',
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '.pt-route-view-popover-drawer-footer'
                    }
                }
            },
        ]
    },
]
export default funcApplicationFuncRelAdminRoutes