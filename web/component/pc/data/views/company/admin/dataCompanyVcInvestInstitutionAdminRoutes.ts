const dataCompanyVcInvestInstitutionAdminRoutes = [
    {
        path: '/admin/dataCompanyVcInvestInstitutionManagePage',
        component: () => import('./DataCompanyVcInvestInstitutionManagePage.vue'),
        meta: {
            root: true,
            code:'adminDataCompanyVcInvestInstitutionManagePage',
            name: '企业投资机构管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dataCompanyVcInvestInstitutionManageAdd',
                component: () => import('./DataCompanyVcInvestInstitutionManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyVcInvestInstitutionManageAdd',
                    name: '企业投资机构添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyVcInvestInstitutionManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyVcInvestInstitutionManageAdd'
                    }
                }
            },

            {
                path: '/admin/dataCompanyVcInvestInstitutionManageUpdate',
                component: () => import('./DataCompanyVcInvestInstitutionManageUpdatePage.vue'),
                props: route => ({ dataCompanyVcInvestInstitutionId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyVcInvestInstitutionManageUpdate',
                    name: '企业投资机构修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyVcInvestInstitutionManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyVcInvestInstitutionManageUpdate'
                    }
                }
            },
        ]
    },
]
export default dataCompanyVcInvestInstitutionAdminRoutes