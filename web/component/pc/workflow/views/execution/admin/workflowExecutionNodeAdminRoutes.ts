const workflowExecutionNodeAdminRoutes = [
    {
        path: '/admin/workflowExecutionNodeManagePage',
        component: () => import('./WorkflowExecutionNodeManagePage.vue'),
        meta: {
            root: true,
            code:'adminWorkflowExecutionNodeManagePage',
            name: '工作流节点执行实例管理',
            keepAlive: true
        },
        children: [
        ]
    },
]
export default workflowExecutionNodeAdminRoutes
