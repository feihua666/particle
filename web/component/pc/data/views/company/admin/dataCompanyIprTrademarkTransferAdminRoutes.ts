const dataCompanyIprTrademarkTransferAdminRoutes = [
    {
        path: '/admin/dataCompanyIprTrademarkTransferManagePage',
        component: () => import('./DataCompanyIprTrademarkTransferManagePage.vue'),
        meta: {
            root: true,
            code:'adminDataCompanyIprTrademarkTransferManagePage',
            name: '企业知识产权商标转让信息管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dataCompanyIprTrademarkTransferManageAdd',
                component: () => import('./DataCompanyIprTrademarkTransferManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyIprTrademarkTransferManageAdd',
                    name: '企业知识产权商标转让信息添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyIprTrademarkTransferManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyIprTrademarkTransferManageAdd'
                    }
                }
            },

            {
                path: '/admin/dataCompanyIprTrademarkTransferManageUpdate',
                component: () => import('./DataCompanyIprTrademarkTransferManageUpdatePage.vue'),
                props: route => ({ dataCompanyIprTrademarkTransferId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyIprTrademarkTransferManageUpdate',
                    name: '企业知识产权商标转让信息修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyIprTrademarkTransferManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyIprTrademarkTransferManageUpdate'
                    }
                }
            },
        ]
    },
]
export default dataCompanyIprTrademarkTransferAdminRoutes