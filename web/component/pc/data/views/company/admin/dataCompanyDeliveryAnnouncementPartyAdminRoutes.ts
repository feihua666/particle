const dataCompanyDeliveryAnnouncementPartyAdminRoutes = [
    {
        path: '/admin/dataCompanyDeliveryAnnouncementPartyManagePage',
        component: () => import('./DataCompanyDeliveryAnnouncementPartyManagePage.vue'),
        meta: {
            root: true,
            code:'adminDataCompanyDeliveryAnnouncementPartyManagePage',
            name: '企业送达公告当事人管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dataCompanyDeliveryAnnouncementPartyManageAdd',
                component: () => import('./DataCompanyDeliveryAnnouncementPartyManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyDeliveryAnnouncementPartyManageAdd',
                    name: '企业送达公告当事人添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyDeliveryAnnouncementPartyManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyDeliveryAnnouncementPartyManageAdd'
                    }
                }
            },

            {
                path: '/admin/dataCompanyDeliveryAnnouncementPartyManageUpdate',
                component: () => import('./DataCompanyDeliveryAnnouncementPartyManageUpdatePage.vue'),
                props: route => ({ dataCompanyDeliveryAnnouncementPartyId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyDeliveryAnnouncementPartyManageUpdate',
                    name: '企业送达公告当事人修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyDeliveryAnnouncementPartyManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyDeliveryAnnouncementPartyManageUpdate'
                    }
                }
            },
        ]
    },
]
export default dataCompanyDeliveryAnnouncementPartyAdminRoutes