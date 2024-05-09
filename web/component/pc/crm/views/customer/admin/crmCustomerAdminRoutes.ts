const crmCustomerAdminRoutes = [
    {
        path: '/admin/crmCustomerManagePage',
        component: () => import('./CrmCustomerManagePage.vue'),
        meta: {
            root: true,
            code:'adminCrmCustomerManagePage',
            name: '客户管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/crmCustomerManageAdd',
                component: () => import('./CrmCustomerManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminCrmCustomerManageAdd',
                    name: '客户添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminCrmCustomerManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminCrmCustomerManageAdd'
                    }
                }
            },

            {
                path: '/admin/crmCustomerManageUpdate',
                component: () => import('./CrmCustomerManageUpdatePage.vue'),
                props: route => ({ crmCustomerId: route.query.id,belongUserId: route.query.belongUserId, belongUserNickname: route.query.belongUserNickname}),
                meta: {
                    showInDrawer: true,
                    code:'adminCrmCustomerManageUpdate',
                    name: '客户修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminCrmCustomerManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminCrmCustomerManageUpdate'
                    }
                }
            },
        ]
    },
]
export default crmCustomerAdminRoutes