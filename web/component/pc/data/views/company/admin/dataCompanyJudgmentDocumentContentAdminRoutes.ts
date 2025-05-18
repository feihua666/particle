const dataCompanyJudgmentDocumentContentAdminRoutes = [
    {
        path: '/admin/dataCompanyJudgmentDocumentContentManagePage',
        component: () => import('./DataCompanyJudgmentDocumentContentManagePage.vue'),
        meta: {
            root: true,
            code:'adminDataCompanyJudgmentDocumentContentManagePage',
            name: '企业裁判文书内容管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dataCompanyJudgmentDocumentContentManageAdd',
                component: () => import('./DataCompanyJudgmentDocumentContentManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyJudgmentDocumentContentManageAdd',
                    name: '企业裁判文书内容添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyJudgmentDocumentContentManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyJudgmentDocumentContentManageAdd'
                    }
                }
            },

            {
                path: '/admin/dataCompanyJudgmentDocumentContentManageUpdate',
                component: () => import('./DataCompanyJudgmentDocumentContentManageUpdatePage.vue'),
                props: route => ({ dataCompanyJudgmentDocumentContentId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyJudgmentDocumentContentManageUpdate',
                    name: '企业裁判文书内容修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyJudgmentDocumentContentManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyJudgmentDocumentContentManageUpdate'
                    }
                }
            },
        ]
    },
]
export default dataCompanyJudgmentDocumentContentAdminRoutes