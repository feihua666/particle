const dataCompanyPunishmentAdminRoutes = [
    {
        path: '/admin/dataCompanyPunishmentManagePage',
        component: () => import('./DataCompanyPunishmentManagePage.vue'),
        meta: {
            root: true,
            code:'adminDataCompanyPunishmentManagePage',
            name: '企业行政处罚管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dataCompanyPunishmentManageAdd',
                component: () => import('./DataCompanyPunishmentManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyPunishmentManageAdd',
                    name: '企业行政处罚添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyPunishmentManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyPunishmentManageAdd'
                    }
                }
            },

            {
                path: '/admin/dataCompanyPunishmentManageUpdate',
                component: () => import('./DataCompanyPunishmentManageUpdatePage.vue'),
                props: route => ({ dataCompanyPunishmentId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyPunishmentManageUpdate',
                    name: '企业行政处罚修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyPunishmentManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyPunishmentManageUpdate'
                    }
                }
            },
        ]
    },
]
export default dataCompanyPunishmentAdminRoutes