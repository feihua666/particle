
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
    {
        path: '/admin/roleUserRelManagePage',
        component: () => import('./views/admin/RoleUserRelManagePage.vue'),
        meta: {
            root: true,
            code:'roleUserRelManagePage',
            name: '用户角色权限',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/roleUserRelManageAdd',
                component: () => import('./views/admin/RoleUserRelManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'roleUserRelManageAdd',
                    name: '用户角色权限添加'
                }
            },
            {
                path: '/admin/roleUserRelManageUserAssignRole',
                component: () => import('./views/admin/RoleUserRelManageUserAssignRolePage.vue'),
                props: route => ({ userId: route.query.userId, userNickname: route.query.userNickname }),
                meta: {
                    showInDrawer: true,
                    code:'roleUserRelManageUserAssignRole',
                    name: '用户分配角色'
                }
            },
            {
                path: '/admin/roleUserRelManageRoleAssignUser',
                component: () => import('./views/admin/RoleUserRelManageRoleAssignUserPage.vue'),
                props: route => ({ roleId: route.query.roleId, roleName: route.query.roleName }),
                meta: {
                    showInDrawer: true,
                    code:'roleUserRelManageRoleAssignUser',
                    name: '角色分配用户'
                }
            },
            {
                path: '/admin/roleUserRelManageDeleteByUserId',
                component: () => import('./views/admin/RoleUserRelManageDeleteByUserIdPage.vue'),
                props: route => ({ userId: route.query.userId, userNickname: route.query.userNickname }),
                meta: {
                    showInDrawer: true,
                    code:'roleUserRelManageDeleteByUserId',
                    name: '清空用户角色'
                }
            },
            {
                path: '/admin/roleUserRelManageDeleteByRoleId',
                component: () => import('./views/admin/RoleUserRelManageDeleteByRoleIdPage.vue'),
                props: route => ({ roleId: route.query.roleId, roleName: route.query.roleName }),
                meta: {
                    showInDrawer: true,
                    code:'roleUserRelManageDeleteByRoleId',
                    name: '清空角色用户'
                }
            },
        ]
    },
    {
        path: '/admin/roleFuncRelManagePage',
        component: () => import('./views/admin/RoleFuncRelManagePage.vue'),
        meta: {
            root: true,
            code:'roleFuncRelManagePage',
            name: '功能菜单角色权限',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/roleFuncRelManageAdd',
                component: () => import('./views/admin/RoleFuncRelManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'roleFuncRelManageAdd',
                    name: '功能菜单角色权限添加'
                }
            },
            {
                path: '/admin/roleFuncRelManageFuncAssignRole',
                component: () => import('./views/admin/RoleFuncRelManageFuncAssignRolePage.vue'),
                props: route => ({ funcId: route.query.funcId, funcName: route.query.funcName }),
                meta: {
                    showInDrawer: true,
                    code:'roleFuncRelManageFuncAssignRole',
                    name: '功能菜单分配角色'
                }
            },
            {
                path: '/admin/roleFuncRelManageRoleAssignFunc',
                component: () => import('./views/admin/RoleFuncRelManageRoleAssignFuncPage.vue'),
                props: route => ({ roleId: route.query.roleId, roleName: route.query.roleName }),
                meta: {
                    showInDrawer: true,
                    code:'roleFuncRelManageRoleAssignFunc',
                    name: '角色分配功能菜单'
                }
            },
            {
                path: '/admin/roleFuncRelManageDeleteByFuncId',
                component: () => import('./views/admin/RoleFuncRelManageDeleteByFuncIdPage.vue'),
                props: route => ({ funcId: route.query.funcId, funcName: route.query.funcName }),
                meta: {
                    showInDrawer: true,
                    code:'roleFuncRelManageDeleteByFuncId',
                    name: '清空功能菜单角色'
                }
            },
            {
                path: '/admin/roleFuncRelManageDeleteByRoleId',
                component: () => import('./views/admin/RoleFuncRelManageDeleteByRoleIdPage.vue'),
                props: route => ({ roleId: route.query.roleId, roleName: route.query.roleName }),
                meta: {
                    showInDrawer: true,
                    code:'roleFuncRelManageDeleteByRoleId',
                    name: '清空角色功能菜单'
                }
            },
        ]
    },
]
export default RoleRoutes