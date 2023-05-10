const trackingPageRecordAdminRoutes = [
    {
        path: '/admin/trackingPageRecordManagePage',
        component: () => import('./TrackingPageRecordManagePage.vue'),
        meta: {
            root: true,
            code:'adminTrackingPageRecordManagePage',
            name: '页面埋点记录管理',
            keepAlive: true
        },
        children: [
        ]
    },
]
export default trackingPageRecordAdminRoutes