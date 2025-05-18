const dataCompanyVcProductCompetitiveProductRelAdminRoutes = [
    {
        path: '/admin/dataCompanyVcProductCompetitiveProductRelManagePage',
        component: () => import('./DataCompanyVcProductCompetitiveProductRelManagePage.vue'),
        meta: {
            root: true,
            code:'adminDataCompanyVcProductCompetitiveProductRelManagePage',
            name: '企业融资产品竞品关系管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dataCompanyVcProductCompetitiveProductRelManageAdd',
                component: () => import('./DataCompanyVcProductCompetitiveProductRelManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyVcProductCompetitiveProductRelManageAdd',
                    name: '企业融资产品竞品关系添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyVcProductCompetitiveProductRelManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyVcProductCompetitiveProductRelManageAdd'
                    }
                }
            },

            {
                path: '/admin/dataCompanyVcProductCompetitiveProductRelManageUpdate',
                component: () => import('./DataCompanyVcProductCompetitiveProductRelManageUpdatePage.vue'),
                props: route => ({ dataCompanyVcProductCompetitiveProductRelId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyVcProductCompetitiveProductRelManageUpdate',
                    name: '企业融资产品竞品关系修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyVcProductCompetitiveProductRelManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyVcProductCompetitiveProductRelManageUpdate'
                    }
                }
            },
            {
                path: '/admin/dataCompanyVcProductCompetitiveProductRelManageCompanyVcCompetitiveProductAssignCompanyVcProduct',
                component: () => import('./DataCompanyVcProductCompetitiveProductRelManageCompanyVcCompetitiveProductAssignCompanyVcProductPage.vue'),
                props: route => ({ companyVcCompetitiveProductId: route.query.companyVcCompetitiveProductId, companyVcCompetitiveProductName: route.query.companyVcCompetitiveProductName }),
                meta: {
                    showInDrawer: true,
                    code:'dataCompanyVcProductCompetitiveProductRelManageCompanyVcCompetitiveProductAssignCompanyVcProduct',
                    name: '企业竞品分配企业融资产品表ID',
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '.pt-route-view-popover-drawer-footer'
                    }
                }
            },
            {
                path: '/admin/dataCompanyVcProductCompetitiveProductRelManageCompanyVcProductAssignCompanyVcCompetitiveProduct',
                component: () => import('./DataCompanyVcProductCompetitiveProductRelManageCompanyVcProductAssignCompanyVcCompetitiveProductPage.vue'),
                props: route => ({ companyVcProductId: route.query.companyVcProductId, companyVcProductName: route.query.companyVcProductName }),
                meta: {
                    showInDrawer: true,
                    code:'dataCompanyVcProductCompetitiveProductRelManageCompanyVcProductAssignCompanyVcCompetitiveProduct',
                    name: '企业融资产品表ID分配企业竞品',
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '.pt-route-view-popover-drawer-footer'
                    }
                }
            },
            {
                path: '/admin/dataCompanyVcProductCompetitiveProductRelManageDeleteByCompanyVcCompetitiveProductId',
                component: () => import('./DataCompanyVcProductCompetitiveProductRelManageDeleteByCompanyVcCompetitiveProductIdPage.vue'),
                props: route => ({ companyVcCompetitiveProductId: route.query.companyVcCompetitiveProductId, companyVcCompetitiveProductName: route.query.companyVcCompetitiveProductName }),
                meta: {
                    showInDrawer: true,
                    code:'dataCompanyVcProductCompetitiveProductRelManageDeleteByCompanyVcCompetitiveProductId',
                    name: '清空企业竞品企业融资产品表ID',
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '.pt-route-view-popover-drawer-footer'
                    }
                }
            },
            {
                path: '/admin/dataCompanyVcProductCompetitiveProductRelManageDeleteByCompanyVcProductId',
                component: () => import('./DataCompanyVcProductCompetitiveProductRelManageDeleteByCompanyVcProductIdPage.vue'),
                props: route => ({ companyVcProductId: route.query.companyVcProductId, companyVcProductName: route.query.companyVcProductName }),
                meta: {
                    showInDrawer: true,
                    code:'dataCompanyVcProductCompetitiveProductRelManageDeleteByCompanyVcProductId',
                    name: '清空企业融资产品表ID企业竞品',
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '.pt-route-view-popover-drawer-footer'
                    }
                }
            },
        ]
    },
]
export default dataCompanyVcProductCompetitiveProductRelAdminRoutes