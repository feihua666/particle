const dataCompanyIprPatentPaymentAdminRoutes = [
    {
        path: '/admin/dataCompanyIprPatentPaymentManagePage',
        component: () => import('./DataCompanyIprPatentPaymentManagePage.vue'),
        meta: {
            root: true,
            code:'adminDataCompanyIprPatentPaymentManagePage',
            name: '企业知识产权专利缴费信息管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dataCompanyIprPatentPaymentManageAdd',
                component: () => import('./DataCompanyIprPatentPaymentManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyIprPatentPaymentManageAdd',
                    name: '企业知识产权专利缴费信息添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyIprPatentPaymentManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyIprPatentPaymentManageAdd'
                    }
                }
            },

            {
                path: '/admin/dataCompanyIprPatentPaymentManageUpdate',
                component: () => import('./DataCompanyIprPatentPaymentManageUpdatePage.vue'),
                props: route => ({ dataCompanyIprPatentPaymentId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyIprPatentPaymentManageUpdate',
                    name: '企业知识产权专利缴费信息修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyIprPatentPaymentManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyIprPatentPaymentManageUpdate'
                    }
                }
            },
        ]
    },
]
export default dataCompanyIprPatentPaymentAdminRoutes