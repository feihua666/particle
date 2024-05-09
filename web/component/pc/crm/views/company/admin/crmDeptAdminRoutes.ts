const crmDeptAdminRoutes = [
    {
        path: '/admin/crmDeptManagePage',
        component: () => import('./CrmDeptManagePage.vue'),
        meta: {
            root: true,
            code:'adminCrmDeptManagePage',
            name: '客户公司部门管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/crmDeptManageAdd',
                component: () => import('./CrmDeptManageAddPage.vue'),
                props: route => ({ parentId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminCrmDeptManageAdd',
                    name: '客户公司部门添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminCrmDeptManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminCrmDeptManageAdd'
                    }
                }
            },

            {
                path: '/admin/crmDeptManageUpdate',
                component: () => import('./CrmDeptManageUpdatePage.vue'),
                props: route => ({ crmDeptId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminCrmDeptManageUpdate',
                    name: '客户公司部门修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminCrmDeptManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminCrmDeptManageUpdate'
                    }
                }
            },
        ]
    },
]
export default crmDeptAdminRoutes