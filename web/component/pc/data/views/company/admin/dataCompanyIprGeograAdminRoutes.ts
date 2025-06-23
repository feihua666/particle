const dataCompanyIprGeograAdminRoutes = [
    {
        path: '/admin/dataCompanyIprGeograManagePage',
        component: () => import('./DataCompanyIprGeograManagePage.vue'),
        meta: {
            root: true,
            code:'adminDataCompanyIprGeograManagePage',
            name: '企业知识产权地理标识管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dataCompanyIprGeograManageAdd',
                component: () => import('./DataCompanyIprGeograManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyIprGeograManageAdd',
                    name: '企业知识产权地理标识添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyIprGeograManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyIprGeograManageAdd'
                    }
                }
            },

            {
                path: '/admin/dataCompanyIprGeograManageUpdate',
                component: () => import('./DataCompanyIprGeograManageUpdatePage.vue'),
                props: route => ({ dataCompanyIprGeograId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyIprGeograManageUpdate',
                    name: '企业知识产权地理标识修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyIprGeograManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyIprGeograManageUpdate'
                    }
                }
            },
        ]
    },
]
export default dataCompanyIprGeograAdminRoutes