const crmCustomerContactAdminRoutes = [
    {
        path: '/admin/crmCustomerContactManagePage',
        component: () => import('./CrmCustomerContactManagePage.vue'),
        props: route => ({ crmCustomerId: route.query.crmCustomerId }),
        meta: {
            root: true,
            code:'adminCrmCustomerContactManagePage',
            name: '客户联系方式管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/crmCustomerContactManageAdd',
                component: () => import('./CrmCustomerContactManageAddPage.vue'),
                props: route => ({ crmCustomerId: route.query.crmCustomerId }),
                meta: {
                    showInDrawer: true,
                    code:'adminCrmCustomerContactManageAdd',
                    name: '客户联系方式添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminCrmCustomerContactManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminCrmCustomerContactManageAdd'
                    }
                }
            },

            {
                path: '/admin/crmCustomerContactManageUpdate',
                component: () => import('./CrmCustomerContactManageUpdatePage.vue'),
                props: route => ({ crmCustomerContactId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminCrmCustomerContactManageUpdate',
                    name: '客户联系方式修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminCrmCustomerContactManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminCrmCustomerContactManageUpdate'
                    }
                }
            },
        ]
    },
]
export default crmCustomerContactAdminRoutes