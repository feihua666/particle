const schedulerJobDataTaskAdminRoutes = [
    {
        path: '/admin/schedulerJobDataTaskManagePage',
        component: () => import('./SchedulerJobDataTaskManagePage.vue'),
        meta: {
            root: true,
            code:'adminSchedulerJobDataTaskManagePage',
            name: '任务计划任务数据管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/schedulerJobDataTaskManageAdd',
                component: () => import('./SchedulerJobDataTaskManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminSchedulerJobDataTaskManageAdd',
                    name: '任务计划任务数据添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminSchedulerJobDataTaskManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminSchedulerJobDataTaskManageAdd'
                    }
                }
            },

            {
                path: '/admin/schedulerJobDataTaskManageUpdate',
                component: () => import('./SchedulerJobDataTaskManageUpdatePage.vue'),
                props: route => ({ schedulerJobDataTaskId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminSchedulerJobDataTaskManageUpdate',
                    name: '任务计划任务数据修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminSchedulerJobDataTaskManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminSchedulerJobDataTaskManageUpdate'
                    }
                }
            },
        ]
    },
]
export default schedulerJobDataTaskAdminRoutes