const crmCustomerRelationAdminRoutes = [
    {
        path: '/admin/crmCustomerRelationManagePage',
        component: () => import('./CrmCustomerRelationManagePage.vue'),
        meta: {
            root: true,
            code:'adminCrmCustomerRelationManagePage',
            name: '客户与客户关系管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/crmCustomerRelationManageAdd',
                component: () => import('./CrmCustomerRelationManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminCrmCustomerRelationManageAdd',
                    name: '客户与客户关系添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminCrmCustomerRelationManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminCrmCustomerRelationManageAdd'
                    }
                }
            },

            {
                path: '/admin/crmCustomerRelationManageUpdate',
                component: () => import('./CrmCustomerRelationManageUpdatePage.vue'),
                props: route => ({ crmCustomerRelationId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminCrmCustomerRelationManageUpdate',
                    name: '客户与客户关系修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminCrmCustomerRelationManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminCrmCustomerRelationManageUpdate'
                    }
                }
            },
        ]
    },
]
export default crmCustomerRelationAdminRoutes