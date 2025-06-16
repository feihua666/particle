const dataCompanyIprPatentPartyAdminRoutes = [
    {
        path: '/admin/dataCompanyIprPatentPartyManagePage',
        component: () => import('./DataCompanyIprPatentPartyManagePage.vue'),
        meta: {
            root: true,
            code:'adminDataCompanyIprPatentPartyManagePage',
            name: '企业知识产权专利当事人管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dataCompanyIprPatentPartyManageAdd',
                component: () => import('./DataCompanyIprPatentPartyManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyIprPatentPartyManageAdd',
                    name: '企业知识产权专利当事人添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyIprPatentPartyManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyIprPatentPartyManageAdd'
                    }
                }
            },

            {
                path: '/admin/dataCompanyIprPatentPartyManageUpdate',
                component: () => import('./DataCompanyIprPatentPartyManageUpdatePage.vue'),
                props: route => ({ dataCompanyIprPatentPartyId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyIprPatentPartyManageUpdate',
                    name: '企业知识产权专利当事人修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyIprPatentPartyManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyIprPatentPartyManageUpdate'
                    }
                }
            },
        ]
    },
]
export default dataCompanyIprPatentPartyAdminRoutes
