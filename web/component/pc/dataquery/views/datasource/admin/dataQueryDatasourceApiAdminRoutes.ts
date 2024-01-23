const dataQueryDatasourceApiAdminRoutes = [
    {
        path: '/admin/dataQueryDatasourceApiManagePage',
        component: () => import('./DataQueryDatasourceApiManagePage.vue'),
        props: route => ({ name: route.query.dataQueryDatasourceApiName }),
        meta: {
            root: true,
            code:'adminDataQueryDatasourceApiManagePage',
            name: '数据查询数据源接口管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dataQueryDatasourceApiManageAdd',
                component: () => import('./DataQueryDatasourceApiManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDataQueryDatasourceApiManageAdd',
                    name: '数据查询数据源接口添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataQueryDatasourceApiManageAdd',
                        size: '60%'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataQueryDatasourceApiManageAdd'
                    }
                }
            },

            {
                path: '/admin/dataQueryDatasourceApiManageUpdate',
                component: () => import('./DataQueryDatasourceApiManageUpdatePage.vue'),
                props: route => ({ dataQueryDatasourceApiId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataQueryDatasourceApiManageUpdate',
                    name: '数据查询数据源接口修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataQueryDatasourceApiManageUpdate',
                        size: '60%'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataQueryDatasourceApiManageUpdate'
                    }
                }
            },
            {
                path: '/admin/dataQueryDatasourceApiManageView',
                component: () => import('./DataQueryDatasourceApiManageViewPage.vue'),
                props: route => ({ dataQueryDatasourceApiId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataQueryDatasourceApiManageView',
                    name: '数据查询数据源接口查看',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataQueryDatasourceApiManageView',
                        size: '60%'
                    },
                }
            },
            {
                path: '/admin/dataQueryDatasourceApiManageTest',
                component: () => import('./DataQueryDatasourceApiManageTestPage.vue'),
                props: route => ({ dataQueryDatasourceApiId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataQueryDatasourceApiManageTest',
                    name: '数据查询数据源接口测试',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        size: '60%'
                    }
                }
            },
        ]
    },
]
export default dataQueryDatasourceApiAdminRoutes