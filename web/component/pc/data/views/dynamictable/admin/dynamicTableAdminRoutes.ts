const dynamicTableAdminRoutes = [
    {
        path: '/admin/dynamicTableManagePage',
        component: () => import('./DynamicTableManagePage.vue'),
        meta: {
            root: true,
            code:'adminDynamicTableManagePage',
            name: '动态数据表格管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dynamicTableManageAdd',
                component: () => import('./DynamicTableManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDynamicTableManageAdd',
                    name: '动态数据表格添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDynamicTableManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDynamicTableManageAdd'
                    }
                }
            },

            {
                path: '/admin/dynamicTableManageUpdate',
                component: () => import('./DynamicTableManageUpdatePage.vue'),
                props: route => ({ dynamicTableId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDynamicTableManageUpdate',
                    name: '动态数据表格修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDynamicTableManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDynamicTableManageUpdate'
                    }
                }
            },
            {
                path: '/admin/dynamicTableManageImportData',
                component: () => import('./DynamicTableManageImportDataPage.vue'),
                props: route => ({ dynamicTableId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDynamicTableManageImportData',
                    name: '动态数据表格导入数据',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDynamicTableManageImportData'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDynamicTableManageImportData'
                    }
                }
            },
            {
                path: '/admin/dynamicTableManageData',
                component: () => import('./DynamicTableManageDataPage.vue'),
                props: route => ({ dynamicTableId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'dynamicTableManageData',
                    name: '动态数据表格数据管理',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'dynamicTableManageData'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#dynamicTableManageData'
                    }
                }
            },
        ]
    },
]
export default dynamicTableAdminRoutes
