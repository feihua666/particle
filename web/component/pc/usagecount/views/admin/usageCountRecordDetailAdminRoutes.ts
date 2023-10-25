const usageCountRecordDetailAdminRoutes = [
    {
        path: '/admin/usageCountRecordDetailManagePage',
        component: () => import('./UsageCountRecordDetailManagePage.vue'),
        meta: {
            root: true,
            code:'adminUsageCountRecordDetailManagePage',
            name: '使用次数记录明细管理',
            keepAlive: true
        },
        children: [
        ]
    },
]
export default usageCountRecordDetailAdminRoutes