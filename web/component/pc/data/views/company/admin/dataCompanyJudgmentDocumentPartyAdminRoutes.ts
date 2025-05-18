const dataCompanyJudgmentDocumentPartyAdminRoutes = [
    {
        path: '/admin/dataCompanyJudgmentDocumentPartyManagePage',
        component: () => import('./DataCompanyJudgmentDocumentPartyManagePage.vue'),
        meta: {
            root: true,
            code:'adminDataCompanyJudgmentDocumentPartyManagePage',
            name: '企业裁判文书当事人管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dataCompanyJudgmentDocumentPartyManageAdd',
                component: () => import('./DataCompanyJudgmentDocumentPartyManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyJudgmentDocumentPartyManageAdd',
                    name: '企业裁判文书当事人添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyJudgmentDocumentPartyManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyJudgmentDocumentPartyManageAdd'
                    }
                }
            },

            {
                path: '/admin/dataCompanyJudgmentDocumentPartyManageUpdate',
                component: () => import('./DataCompanyJudgmentDocumentPartyManageUpdatePage.vue'),
                props: route => ({ dataCompanyJudgmentDocumentPartyId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyJudgmentDocumentPartyManageUpdate',
                    name: '企业裁判文书当事人修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyJudgmentDocumentPartyManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyJudgmentDocumentPartyManageUpdate'
                    }
                }
            },
        ]
    },
]
export default dataCompanyJudgmentDocumentPartyAdminRoutes