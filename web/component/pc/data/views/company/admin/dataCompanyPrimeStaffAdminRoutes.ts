const dataCompanyPrimeStaffAdminRoutes = [
    {
        path: '/admin/dataCompanyPrimeStaffManagePage',
        component: () => import('./DataCompanyPrimeStaffManagePage.vue'),
        meta: {
            root: true,
            code:'adminDataCompanyPrimeStaffManagePage',
            name: '企业主要人员管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dataCompanyPrimeStaffManageAdd',
                component: () => import('./DataCompanyPrimeStaffManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyPrimeStaffManageAdd',
                    name: '企业主要人员添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyPrimeStaffManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyPrimeStaffManageAdd'
                    }
                }
            },

            {
                path: '/admin/dataCompanyPrimeStaffManageUpdate',
                component: () => import('./DataCompanyPrimeStaffManageUpdatePage.vue'),
                props: route => ({ dataCompanyPrimeStaffId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyPrimeStaffManageUpdate',
                    name: '企业主要人员修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyPrimeStaffManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyPrimeStaffManageUpdate'
                    }
                }
            },
        ]
    },
]
export default dataCompanyPrimeStaffAdminRoutes