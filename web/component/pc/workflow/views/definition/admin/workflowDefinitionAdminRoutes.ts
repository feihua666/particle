const workflowDefinitionAdminRoutes = [
    {
        path: '/admin/workflowDefinitionManagePage',
        component: () => import('./WorkflowDefinitionManagePage.vue'),
        props: route => ({ workflowProjectId: route.query.workflowProjectId }),
        meta: {
            root: true,
            code:'adminWorkflowDefinitionManagePage',
            name: '工作流定义管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/workflowDefinitionManageAdd',
                component: () => import('./WorkflowDefinitionManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminWorkflowDefinitionManageAdd',
                    name: '工作流定义添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminWorkflowDefinitionManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminWorkflowDefinitionManageAdd'
                    }
                }
            },

            {
                path: '/admin/workflowDefinitionManageUpdate',
                component: () => import('./WorkflowDefinitionManageUpdatePage.vue'),
                props: route => ({ workflowDefinitionId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminWorkflowDefinitionManageUpdate',
                    name: '工作流定义修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminWorkflowDefinitionManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminWorkflowDefinitionManageUpdate'
                    }
                }
            },
        ]
    },
]
export default workflowDefinitionAdminRoutes
