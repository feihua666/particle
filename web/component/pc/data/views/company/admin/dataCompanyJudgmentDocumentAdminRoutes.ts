const dataCompanyJudgmentDocumentAdminRoutes = [
    {
        path: '/admin/dataCompanyJudgmentDocumentManagePage',
        component: () => import('./DataCompanyJudgmentDocumentManagePage.vue'),
        meta: {
            root: true,
            code:'adminDataCompanyJudgmentDocumentManagePage',
            name: '企业裁判文书管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dataCompanyJudgmentDocumentManageAdd',
                component: () => import('./DataCompanyJudgmentDocumentManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyJudgmentDocumentManageAdd',
                    name: '企业裁判文书添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyJudgmentDocumentManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyJudgmentDocumentManageAdd'
                    }
                }
            },

            {
                path: '/admin/dataCompanyJudgmentDocumentManageUpdate',
                component: () => import('./DataCompanyJudgmentDocumentManageUpdatePage.vue'),
                props: route => ({ dataCompanyJudgmentDocumentId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyJudgmentDocumentManageUpdate',
                    name: '企业裁判文书修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyJudgmentDocumentManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyJudgmentDocumentManageUpdate'
                    }
                }
            },
        ]
    },
]
export default dataCompanyJudgmentDocumentAdminRoutes