const dataScopeCustomDataRelAdminRoutes = [
    {
        path: '/admin/dataScopeCustomDataRelManagePage',
        component: () => import('./DataScopeCustomDataRelManagePage.vue'),
        meta: {
            root: true,
            code:'adminDataScopeCustomDataRelManagePage',
            name: '数据范围自定义数据关系管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dataScopeCustomDataRelManageAdd',
                component: () => import('./DataScopeCustomDataRelManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDataScopeCustomDataRelManageAdd',
                    name: '数据范围自定义数据关系添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataScopeCustomDataRelManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataScopeCustomDataRelManageAdd'
                    }
                }
            },

            {
                path: '/admin/dataScopeCustomDataRelManageUpdate',
                component: () => import('./DataScopeCustomDataRelManageUpdatePage.vue'),
                props: route => ({ dataScopeCustomDataRelId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataScopeCustomDataRelManageUpdate',
                    name: '数据范围自定义数据关系修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataScopeCustomDataRelManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataScopeCustomDataRelManageUpdate'
                    }
                }
            },
        ]
    },
]
export default dataScopeCustomDataRelAdminRoutes