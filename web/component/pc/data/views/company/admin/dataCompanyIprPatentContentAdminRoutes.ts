const dataCompanyIprPatentContentAdminRoutes = [
    {
        path: '/admin/dataCompanyIprPatentContentManagePage',
        component: () => import('./DataCompanyIprPatentContentManagePage.vue'),
        meta: {
            root: true,
            code:'adminDataCompanyIprPatentContentManagePage',
            name: '企业知识产权专利内容管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dataCompanyIprPatentContentManageAdd',
                component: () => import('./DataCompanyIprPatentContentManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyIprPatentContentManageAdd',
                    name: '企业知识产权专利内容添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyIprPatentContentManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyIprPatentContentManageAdd'
                    }
                }
            },

            {
                path: '/admin/dataCompanyIprPatentContentManageUpdate',
                component: () => import('./DataCompanyIprPatentContentManageUpdatePage.vue'),
                props: route => ({ dataCompanyIprPatentContentId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyIprPatentContentManageUpdate',
                    name: '企业知识产权专利内容修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyIprPatentContentManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyIprPatentContentManageUpdate'
                    }
                }
            },
        ]
    },
]
export default dataCompanyIprPatentContentAdminRoutes