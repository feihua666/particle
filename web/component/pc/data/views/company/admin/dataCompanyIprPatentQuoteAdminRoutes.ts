const dataCompanyIprPatentQuoteAdminRoutes = [
    {
        path: '/admin/dataCompanyIprPatentQuoteManagePage',
        component: () => import('./DataCompanyIprPatentQuoteManagePage.vue'),
        meta: {
            root: true,
            code:'adminDataCompanyIprPatentQuoteManagePage',
            name: '企业知识产权专利引证信息管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dataCompanyIprPatentQuoteManageAdd',
                component: () => import('./DataCompanyIprPatentQuoteManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyIprPatentQuoteManageAdd',
                    name: '企业知识产权专利引证信息添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyIprPatentQuoteManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyIprPatentQuoteManageAdd'
                    }
                }
            },

            {
                path: '/admin/dataCompanyIprPatentQuoteManageUpdate',
                component: () => import('./DataCompanyIprPatentQuoteManageUpdatePage.vue'),
                props: route => ({ dataCompanyIprPatentQuoteId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyIprPatentQuoteManageUpdate',
                    name: '企业知识产权专利引证信息修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyIprPatentQuoteManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyIprPatentQuoteManageUpdate'
                    }
                }
            },
        ]
    },
]
export default dataCompanyIprPatentQuoteAdminRoutes