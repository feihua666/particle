const dataScopeAdminRoutes = [
    {
        path: '/admin/dataScopeManagePage',
        component: () => import('./DataScopeManagePage.vue'),
        meta: {
            root: true,
            code:'adminDataScopeManagePage',
            name: '数据范围管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dataScopeManageAdd',
                component: () => import('./DataScopeManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDataScopeManageAdd',
                    name: '数据范围添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataScopeManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataScopeManageAdd'
                    }
                }
            },

            {
                path: '/admin/dataScopeManageUpdate',
                component: () => import('./DataScopeManageUpdatePage.vue'),
                props: route => ({ dataScopeId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataScopeManageUpdate',
                    name: '数据范围修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataScopeManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataScopeManageUpdate'
                    }
                }
            },
            {
                path: '/admin/dataScopeManagedataScopeAssignCustomData',
                component: () => import('./DataScopeCustomDataRelManageDataScopeAssignCustomDataPage.vue'),
                props: route => ({ dataScopeId: route.query.dataScopeId, dataScopeName: route.query.dataScopeName ,dataObjectId: route.query.dataObjectId}),
                meta: {
                    showInDrawer: true,
                    code:'dataScopeCustomDataRelManagedataScopeAssignCustomData',
                    name: '数据范围分配自定义数据',
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '.pt-route-view-popover-drawer-footer'
                    }
                }
            },
        ]
    },
]
export default dataScopeAdminRoutes