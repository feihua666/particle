const scheduleTriggerAdminRoutes = [
    {
        path: '/admin/scheduleTriggerManagePage',
        component: () => import('./ScheduleTriggerManagePage.vue'),
        props: route => ({ schedulerName: route.query.schedulerName,schedulerInstanceId: route.query.schedulerInstanceId }),
        meta: {
            root: true,
            code:'adminScheduleTriggerManagePage',
            name: '任务计划触发器管理',
            keepAlive: true
        },
        children: [

        ]
    },
]
export default scheduleTriggerAdminRoutes