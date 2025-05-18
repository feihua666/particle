const dataCompanyIprPatentFamilyAdminRoutes = [
    {
        path: '/admin/dataCompanyIprPatentFamilyManagePage',
        component: () => import('./DataCompanyIprPatentFamilyManagePage.vue'),
        meta: {
            root: true,
            code:'adminDataCompanyIprPatentFamilyManagePage',
            name: '企业知识产权专利同族信息管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dataCompanyIprPatentFamilyManageAdd',
                component: () => import('./DataCompanyIprPatentFamilyManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyIprPatentFamilyManageAdd',
                    name: '企业知识产权专利同族信息添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyIprPatentFamilyManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyIprPatentFamilyManageAdd'
                    }
                }
            },

            {
                path: '/admin/dataCompanyIprPatentFamilyManageUpdate',
                component: () => import('./DataCompanyIprPatentFamilyManageUpdatePage.vue'),
                props: route => ({ dataCompanyIprPatentFamilyId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyIprPatentFamilyManageUpdate',
                    name: '企业知识产权专利同族信息修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyIprPatentFamilyManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyIprPatentFamilyManageUpdate'
                    }
                }
            },
        ]
    },
]
export default dataCompanyIprPatentFamilyAdminRoutes