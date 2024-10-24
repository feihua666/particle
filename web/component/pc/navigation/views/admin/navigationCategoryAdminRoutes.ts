const navigationCategoryAdminRoutes = [
    {
        path: '/admin/navigationCategoryManagePage',
        component: () => import('./NavigationCategoryManagePage.vue'),
        meta: {
            root: true,
            code:'adminNavigationCategoryManagePage',
            name: '导航分类管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/navigationCategoryManageAdd',
                component: () => import('./NavigationCategoryManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminNavigationCategoryManageAdd',
                    name: '导航分类添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminNavigationCategoryManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminNavigationCategoryManageAdd'
                    }
                }
            },

            {
                path: '/admin/navigationCategoryManageUpdate',
                component: () => import('./NavigationCategoryManageUpdatePage.vue'),
                props: route => ({ navigationCategoryId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminNavigationCategoryManageUpdate',
                    name: '导航分类修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminNavigationCategoryManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminNavigationCategoryManageUpdate'
                    }
                }
            },
        ]
    },
]
export default navigationCategoryAdminRoutes