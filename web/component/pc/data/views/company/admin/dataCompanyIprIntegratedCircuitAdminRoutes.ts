const dataCompanyIprIntegratedCircuitAdminRoutes = [
    {
        path: '/admin/dataCompanyIprIntegratedCircuitManagePage',
        component: () => import('./DataCompanyIprIntegratedCircuitManagePage.vue'),
        meta: {
            root: true,
            code:'adminDataCompanyIprIntegratedCircuitManagePage',
            name: '企业知识产权集成电路管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dataCompanyIprIntegratedCircuitManageAdd',
                component: () => import('./DataCompanyIprIntegratedCircuitManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyIprIntegratedCircuitManageAdd',
                    name: '企业知识产权集成电路添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyIprIntegratedCircuitManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyIprIntegratedCircuitManageAdd'
                    }
                }
            },

            {
                path: '/admin/dataCompanyIprIntegratedCircuitManageUpdate',
                component: () => import('./DataCompanyIprIntegratedCircuitManageUpdatePage.vue'),
                props: route => ({ dataCompanyIprIntegratedCircuitId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyIprIntegratedCircuitManageUpdate',
                    name: '企业知识产权集成电路修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyIprIntegratedCircuitManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyIprIntegratedCircuitManageUpdate'
                    }
                }
            },
        ]
    },
]
export default dataCompanyIprIntegratedCircuitAdminRoutes