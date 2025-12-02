const dynamicTableFieldAdminRoutes = [
    {
        path: '/admin/dynamicTableFieldManagePage',
        component: () => import('./DynamicTableFieldManagePage.vue'),
        meta: {
            root: true,
            code:'adminDynamicTableFieldManagePage',
            name: '动态数据表格字段管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dynamicTableFieldManageAdd',
                component: () => import('./DynamicTableFieldManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDynamicTableFieldManageAdd',
                    name: '动态数据表格字段添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDynamicTableFieldManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDynamicTableFieldManageAdd'
                    }
                }
            },

            {
                path: '/admin/dynamicTableFieldManageUpdate',
                component: () => import('./DynamicTableFieldManageUpdatePage.vue'),
                props: route => ({ dynamicTableFieldId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDynamicTableFieldManageUpdate',
                    name: '动态数据表格字段修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDynamicTableFieldManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDynamicTableFieldManageUpdate'
                    }
                }
            },
        ]
    },
]
export default dynamicTableFieldAdminRoutes