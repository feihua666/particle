const dataCompanyIprTrademarkTransferPersonAdminRoutes = [
    {
        path: '/admin/dataCompanyIprTrademarkTransferPersonManagePage',
        component: () => import('./DataCompanyIprTrademarkTransferPersonManagePage.vue'),
        meta: {
            root: true,
            code:'adminDataCompanyIprTrademarkTransferPersonManagePage',
            name: '企业知识产权商标转让人管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dataCompanyIprTrademarkTransferPersonManageAdd',
                component: () => import('./DataCompanyIprTrademarkTransferPersonManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyIprTrademarkTransferPersonManageAdd',
                    name: '企业知识产权商标转让人添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyIprTrademarkTransferPersonManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyIprTrademarkTransferPersonManageAdd'
                    }
                }
            },

            {
                path: '/admin/dataCompanyIprTrademarkTransferPersonManageUpdate',
                component: () => import('./DataCompanyIprTrademarkTransferPersonManageUpdatePage.vue'),
                props: route => ({ dataCompanyIprTrademarkTransferPersonId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyIprTrademarkTransferPersonManageUpdate',
                    name: '企业知识产权商标转让人修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyIprTrademarkTransferPersonManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyIprTrademarkTransferPersonManageUpdate'
                    }
                }
            },
        ]
    },
]
export default dataCompanyIprTrademarkTransferPersonAdminRoutes