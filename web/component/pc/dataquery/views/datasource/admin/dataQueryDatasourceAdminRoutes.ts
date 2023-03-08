const dataQueryDatasourceAdminRoutes = [
    {
        path: '/admin/dataQueryDatasourceManagePage',
        component: () => import('./DataQueryDatasourceManagePage.vue'),
        meta: {
            root: true,
            code:'adminDataQueryDatasourceManagePage',
            name: '数据查询数据源管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dataQueryDatasourceManageAdd',
                component: () => import('./DataQueryDatasourceManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDataQueryDatasourceManageAdd',
                    name: '数据查询数据源添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataQueryDatasourceManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataQueryDatasourceManageAdd'
                    }
                }
            },

            {
                path: '/admin/dataQueryDatasourceManageUpdate',
                component: () => import('./DataQueryDatasourceManageUpdatePage.vue'),
                props: route => ({ dataQueryDatasourceId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataQueryDatasourceManageUpdate',
                    name: '数据查询数据源修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataQueryDatasourceManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataQueryDatasourceManageUpdate'
                    }
                }
            },
        ]
    },
]
export default dataQueryDatasourceAdminRoutes