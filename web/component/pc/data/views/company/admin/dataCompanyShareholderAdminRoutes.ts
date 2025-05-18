const dataCompanyShareholderAdminRoutes = [
    {
        path: '/admin/dataCompanyShareholderManagePage',
        component: () => import('./DataCompanyShareholderManagePage.vue'),
        meta: {
            root: true,
            code:'adminDataCompanyShareholderManagePage',
            name: '企业股东管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dataCompanyShareholderManageAdd',
                component: () => import('./DataCompanyShareholderManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyShareholderManageAdd',
                    name: '企业股东添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyShareholderManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyShareholderManageAdd'
                    }
                }
            },

            {
                path: '/admin/dataCompanyShareholderManageUpdate',
                component: () => import('./DataCompanyShareholderManageUpdatePage.vue'),
                props: route => ({ dataCompanyShareholderId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyShareholderManageUpdate',
                    name: '企业股东修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyShareholderManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyShareholderManageUpdate'
                    }
                }
            },
        ]
    },
]
export default dataCompanyShareholderAdminRoutes