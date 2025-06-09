const dataCompanyAbnormalAdminRoutes = [
    {
        path: '/admin/dataCompanyAbnormalManagePage',
        component: () => import('./DataCompanyAbnormalManagePage.vue'),
        meta: {
            root: true,
            code:'adminDataCompanyAbnormalManagePage',
            name: '企业经营异常管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dataCompanyAbnormalManageAdd',
                component: () => import('./DataCompanyAbnormalManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyAbnormalManageAdd',
                    name: '企业经营异常添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyAbnormalManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyAbnormalManageAdd'
                    }
                }
            },

            {
                path: '/admin/dataCompanyAbnormalManageUpdate',
                component: () => import('./DataCompanyAbnormalManageUpdatePage.vue'),
                props: route => ({ dataCompanyAbnormalId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyAbnormalManageUpdate',
                    name: '企业经营异常修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyAbnormalManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyAbnormalManageUpdate'
                    }
                }
            },
        ]
    },
]
export default dataCompanyAbnormalAdminRoutes