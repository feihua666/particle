const dataQueryDataApiAdminRoutes = [
    {
        path: '/admin/dataQueryDataApiManagePage',
        component: () => import('./DataQueryDataApiManagePage.vue'),
        props: route => ({ url: route.query.dqUrl }),
        meta: {
            root: true,
            code:'adminDataQueryDataApiManagePage',
            name: '数据查询数据接口管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dataQueryDataApiManageAdd',
                component: () => import('./DataQueryDataApiManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDataQueryDataApiManageAdd',
                    name: '数据查询数据接口添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataQueryDataApiManageAdd',
                        size: '60%'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataQueryDataApiManageAdd'
                    }
                }
            },

            {
                path: '/admin/dataQueryDataApiManageUpdate',
                component: () => import('./DataQueryDataApiManageUpdatePage.vue'),
                props: route => ({ dataQueryDataApiId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataQueryDataApiManageUpdate',
                    name: '数据查询数据接口修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataQueryDataApiManageUpdate',
                        size: '60%'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataQueryDataApiManageUpdate'
                    }
                }
            },
            {
                path: '/admin/dataQueryDataApiManageTest',
                component: () => import('./DataQueryDataApiManageTestPage.vue'),
                props: route => ({ dataQueryDataApiId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataQueryDataApiManageTest',
                    name: '数据查询数据接口测试',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        size: '60%'
                    }
                }
            },
        ]
    },
]
export default dataQueryDataApiAdminRoutes