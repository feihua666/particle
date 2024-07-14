const dataCompanyMd5IdsAdminRoutes = [
    {
        path: '/admin/dataCompanyMd5IdsManagePage',
        component: () => import('./DataCompanyMd5IdsManagePage.vue'),
        meta: {
            root: true,
            code:'adminDataCompanyMd5IdsManagePage',
            name: '企业md5ids管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dataCompanyMd5IdsManageAdd',
                component: () => import('./DataCompanyMd5IdsManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyMd5IdsManageAdd',
                    name: '企业md5ids添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyMd5IdsManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyMd5IdsManageAdd'
                    }
                }
            },

            {
                path: '/admin/dataCompanyMd5IdsManageUpdate',
                component: () => import('./DataCompanyMd5IdsManageUpdatePage.vue'),
                props: route => ({ dataCompanyMd5IdsId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyMd5IdsManageUpdate',
                    name: '企业md5ids修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyMd5IdsManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyMd5IdsManageUpdate'
                    }
                }
            },
        ]
    },
]
export default dataCompanyMd5IdsAdminRoutes