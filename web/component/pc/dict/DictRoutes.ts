
const DictRoutes = [
    {
        path: '/admin/dictManagePage',
        component: () => import('./views/admin/DictManagePage.vue'),
        meta: {
            root: true,
            code:'adminDictManagePage',
            name: '字典管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dictManageAdd',
                component: () => import('./views/admin/DictManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDictManageAdd',
                    name: '字典添加'
                }
            },

            {
                path: '/admin/dictManageUpdate',
                component: () => import('./views/admin/DictManageUpdatePage.vue'),
                props: route => ({ dictId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDictManageUpdate',
                    name: '字典修改'
                }
            },
        ]
    },

]
export default DictRoutes