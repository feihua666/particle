
const FuncRoutes = [
    {
        path: '/admin/funcManagePage',
        component: () => import('./views/admin/FuncManagePage.vue'),
        meta: {
            root: true,
            code:'FuncSearchList',
            name: '菜单功能管理',
            keepAlive: true
        }
    },
    {
        path: '/admin/funcManageAdd',
        component: () => import('./views/admin/FuncManagePage.vue'),
        meta: {
            code:'FuncAdd',
            name: '菜单功能添加'
        }
    },
    {
        path: '/admin/funcManageUpdate',
        component: () => import('./views/admin/FuncManagePage.vue'),
        meta: {
            code:'FuncUpdate',
            name: '菜单功能修改'
        }
    },
]
export default FuncRoutes