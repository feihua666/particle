const dataCompanyIprPatentAdminRoutes = [
    {
        path: '/admin/dataCompanyIprPatentManagePage',
        component: () => import('./DataCompanyIprPatentManagePage.vue'),
        meta: {
            root: true,
            code:'adminDataCompanyIprPatentManagePage',
            name: '企业知识产权专利管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dataCompanyIprPatentManageAdd',
                component: () => import('./DataCompanyIprPatentManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyIprPatentManageAdd',
                    name: '企业知识产权专利添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyIprPatentManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyIprPatentManageAdd'
                    }
                }
            },

            {
                path: '/admin/dataCompanyIprPatentManageUpdate',
                component: () => import('./DataCompanyIprPatentManageUpdatePage.vue'),
                props: route => ({ dataCompanyIprPatentId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyIprPatentManageUpdate',
                    name: '企业知识产权专利修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyIprPatentManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyIprPatentManageUpdate'
                    }
                }
            },
        ]
    },
]
export default dataCompanyIprPatentAdminRoutes