const dataCompanyIprTrademarkPartyAdminRoutes = [
    {
        path: '/admin/dataCompanyIprTrademarkPartyManagePage',
        component: () => import('./DataCompanyIprTrademarkPartyManagePage.vue'),
        meta: {
            root: true,
            code:'adminDataCompanyIprTrademarkPartyManagePage',
            name: '企业知识产权商标当事人管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dataCompanyIprTrademarkPartyManageAdd',
                component: () => import('./DataCompanyIprTrademarkPartyManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyIprTrademarkPartyManageAdd',
                    name: '企业知识产权商标当事人添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyIprTrademarkPartyManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyIprTrademarkPartyManageAdd'
                    }
                }
            },

            {
                path: '/admin/dataCompanyIprTrademarkPartyManageUpdate',
                component: () => import('./DataCompanyIprTrademarkPartyManageUpdatePage.vue'),
                props: route => ({ dataCompanyIprTrademarkPartyId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyIprTrademarkPartyManageUpdate',
                    name: '企业知识产权商标当事人修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyIprTrademarkPartyManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyIprTrademarkPartyManageUpdate'
                    }
                }
            },
        ]
    },
]
export default dataCompanyIprTrademarkPartyAdminRoutes