const dataQueryProviderAdminRoutes = [
    {
        path: '/admin/dataQueryProviderManagePage',
        component: () => import('./DataQueryProviderManagePage.vue'),
        meta: {
            root: true,
            code:'adminDataQueryProviderManagePage',
            name: '数据查询供应商管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dataQueryProviderManageAdd',
                component: () => import('./DataQueryProviderManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDataQueryProviderManageAdd',
                    name: '数据查询供应商添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataQueryProviderManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataQueryProviderManageAdd'
                    }
                }
            },

            {
                path: '/admin/dataQueryProviderManageUpdate',
                component: () => import('./DataQueryProviderManageUpdatePage.vue'),
                props: route => ({ dataQueryProviderId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataQueryProviderManageUpdate',
                    name: '数据查询供应商修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataQueryProviderManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataQueryProviderManageUpdate'
                    }
                }
            },
        ]
    },
]
export default dataQueryProviderAdminRoutes