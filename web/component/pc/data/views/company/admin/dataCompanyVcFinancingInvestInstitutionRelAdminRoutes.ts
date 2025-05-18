const dataCompanyVcFinancingInvestInstitutionRelAdminRoutes = [
    {
        path: '/admin/dataCompanyVcFinancingInvestInstitutionRelManagePage',
        component: () => import('./DataCompanyVcFinancingInvestInstitutionRelManagePage.vue'),
        meta: {
            root: true,
            code:'adminDataCompanyVcFinancingInvestInstitutionRelManagePage',
            name: '企业融资历史投资机构关系管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dataCompanyVcFinancingInvestInstitutionRelManageAdd',
                component: () => import('./DataCompanyVcFinancingInvestInstitutionRelManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyVcFinancingInvestInstitutionRelManageAdd',
                    name: '企业融资历史投资机构关系添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyVcFinancingInvestInstitutionRelManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyVcFinancingInvestInstitutionRelManageAdd'
                    }
                }
            },

            {
                path: '/admin/dataCompanyVcFinancingInvestInstitutionRelManageUpdate',
                component: () => import('./DataCompanyVcFinancingInvestInstitutionRelManageUpdatePage.vue'),
                props: route => ({ dataCompanyVcFinancingInvestInstitutionRelId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyVcFinancingInvestInstitutionRelManageUpdate',
                    name: '企业融资历史投资机构关系修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyVcFinancingInvestInstitutionRelManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyVcFinancingInvestInstitutionRelManageUpdate'
                    }
                }
            },
            {
                path: '/admin/dataCompanyVcFinancingInvestInstitutionRelManageCompanyVcInvestInstitutionAssignCompanyVcFinancing',
                component: () => import('./DataCompanyVcFinancingInvestInstitutionRelManageCompanyVcInvestInstitutionAssignCompanyVcFinancingPage.vue'),
                props: route => ({ companyVcInvestInstitutionId: route.query.companyVcInvestInstitutionId, companyVcInvestInstitutionName: route.query.companyVcInvestInstitutionName }),
                meta: {
                    showInDrawer: true,
                    code:'dataCompanyVcFinancingInvestInstitutionRelManageCompanyVcInvestInstitutionAssignCompanyVcFinancing',
                    name: '企业投资机构表分配企业融资表ID',
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '.pt-route-view-popover-drawer-footer'
                    }
                }
            },
            {
                path: '/admin/dataCompanyVcFinancingInvestInstitutionRelManageCompanyVcFinancingAssignCompanyVcInvestInstitution',
                component: () => import('./DataCompanyVcFinancingInvestInstitutionRelManageCompanyVcFinancingAssignCompanyVcInvestInstitutionPage.vue'),
                props: route => ({ companyVcFinancingId: route.query.companyVcFinancingId, companyVcFinancingName: route.query.companyVcFinancingName }),
                meta: {
                    showInDrawer: true,
                    code:'dataCompanyVcFinancingInvestInstitutionRelManageCompanyVcFinancingAssignCompanyVcInvestInstitution',
                    name: '企业融资表ID分配企业投资机构表',
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '.pt-route-view-popover-drawer-footer'
                    }
                }
            },
            {
                path: '/admin/dataCompanyVcFinancingInvestInstitutionRelManageDeleteByCompanyVcInvestInstitutionId',
                component: () => import('./DataCompanyVcFinancingInvestInstitutionRelManageDeleteByCompanyVcInvestInstitutionIdPage.vue'),
                props: route => ({ companyVcInvestInstitutionId: route.query.companyVcInvestInstitutionId, companyVcInvestInstitutionName: route.query.companyVcInvestInstitutionName }),
                meta: {
                    showInDrawer: true,
                    code:'dataCompanyVcFinancingInvestInstitutionRelManageDeleteByCompanyVcInvestInstitutionId',
                    name: '清空企业投资机构表企业融资表ID',
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '.pt-route-view-popover-drawer-footer'
                    }
                }
            },
            {
                path: '/admin/dataCompanyVcFinancingInvestInstitutionRelManageDeleteByCompanyVcFinancingId',
                component: () => import('./DataCompanyVcFinancingInvestInstitutionRelManageDeleteByCompanyVcFinancingIdPage.vue'),
                props: route => ({ companyVcFinancingId: route.query.companyVcFinancingId, companyVcFinancingName: route.query.companyVcFinancingName }),
                meta: {
                    showInDrawer: true,
                    code:'dataCompanyVcFinancingInvestInstitutionRelManageDeleteByCompanyVcFinancingId',
                    name: '清空企业融资表ID企业投资机构表',
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '.pt-route-view-popover-drawer-footer'
                    }
                }
            },
        ]
    },
]
export default dataCompanyVcFinancingInvestInstitutionRelAdminRoutes
