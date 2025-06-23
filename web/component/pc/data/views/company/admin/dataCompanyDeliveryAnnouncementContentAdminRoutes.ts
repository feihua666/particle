const dataCompanyDeliveryAnnouncementContentAdminRoutes = [
    {
        path: '/admin/dataCompanyDeliveryAnnouncementContentManagePage',
        component: () => import('./DataCompanyDeliveryAnnouncementContentManagePage.vue'),
        meta: {
            root: true,
            code:'adminDataCompanyDeliveryAnnouncementContentManagePage',
            name: '企业送达公告内容管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dataCompanyDeliveryAnnouncementContentManageAdd',
                component: () => import('./DataCompanyDeliveryAnnouncementContentManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyDeliveryAnnouncementContentManageAdd',
                    name: '企业送达公告内容添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyDeliveryAnnouncementContentManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyDeliveryAnnouncementContentManageAdd'
                    }
                }
            },

            {
                path: '/admin/dataCompanyDeliveryAnnouncementContentManageUpdate',
                component: () => import('./DataCompanyDeliveryAnnouncementContentManageUpdatePage.vue'),
                props: route => ({ dataCompanyDeliveryAnnouncementContentId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyDeliveryAnnouncementContentManageUpdate',
                    name: '企业送达公告内容修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyDeliveryAnnouncementContentManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyDeliveryAnnouncementContentManageUpdate'
                    }
                }
            },
        ]
    },
]
export default dataCompanyDeliveryAnnouncementContentAdminRoutes