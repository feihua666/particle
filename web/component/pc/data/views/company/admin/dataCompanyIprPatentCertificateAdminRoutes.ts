const dataCompanyIprPatentCertificateAdminRoutes = [
    {
        path: '/admin/dataCompanyIprPatentCertificateManagePage',
        component: () => import('./DataCompanyIprPatentCertificateManagePage.vue'),
        meta: {
            root: true,
            code:'adminDataCompanyIprPatentCertificateManagePage',
            name: '企业知识产权专利证书信息管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dataCompanyIprPatentCertificateManageAdd',
                component: () => import('./DataCompanyIprPatentCertificateManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyIprPatentCertificateManageAdd',
                    name: '企业知识产权专利证书信息添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyIprPatentCertificateManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyIprPatentCertificateManageAdd'
                    }
                }
            },

            {
                path: '/admin/dataCompanyIprPatentCertificateManageUpdate',
                component: () => import('./DataCompanyIprPatentCertificateManageUpdatePage.vue'),
                props: route => ({ dataCompanyIprPatentCertificateId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyIprPatentCertificateManageUpdate',
                    name: '企业知识产权专利证书信息修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyIprPatentCertificateManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyIprPatentCertificateManageUpdate'
                    }
                }
            },
        ]
    },
]
export default dataCompanyIprPatentCertificateAdminRoutes