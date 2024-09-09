const scheduleAdminRoutes = [
    {
        path: '/admin/scheduleManagePage',
        component: () => import('./ScheduleManagePage.vue'),
        meta: {
            root: true,
            code:'adminScheduleManagePage',
            name: '任务计划管理',
            keepAlive: true
        },
        children: [
        ]
    },
]
export default scheduleAdminRoutes