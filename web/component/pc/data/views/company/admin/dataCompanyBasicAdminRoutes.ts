const dataCompanyBasicAdminRoutes = [
    {
        path: '/admin/dataCompanyBasicManagePage',
        component: () => import('./DataCompanyBasicManagePage.vue'),
        meta: {
            root: true,
            code:'adminDataCompanyBasicManagePage',
            name: '企业基本信息管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dataCompanyBasicManageAdd',
                component: () => import('./DataCompanyBasicManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyBasicManageAdd',
                    name: '企业基本信息添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyBasicManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyBasicManageAdd'
                    }
                }
            },

            {
                path: '/admin/dataCompanyBasicManageUpdate',
                component: () => import('./DataCompanyBasicManageUpdatePage.vue'),
                props: route => ({ dataCompanyBasicId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyBasicManageUpdate',
                    name: '企业基本信息修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyBasicManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyBasicManageUpdate'
                    }
                }
            },
        ]
    },
]
export default dataCompanyBasicAdminRoutes
