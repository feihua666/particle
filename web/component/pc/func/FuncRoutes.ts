
const FuncRoutes = [
    {
        path: '/admin/funcManagePage',
        component: () => import('./views/admin/FuncManagePage.vue'),
        meta: {
            root: true,
            code:'adminFuncManagePage',
            name: '菜单功能管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/funcManageAdd',
                component: () => import('./views/admin/FuncManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminFuncManageAdd',
                    name: '菜单功能添加'
                }
            },

            {
                path: '/admin/funcManageUpdate',
                component: () => import('./views/admin/FuncManageUpdatePage.vue'),
                props: route => ({ funcId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminFuncManageUpdate',
                    name: '菜单功能修改'
                }
            },
        ]
    },
    {
        path: '/admin/funcGroupManagePage',
        component: () => import('./views/admin/FuncGroupManagePage.vue'),
        meta: {
            root: true,
            code:'adminFuncGroupManagePage',
            name: '菜单功能组管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/funcGroupManageAdd',
                component: () => import('./views/admin/FuncGroupManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminFuncGroupManageAdd',
                    name: '菜单功能组添加'
                }
            },

            {
                path: '/admin/funcGroupManageUpdate',
                component: () => import('./views/admin/FuncGroupManageUpdatePage.vue'),
                props: route => ({ funcGroupId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminFuncGroupManageUpdate',
                    name: '菜单功能组修改'
                }
            },
        ]
    },

]
export default FuncRoutes