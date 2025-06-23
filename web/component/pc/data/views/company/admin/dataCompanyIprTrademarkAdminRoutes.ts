const dataCompanyIprTrademarkAdminRoutes = [
    {
        path: '/admin/dataCompanyIprTrademarkManagePage',
        component: () => import('./DataCompanyIprTrademarkManagePage.vue'),
        meta: {
            root: true,
            code:'adminDataCompanyIprTrademarkManagePage',
            name: '企业知识产权商标管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dataCompanyIprTrademarkManageAdd',
                component: () => import('./DataCompanyIprTrademarkManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyIprTrademarkManageAdd',
                    name: '企业知识产权商标添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyIprTrademarkManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyIprTrademarkManageAdd'
                    }
                }
            },

            {
                path: '/admin/dataCompanyIprTrademarkManageUpdate',
                component: () => import('./DataCompanyIprTrademarkManageUpdatePage.vue'),
                props: route => ({ dataCompanyIprTrademarkId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyIprTrademarkManageUpdate',
                    name: '企业知识产权商标修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyIprTrademarkManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyIprTrademarkManageUpdate'
                    }
                }
            },
        ]
    },
]
export default dataCompanyIprTrademarkAdminRoutes