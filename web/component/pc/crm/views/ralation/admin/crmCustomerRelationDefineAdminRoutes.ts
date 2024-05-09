const crmCustomerRelationDefineAdminRoutes = [
    {
        path: '/admin/crmCustomerRelationDefineManagePage',
        component: () => import('./CrmCustomerRelationDefineManagePage.vue'),
        meta: {
            root: true,
            code:'adminCrmCustomerRelationDefineManagePage',
            name: '客户关系定义管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/crmCustomerRelationDefineManageAdd',
                component: () => import('./CrmCustomerRelationDefineManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminCrmCustomerRelationDefineManageAdd',
                    name: '客户关系定义添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminCrmCustomerRelationDefineManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminCrmCustomerRelationDefineManageAdd'
                    }
                }
            },

            {
                path: '/admin/crmCustomerRelationDefineManageUpdate',
                component: () => import('./CrmCustomerRelationDefineManageUpdatePage.vue'),
                props: route => ({ crmCustomerRelationDefineId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminCrmCustomerRelationDefineManageUpdate',
                    name: '客户关系定义修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminCrmCustomerRelationDefineManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminCrmCustomerRelationDefineManageUpdate'
                    }
                }
            },
        ]
    },
]
export default crmCustomerRelationDefineAdminRoutes