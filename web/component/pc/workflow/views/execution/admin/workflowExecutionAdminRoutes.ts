const workflowExecutionAdminRoutes = [
    {
        path: '/admin/workflowExecutionManagePage',
        component: () => import('./WorkflowExecutionManagePage.vue'),
        meta: {
            root: true,
            code:'adminWorkflowExecutionManagePage',
            name: '工作流执行实例管理',
            keepAlive: true
        },
        children: [
        ]
    },
]
export default workflowExecutionAdminRoutes
