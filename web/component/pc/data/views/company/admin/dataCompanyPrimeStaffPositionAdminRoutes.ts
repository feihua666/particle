const dataCompanyPrimeStaffPositionAdminRoutes = [
    {
        path: '/admin/dataCompanyPrimeStaffPositionManagePage',
        component: () => import('./DataCompanyPrimeStaffPositionManagePage.vue'),
        meta: {
            root: true,
            code:'adminDataCompanyPrimeStaffPositionManagePage',
            name: '企业主要人员职位管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dataCompanyPrimeStaffPositionManageAdd',
                component: () => import('./DataCompanyPrimeStaffPositionManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyPrimeStaffPositionManageAdd',
                    name: '企业主要人员职位添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyPrimeStaffPositionManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyPrimeStaffPositionManageAdd'
                    }
                }
            },

            {
                path: '/admin/dataCompanyPrimeStaffPositionManageUpdate',
                component: () => import('./DataCompanyPrimeStaffPositionManageUpdatePage.vue'),
                props: route => ({ dataCompanyPrimeStaffPositionId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyPrimeStaffPositionManageUpdate',
                    name: '企业主要人员职位修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyPrimeStaffPositionManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyPrimeStaffPositionManageUpdate'
                    }
                }
            },
        ]
    },
]
export default dataCompanyPrimeStaffPositionAdminRoutes