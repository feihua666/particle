const dynamicDataIndicatorCategoryAdminRoutes = [
    {
        path: '/admin/dynamicDataIndicatorCategoryManagePage',
        component: () => import('./DynamicDataIndicatorCategoryManagePage.vue'),
        meta: {
            root: true,
            code:'adminDynamicDataIndicatorCategoryManagePage',
            name: '动态数据指标分类管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dynamicDataIndicatorCategoryManageAdd',
                component: () => import('./DynamicDataIndicatorCategoryManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDynamicDataIndicatorCategoryManageAdd',
                    name: '动态数据指标分类添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDynamicDataIndicatorCategoryManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDynamicDataIndicatorCategoryManageAdd'
                    }
                }
            },

            {
                path: '/admin/dynamicDataIndicatorCategoryManageUpdate',
                component: () => import('./DynamicDataIndicatorCategoryManageUpdatePage.vue'),
                props: route => ({ dynamicDataIndicatorCategoryId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDynamicDataIndicatorCategoryManageUpdate',
                    name: '动态数据指标分类修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDynamicDataIndicatorCategoryManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDynamicDataIndicatorCategoryManageUpdate'
                    }
                }
            },
            {
                path: '/admin/dynamicDataIndicatorCategoryManageImportData',
                component: () => import('./DynamicDataIndicatorCategoryManageImportDataPage.vue'),
                props: route => ({ dynamicDataIndicatorCategoryId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDynamicDataIndicatorCategoryManageImportData',
                    name: '动态数据指标分类导入数据',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDynamicDataIndicatorCategoryManageImportData'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDynamicDataIndicatorCategoryManageImportData'
                    }
                }
            },
            {
                path: '/admin/dynamicDataIndicatorCategoryManageData',
                component: () => import('./DynamicDataIndicatorCategoryManageDataPage.vue'),
                props: route => ({ dynamicDataIndicatorCategoryId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'dynamicDataIndicatorCategoryManageData',
                    name: '动态数据指标分类数据管理',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'dynamicDataIndicatorCategoryManageData'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#dynamicDataIndicatorCategoryManageData'
                    }
                }
            },
        ]
    },
]
export default dynamicDataIndicatorCategoryAdminRoutes
