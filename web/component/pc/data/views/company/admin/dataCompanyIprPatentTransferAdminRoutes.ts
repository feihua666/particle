const dataCompanyIprPatentTransferAdminRoutes = [
    {
        path: '/admin/dataCompanyIprPatentTransferManagePage',
        component: () => import('./DataCompanyIprPatentTransferManagePage.vue'),
        meta: {
            root: true,
            code:'adminDataCompanyIprPatentTransferManagePage',
            name: '企业知识产权专利转让信息管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dataCompanyIprPatentTransferManageAdd',
                component: () => import('./DataCompanyIprPatentTransferManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyIprPatentTransferManageAdd',
                    name: '企业知识产权专利转让信息添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyIprPatentTransferManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyIprPatentTransferManageAdd'
                    }
                }
            },

            {
                path: '/admin/dataCompanyIprPatentTransferManageUpdate',
                component: () => import('./DataCompanyIprPatentTransferManageUpdatePage.vue'),
                props: route => ({ dataCompanyIprPatentTransferId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyIprPatentTransferManageUpdate',
                    name: '企业知识产权专利转让信息修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyIprPatentTransferManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyIprPatentTransferManageUpdate'
                    }
                }
            },
        ]
    },
]
export default dataCompanyIprPatentTransferAdminRoutes