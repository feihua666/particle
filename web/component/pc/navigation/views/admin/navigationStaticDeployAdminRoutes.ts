const navigationStaticDeployAdminRoutes = [
    {
        path: '/admin/navigationStaticDeployManagePage',
        component: () => import('./NavigationStaticDeployManagePage.vue'),
        meta: {
            root: true,
            code:'adminNavigationStaticDeployManagePage',
            name: '导航网站静态部署管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/navigationStaticDeployManageAdd',
                component: () => import('./NavigationStaticDeployManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminNavigationStaticDeployManageAdd',
                    name: '导航网站静态部署添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminNavigationStaticDeployManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminNavigationStaticDeployManageAdd'
                    }
                }
            },

            {
                path: '/admin/navigationStaticDeployManageUpdate',
                component: () => import('./NavigationStaticDeployManageUpdatePage.vue'),
                props: route => ({ navigationStaticDeployId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminNavigationStaticDeployManageUpdate',
                    name: '导航网站静态部署修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminNavigationStaticDeployManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminNavigationStaticDeployManageUpdate'
                    }
                }
            },

            {
                path: '/admin/navigationStaticDeployManageDeploy',
                component: () => import('./NavigationStaticDeployManageDeployPage.vue'),
                props: route => ({ navigationStaticDeployId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminNavigationStaticDeployManageDeploy',
                    name: '导航网站静态部署',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminNavigationStaticDeployManageDeploy'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminNavigationStaticDeployManageDeploy'
                    }
                }
            },
        ]
    },
]
export default navigationStaticDeployAdminRoutes
