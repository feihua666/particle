
const UserRoutes = [
    {
        path: '/admin/userManagePage',
        component: () => import('./views/admin/UserManagePage.vue'),
        meta: {
            root: true,
            code:'adminUserManagePage',
            name: '用户管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/userManageAdd',
                component: () => import('./views/admin/UserManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminUserManageAdd',
                    name: '用户添加',
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '.pt-route-view-popover-drawer-footer'
                    }
                }
            },

            {
                path: '/admin/userManageUpdate',
                component: () => import('./views/admin/UserManageUpdatePage.vue'),
                props: route => ({ userId: route.query.userId }),
                meta: {
                    showInDrawer: true,
                    code:'adminUserManageUpdate',
                    name: '用户修改',
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '.pt-route-view-popover-drawer-footer'
                    }
                }
            },
            {
                path: '/admin/userIdentifierManageAddFixed',
                component: () => import('./views/admin/UserIdentifierManageAddPage.vue'),
                props: route => ({ userId: route.query.userId,userNickname: route.query.nickname }),

                meta: {
                    showInDrawer: true,
                    code:'adminUserIdentifierManageAdd',
                    name: '用户登录标识添加',
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '.pt-route-view-popover-drawer-footer'
                    }
                }
            },
            {
                path: '/admin/userPwdManageResetPassword',
                component: () => import('./views/admin/UserResetPassowordPage.vue'),
                props: route => ({ userId: route.query.userId }),
                meta: {
                    showInDialog: true,
                    code:'userPwdManageResetPassword',
                    name: '用户重置密码',
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '.pt-route-view-popover-drawer-footer'
                    }
                }
            },
        ]
    },
    {
        path: '/admin/userIdentifierManagePage',
        component: () => import('./views/admin/UserIdentifierManagePage.vue'),
        props: route => ({ userId: route.query.userId,userNickname: route.query.nickname }),
        meta: {
            root: true,
            code:'adminUserIdentifierManagePage',
            name: '用户登录标识管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/userIdentifierManageAdd',
                component: () => import('./views/admin/UserIdentifierManageAddPage.vue'),
                props: route => ({ userId: route.query.userId,userNickname: route.query.nickname }),

                meta: {
                    showInDrawer: true,
                    code:'adminUserIdentifierManageAdd',
                    name: '用户登录标识添加',
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '.pt-route-view-popover-drawer-footer'
                    }
                }
            },

            {
                path: '/admin/userIdentifierManageUpdate',
                component: () => import('./views/admin/UserIdentifierManageUpdatePage.vue'),
                props: route => ({ identifierId: route.query.identifierId }),
                meta: {
                    showInDrawer: true,
                    code:'adminUserIdentifierManageUpdate',
                    name: '用户登录标识修改',
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '.pt-route-view-popover-drawer-footer'
                    }
                }
            },
            {
                path: '/admin/userIdentifierPwdManageResetPassword',
                component: () => import('./views/admin/UserIdentifierResetPassowordPage.vue'),
                props: route => ({ identifierId: route.query.identifierId }),
                meta: {
                    showInDialog: true,
                    code:'userIdentifierPwdManageResetPassword',
                    name: '用户登录标识重置密码',
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '.pt-route-view-popover-drawer-footer'
                    }
                }
            },
        ]
    },
    {
        path: '/admin/userIdentifierPwdManagePage',
        component: () => import('./views/admin/UserIdentifierPwdManagePage.vue'),
        props: route => ({ userId: route.query.userId,userNickname: route.query.nickname,identifierId: route.query.identifierId,identifier:route.query.identifier }),

        meta: {
            root: true,
            code:'adminUserIdentifierPwdManagePage',
            name: '用户登录标识密码管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/userIdentifierPwdManageAdd',
                component: () => import('./views/admin/UserIdentifierPwdManageAddPage.vue'),
                props: route => ({ userId: route.query.userId,userNickname: route.query.nickname }),

                meta: {
                    showInDrawer: true,
                    code:'adminUserIdentifierPwdManageAdd',
                    name: '用户登录标识密码添加',
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '.pt-route-view-popover-drawer-footer'
                    }
                }
            },

            {
                path: '/admin/userIdentifierPwdManageUpdate',
                component: () => import('./views/admin/UserIdentifierPwdManageUpdatePage.vue'),
                props: route => ({ userIdentifierPwdId: route.query.id,userId: route.query.userId,userNickname: route.query.nickname,identifierId: route.query.identifierId,identifier:route.query.identifier  }),
                meta: {
                    showInDrawer: true,
                    code:'adminUserIdentifierPwdManageUpdate',
                    name: '用户登录标识密码修改',
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '.pt-route-view-popover-drawer-footer'
                    }
                }
            },
        ]
    },
]
export default UserRoutes