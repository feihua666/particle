const dataCompanyIprPatentLegalStatusAdminRoutes = [
    {
        path: '/admin/dataCompanyIprPatentLegalStatusManagePage',
        component: () => import('./DataCompanyIprPatentLegalStatusManagePage.vue'),
        meta: {
            root: true,
            code:'adminDataCompanyIprPatentLegalStatusManagePage',
            name: '企业知识产权专利法律状态管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dataCompanyIprPatentLegalStatusManageAdd',
                component: () => import('./DataCompanyIprPatentLegalStatusManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyIprPatentLegalStatusManageAdd',
                    name: '企业知识产权专利法律状态添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyIprPatentLegalStatusManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyIprPatentLegalStatusManageAdd'
                    }
                }
            },

            {
                path: '/admin/dataCompanyIprPatentLegalStatusManageUpdate',
                component: () => import('./DataCompanyIprPatentLegalStatusManageUpdatePage.vue'),
                props: route => ({ dataCompanyIprPatentLegalStatusId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyIprPatentLegalStatusManageUpdate',
                    name: '企业知识产权专利法律状态修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyIprPatentLegalStatusManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyIprPatentLegalStatusManageUpdate'
                    }
                }
            },
        ]
    },
]
export default dataCompanyIprPatentLegalStatusAdminRoutes