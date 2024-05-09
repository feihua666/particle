const crmCompanyAdminRoutes = [
    {
        path: '/admin/crmCompanyManagePage',
        component: () => import('./CrmCompanyManagePage.vue'),
        meta: {
            root: true,
            code:'adminCrmCompanyManagePage',
            name: '客户公司管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/crmCompanyManageAdd',
                component: () => import('./CrmCompanyManageAddPage.vue'),
                props: route => ({ parentId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminCrmCompanyManageAdd',
                    name: '客户公司添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminCrmCompanyManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminCrmCompanyManageAdd'
                    }
                }
            },

            {
                path: '/admin/crmCompanyManageUpdate',
                component: () => import('./CrmCompanyManageUpdatePage.vue'),
                props: route => ({ crmCompanyId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminCrmCompanyManageUpdate',
                    name: '客户公司修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminCrmCompanyManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminCrmCompanyManageUpdate'
                    }
                }
            },
        ]
    },
]
export default crmCompanyAdminRoutes