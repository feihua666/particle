const roleDataScopeRelAdminRoutes = [
    {
        path: '/admin/roleDataScopeRelManagePage',
        component: () => import('./RoleDataScopeRelManagePage.vue'),
        meta: {
            root: true,
            code:'adminRoleDataScopeRelManagePage',
            name: '角色数据范围关系管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/roleDataScopeRelManageAdd',
                component: () => import('./RoleDataScopeRelManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminRoleDataScopeRelManageAdd',
                    name: '角色数据范围关系添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminRoleDataScopeRelManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminRoleDataScopeRelManageAdd'
                    }
                }
            },

            {
                path: '/admin/roleDataScopeRelManageUpdate',
                component: () => import('./RoleDataScopeRelManageUpdatePage.vue'),
                props: route => ({ roleDataScopeRelId: route.query.id ,roleId: route.query.roleId, roleName: route.query.roleName }),
                meta: {
                    showInDrawer: true,
                    code:'adminRoleDataScopeRelManageUpdate',
                    name: '角色数据范围关系修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminRoleDataScopeRelManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminRoleDataScopeRelManageUpdate'
                    }
                }
            },
            {
                path: '/admin/roleDataScopeRelManageDataScopeAssignRole',
                component: () => import('./RoleDataScopeRelManageDataScopeAssignRolePage.vue'),
                props: route => ({ dataScopeId: route.query.dataScopeId, dataScopeName: route.query.dataScopeName,dataObjectId: route.query.dataObjectId }),
                meta: {
                    showInDrawer: true,
                    code:'roleDataScopeRelManageDataScopeAssignRole',
                    name: '数据范围分配角色',
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '.pt-route-view-popover-drawer-footer'
                    }
                }
            },
            {
                path: '/admin/roleDataScopeRelManageRoleAssignDataScope',
                component: () => import('./RoleDataScopeRelManageRoleAssignDataScopePage.vue'),
                props: route => ({ roleId: route.query.roleId, roleName: route.query.roleName }),
                meta: {
                    showInDrawer: true,
                    code:'roleDataScopeRelManageRoleAssignDataScope',
                    name: '角色分配数据范围',
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '.pt-route-view-popover-drawer-footer'
                    }
                }
            },
            {
                path: '/admin/roleDataScopeRelManageDeleteByDataScopeId',
                component: () => import('./RoleDataScopeRelManageDeleteByDataScopeIdPage.vue'),
                props: route => ({ dataScopeId: route.query.dataScopeId, dataScopeName: route.query.dataScopeName }),
                meta: {
                    showInDrawer: true,
                    code:'roleDataScopeRelManageDeleteByDataScopeId',
                    name: '清空数据范围角色',
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '.pt-route-view-popover-drawer-footer'
                    }
                }
            },
            {
                path: '/admin/roleDataScopeRelManageDeleteByRoleId',
                component: () => import('./RoleDataScopeRelManageDeleteByRoleIdPage.vue'),
                props: route => ({ roleId: route.query.roleId, roleName: route.query.roleName }),
                meta: {
                    showInDrawer: true,
                    code:'roleDataScopeRelManageDeleteByRoleId',
                    name: '清空角色数据范围',
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '.pt-route-view-popover-drawer-footer'
                    }
                }
            },
        ]
    },
]
export default roleDataScopeRelAdminRoutes