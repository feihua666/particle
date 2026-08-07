const workflowDefinitionHistoryAdminRoutes = [
    {
        path: '/admin/workflowDefinitionHistoryManagePage',
        component: () => import('./WorkflowDefinitionHistoryManagePage.vue'),
        props: route => ({workflowProjectId: route.query.workflowProjectId, workflowDefinitionId: route.query.workflowDefinitionId }),
        meta: {
            root: true,
            code:'adminWorkflowDefinitionHistoryManagePage',
            name: '工作流定义历史管理',
            keepAlive: true
        },
        children: [
        ]
    },
    {
        path: '/admin/workflowDefinitionHistoryWorkflowEdit',
        component: () => import('./WorkflowDefinitionHistoryWorkflowEditPage.vue'),
        props: route => ({ workflowDefinitionHistoryId: route.query.id ,workflowExecutionId: route.query.workflowExecutionId}),
        meta: {
            root: true,
            code:'adminWorkflowDefinitionHistoryWorkflowEditPage',
            name: '工作流定义历史画布编辑'
        }
    },
]
export default workflowDefinitionHistoryAdminRoutes
