const dataCompanyIprWorkCopyrightAdminRoutes = [
    {
        path: '/admin/dataCompanyIprWorkCopyrightManagePage',
        component: () => import('./DataCompanyIprWorkCopyrightManagePage.vue'),
        meta: {
            root: true,
            code:'adminDataCompanyIprWorkCopyrightManagePage',
            name: '企业知识产权作品著作管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dataCompanyIprWorkCopyrightManageAdd',
                component: () => import('./DataCompanyIprWorkCopyrightManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyIprWorkCopyrightManageAdd',
                    name: '企业知识产权作品著作添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyIprWorkCopyrightManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyIprWorkCopyrightManageAdd'
                    }
                }
            },

            {
                path: '/admin/dataCompanyIprWorkCopyrightManageUpdate',
                component: () => import('./DataCompanyIprWorkCopyrightManageUpdatePage.vue'),
                props: route => ({ dataCompanyIprWorkCopyrightId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyIprWorkCopyrightManageUpdate',
                    name: '企业知识产权作品著作修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyIprWorkCopyrightManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyIprWorkCopyrightManageUpdate'
                    }
                }
            },
        ]
    },
]
export default dataCompanyIprWorkCopyrightAdminRoutes