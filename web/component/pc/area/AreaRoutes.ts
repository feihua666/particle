
const AreaRoutes = [
    {
        path: '/admin/areaManagePage',
        component: () => import('./views/admin/AreaManagePage.vue'),
        meta: {
            root: true,
            code:'adminAreaManagePage',
            name: '区域管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/areaManageAdd',
                component: () => import('./views/admin/AreaManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminAreaManageAdd',
                    name: '区域添加'
                }
            },

            {
                path: '/admin/areaManageUpdate',
                component: () => import('./views/admin/AreaManageUpdatePage.vue'),
                props: route => ({ areaId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminAreaManageUpdate',
                    name: '区域修改'
                }
            },
        ]
    },

]
export default AreaRoutes