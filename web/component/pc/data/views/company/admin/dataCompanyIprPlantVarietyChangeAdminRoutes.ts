const dataCompanyIprPlantVarietyChangeAdminRoutes = [
    {
        path: '/admin/dataCompanyIprPlantVarietyChangeManagePage',
        component: () => import('./DataCompanyIprPlantVarietyChangeManagePage.vue'),
        meta: {
            root: true,
            code:'adminDataCompanyIprPlantVarietyChangeManagePage',
            name: '企业知识产权植物新品种变更信息管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dataCompanyIprPlantVarietyChangeManageAdd',
                component: () => import('./DataCompanyIprPlantVarietyChangeManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyIprPlantVarietyChangeManageAdd',
                    name: '企业知识产权植物新品种变更信息添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyIprPlantVarietyChangeManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyIprPlantVarietyChangeManageAdd'
                    }
                }
            },

            {
                path: '/admin/dataCompanyIprPlantVarietyChangeManageUpdate',
                component: () => import('./DataCompanyIprPlantVarietyChangeManageUpdatePage.vue'),
                props: route => ({ dataCompanyIprPlantVarietyChangeId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyIprPlantVarietyChangeManageUpdate',
                    name: '企业知识产权植物新品种变更信息修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyIprPlantVarietyChangeManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyIprPlantVarietyChangeManageUpdate'
                    }
                }
            },
        ]
    },
]
export default dataCompanyIprPlantVarietyChangeAdminRoutes