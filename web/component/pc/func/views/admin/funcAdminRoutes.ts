const funcAdminRoutes = [
    {
        path: '/admin/funcManagePage',
        component: () => import('./FuncManagePage.vue'),
        meta: {
            root: true,
            code:'adminFuncManagePage',
            name: '菜单功能管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/funcManageAdd',
                component: () => import('./FuncManageAddPage.vue'),
                props: route => ({ parentId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminFuncManageAdd',
                    name: '菜单功能添加',
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '.pt-route-view-popover-drawer-footer'
                    }
                }
            },
            {
                path: '/admin/funcManageCrudAdd',
                component: () => import('./FuncManageCrudAddPage.vue'),
                props: route => ({ parentId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminFuncManageCrudAdd',
                    name: '菜单功能添加Crud',
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '.pt-route-view-popover-drawer-footer'
                    }
                }
            },
            {
                path: '/admin/funcManageUpdate',
                component: () => import('./FuncManageUpdatePage.vue'),
                props: route => ({ funcId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminFuncManageUpdate',
                    name: '菜单功能修改',
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '.pt-route-view-popover-drawer-footer'
                    }
                }
            },
        ]
    },
    {
        path: '/admin/funcGroupManagePage',
        component: () => import('./FuncGroupManagePage.vue'),
        meta: {
            root: true,
            code:'adminFuncGroupManagePage',
            name: '菜单功能组管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/funcGroupManageAdd',
                component: () => import('./FuncGroupManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminFuncGroupManageAdd',
                    name: '菜单功能组添加',
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '.pt-route-view-popover-drawer-footer'
                    }
                }
            },

            {
                path: '/admin/funcGroupManageUpdate',
                component: () => import('./FuncGroupManageUpdatePage.vue'),
                props: route => ({ funcGroupId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminFuncGroupManageUpdate',
                    name: '菜单功能组修改',
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '.pt-route-view-popover-drawer-footer'
                    }
                }
            },
        ]
    },

]
export default funcAdminRoutes