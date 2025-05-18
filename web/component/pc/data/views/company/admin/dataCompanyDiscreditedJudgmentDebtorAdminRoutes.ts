const dataCompanyDiscreditedJudgmentDebtorAdminRoutes = [
    {
        path: '/admin/dataCompanyDiscreditedJudgmentDebtorManagePage',
        component: () => import('./DataCompanyDiscreditedJudgmentDebtorManagePage.vue'),
        meta: {
            root: true,
            code:'adminDataCompanyDiscreditedJudgmentDebtorManagePage',
            name: '企业失信被执行人管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dataCompanyDiscreditedJudgmentDebtorManageAdd',
                component: () => import('./DataCompanyDiscreditedJudgmentDebtorManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyDiscreditedJudgmentDebtorManageAdd',
                    name: '企业失信被执行人添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyDiscreditedJudgmentDebtorManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyDiscreditedJudgmentDebtorManageAdd'
                    }
                }
            },

            {
                path: '/admin/dataCompanyDiscreditedJudgmentDebtorManageUpdate',
                component: () => import('./DataCompanyDiscreditedJudgmentDebtorManageUpdatePage.vue'),
                props: route => ({ dataCompanyDiscreditedJudgmentDebtorId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyDiscreditedJudgmentDebtorManageUpdate',
                    name: '企业失信被执行人修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyDiscreditedJudgmentDebtorManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyDiscreditedJudgmentDebtorManageUpdate'
                    }
                }
            },
        ]
    },
]
export default dataCompanyDiscreditedJudgmentDebtorAdminRoutes