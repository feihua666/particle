const opLogAuditDataAdminRoutes = [
    {
        path: '/admin/opLogAuditDataManagePage',
        component: () => import('./OpLogAuditDataManagePage.vue'),
        meta: {
            root: true,
            code:'adminOpLogAuditDataManagePage',
            name: '操作日志审计数据管理',
            keepAlive: true
        },
    },
]
export default opLogAuditDataAdminRoutes