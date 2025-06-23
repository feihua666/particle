const dataCompanyIprTrademarkLicensePersonAdminRoutes = [
    {
        path: '/admin/dataCompanyIprTrademarkLicensePersonManagePage',
        component: () => import('./DataCompanyIprTrademarkLicensePersonManagePage.vue'),
        meta: {
            root: true,
            code:'adminDataCompanyIprTrademarkLicensePersonManagePage',
            name: '企业知识产权商标许可人管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dataCompanyIprTrademarkLicensePersonManageAdd',
                component: () => import('./DataCompanyIprTrademarkLicensePersonManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyIprTrademarkLicensePersonManageAdd',
                    name: '企业知识产权商标许可人添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyIprTrademarkLicensePersonManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyIprTrademarkLicensePersonManageAdd'
                    }
                }
            },

            {
                path: '/admin/dataCompanyIprTrademarkLicensePersonManageUpdate',
                component: () => import('./DataCompanyIprTrademarkLicensePersonManageUpdatePage.vue'),
                props: route => ({ dataCompanyIprTrademarkLicensePersonId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyIprTrademarkLicensePersonManageUpdate',
                    name: '企业知识产权商标许可人修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyIprTrademarkLicensePersonManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyIprTrademarkLicensePersonManageUpdate'
                    }
                }
            },
        ]
    },
]
export default dataCompanyIprTrademarkLicensePersonAdminRoutes