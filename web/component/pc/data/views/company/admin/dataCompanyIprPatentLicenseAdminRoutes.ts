const dataCompanyIprPatentLicenseAdminRoutes = [
    {
        path: '/admin/dataCompanyIprPatentLicenseManagePage',
        component: () => import('./DataCompanyIprPatentLicenseManagePage.vue'),
        meta: {
            root: true,
            code:'adminDataCompanyIprPatentLicenseManagePage',
            name: '企业知识产权专利许可信息管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dataCompanyIprPatentLicenseManageAdd',
                component: () => import('./DataCompanyIprPatentLicenseManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyIprPatentLicenseManageAdd',
                    name: '企业知识产权专利许可信息添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyIprPatentLicenseManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyIprPatentLicenseManageAdd'
                    }
                }
            },

            {
                path: '/admin/dataCompanyIprPatentLicenseManageUpdate',
                component: () => import('./DataCompanyIprPatentLicenseManageUpdatePage.vue'),
                props: route => ({ dataCompanyIprPatentLicenseId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyIprPatentLicenseManageUpdate',
                    name: '企业知识产权专利许可信息修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyIprPatentLicenseManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyIprPatentLicenseManageUpdate'
                    }
                }
            },
        ]
    },
]
export default dataCompanyIprPatentLicenseAdminRoutes