const dataCompanyVcFinancingAdminRoutes = [
    {
        path: '/admin/dataCompanyVcFinancingManagePage',
        component: () => import('./DataCompanyVcFinancingManagePage.vue'),
        meta: {
            root: true,
            code:'adminDataCompanyVcFinancingManagePage',
            name: '企业融资管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dataCompanyVcFinancingManageAdd',
                component: () => import('./DataCompanyVcFinancingManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyVcFinancingManageAdd',
                    name: '企业融资添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyVcFinancingManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyVcFinancingManageAdd'
                    }
                }
            },

            {
                path: '/admin/dataCompanyVcFinancingManageUpdate',
                component: () => import('./DataCompanyVcFinancingManageUpdatePage.vue'),
                props: route => ({ dataCompanyVcFinancingId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyVcFinancingManageUpdate',
                    name: '企业融资修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyVcFinancingManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyVcFinancingManageUpdate'
                    }
                }
            },
        ]
    },
]
export default dataCompanyVcFinancingAdminRoutes