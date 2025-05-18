const dataCompanyRestrictHighConsumeAdminRoutes = [
    {
        path: '/admin/dataCompanyRestrictHighConsumeManagePage',
        component: () => import('./DataCompanyRestrictHighConsumeManagePage.vue'),
        meta: {
            root: true,
            code:'adminDataCompanyRestrictHighConsumeManagePage',
            name: '企业限制高消费管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dataCompanyRestrictHighConsumeManageAdd',
                component: () => import('./DataCompanyRestrictHighConsumeManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyRestrictHighConsumeManageAdd',
                    name: '企业限制高消费添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyRestrictHighConsumeManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyRestrictHighConsumeManageAdd'
                    }
                }
            },

            {
                path: '/admin/dataCompanyRestrictHighConsumeManageUpdate',
                component: () => import('./DataCompanyRestrictHighConsumeManageUpdatePage.vue'),
                props: route => ({ dataCompanyRestrictHighConsumeId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyRestrictHighConsumeManageUpdate',
                    name: '企业限制高消费修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyRestrictHighConsumeManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyRestrictHighConsumeManageUpdate'
                    }
                }
            },
        ]
    },
]
export default dataCompanyRestrictHighConsumeAdminRoutes