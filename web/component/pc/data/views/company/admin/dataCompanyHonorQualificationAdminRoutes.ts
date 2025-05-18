const dataCompanyHonorQualificationAdminRoutes = [
    {
        path: '/admin/dataCompanyHonorQualificationManagePage',
        component: () => import('./DataCompanyHonorQualificationManagePage.vue'),
        meta: {
            root: true,
            code:'adminDataCompanyHonorQualificationManagePage',
            name: '企业荣誉资质管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dataCompanyHonorQualificationManageAdd',
                component: () => import('./DataCompanyHonorQualificationManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyHonorQualificationManageAdd',
                    name: '企业荣誉资质添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyHonorQualificationManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyHonorQualificationManageAdd'
                    }
                }
            },

            {
                path: '/admin/dataCompanyHonorQualificationManageUpdate',
                component: () => import('./DataCompanyHonorQualificationManageUpdatePage.vue'),
                props: route => ({ dataCompanyHonorQualificationId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyHonorQualificationManageUpdate',
                    name: '企业荣誉资质修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyHonorQualificationManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyHonorQualificationManageUpdate'
                    }
                }
            },
        ]
    },
]
export default dataCompanyHonorQualificationAdminRoutes