const dataCompanyMd5AdminRoutes = [
    {
        path: '/admin/dataCompanyMd5ManagePage',
        component: () => import('./DataCompanyMd5ManagePage.vue'),
        meta: {
            root: true,
            code:'adminDataCompanyMd5ManagePage',
            name: '企业md5管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dataCompanyMd5ManageAdd',
                component: () => import('./DataCompanyMd5ManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyMd5ManageAdd',
                    name: '企业md5添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyMd5ManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyMd5ManageAdd'
                    }
                }
            },

            {
                path: '/admin/dataCompanyMd5ManageUpdate',
                component: () => import('./DataCompanyMd5ManageUpdatePage.vue'),
                props: route => ({ dataCompanyMd5Id: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyMd5ManageUpdate',
                    name: '企业md5修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyMd5ManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyMd5ManageUpdate'
                    }
                }
            },
        ]
    },
]
export default dataCompanyMd5AdminRoutes