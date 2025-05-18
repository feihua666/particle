const dataCompanyIprPatentPledgeAdminRoutes = [
    {
        path: '/admin/dataCompanyIprPatentPledgeManagePage',
        component: () => import('./DataCompanyIprPatentPledgeManagePage.vue'),
        meta: {
            root: true,
            code:'adminDataCompanyIprPatentPledgeManagePage',
            name: '企业知识产权专利质押信息管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dataCompanyIprPatentPledgeManageAdd',
                component: () => import('./DataCompanyIprPatentPledgeManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyIprPatentPledgeManageAdd',
                    name: '企业知识产权专利质押信息添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyIprPatentPledgeManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyIprPatentPledgeManageAdd'
                    }
                }
            },

            {
                path: '/admin/dataCompanyIprPatentPledgeManageUpdate',
                component: () => import('./DataCompanyIprPatentPledgeManageUpdatePage.vue'),
                props: route => ({ dataCompanyIprPatentPledgeId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyIprPatentPledgeManageUpdate',
                    name: '企业知识产权专利质押信息修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyIprPatentPledgeManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyIprPatentPledgeManageUpdate'
                    }
                }
            },
        ]
    },
]
export default dataCompanyIprPatentPledgeAdminRoutes