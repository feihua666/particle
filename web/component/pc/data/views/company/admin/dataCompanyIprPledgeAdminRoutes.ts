const dataCompanyIprPledgeAdminRoutes = [
    {
        path: '/admin/dataCompanyIprPledgeManagePage',
        component: () => import('./DataCompanyIprPledgeManagePage.vue'),
        meta: {
            root: true,
            code:'adminDataCompanyIprPledgeManagePage',
            name: '企业知识产权出质管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dataCompanyIprPledgeManageAdd',
                component: () => import('./DataCompanyIprPledgeManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyIprPledgeManageAdd',
                    name: '企业知识产权出质添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyIprPledgeManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyIprPledgeManageAdd'
                    }
                }
            },

            {
                path: '/admin/dataCompanyIprPledgeManageUpdate',
                component: () => import('./DataCompanyIprPledgeManageUpdatePage.vue'),
                props: route => ({ dataCompanyIprPledgeId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyIprPledgeManageUpdate',
                    name: '企业知识产权出质修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyIprPledgeManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyIprPledgeManageUpdate'
                    }
                }
            },
        ]
    },
]
export default dataCompanyIprPledgeAdminRoutes