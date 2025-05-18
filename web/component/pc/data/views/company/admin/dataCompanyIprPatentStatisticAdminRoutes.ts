const dataCompanyIprPatentStatisticAdminRoutes = [
    {
        path: '/admin/dataCompanyIprPatentStatisticManagePage',
        component: () => import('./DataCompanyIprPatentStatisticManagePage.vue'),
        meta: {
            root: true,
            code:'adminDataCompanyIprPatentStatisticManagePage',
            name: '企业知识产权专利统计管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dataCompanyIprPatentStatisticManageAdd',
                component: () => import('./DataCompanyIprPatentStatisticManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyIprPatentStatisticManageAdd',
                    name: '企业知识产权专利统计添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyIprPatentStatisticManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyIprPatentStatisticManageAdd'
                    }
                }
            },

            {
                path: '/admin/dataCompanyIprPatentStatisticManageUpdate',
                component: () => import('./DataCompanyIprPatentStatisticManageUpdatePage.vue'),
                props: route => ({ dataCompanyIprPatentStatisticId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyIprPatentStatisticManageUpdate',
                    name: '企业知识产权专利统计修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyIprPatentStatisticManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyIprPatentStatisticManageUpdate'
                    }
                }
            },
        ]
    },
]
export default dataCompanyIprPatentStatisticAdminRoutes