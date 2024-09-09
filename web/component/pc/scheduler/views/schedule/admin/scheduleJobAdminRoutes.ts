const scheduleJobAdminRoutes = [
    {
        path: '/admin/scheduleJobManagePage',
        component: () => import('./ScheduleJobManagePage.vue'),
        props: route => ({ schedulerName: route.query.schedulerName,schedulerInstanceId: route.query.schedulerInstanceId }),
        meta: {
            root: true,
            code:'adminScheduleJobManagePage',
            name: '任务计划任务管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/scheduleJobManageAddPage',
                component: () => import('./ScheduleJobManageAddPage.vue'),
                props: route => ({ schedulerName: route.query.schedulerName,schedulerInstanceId: route.query.schedulerInstanceId }),
                meta: {
                    showInDrawer: true,
                    code:'adminScheduleJobManageAddPage',
                    name: '任务计划任务管理任务添加',
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '.pt-route-view-popover-drawer-footer'
                    }
                }
            },
            {
                path: '/admin/scheduleJobManageUpdatePage',
                component: () => import('./ScheduleJobManageUpdatePage.vue'),
                props: route => ({ schedulerName: route.query.schedulerName,schedulerInstanceId: route.query.schedulerInstanceId,name: route.query.name,group: route.query.group }),
                meta: {
                    showInDrawer: true,
                    code:'adminScheduleJobManageUpdatePage',
                    name: '任务计划任务管理任务修改',
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '.pt-route-view-popover-drawer-footer'
                    }
                }
            },
        ]
    },
]
export default scheduleJobAdminRoutes