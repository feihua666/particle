const dataCompanyIprPatentCitedAdminRoutes = [
    {
        path: '/admin/dataCompanyIprPatentCitedManagePage',
        component: () => import('./DataCompanyIprPatentCitedManagePage.vue'),
        meta: {
            root: true,
            code:'adminDataCompanyIprPatentCitedManagePage',
            name: '企业知识产权专利被引证信息管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dataCompanyIprPatentCitedManageAdd',
                component: () => import('./DataCompanyIprPatentCitedManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyIprPatentCitedManageAdd',
                    name: '企业知识产权专利被引证信息添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyIprPatentCitedManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyIprPatentCitedManageAdd'
                    }
                }
            },

            {
                path: '/admin/dataCompanyIprPatentCitedManageUpdate',
                component: () => import('./DataCompanyIprPatentCitedManageUpdatePage.vue'),
                props: route => ({ dataCompanyIprPatentCitedId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyIprPatentCitedManageUpdate',
                    name: '企业知识产权专利被引证信息修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyIprPatentCitedManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyIprPatentCitedManageUpdate'
                    }
                }
            },
        ]
    },
]
export default dataCompanyIprPatentCitedAdminRoutes