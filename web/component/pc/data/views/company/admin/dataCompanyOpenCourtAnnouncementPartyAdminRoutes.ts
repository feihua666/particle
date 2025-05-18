const dataCompanyOpenCourtAnnouncementPartyAdminRoutes = [
    {
        path: '/admin/dataCompanyOpenCourtAnnouncementPartyManagePage',
        component: () => import('./DataCompanyOpenCourtAnnouncementPartyManagePage.vue'),
        meta: {
            root: true,
            code:'adminDataCompanyOpenCourtAnnouncementPartyManagePage',
            name: '企业开庭公告当事人管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dataCompanyOpenCourtAnnouncementPartyManageAdd',
                component: () => import('./DataCompanyOpenCourtAnnouncementPartyManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyOpenCourtAnnouncementPartyManageAdd',
                    name: '企业开庭公告当事人添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyOpenCourtAnnouncementPartyManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyOpenCourtAnnouncementPartyManageAdd'
                    }
                }
            },

            {
                path: '/admin/dataCompanyOpenCourtAnnouncementPartyManageUpdate',
                component: () => import('./DataCompanyOpenCourtAnnouncementPartyManageUpdatePage.vue'),
                props: route => ({ dataCompanyOpenCourtAnnouncementPartyId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyOpenCourtAnnouncementPartyManageUpdate',
                    name: '企业开庭公告当事人修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyOpenCourtAnnouncementPartyManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyOpenCourtAnnouncementPartyManageUpdate'
                    }
                }
            },
        ]
    },
]
export default dataCompanyOpenCourtAnnouncementPartyAdminRoutes