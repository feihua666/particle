const navigationSiteAdminRoutes = [
    {
        path: '/admin/navigationSiteManagePage',
        component: () => import('./NavigationSiteManagePage.vue'),
        meta: {
            root: true,
            code:'adminNavigationSiteManagePage',
            name: '导航网站管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/navigationSiteManageAdd',
                component: () => import('./NavigationSiteManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminNavigationSiteManageAdd',
                    name: '导航网站添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminNavigationSiteManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminNavigationSiteManageAdd'
                    }
                }
            },

            {
                path: '/admin/navigationSiteManageUpdate',
                component: () => import('./NavigationSiteManageUpdatePage.vue'),
                props: route => ({ navigationSiteId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminNavigationSiteManageUpdate',
                    name: '导航网站修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminNavigationSiteManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminNavigationSiteManageUpdate'
                    }
                }
            },
        ]
    },
]
export default navigationSiteAdminRoutes