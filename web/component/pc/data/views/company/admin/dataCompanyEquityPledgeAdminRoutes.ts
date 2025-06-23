const dataCompanyEquityPledgeAdminRoutes = [
    {
        path: '/admin/dataCompanyEquityPledgeManagePage',
        component: () => import('./DataCompanyEquityPledgeManagePage.vue'),
        meta: {
            root: true,
            code:'adminDataCompanyEquityPledgeManagePage',
            name: '企业股权出质管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dataCompanyEquityPledgeManageAdd',
                component: () => import('./DataCompanyEquityPledgeManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyEquityPledgeManageAdd',
                    name: '企业股权出质添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyEquityPledgeManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyEquityPledgeManageAdd'
                    }
                }
            },

            {
                path: '/admin/dataCompanyEquityPledgeManageUpdate',
                component: () => import('./DataCompanyEquityPledgeManageUpdatePage.vue'),
                props: route => ({ dataCompanyEquityPledgeId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyEquityPledgeManageUpdate',
                    name: '企业股权出质修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyEquityPledgeManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyEquityPledgeManageUpdate'
                    }
                }
            },
        ]
    },
]
export default dataCompanyEquityPledgeAdminRoutes