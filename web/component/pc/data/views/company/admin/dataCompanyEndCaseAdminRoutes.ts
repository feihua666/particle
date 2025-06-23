const dataCompanyEndCaseAdminRoutes = [
    {
        path: '/admin/dataCompanyEndCaseManagePage',
        component: () => import('./DataCompanyEndCaseManagePage.vue'),
        meta: {
            root: true,
            code:'adminDataCompanyEndCaseManagePage',
            name: '企业终本案件管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dataCompanyEndCaseManageAdd',
                component: () => import('./DataCompanyEndCaseManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyEndCaseManageAdd',
                    name: '企业终本案件添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyEndCaseManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyEndCaseManageAdd'
                    }
                }
            },

            {
                path: '/admin/dataCompanyEndCaseManageUpdate',
                component: () => import('./DataCompanyEndCaseManageUpdatePage.vue'),
                props: route => ({ dataCompanyEndCaseId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyEndCaseManageUpdate',
                    name: '企业终本案件修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyEndCaseManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyEndCaseManageUpdate'
                    }
                }
            },
        ]
    },
]
export default dataCompanyEndCaseAdminRoutes