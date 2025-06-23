const dataCompanyDeliveryAnnouncementAdminRoutes = [
    {
        path: '/admin/dataCompanyDeliveryAnnouncementManagePage',
        component: () => import('./DataCompanyDeliveryAnnouncementManagePage.vue'),
        meta: {
            root: true,
            code:'adminDataCompanyDeliveryAnnouncementManagePage',
            name: '企业送达公告管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dataCompanyDeliveryAnnouncementManageAdd',
                component: () => import('./DataCompanyDeliveryAnnouncementManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyDeliveryAnnouncementManageAdd',
                    name: '企业送达公告添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyDeliveryAnnouncementManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyDeliveryAnnouncementManageAdd'
                    }
                }
            },

            {
                path: '/admin/dataCompanyDeliveryAnnouncementManageUpdate',
                component: () => import('./DataCompanyDeliveryAnnouncementManageUpdatePage.vue'),
                props: route => ({ dataCompanyDeliveryAnnouncementId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyDeliveryAnnouncementManageUpdate',
                    name: '企业送达公告修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyDeliveryAnnouncementManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyDeliveryAnnouncementManageUpdate'
                    }
                }
            },
        ]
    },
]
export default dataCompanyDeliveryAnnouncementAdminRoutes