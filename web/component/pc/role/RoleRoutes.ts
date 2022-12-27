
const RoleRoutes = [
    {
        path: '/admin/roleManagePage',
        component: () => import('./views/admin/RoleManagePage.vue'),
        meta: {
            root: true,
            code:'adminRoleManagePage',
            name: '角色管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/roleManageAdd',
                component: () => import('./views/admin/RoleManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminRoleManageAdd',
                    name: '角色添加'
                }
            },

            {
                path: '/admin/roleManageUpdate',
                component: () => import('./views/admin/RoleManageUpdatePage.vue'),
                props: route => ({ roleId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminRoleManageUpdate',
                    name: '角色修改'
                }
            },
        ]
    },

]
export default RoleRoutes