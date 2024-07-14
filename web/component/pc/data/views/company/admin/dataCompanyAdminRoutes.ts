const dataCompanyAdminRoutes = [
    {
        path: '/admin/dataCompanyManagePage',
        component: () => import('./DataCompanyManagePage.vue'),
        meta: {
            root: true,
            code:'adminDataCompanyManagePage',
            name: '企业管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dataCompanyManageAdd',
                component: () => import('./DataCompanyManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyManageAdd',
                    name: '企业添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyManageAdd'
                    }
                }
            },

            {
                path: '/admin/dataCompanyManageUpdate',
                component: () => import('./DataCompanyManageUpdatePage.vue'),
                props: route => ({ dataCompanyId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyManageUpdate',
                    name: '企业修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyManageUpdate'
                    }
                }
            },
        ]
    },
]
export default dataCompanyAdminRoutes