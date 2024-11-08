const navigationSiteTagAdminRoutes = [
    {
        path: '/admin/navigationSiteTagManagePage',
        component: () => import('./NavigationSiteTagManagePage.vue'),
        meta: {
            root: true,
            code:'adminNavigationSiteTagManagePage',
            name: '导航网站标签管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/navigationSiteTagManageAdd',
                component: () => import('./NavigationSiteTagManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminNavigationSiteTagManageAdd',
                    name: '导航网站标签添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminNavigationSiteTagManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminNavigationSiteTagManageAdd'
                    }
                }
            },

            {
                path: '/admin/navigationSiteTagManageUpdate',
                component: () => import('./NavigationSiteTagManageUpdatePage.vue'),
                props: route => ({ navigationSiteTagId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminNavigationSiteTagManageUpdate',
                    name: '导航网站标签修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminNavigationSiteTagManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminNavigationSiteTagManageUpdate'
                    }
                }
            },
        ]
    },
]
export default navigationSiteTagAdminRoutes