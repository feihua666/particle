const dataCompanyIprPlantVarietyAdminRoutes = [
    {
        path: '/admin/dataCompanyIprPlantVarietyManagePage',
        component: () => import('./DataCompanyIprPlantVarietyManagePage.vue'),
        meta: {
            root: true,
            code:'adminDataCompanyIprPlantVarietyManagePage',
            name: '企业知识产权植物新品种管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dataCompanyIprPlantVarietyManageAdd',
                component: () => import('./DataCompanyIprPlantVarietyManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyIprPlantVarietyManageAdd',
                    name: '企业知识产权植物新品种添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyIprPlantVarietyManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyIprPlantVarietyManageAdd'
                    }
                }
            },

            {
                path: '/admin/dataCompanyIprPlantVarietyManageUpdate',
                component: () => import('./DataCompanyIprPlantVarietyManageUpdatePage.vue'),
                props: route => ({ dataCompanyIprPlantVarietyId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyIprPlantVarietyManageUpdate',
                    name: '企业知识产权植物新品种修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyIprPlantVarietyManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyIprPlantVarietyManageUpdate'
                    }
                }
            },
        ]
    },
]
export default dataCompanyIprPlantVarietyAdminRoutes