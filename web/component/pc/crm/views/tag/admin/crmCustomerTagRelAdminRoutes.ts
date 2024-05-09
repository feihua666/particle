const crmCustomerTagRelAdminRoutes = [
    {
        path: '/admin/crmCustomerTagRelManagePage',
        component: () => import('./CrmCustomerTagRelManagePage.vue'),
        meta: {
            root: true,
            code:'adminCrmCustomerTagRelManagePage',
            name: '客户标签关系管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/crmCustomerTagRelManageAdd',
                component: () => import('./CrmCustomerTagRelManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminCrmCustomerTagRelManageAdd',
                    name: '客户标签关系添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminCrmCustomerTagRelManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminCrmCustomerTagRelManageAdd'
                    }
                }
            },

            {
                path: '/admin/crmCustomerTagRelManageUpdate',
                component: () => import('./CrmCustomerTagRelManageUpdatePage.vue'),
                props: route => ({ crmCustomerTagRelId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminCrmCustomerTagRelManageUpdate',
                    name: '客户标签关系修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminCrmCustomerTagRelManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminCrmCustomerTagRelManageUpdate'
                    }
                }
            },
        ]
    },
]
export default crmCustomerTagRelAdminRoutes