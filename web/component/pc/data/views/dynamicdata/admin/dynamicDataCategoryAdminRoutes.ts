const dynamicDataCategoryAdminRoutes = [
    {
        path: '/admin/dynamicDataCategoryManagePage',
        component: () => import('./DynamicDataCategoryManagePage.vue'),
        meta: {
            root: true,
            code:'adminDynamicDataCategoryManagePage',
            name: '动态数据分类管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dynamicDataCategoryManageAdd',
                component: () => import('./DynamicDataCategoryManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDynamicDataCategoryManageAdd',
                    name: '动态数据分类添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDynamicDataCategoryManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDynamicDataCategoryManageAdd'
                    }
                }
            },

            {
                path: '/admin/dynamicDataCategoryManageUpdate',
                component: () => import('./DynamicDataCategoryManageUpdatePage.vue'),
                props: route => ({ dynamicDataCategoryId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDynamicDataCategoryManageUpdate',
                    name: '动态数据分类修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDynamicDataCategoryManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDynamicDataCategoryManageUpdate'
                    }
                }
            },
        ]
    },
]
export default dynamicDataCategoryAdminRoutes