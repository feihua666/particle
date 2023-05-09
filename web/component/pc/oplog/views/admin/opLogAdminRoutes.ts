const opLogAdminRoutes = [
    {
        path: '/admin/opLogManagePage',
        component: () => import('./OpLogManagePage.vue'),
        meta: {
            root: true,
            code:'adminOpLogManagePage',
            name: '操作日志管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/opLogAuditDataManagePopoverPage',
                component: () => import('./OpLogAuditDataManagePage.vue'),
                props: route => ({ opLogId: route.query.opLogId,opLogName: route.query.opLogName }),
                meta: {
                    showInDrawer: true,
                    code:'adminOpLogAuditDataManagePage',
                    name: '操作日志审计数据管理',
                },
            },
        ]
    },
]
export default opLogAdminRoutes