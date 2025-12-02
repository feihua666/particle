const dynamicDataIndicatorCategoryUploadRecordAdminRoutes = [
    {
        path: '/admin/dynamicDataIndicatorCategoryUploadRecordManagePage',
        component: () => import('./DynamicDataIndicatorCategoryUploadRecordManagePage.vue'),
        meta: {
            root: true,
            code:'adminDynamicDataIndicatorCategoryUploadRecordManagePage',
            name: '动态数据指标分类上传记录管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dynamicDataIndicatorCategoryUploadRecordManageData',
                component: () => import('./DynamicDataIndicatorCategoryManageDataPage.vue'),
                props: route => ({ dynamicDataIndicatorCategoryId: route.query.dynamicDataIndicatorCategoryId,batchId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'dynamicDataIndicatorCategoryUploadRecordManageData',
                    name: '动态数据指标分类上传记录数据管理',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'dynamicDataIndicatorCategoryUploadRecordManageData'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#dynamicDataIndicatorCategoryUploadRecordManageData'
                    }
                }
            },
        ]
    },
]
export default dynamicDataIndicatorCategoryUploadRecordAdminRoutes
