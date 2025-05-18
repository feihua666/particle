const dataCompanyRestrictHighConsumePartyAdminRoutes = [
    {
        path: '/admin/dataCompanyRestrictHighConsumePartyManagePage',
        component: () => import('./DataCompanyRestrictHighConsumePartyManagePage.vue'),
        meta: {
            root: true,
            code:'adminDataCompanyRestrictHighConsumePartyManagePage',
            name: '企业限制高消费当事人管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dataCompanyRestrictHighConsumePartyManageAdd',
                component: () => import('./DataCompanyRestrictHighConsumePartyManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyRestrictHighConsumePartyManageAdd',
                    name: '企业限制高消费当事人添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyRestrictHighConsumePartyManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyRestrictHighConsumePartyManageAdd'
                    }
                }
            },

            {
                path: '/admin/dataCompanyRestrictHighConsumePartyManageUpdate',
                component: () => import('./DataCompanyRestrictHighConsumePartyManageUpdatePage.vue'),
                props: route => ({ dataCompanyRestrictHighConsumePartyId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyRestrictHighConsumePartyManageUpdate',
                    name: '企业限制高消费当事人修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyRestrictHighConsumePartyManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyRestrictHighConsumePartyManageUpdate'
                    }
                }
            },
        ]
    },
]
export default dataCompanyRestrictHighConsumePartyAdminRoutes