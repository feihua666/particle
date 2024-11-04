const navigationSubmitAdminRoutes = [
    {
        path: '/admin/navigationSubmitManagePage',
        component: () => import('./NavigationSubmitManagePage.vue'),
        meta: {
            root: true,
            code:'adminNavigationSubmitManagePage',
            name: '导航提交管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/navigationSubmitManageAdd',
                component: () => import('./NavigationSubmitManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminNavigationSubmitManageAdd',
                    name: '导航提交添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminNavigationSubmitManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminNavigationSubmitManageAdd'
                    }
                }
            },

            {
                path: '/admin/navigationSubmitManageUpdate',
                component: () => import('./NavigationSubmitManageUpdatePage.vue'),
                props: route => ({ navigationSubmitId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminNavigationSubmitManageUpdate',
                    name: '导航提交修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminNavigationSubmitManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminNavigationSubmitManageUpdate'
                    }
                }
            },
            {
                path: '/admin/navigationSubmitManageUpdateContent',
                component: () => import('./NavigationSubmitManageUpdateContentPage.vue'),
                props: route => ({ navigationSubmitId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'navigationSubmitManageUpdateContent',
                    name: '导航提交修改内容',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'navigationSubmitManageUpdateContent'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#navigationSubmitManageUpdateContent'
                    }
                }
            },
        ]
    },
]
export default navigationSubmitAdminRoutes
