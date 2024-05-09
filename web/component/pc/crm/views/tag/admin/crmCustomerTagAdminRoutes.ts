const crmCustomerTagAdminRoutes = [
    {
        path: '/admin/crmCustomerTagManagePage',
        component: () => import('./CrmCustomerTagManagePage.vue'),
        meta: {
            root: true,
            code:'adminCrmCustomerTagManagePage',
            name: '客户标签管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/crmCustomerTagManageAdd',
                component: () => import('./CrmCustomerTagManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminCrmCustomerTagManageAdd',
                    name: '客户标签添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminCrmCustomerTagManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminCrmCustomerTagManageAdd'
                    }
                }
            },

            {
                path: '/admin/crmCustomerTagManageUpdate',
                component: () => import('./CrmCustomerTagManageUpdatePage.vue'),
                props: route => ({ crmCustomerTagId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminCrmCustomerTagManageUpdate',
                    name: '客户标签修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminCrmCustomerTagManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminCrmCustomerTagManageUpdate'
                    }
                }
            },
        ]
    },
]
export default crmCustomerTagAdminRoutes