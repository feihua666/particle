const dynamicTableUploadRecordAdminRoutes = [
    {
        path: '/admin/dynamicTableUploadRecordManagePage',
        component: () => import('./DynamicTableUploadRecordManagePage.vue'),
        meta: {
            root: true,
            code:'adminDynamicTableUploadRecordManagePage',
            name: '动态数据表格上传记录管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dynamicTableUploadRecordManageData',
                component: () => import('./DynamicTableManageDataPage.vue'),
                props: route => ({ dynamicTableId: route.query.dynamicTableId,batchId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'dynamicTableUploadRecordManageData',
                    name: '动态数据表格上传记录数据管理',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'dynamicTableUploadRecordManageData'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#dynamicTableUploadRecordManageData'
                    }
                }
            },
        ]
    },
]
export default dynamicTableUploadRecordAdminRoutes
