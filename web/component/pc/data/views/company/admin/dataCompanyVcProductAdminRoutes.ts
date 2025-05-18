const dataCompanyVcProductAdminRoutes = [
    {
        path: '/admin/dataCompanyVcProductManagePage',
        component: () => import('./DataCompanyVcProductManagePage.vue'),
        meta: {
            root: true,
            code:'adminDataCompanyVcProductManagePage',
            name: '企业融资产品管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dataCompanyVcProductManageAdd',
                component: () => import('./DataCompanyVcProductManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyVcProductManageAdd',
                    name: '企业融资产品添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyVcProductManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyVcProductManageAdd'
                    }
                }
            },

            {
                path: '/admin/dataCompanyVcProductManageUpdate',
                component: () => import('./DataCompanyVcProductManageUpdatePage.vue'),
                props: route => ({ dataCompanyVcProductId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyVcProductManageUpdate',
                    name: '企业融资产品修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyVcProductManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyVcProductManageUpdate'
                    }
                }
            },
        ]
    },
]
export default dataCompanyVcProductAdminRoutes