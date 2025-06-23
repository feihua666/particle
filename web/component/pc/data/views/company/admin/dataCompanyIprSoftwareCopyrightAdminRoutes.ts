const dataCompanyIprSoftwareCopyrightAdminRoutes = [
    {
        path: '/admin/dataCompanyIprSoftwareCopyrightManagePage',
        component: () => import('./DataCompanyIprSoftwareCopyrightManagePage.vue'),
        meta: {
            root: true,
            code:'adminDataCompanyIprSoftwareCopyrightManagePage',
            name: '企业知识产权软件著作管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dataCompanyIprSoftwareCopyrightManageAdd',
                component: () => import('./DataCompanyIprSoftwareCopyrightManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyIprSoftwareCopyrightManageAdd',
                    name: '企业知识产权软件著作添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyIprSoftwareCopyrightManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyIprSoftwareCopyrightManageAdd'
                    }
                }
            },

            {
                path: '/admin/dataCompanyIprSoftwareCopyrightManageUpdate',
                component: () => import('./DataCompanyIprSoftwareCopyrightManageUpdatePage.vue'),
                props: route => ({ dataCompanyIprSoftwareCopyrightId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyIprSoftwareCopyrightManageUpdate',
                    name: '企业知识产权软件著作修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyIprSoftwareCopyrightManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyIprSoftwareCopyrightManageUpdate'
                    }
                }
            },
        ]
    },
]
export default dataCompanyIprSoftwareCopyrightAdminRoutes