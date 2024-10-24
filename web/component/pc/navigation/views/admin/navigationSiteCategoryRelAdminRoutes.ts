const navigationSiteCategoryRelAdminRoutes = [
    {
        path: '/admin/navigationSiteCategoryRelManagePage',
        component: () => import('./NavigationSiteCategoryRelManagePage.vue'),
        meta: {
            root: true,
            code:'adminNavigationSiteCategoryRelManagePage',
            name: '导航网站分类关系管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/navigationSiteCategoryRelManageAdd',
                component: () => import('./NavigationSiteCategoryRelManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminNavigationSiteCategoryRelManageAdd',
                    name: '导航网站分类关系添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminNavigationSiteCategoryRelManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminNavigationSiteCategoryRelManageAdd'
                    }
                }
            },

            {
                path: '/admin/navigationSiteCategoryRelManageUpdate',
                component: () => import('./NavigationSiteCategoryRelManageUpdatePage.vue'),
                props: route => ({ navigationSiteCategoryRelId: route.query.id ,navigationSiteId: route.query.navigationSiteId, navigationSiteName: route.query.navigationSiteName}),
                meta: {
                    showInDrawer: true,
                    code:'adminNavigationSiteCategoryRelManageUpdate',
                    name: '导航网站分类关系修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminNavigationSiteCategoryRelManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminNavigationSiteCategoryRelManageUpdate'
                    }
                }
            },
            {
                path: '/admin/navigationSiteCategoryRelManageNavigationCategoryAssignNavigationSite',
                component: () => import('./NavigationSiteCategoryRelManageNavigationCategoryAssignNavigationSitePage.vue'),
                props: route => ({ navigationCategoryId: route.query.navigationCategoryId, navigationCategoryName: route.query.navigationCategoryName }),
                meta: {
                    showInDrawer: true,
                    code:'navigationSiteCategoryRelManageNavigationCategoryAssignNavigationSite',
                    name: '导航分类分配导航网站',
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '.pt-route-view-popover-drawer-footer'
                    }
                }
            },
            {
                path: '/admin/navigationSiteCategoryRelManageNavigationSiteAssignNavigationCategory',
                component: () => import('./NavigationSiteCategoryRelManageNavigationSiteAssignNavigationCategoryPage.vue'),
                props: route => ({ navigationSiteId: route.query.navigationSiteId, navigationSiteName: route.query.navigationSiteName }),
                meta: {
                    showInDrawer: true,
                    code:'navigationSiteCategoryRelManageNavigationSiteAssignNavigationCategory',
                    name: '导航网站分配导航分类',
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '.pt-route-view-popover-drawer-footer'
                    }
                }
            },
            {
                path: '/admin/navigationSiteCategoryRelManageDeleteByNavigationCategoryId',
                component: () => import('./NavigationSiteCategoryRelManageDeleteByNavigationCategoryIdPage.vue'),
                props: route => ({ navigationCategoryId: route.query.navigationCategoryId, navigationCategoryName: route.query.navigationCategoryName }),
                meta: {
                    showInDrawer: true,
                    code:'navigationSiteCategoryRelManageDeleteByNavigationCategoryId',
                    name: '清空导航分类导航网站',
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '.pt-route-view-popover-drawer-footer'
                    }
                }
            },
            {
                path: '/admin/navigationSiteCategoryRelManageDeleteByNavigationSiteId',
                component: () => import('./NavigationSiteCategoryRelManageDeleteByNavigationSiteIdPage.vue'),
                props: route => ({ navigationSiteId: route.query.navigationSiteId, navigationSiteName: route.query.navigationSiteName }),
                meta: {
                    showInDrawer: true,
                    code:'navigationSiteCategoryRelManageDeleteByNavigationSiteId',
                    name: '清空导航网站导航分类',
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '.pt-route-view-popover-drawer-footer'
                    }
                }
            },
        ]
    },
]
export default navigationSiteCategoryRelAdminRoutes
