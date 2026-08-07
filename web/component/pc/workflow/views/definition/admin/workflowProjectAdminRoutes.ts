const workflowProjectAdminRoutes = [
    {
        path: '/admin/workflowProjectManagePage',
        component: () => import('./WorkflowProjectManagePage.vue'),
        meta: {
            root: true,
            code:'adminWorkflowProjectManagePage',
            name: '工作流项目管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/workflowProjectManageAdd',
                component: () => import('./WorkflowProjectManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminWorkflowProjectManageAdd',
                    name: '工作流项目添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminWorkflowProjectManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminWorkflowProjectManageAdd'
                    }
                }
            },

            {
                path: '/admin/workflowProjectManageUpdate',
                component: () => import('./WorkflowProjectManageUpdatePage.vue'),
                props: route => ({ workflowProjectId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminWorkflowProjectManageUpdate',
                    name: '工作流项目修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminWorkflowProjectManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminWorkflowProjectManageUpdate'
                    }
                }
            },
        ]
    },
]
export default workflowProjectAdminRoutes