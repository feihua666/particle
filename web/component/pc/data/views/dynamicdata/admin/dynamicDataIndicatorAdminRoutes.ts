const dynamicDataIndicatorAdminRoutes = [
    {
        path: '/admin/dynamicDataIndicatorManagePage',
        component: () => import('./DynamicDataIndicatorManagePage.vue'),
        meta: {
            root: true,
            code:'adminDynamicDataIndicatorManagePage',
            name: '动态数据指标管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dynamicDataIndicatorManageAdd',
                component: () => import('./DynamicDataIndicatorManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDynamicDataIndicatorManageAdd',
                    name: '动态数据指标添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDynamicDataIndicatorManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDynamicDataIndicatorManageAdd'
                    }
                }
            },

            {
                path: '/admin/dynamicDataIndicatorManageUpdate',
                component: () => import('./DynamicDataIndicatorManageUpdatePage.vue'),
                props: route => ({ dynamicDataIndicatorId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDynamicDataIndicatorManageUpdate',
                    name: '动态数据指标修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDynamicDataIndicatorManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDynamicDataIndicatorManageUpdate'
                    }
                }
            },
        ]
    },
]
export default dynamicDataIndicatorAdminRoutes