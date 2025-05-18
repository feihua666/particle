const dataCompanyJudgmentDebtorAdminRoutes = [
    {
        path: '/admin/dataCompanyJudgmentDebtorManagePage',
        component: () => import('./DataCompanyJudgmentDebtorManagePage.vue'),
        meta: {
            root: true,
            code:'adminDataCompanyJudgmentDebtorManagePage',
            name: '企业被执行人管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dataCompanyJudgmentDebtorManageAdd',
                component: () => import('./DataCompanyJudgmentDebtorManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyJudgmentDebtorManageAdd',
                    name: '企业被执行人添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyJudgmentDebtorManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyJudgmentDebtorManageAdd'
                    }
                }
            },

            {
                path: '/admin/dataCompanyJudgmentDebtorManageUpdate',
                component: () => import('./DataCompanyJudgmentDebtorManageUpdatePage.vue'),
                props: route => ({ dataCompanyJudgmentDebtorId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyJudgmentDebtorManageUpdate',
                    name: '企业被执行人修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyJudgmentDebtorManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyJudgmentDebtorManageUpdate'
                    }
                }
            },
        ]
    },
]
export default dataCompanyJudgmentDebtorAdminRoutes