const dataCompanyCourtAnnouncementPartyAdminRoutes = [
    {
        path: '/admin/dataCompanyCourtAnnouncementPartyManagePage',
        component: () => import('./DataCompanyCourtAnnouncementPartyManagePage.vue'),
        meta: {
            root: true,
            code:'adminDataCompanyCourtAnnouncementPartyManagePage',
            name: '企业法院公告当事人管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dataCompanyCourtAnnouncementPartyManageAdd',
                component: () => import('./DataCompanyCourtAnnouncementPartyManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyCourtAnnouncementPartyManageAdd',
                    name: '企业法院公告当事人添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyCourtAnnouncementPartyManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyCourtAnnouncementPartyManageAdd'
                    }
                }
            },

            {
                path: '/admin/dataCompanyCourtAnnouncementPartyManageUpdate',
                component: () => import('./DataCompanyCourtAnnouncementPartyManageUpdatePage.vue'),
                props: route => ({ dataCompanyCourtAnnouncementPartyId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyCourtAnnouncementPartyManageUpdate',
                    name: '企业法院公告当事人修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyCourtAnnouncementPartyManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyCourtAnnouncementPartyManageUpdate'
                    }
                }
            },
        ]
    },
]
export default dataCompanyCourtAnnouncementPartyAdminRoutes