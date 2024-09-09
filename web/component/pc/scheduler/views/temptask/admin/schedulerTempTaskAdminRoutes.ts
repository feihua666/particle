const schedulerTempTaskAdminRoutes = [
    {
        path: '/admin/schedulerTempTaskManagePage',
        component: () => import('./SchedulerTempTaskManagePage.vue'),
        meta: {
            root: true,
            code:'adminSchedulerTempTaskManagePage',
            name: '任务计划临时任务管理',
            keepAlive: true
        },
        children: [
        ]
    },
]
export default schedulerTempTaskAdminRoutes