const schedulerTempTaskRunRecordAdminRoutes = [
    {
        path: '/admin/schedulerTempTaskRunRecordManagePage',
        component: () => import('./SchedulerTempTaskRunRecordManagePage.vue'),
        props: route => ({ schedulerTempTaskId: route.query.schedulerTempTaskId }),
        meta: {
            root: true,
            code:'adminSchedulerTempTaskRunRecordManagePage',
            name: '任务计划临时任务运行记录管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/schedulerTempTaskRunRecordManageUpdate',
                component: () => import('./SchedulerTempTaskRunRecordManageUpdatePage.vue'),
                props: route => ({ schedulerTempTaskRunRecordId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminSchedulerTempTaskRunRecordManageUpdate',
                    name: '任务计划临时任务运行记录修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminSchedulerTempTaskRunRecordManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminSchedulerTempTaskRunRecordManageUpdate'
                    }
                }
            },
        ]
    },
]
export default schedulerTempTaskRunRecordAdminRoutes