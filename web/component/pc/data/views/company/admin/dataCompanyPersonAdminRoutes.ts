const dataCompanyPersonAdminRoutes = [
    {
        path: '/admin/dataCompanyPersonManagePage',
        component: () => import('./DataCompanyPersonManagePage.vue'),
        meta: {
            root: true,
            code:'adminDataCompanyPersonManagePage',
            name: '企业个人管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dataCompanyPersonManageAdd',
                component: () => import('./DataCompanyPersonManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyPersonManageAdd',
                    name: '企业个人添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyPersonManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyPersonManageAdd'
                    }
                }
            },

            {
                path: '/admin/dataCompanyPersonManageUpdate',
                component: () => import('./DataCompanyPersonManageUpdatePage.vue'),
                props: route => ({ dataCompanyPersonId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyPersonManageUpdate',
                    name: '企业个人修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyPersonManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyPersonManageUpdate'
                    }
                }
            },
        ]
    },
]
export default dataCompanyPersonAdminRoutes