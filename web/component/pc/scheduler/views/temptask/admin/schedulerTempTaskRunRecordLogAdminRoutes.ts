const schedulerTempTaskRunRecordLogAdminRoutes = [
    {
        path: '/admin/schedulerTempTaskRunRecordLogManagePage',
        component: () => import('./SchedulerTempTaskRunRecordLogManagePage.vue'),
        props: route => ({ schedulerTempTaskRunRecordId: route.query.schedulerTempTaskRunRecordId ,schedulerTempTaskName:route.query.schedulerTempTaskName}),
        meta: {
            root: true,
            code:'adminSchedulerTempTaskRunRecordLogManagePage',
            name: '任务计划临时任务运行记录日志管理',
            keepAlive: true
        },
        children: [
        ]
    },
]
export default schedulerTempTaskRunRecordLogAdminRoutes