const navigationSiteTagRelAdminRoutes = [
    {
        path: '/admin/navigationSiteTagRelManagePage',
        component: () => import('./NavigationSiteTagRelManagePage.vue'),
        meta: {
            root: true,
            code:'adminNavigationSiteTagRelManagePage',
            name: '导航网站标签关系管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/navigationSiteTagRelManageAdd',
                component: () => import('./NavigationSiteTagRelManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminNavigationSiteTagRelManageAdd',
                    name: '导航网站标签关系添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminNavigationSiteTagRelManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminNavigationSiteTagRelManageAdd'
                    }
                }
            },

            {
                path: '/admin/navigationSiteTagRelManageNavigationSiteTagAssignNavigationSite',
                component: () => import('./NavigationSiteTagRelManageNavigationSiteTagAssignNavigationSitePage.vue'),
                props: route => ({ navigationSiteTagId: route.query.navigationSiteTagId, navigationSiteTagName: route.query.navigationSiteTagName }),
                meta: {
                    showInDrawer: true,
                    code:'navigationSiteTagRelManageNavigationSiteTagAssignNavigationSite',
                    name: '网站标签分配网站',
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '.pt-route-view-popover-drawer-footer'
                    }
                }
            },
            {
                path: '/admin/navigationSiteTagRelManageNavigationSiteAssignNavigationSiteTag',
                component: () => import('./NavigationSiteTagRelManageNavigationSiteAssignNavigationSiteTagPage.vue'),
                props: route => ({ navigationSiteId: route.query.navigationSiteId, navigationSiteName: route.query.navigationSiteName }),
                meta: {
                    showInDrawer: true,
                    code:'navigationSiteTagRelManageNavigationSiteAssignNavigationSiteTag',
                    name: '网站分配网站标签',
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '.pt-route-view-popover-drawer-footer'
                    }
                }
            },
            {
                path: '/admin/navigationSiteTagRelManageDeleteByNavigationSiteTagId',
                component: () => import('./NavigationSiteTagRelManageDeleteByNavigationSiteTagIdPage.vue'),
                props: route => ({ navigationSiteTagId: route.query.navigationSiteTagId, navigationSiteTagName: route.query.navigationSiteTagName }),
                meta: {
                    showInDrawer: true,
                    code:'navigationSiteTagRelManageDeleteByNavigationSiteTagId',
                    name: '清空网站标签网站',
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '.pt-route-view-popover-drawer-footer'
                    }
                }
            },
            {
                path: '/admin/navigationSiteTagRelManageDeleteByNavigationSiteId',
                component: () => import('./NavigationSiteTagRelManageDeleteByNavigationSiteIdPage.vue'),
                props: route => ({ navigationSiteId: route.query.navigationSiteId, navigationSiteName: route.query.navigationSiteName }),
                meta: {
                    showInDrawer: true,
                    code:'navigationSiteTagRelManageDeleteByNavigationSiteId',
                    name: '清空网站网站标签',
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '.pt-route-view-popover-drawer-footer'
                    }
                }
            },
        ]
    },
]
export default navigationSiteTagRelAdminRoutes
