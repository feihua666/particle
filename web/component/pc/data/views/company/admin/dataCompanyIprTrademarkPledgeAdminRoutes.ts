const dataCompanyIprTrademarkPledgeAdminRoutes = [
    {
        path: '/admin/dataCompanyIprTrademarkPledgeManagePage',
        component: () => import('./DataCompanyIprTrademarkPledgeManagePage.vue'),
        meta: {
            root: true,
            code:'adminDataCompanyIprTrademarkPledgeManagePage',
            name: '企业知识产权商标质押信息管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dataCompanyIprTrademarkPledgeManageAdd',
                component: () => import('./DataCompanyIprTrademarkPledgeManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyIprTrademarkPledgeManageAdd',
                    name: '企业知识产权商标质押信息添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyIprTrademarkPledgeManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyIprTrademarkPledgeManageAdd'
                    }
                }
            },

            {
                path: '/admin/dataCompanyIprTrademarkPledgeManageUpdate',
                component: () => import('./DataCompanyIprTrademarkPledgeManageUpdatePage.vue'),
                props: route => ({ dataCompanyIprTrademarkPledgeId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyIprTrademarkPledgeManageUpdate',
                    name: '企业知识产权商标质押信息修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyIprTrademarkPledgeManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyIprTrademarkPledgeManageUpdate'
                    }
                }
            },
        ]
    },
]
export default dataCompanyIprTrademarkPledgeAdminRoutes