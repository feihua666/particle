const schedulerExecuteRecordAdminRoutes = [
    {
        path: '/admin/schedulerExecuteRecordManagePage',
        component: () => import('./SchedulerExecuteRecordManagePage.vue'),
        props: route => ({ schedulerName: route.query.schedulerName,
            schedulerInstanceId: route.query.schedulerInstanceId,
            name: route.query.name,
            group: route.query.group,
        }),
        meta: {
            root: true,
            code:'adminSchedulerExecuteRecordManagePage',
            name: '任务计划执行记录管理',
            keepAlive: true
        },
        children: [
        ]
    },
]
export default schedulerExecuteRecordAdminRoutes