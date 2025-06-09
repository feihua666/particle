const dataCompanyStatisticAdminRoutes = [
    {
        path: '/admin/dataCompanyStatisticManagePage',
        component: () => import('./DataCompanyStatisticManagePage.vue'),
        meta: {
            root: true,
            code:'adminDataCompanyStatisticManagePage',
            name: '企业统计管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dataCompanyStatisticManageAdd',
                component: () => import('./DataCompanyStatisticManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyStatisticManageAdd',
                    name: '企业统计添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyStatisticManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyStatisticManageAdd'
                    }
                }
            },

            {
                path: '/admin/dataCompanyStatisticManageUpdate',
                component: () => import('./DataCompanyStatisticManageUpdatePage.vue'),
                props: route => ({ dataCompanyStatisticId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyStatisticManageUpdate',
                    name: '企业统计修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyStatisticManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyStatisticManageUpdate'
                    }
                }
            },
        ]
    },
]
export default dataCompanyStatisticAdminRoutes