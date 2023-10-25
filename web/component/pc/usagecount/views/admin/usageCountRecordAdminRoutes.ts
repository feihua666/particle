const usageCountRecordAdminRoutes = [
    {
        path: '/admin/usageCountRecordManagePage',
        component: () => import('./UsageCountRecordManagePage.vue'),
        meta: {
            root: true,
            code:'adminUsageCountRecordManagePage',
            name: '使用次数记录管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/usageCountRecordDetailPage',
                component: () => import('./UsageCountRecordDetailManagePage.vue'),
                props: route => ({ usageCountRecordId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminUsageCountRecordDetailManagePage',
                    name: '使用次数记录明细',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminUsageCountRecordDetailPage'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminUsageCountRecordDetailPage'
                    }
                },
                children: [
                ]
            },
        ]
    },
]
export default usageCountRecordAdminRoutes