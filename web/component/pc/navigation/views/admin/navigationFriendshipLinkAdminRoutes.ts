const navigationFriendshipLinkAdminRoutes = [
    {
        path: '/admin/navigationFriendshipLinkManagePage',
        component: () => import('./NavigationFriendshipLinkManagePage.vue'),
        meta: {
            root: true,
            code:'adminNavigationFriendshipLinkManagePage',
            name: '导航友情链接管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/navigationFriendshipLinkManageAdd',
                component: () => import('./NavigationFriendshipLinkManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminNavigationFriendshipLinkManageAdd',
                    name: '导航友情链接添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminNavigationFriendshipLinkManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminNavigationFriendshipLinkManageAdd'
                    }
                }
            },

            {
                path: '/admin/navigationFriendshipLinkManageUpdate',
                component: () => import('./NavigationFriendshipLinkManageUpdatePage.vue'),
                props: route => ({ navigationFriendshipLinkId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminNavigationFriendshipLinkManageUpdate',
                    name: '导航友情链接修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminNavigationFriendshipLinkManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminNavigationFriendshipLinkManageUpdate'
                    }
                }
            },
        ]
    },
]
export default navigationFriendshipLinkAdminRoutes