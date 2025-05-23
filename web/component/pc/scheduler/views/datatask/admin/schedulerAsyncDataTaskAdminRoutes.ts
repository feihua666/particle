const schedulerAsyncDataTaskAdminRoutes = [
    {
        path: '/admin/schedulerAsyncDataTaskManagePage',
        component: () => import('./SchedulerAsyncDataTaskManagePage.vue'),
        meta: {
            root: true,
            code:'adminSchedulerAsyncDataTaskManagePage',
            name: '任务计划异步任务数据管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/schedulerAsyncDataTaskManageAdd',
                component: () => import('./SchedulerAsyncDataTaskManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminSchedulerAsyncDataTaskManageAdd',
                    name: '任务计划异步任务数据添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminSchedulerAsyncDataTaskManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminSchedulerAsyncDataTaskManageAdd'
                    }
                }
            },

            {
                path: '/admin/schedulerAsyncDataTaskManageUpdate',
                component: () => import('./SchedulerAsyncDataTaskManageUpdatePage.vue'),
                props: route => ({ schedulerAsyncDataTaskId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminSchedulerAsyncDataTaskManageUpdate',
                    name: '任务计划异步任务数据修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminSchedulerAsyncDataTaskManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminSchedulerAsyncDataTaskManageUpdate'
                    }
                }
            },
        ]
    },
]
export default schedulerAsyncDataTaskAdminRoutes